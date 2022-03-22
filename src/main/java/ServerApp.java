import io.grpc.Server;
import io.grpc.ServerBuilder;
import grpc.UserServiceImpl;
import java.io.IOException;

public class ServerApp {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(9090)
                .addService(new UserServiceImpl()).build();

        server.start();
        System.out.println(" Server started");
        server.awaitTermination();
    }
}
