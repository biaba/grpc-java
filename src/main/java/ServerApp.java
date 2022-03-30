import io.grpc.*;
import grpc.UserServiceImpl;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.ClientAuth;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;

import javax.net.ssl.SSLException;
import java.io.File;
import java.io.IOException;

public class ServerApp {

    private static final int port = 9090;
    private static Server server;
    // ssl or simple connection
    private static boolean sslConnection = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        if(args.length==1) sslConnection = args[0].equals("true")? true : false;
        if(!sslConnection) {
            simpleServerStart();
        } else {
            mtlsServerStart();
        }
    }

    public static void simpleServerStart() throws IOException, InterruptedException {
        server = ServerBuilder
                .forPort(port)
                .addService(new UserServiceImpl()).build();

        server.start();
        System.out.println(" Server started");
        server.awaitTermination();
    }

    public static void mtlsServerStart() throws IOException, InterruptedException {
        SslContext sslContext = ServerApp.loadTLSCredentials();

        server = NettyServerBuilder.forPort(9090).sslContext(sslContext)
                .addService(new UserServiceImpl()).build();
        server.start();

        System.out.println("mTLS Server started");
        server.awaitTermination();

    }

    public static void closeServer() {
        server.shutdown();
    }

    public static SslContext loadTLSCredentials() throws SSLException {
        File serverCertFile = new File("cert/server-cert.pem");
        File serverKeyFile = new File("cert/server-key.pem");
        File clientCACertFile = new File("cert/ca-cert.pem");

        SslContextBuilder ctxBuilder = SslContextBuilder.forServer(serverCertFile, serverKeyFile)
                .clientAuth(ClientAuth.REQUIRE)
                .trustManager(clientCACertFile);

        return GrpcSslContexts.configure(ctxBuilder).build();
    }
}
