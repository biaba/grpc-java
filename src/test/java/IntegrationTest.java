import com.proto.user.User;
import com.proto.user.UserRequest;
import com.proto.user.UserResponse;
import grpc.UserServiceImpl;
import io.grpc.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    private static final int port = 9090;
    private static Server server = ServerBuilder
            .forPort(port)
            .addService(new UserServiceImpl()).build();

    @BeforeAll
    public static void startingServer() throws IOException {
        server.start();
    }

    @Test
    void registerUniDirection() throws Exception {
        UserRequest request = createUserRequest(11l, "oto", "pssw");
        UserResponse response = ClientApp.callToServer(request);
        assertEquals(true, response.getRegistered());
    }

    @Test
    void registerServerStreaming() throws Exception {
        UserRequest request = createUserRequest(11l, "oto", "pssw");
        ClientApp.serverSideStr(request);
    }

    @Test
    void registerClientStreaming() throws Exception {
        List<UserRequest> requestList = createUserRequestList();
        ClientApp.clientSideStr(requestList);
    }

    @Test
    void registerBiDirectional() throws Exception {
        List<UserRequest> requestList = createUserRequestList();
        ClientApp.bidirectionalStr(requestList);
    }

    @AfterAll
    public static void closingServer() {
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

    private static List<UserRequest> createUserRequestList() {
        List<UserRequest> users = new ArrayList<>();
        users.add(UserRequest.newBuilder().setUser(User.newBuilder().setUserId(1l).setUsername("anna").setPassword("pssw").build()).build());
        users.add(UserRequest.newBuilder().setUser(User.newBuilder().setUserId(2l).setUsername("grieta").setPassword("pssw2").build()).build());
        users.add(UserRequest.newBuilder().setUser(User.newBuilder().setUserId(3l).setUsername("oto").setPassword("pssw3").build()).build());
        return users;
    }
}
