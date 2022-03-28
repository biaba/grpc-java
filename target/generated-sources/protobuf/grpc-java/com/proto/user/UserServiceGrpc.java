package com.proto.user;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: user_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final String SERVICE_NAME = "UserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.proto.user.UserRequest,
      com.proto.user.UserResponse> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "register",
      requestType = com.proto.user.UserRequest.class,
      responseType = com.proto.user.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.proto.user.UserRequest,
      com.proto.user.UserResponse> getRegisterMethod() {
    io.grpc.MethodDescriptor<com.proto.user.UserRequest, com.proto.user.UserResponse> getRegisterMethod;
    if ((getRegisterMethod = UserServiceGrpc.getRegisterMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getRegisterMethod = UserServiceGrpc.getRegisterMethod) == null) {
          UserServiceGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<com.proto.user.UserRequest, com.proto.user.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.user.UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.user.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("register"))
              .build();
        }
      }
    }
    return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.proto.user.UserRequest,
      com.proto.user.UserResponse> getServerStrGetRegisteredUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "serverStrGetRegisteredUsers",
      requestType = com.proto.user.UserRequest.class,
      responseType = com.proto.user.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.proto.user.UserRequest,
      com.proto.user.UserResponse> getServerStrGetRegisteredUsersMethod() {
    io.grpc.MethodDescriptor<com.proto.user.UserRequest, com.proto.user.UserResponse> getServerStrGetRegisteredUsersMethod;
    if ((getServerStrGetRegisteredUsersMethod = UserServiceGrpc.getServerStrGetRegisteredUsersMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getServerStrGetRegisteredUsersMethod = UserServiceGrpc.getServerStrGetRegisteredUsersMethod) == null) {
          UserServiceGrpc.getServerStrGetRegisteredUsersMethod = getServerStrGetRegisteredUsersMethod =
              io.grpc.MethodDescriptor.<com.proto.user.UserRequest, com.proto.user.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "serverStrGetRegisteredUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.user.UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.user.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("serverStrGetRegisteredUsers"))
              .build();
        }
      }
    }
    return getServerStrGetRegisteredUsersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.proto.user.UserRequest,
      com.proto.user.UserResponse> getClientStrRegisterUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "clientStrRegisterUsers",
      requestType = com.proto.user.UserRequest.class,
      responseType = com.proto.user.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.proto.user.UserRequest,
      com.proto.user.UserResponse> getClientStrRegisterUsersMethod() {
    io.grpc.MethodDescriptor<com.proto.user.UserRequest, com.proto.user.UserResponse> getClientStrRegisterUsersMethod;
    if ((getClientStrRegisterUsersMethod = UserServiceGrpc.getClientStrRegisterUsersMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getClientStrRegisterUsersMethod = UserServiceGrpc.getClientStrRegisterUsersMethod) == null) {
          UserServiceGrpc.getClientStrRegisterUsersMethod = getClientStrRegisterUsersMethod =
              io.grpc.MethodDescriptor.<com.proto.user.UserRequest, com.proto.user.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "clientStrRegisterUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.user.UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.user.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("clientStrRegisterUsers"))
              .build();
        }
      }
    }
    return getClientStrRegisterUsersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.proto.user.UserRequest,
      com.proto.user.UserResponse> getBiStrRegisterAndGetRegisteredMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "biStrRegisterAndGetRegistered",
      requestType = com.proto.user.UserRequest.class,
      responseType = com.proto.user.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.proto.user.UserRequest,
      com.proto.user.UserResponse> getBiStrRegisterAndGetRegisteredMethod() {
    io.grpc.MethodDescriptor<com.proto.user.UserRequest, com.proto.user.UserResponse> getBiStrRegisterAndGetRegisteredMethod;
    if ((getBiStrRegisterAndGetRegisteredMethod = UserServiceGrpc.getBiStrRegisterAndGetRegisteredMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getBiStrRegisterAndGetRegisteredMethod = UserServiceGrpc.getBiStrRegisterAndGetRegisteredMethod) == null) {
          UserServiceGrpc.getBiStrRegisterAndGetRegisteredMethod = getBiStrRegisterAndGetRegisteredMethod =
              io.grpc.MethodDescriptor.<com.proto.user.UserRequest, com.proto.user.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "biStrRegisterAndGetRegistered"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.user.UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.user.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("biStrRegisterAndGetRegistered"))
              .build();
        }
      }
    }
    return getBiStrRegisterAndGetRegisteredMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceStub>() {
        @java.lang.Override
        public UserServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceStub(channel, callOptions);
        }
      };
    return UserServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub>() {
        @java.lang.Override
        public UserServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceBlockingStub(channel, callOptions);
        }
      };
    return UserServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub>() {
        @java.lang.Override
        public UserServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceFutureStub(channel, callOptions);
        }
      };
    return UserServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class UserServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void register(com.proto.user.UserRequest request,
        io.grpc.stub.StreamObserver<com.proto.user.UserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    public void serverStrGetRegisteredUsers(com.proto.user.UserRequest request,
        io.grpc.stub.StreamObserver<com.proto.user.UserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getServerStrGetRegisteredUsersMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.user.UserRequest> clientStrRegisterUsers(
        io.grpc.stub.StreamObserver<com.proto.user.UserResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getClientStrRegisterUsersMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.user.UserRequest> biStrRegisterAndGetRegistered(
        io.grpc.stub.StreamObserver<com.proto.user.UserResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getBiStrRegisterAndGetRegisteredMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.proto.user.UserRequest,
                com.proto.user.UserResponse>(
                  this, METHODID_REGISTER)))
          .addMethod(
            getServerStrGetRegisteredUsersMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                com.proto.user.UserRequest,
                com.proto.user.UserResponse>(
                  this, METHODID_SERVER_STR_GET_REGISTERED_USERS)))
          .addMethod(
            getClientStrRegisterUsersMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                com.proto.user.UserRequest,
                com.proto.user.UserResponse>(
                  this, METHODID_CLIENT_STR_REGISTER_USERS)))
          .addMethod(
            getBiStrRegisterAndGetRegisteredMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                com.proto.user.UserRequest,
                com.proto.user.UserResponse>(
                  this, METHODID_BI_STR_REGISTER_AND_GET_REGISTERED)))
          .build();
    }
  }

  /**
   */
  public static final class UserServiceStub extends io.grpc.stub.AbstractAsyncStub<UserServiceStub> {
    private UserServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     */
    public void register(com.proto.user.UserRequest request,
        io.grpc.stub.StreamObserver<com.proto.user.UserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void serverStrGetRegisteredUsers(com.proto.user.UserRequest request,
        io.grpc.stub.StreamObserver<com.proto.user.UserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getServerStrGetRegisteredUsersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.user.UserRequest> clientStrRegisterUsers(
        io.grpc.stub.StreamObserver<com.proto.user.UserResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getClientStrRegisterUsersMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.user.UserRequest> biStrRegisterAndGetRegistered(
        io.grpc.stub.StreamObserver<com.proto.user.UserResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getBiStrRegisterAndGetRegisteredMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class UserServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<UserServiceBlockingStub> {
    public UserServiceBlockingStub(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.proto.user.UserResponse register(com.proto.user.UserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.proto.user.UserResponse> serverStrGetRegisteredUsers(
        com.proto.user.UserRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getServerStrGetRegisteredUsersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserServiceFutureStub extends io.grpc.stub.AbstractFutureStub<UserServiceFutureStub> {
    private UserServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.proto.user.UserResponse> register(
        com.proto.user.UserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_SERVER_STR_GET_REGISTERED_USERS = 1;
  private static final int METHODID_CLIENT_STR_REGISTER_USERS = 2;
  private static final int METHODID_BI_STR_REGISTER_AND_GET_REGISTERED = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((com.proto.user.UserRequest) request,
              (io.grpc.stub.StreamObserver<com.proto.user.UserResponse>) responseObserver);
          break;
        case METHODID_SERVER_STR_GET_REGISTERED_USERS:
          serviceImpl.serverStrGetRegisteredUsers((com.proto.user.UserRequest) request,
              (io.grpc.stub.StreamObserver<com.proto.user.UserResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CLIENT_STR_REGISTER_USERS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.clientStrRegisterUsers(
              (io.grpc.stub.StreamObserver<com.proto.user.UserResponse>) responseObserver);
        case METHODID_BI_STR_REGISTER_AND_GET_REGISTERED:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.biStrRegisterAndGetRegistered(
              (io.grpc.stub.StreamObserver<com.proto.user.UserResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.proto.user.UserServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserService");
    }
  }

  private static final class UserServiceFileDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier {
    UserServiceFileDescriptorSupplier() {}
  }

  private static final class UserServiceMethodDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceFileDescriptorSupplier())
              .addMethod(getRegisterMethod())
              .addMethod(getServerStrGetRegisteredUsersMethod())
              .addMethod(getClientStrRegisterUsersMethod())
              .addMethod(getBiStrRegisterAndGetRegisteredMethod())
              .build();
        }
      }
    }
    return result;
  }
}
