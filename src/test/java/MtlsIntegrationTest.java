import com.proto.user.User;
import com.proto.user.UserRequest;
import com.proto.user.UserResponse;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MtlsIntegrationTest {

    @BeforeAll
    public static void startingServer() throws IOException, InterruptedException {
        ServerApp.mtlsServerStart();
        new ClientApp(ClientApp.loadTLSCredentials());
    }

    @Test
    void mtlsUniDirection() throws Exception {
        UserRequest request = createUserRequest(11l, "oto", "pssw");
        UserResponse response = ClientApp.mtlsCallToServer(request);
        assertEquals(true, response.getRegistered());
    }

    @AfterAll
    public static void closingServer() {
        ServerApp.closeServer();
    }

    private UserRequest createUserRequest(long userId, String username, String password) {
        User user = User.newBuilder().setUsername(username).setUserId(userId).setPassword(password).build();

        UserRequest request = UserRequest.newBuilder().setUser(user).build();
        return request;
    }
}
