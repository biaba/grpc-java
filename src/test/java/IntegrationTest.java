import com.proto.user.User;
import com.proto.user.UserRequest;
import com.proto.user.UserResponse;
import grpc.UserServiceImpl;
import io.grpc.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    private static final String host = "localhost";
    private static final int port = 9090;
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
        UserRequest request = createUserRequest(4l,"ivo", "pssw");
        // DOESN'T WORK
        assertThrows(StatusRuntimeException.class, () -> ClientApp.callToServer(request));
       // WORKS StatusRuntimeException thrown = assertThrows(StatusRuntimeException.class, () -> blockingStub.register(request));
       // WORKS assertEquals("ALREADY_EXISTS", thrown.getStatus().getCode().toString());

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
