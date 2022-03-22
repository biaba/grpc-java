package services;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import com.proto.user.UserRequest;
import com.proto.user.UserResponse;
import com.proto.user.UserServiceGrpc;
import com.proto.user.ErrorResponse;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void register(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        System.out.println("New User for registration: " + request.toString());
        Metadata metadata = userExists(request);
        if (metadata.keys().size()>0) {

            responseObserver.onError(Status.ALREADY_EXISTS.withDescription("The id or username exists")
                    .asRuntimeException(metadata));
        } else {
            System.out.println("");
            UserResponse response = UserResponse.newBuilder().setRegistered(true).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    private Metadata userExists(UserRequest request) {
        Metadata metadata = new Metadata();
        // db connection. Checking for username or id exists
        // imitating response
        long id = 4;
        long id2 =5;
        String username = "ivo";
        if (request.getUser().getUserId() == id || request.getUser().getUsername().equals(username)) {
            Metadata.Key<ErrorResponse> errorResponseKey = ProtoUtils.keyForProto(ErrorResponse.getDefaultInstance());
            ErrorResponse errorResponse = ErrorResponse.newBuilder()
                    // got back from DB existing id
                    .setUserId(id)
                    // got back next available id
                    .setExpectedUserId(id2)
                    //got back from DB existing username
                    .setUsername(username)
                    // .setExpectedValue("Only Commodity1, Commodity2 are supported")
                    .build();
            metadata.put(errorResponseKey, errorResponse);
        }
        return metadata;
    }
}
