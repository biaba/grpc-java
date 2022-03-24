package grpc;

import com.proto.user.*;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.testing.StreamRecorder;
import io.grpc.protobuf.ProtoUtils;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import services.UserRegService;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        when(regService.userExistsInDb(any(User.class))).thenReturn(false);

        StreamRecorder<UserResponse> responseObserver = StreamRecorder.create();
        service.register(userRequest, responseObserver);
        verify(regService, times(1)).registerUser(any(User.class));
        assertNull(responseObserver.getError());
        List<UserResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        UserResponse response = results.get(0);
        assertEquals(userResponse, response);
    }

    @Test
    void registerUserExists() throws Exception {
        userRequest = createUserRequest(4, "ivo", "pssw");
        userResponse = createUserResponse(false);
        when(regService.userExistsInDb(any(User.class))).thenReturn(true);
        when(regService.getNextId()).thenReturn(5l);


        StreamRecorder<UserResponse> responseObserver = StreamRecorder.create();

        service.register(userRequest, responseObserver);
        // in unit test asserts to zero. In integration test -> response.getRegistered()->true Why?
        assertEquals(0,responseObserver.getValues().size());
        assertEquals(responseObserver.getError().getMessage(), "ALREADY_EXISTS: The id or username exists");

        verify(regService, times(0)).registerUser(any(User.class));
        Metadata metadata = Status.trailersFromThrowable(responseObserver.getError());
        ErrorResponse errorResponse = metadata.get(ProtoUtils.keyForProto(ErrorResponse.getDefaultInstance()));
        assertEquals("ivo", errorResponse.getUsername());
        assertEquals(4l, errorResponse.getUserId());
        assertEquals(5l, errorResponse.getExpectedUserId());
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
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