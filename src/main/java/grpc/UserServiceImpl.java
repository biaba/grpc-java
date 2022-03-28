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
    public void register(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        System.out.println("New User for registration: " + request.toString());
        // while in memory db - registered will always be true (db entries are not persistent)
        boolean registered = false;
        try {
            registered = userRegService.registerUser(request.getUser());
        } catch (SQLException ex) {
            System.out.println("SQL error " + ex.getLocalizedMessage());
        }
        UserResponse response = UserResponse.newBuilder().setRegistered(registered ? true : false).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void serverStrGetRegisteredUsers(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        for (int i = 1; i <= 5; i++) {
            UserResponse response = UserResponse.newBuilder()
                    .setRegistered(true)
                    .build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<UserRequest> clientStrRegisterUsers(StreamObserver<UserResponse> response) {
        return new StreamObserver<>() {
            int total;
            int counter;

            @Override
            public void onNext(UserRequest request) {
                boolean registered = false;
                try {
                    registered = userRegService.registerUser(request.getUser());
                } catch (SQLException ex) {
                    System.out.println("SQL error " + ex.getLocalizedMessage());
                }
                total++;
                if (registered) counter++;
                System.out.println(total + " total and counter: " + counter);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error + " + throwable.getLocalizedMessage());
            }

            @Override
            public void onCompleted() {
                UserResponse userResponse = UserResponse.newBuilder().setRegistered(total == counter ? true : false).build();
                response.onNext(userResponse);
                response.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<UserRequest> biStrRegisterAndGetRegistered(StreamObserver<UserResponse> response) {
        return new StreamObserver<>() {
            int total;
            int counter;

            @Override
            public void onNext(UserRequest request) {
                boolean registered = false;
                try {
                    registered = userRegService.registerUser(request.getUser());
                } catch (SQLException ex) {
                    System.out.println("SQL error " + ex.getLocalizedMessage());
                }
                total++;
                if (registered) counter++;
                UserResponse registeredUser = UserResponse.newBuilder().setRegistered(registered ? true : false).build();
                response.onNext(registeredUser);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error: " + throwable.getLocalizedMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("From total incoming requests: " + total + " succeeded: " + counter);
                response.onCompleted();
            }
        };
    }
}
