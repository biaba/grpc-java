import com.proto.user.*;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.okhttp.OkHttpChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ClientApp {
    private static final String host = "localhost";
    private static final int port = 9090;
    private static ManagedChannel channel;
    private static UserServiceGrpc.UserServiceBlockingStub blockingStub;
    private static UserServiceGrpc.UserServiceStub nonBlockingStub;
    private static List<UserRequest> userRequestList;
    private static UserRequest userRequest;

    static {
        channel = OkHttpChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = UserServiceGrpc.newBlockingStub(channel);
        nonBlockingStub = UserServiceGrpc.newStub(channel);
        userRequestList = createUserRequestList();
        userRequest = createUserRequest();
    }

    public static void main(String[] args) throws InterruptedException {
        callToServer(userRequest);
        serverSideStr(userRequest);
        clientSideStr(userRequestList);
        bidirectionalStr(userRequestList);
    }

    public static UserResponse callToServer(UserRequest request) {
        System.out.println("Unidirectional - User created and will be sent over: " + request);
        UserResponse response = blockingStub.register(request);
        System.out.println("Unidirectional - User registered: " + response.getRegistered());
        return response;
    }

    public static List<Boolean> serverSideStr(UserRequest request) {
        Iterator<UserResponse> responses;
        List<Boolean> successList = new ArrayList<>();
        try {
            System.out.println("User created and will be sent over: " + request);
            responses = blockingStub.serverStrGetRegisteredUsers(request);
            for (int i = 1; responses.hasNext(); i++) {
                UserResponse r = responses.next();
                System.out.println("Server streaming - Response "+ r.getRegistered());
                successList.add(r.getRegistered());
            }
        } catch (StatusRuntimeException e) {
            System.out.println("Error: "+ e.getLocalizedMessage());
        }

        return successList;
    }

    public static boolean clientSideStr(List<UserRequest> userRequestList) throws InterruptedException {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        final boolean[] allDone = new boolean[1];
        StreamObserver<UserResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(UserResponse allRegistered) {
                allDone[0] = allRegistered.getRegistered();
                System.out.println("Client side streaming - all users registered? "+ allRegistered.getRegistered());
                }

            @Override
            public void onCompleted() {
                System.out.println("Client side streaming completed");
                finishLatch.countDown();
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Error "+ t.getLocalizedMessage());
                finishLatch.countDown();
            }
        };

        StreamObserver<UserRequest> requestObserver = nonBlockingStub.clientStrRegisterUsers(responseObserver);
        try {

            for (UserRequest r : userRequestList) {
                System.out.println("User to be sent over: "+ r.getUser());
                requestObserver.onNext(r);
                if (finishLatch.getCount() == 0) {
                    return allDone[0];
                }
            }
        } catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }
        requestObserver.onCompleted();
        if (!finishLatch.await(1, TimeUnit.MINUTES)) {
            System.out.println("Process didn't finish within 1 minute");
        }

        return allDone[0];
    }

    public static List<Boolean> bidirectionalStr(List<UserRequest> userRequestList) throws InterruptedException{
        System.out.println("Bidirectional streaming");
        final CountDownLatch finishLatch = new CountDownLatch(1);
        List<Boolean> successList = new ArrayList<>();

        StreamObserver<UserResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(UserResponse response) {
                successList.add(response.getRegistered());
                System.out.println("Client getting response: "+ response.getRegistered());
            }

            @Override
            public void onCompleted() {
                System.out.println("Bidirectional streaming completed");
                finishLatch.countDown();
            }

            @Override
            public void onError(Throwable t) {
                finishLatch.countDown();
                System.out.println("Error "+ t.getLocalizedMessage());
            }
        };

        StreamObserver<UserRequest> requestObserver = nonBlockingStub.biStrRegisterAndGetRegistered(responseObserver);
        try {
            for (UserRequest r : userRequestList) {
                System.out.println("User created and will be sent over: "+r.getUser());
                requestObserver.onNext(r);
                Thread.sleep(200);
                if (finishLatch.getCount() == 0) {
                    return successList;
                }
            }
        } catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }
        requestObserver.onCompleted();

        if (!finishLatch.await(1, TimeUnit.MINUTES)) {
            System.out.println("The task can not be completed within 1 minute");
        }
        return successList;
    }

    private static List<UserRequest> createUserRequestList() {
        List<UserRequest> users = new ArrayList<>();
        users.add(UserRequest.newBuilder().setUser(User.newBuilder().setUserId(1l).setUsername("anna").setPassword("pssw").build()).build());
        users.add(UserRequest.newBuilder().setUser(User.newBuilder().setUserId(2l).setUsername("grieta").setPassword("pssw2").build()).build());
        users.add(UserRequest.newBuilder().setUser(User.newBuilder().setUserId(3l).setUsername("oto").setPassword("pssw3").build()).build());
        return users;
    }

    private static UserRequest createUserRequest() {
        User user = User.newBuilder().setUserId(1l).setUsername("maija").setPassword("drm").build();
        UserRequest userRequest = UserRequest.newBuilder().setUser(user).build();
        return userRequest;
    }
}