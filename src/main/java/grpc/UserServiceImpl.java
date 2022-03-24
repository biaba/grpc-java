package grpc;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import com.proto.user.UserRequest;
import com.proto.user.UserResponse;
import com.proto.user.UserServiceGrpc;
import com.proto.user.ErrorResponse;
import services.UserRegService;
import services.impl.UserRegServiceIml;

import java.sql.SQLException;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    UserRegService userRegService = new UserRegServiceIml();

    @Override
    public void register(UserRequest request, StreamObserver<UserResponse> responseObserver) throws SQLException {
        System.out.println("New User for registration: " + request.toString());
        Metadata metadata = userExists(request);
        if (metadata.keys().size()>0) {
            responseObserver.onError(Status.ALREADY_EXISTS.withDescription("The id or username exists")
                    .asRuntimeException(metadata));
        } else {
            userRegService.registerUser(request.getUser());
            UserResponse response = UserResponse.newBuilder().setRegistered(true).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    public Metadata userExists(UserRequest request) throws SQLException {
        Metadata metadata = new Metadata();

        if (userRegService.userExistsInDb(request.getUser())) {

            Metadata.Key<ErrorResponse> errorResponseKey = ProtoUtils.keyForProto(ErrorResponse.getDefaultInstance());
            ErrorResponse errorResponse = ErrorResponse.newBuilder()
                    .setUserId(request.getUser().getUserId())
                    .setExpectedUserId(userRegService.getNextId())
                    .setUsername(request.getUser().getUsername())
                    .build();
            metadata.put(errorResponseKey, errorResponse);
        }
        return metadata;
    }
}
