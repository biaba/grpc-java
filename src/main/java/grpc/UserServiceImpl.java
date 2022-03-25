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
    public void register(UserRequest request, StreamObserver<UserResponse> responseObserver)  {
        System.out.println("New User for registration: " + request.toString());
        // while in memory db - registered will always be true (db entries are not persistent)
        boolean registered = false;
        try {
            registered = userRegService.registerUser(request.getUser());
        } catch(SQLException ex) {
            System.out.println("SQL error "+ ex.getLocalizedMessage());
        }
        UserResponse response = UserResponse.newBuilder().setRegistered(registered? true : false).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
