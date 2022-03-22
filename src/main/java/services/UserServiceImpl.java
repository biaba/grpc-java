package services;

import io.grpc.stub.StreamObserver;
import com.proto.user.UserRequest;
import com.proto.user.UserResponse;
import com.proto.user.UserServiceGrpc;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void register(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        System.out.println("New User for registration: "+ request.toString());

        UserResponse response = UserResponse.newBuilder()
                .setRegistered(true)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
