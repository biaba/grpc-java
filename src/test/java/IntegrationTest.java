import com.proto.user.User;
import com.proto.user.UserRequest;
import com.proto.user.UserResponse;
import com.proto.user.UserServiceGrpc;
import grpc.UserServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.okhttp.OkHttpChannelBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    private static final String host = "localhost";
    private static final int port = 9090;
    private static ManagedChannel channel = OkHttpChannelBuilder
            .forAddress(host, port)
            .usePlaintext()
            .build();
    private static UserServiceGrpc.UserServiceBlockingStub blockingStub =
            UserServiceGrpc.newBlockingStub(channel);
    private static Server server = ServerBuilder
            .forPort(port)
            .addService(new UserServiceImpl()).build();


    @Test
    void registerSuccess() throws Exception {
        server.start();
        UserRequest request = createUserRequest(11l, "oto", "pssw");
        UserResponse response = ClientApp.callToServer(request);
        assertEquals(true, response.getRegistered());
    }

    @Test
    void registerNonSuccess() {
        UserResponse response = ClientApp.callToServer(createUserRequest(4l,"ivo", "pssw"));

        System.out.println(" 2nd test "+ response);

        assertEquals(null, response);
        server.shutdown();
    }


    private UserRequest createUserRequest(long userId, String username, String password) {
        User user = User.newBuilder()
                .setUsername(username)
                .setUserId(userId)
                .setPassword(password)
                .build();

        UserRequest request = UserRequest.newBuilder()
                .setUser(user)
                .build();
        return request;
    }
}
