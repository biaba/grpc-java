package grpc;

import io.grpc.stub.StreamObserver;
import com.proto.user.UserRequest;
import com.proto.user.UserResponse;
import com.proto.user.UserServiceGrpc;
import services.UserRegService;
import services.impl.UserRegServiceIml;

import java.sql.SQLException;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    UserRegService userRegService = new UserRegServiceIml();

    @Override
    public void register(UserRequest request, StreamObserver<UserResponse> responseObserver) throws SQLException {
        System.out.println("New User for registration: " + request.toString());
        boolean registered = userRegService.registerUser(request.getUser());
        UserResponse response = UserResponse.newBuilder().setRegistered(registered?true:false).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
