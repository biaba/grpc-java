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

    public static void main(String[] args) {
        channel = OkHttpChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = UserServiceGrpc.newBlockingStub(channel);

        UserRequest request = UserRequest.newBuilder().setUserId(1).setUsername("mia").setPassword("unsafe").build();
        UserResponse response;
        System.out.println("User created and will be sent over: "+request.toString());
        try {
            response = blockingStub.register(request);
        } catch (StatusRuntimeException e) {
            System.out.println("1 Exception: "+ e.getLocalizedMessage());
            return;
        }
        System.out.println("User registered: "+ response.getRegistered());
    }
}
