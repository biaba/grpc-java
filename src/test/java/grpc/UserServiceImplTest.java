package grpc;

import com.proto.user.*;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import services.UserRegService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRegService regService;

    private UserRequest userRequest;
    private UserResponse userResponse;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerSuccess() throws Exception {
        userRequest = createUserRequest(5, "marko", "pssw");
        userResponse = createUserResponse(true);
        when(regService.registerUser(any(User.class))).thenReturn(true);

        StreamRecorder<UserResponse> responseObserver = StreamRecorder.create();
        service.register(userRequest, responseObserver);
        verify(regService, times(1)).registerUser(any(User.class));
        List<UserResponse> results = responseObserver.getValues();
        UserResponse response = results.get(0);
        assertEquals(userResponse, response);
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

    private UserResponse createUserResponse(boolean registered) {
        return UserResponse.newBuilder().setRegistered(registered).build();
    }
}