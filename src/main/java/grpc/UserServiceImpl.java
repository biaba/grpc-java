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

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    UserRegService userRegService = new UserRegServiceIml();

    @Override
    public void register(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        System.out.println("New User for registration: " + request.toString()+" "+ request.getUser());
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

    private Metadata userExists(UserRequest request) {
        Metadata metadata = new Metadata();

        if (userRegService.userExistsInDb(request.getUser())) {

            Metadata.Key<ErrorResponse> errorResponseKey = ProtoUtils.keyForProto(ErrorResponse.getDefaultInstance());
            ErrorResponse errorResponse = ErrorResponse.newBuilder()
                    // got back from DB existing id
                    .setUserId(request.getUser().getUserId())
                    // got back next available id
                    .setExpectedUserId(userRegService.getNextId())
                    //got back from DB existing username
                    .setUsername(request.getUser().getUsername())
                    .build();
            metadata.put(errorResponseKey, errorResponse);
        }
        return metadata;
    }
}
