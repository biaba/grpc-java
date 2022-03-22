package services;

import com.proto.user.ErrorResponse;
import com.proto.user.UserRequest;
import com.proto.user.UserResponse;
import com.proto.user.UserServiceGrpc;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    UserServiceGrpc.UserServiceBlockingStub blockingStub;

    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    @BeforeEach
    public void setup() throws Exception{

        String serverName = InProcessServerBuilder.generateName();

        grpcCleanup.register(InProcessServerBuilder.forName(serverName)
                .directExecutor()
                .addService(new UserServiceImpl())
                .build()
                .start());

        blockingStub = UserServiceGrpc.newBlockingStub(grpcCleanup.register(InProcessChannelBuilder.forName(serverName)
                .directExecutor()
                .build()));
    }


    @Test
    public void userExistsInDB_idOrUserName() throws Exception {

        UserRequest request = UserRequest.newBuilder()
                .setUsername("ivo")
                .setUserId(4l)
                .setPassword("pssw")
                .build();

        StatusRuntimeException thrown = Assertions.assertThrows(StatusRuntimeException.class, () -> blockingStub.register(request));

        assertEquals("ALREADY_EXISTS", thrown.getStatus().getCode().toString());
        assertEquals("ALREADY_EXISTS: The id or username exists", thrown.getMessage());
        Metadata metadata = Status.trailersFromThrowable(thrown);
        ErrorResponse errorResponse = metadata.get(ProtoUtils.keyForProto(ErrorResponse.getDefaultInstance()));
        assertEquals("ivo",errorResponse.getUsername());
        assertEquals(4l, errorResponse.getUserId());
        assertEquals(5l, errorResponse.getExpectedUserId());
    }

    @Test
    public void userRegister_success() throws Exception {

        UserRequest request = UserRequest.newBuilder()
                .setUsername("marko")
                .setUserId(5l)
                .setPassword("pssw")
                .build();

        UserResponse reply =
                blockingStub.register(request);

        assertEquals(true, reply.getRegistered());
    }

}