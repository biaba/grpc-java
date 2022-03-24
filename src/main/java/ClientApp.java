import com.proto.user.User;
import com.proto.user.UserRequest;
import com.proto.user.UserResponse;
import com.proto.user.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.okhttp.OkHttpChannelBuilder;

public class ClientApp {
    private static final String host = "localhost";
    private static final int port = 9090;
    private static ManagedChannel channel;
    private static UserServiceGrpc.UserServiceBlockingStub blockingStub;
    private static UserResponse response;

    public static void main(String[] args) {
        User user = User.newBuilder().setUserId(1).setUsername("iivo").setPassword("unsafe").build();
        UserRequest request = UserRequest.newBuilder().setUser(user).build();

        UserResponse response = callToServer(request);
        System.out.println("User created and will be sent over: "+request.toString());

        System.out.println(response==null? " User not registered" : "User registered");
    }

    public static UserResponse callToServer(UserRequest request) {
        channel = OkHttpChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = UserServiceGrpc.newBlockingStub(channel);

        System.out.println("User created and will be sent over: "+request.toString());
        try {
            response = blockingStub.register(request);
        } catch (StatusRuntimeException e) {
            System.out.println("Exception: "+ e.getLocalizedMessage());
            return response;
        }
        return response;
    }
}