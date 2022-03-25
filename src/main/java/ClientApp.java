import com.proto.user.*;
import io.grpc.ManagedChannel;
import io.grpc.okhttp.OkHttpChannelBuilder;

public class ClientApp {
    private static final String host = "localhost";
    private static final int port = 9090;
    private static ManagedChannel channel;
    private static UserServiceGrpc.UserServiceBlockingStub blockingStub;
    private static UserResponse response;

    public static void main(String[] args) {
        if (args.length != 3) {
            String[] ar = new String[3];
            args = ar;
            args[0] = "3";
            args[1] = "ivo";
            args[2] = "pssw";
        }
        User user = User.newBuilder().setUserId(Long.valueOf(args[0])).setUsername(args[1]).setPassword(args[2]).build();
        UserRequest request = UserRequest.newBuilder().setUser(user).build();
        System.out.println("User created and will be sent over: " + request);

        UserResponse response = callToServer(request);
        System.out.println("User registered: " + response.getRegistered());
    }

    public static UserResponse callToServer(UserRequest request) {
        channel = OkHttpChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = UserServiceGrpc.newBlockingStub(channel);
        response = blockingStub.register(request);

        return response;
    }
}