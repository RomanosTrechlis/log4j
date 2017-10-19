package com.romanostrechlis.scribe.log4j;

import com.romanostrechlis.scribe.api.LogScribeGrpc;
import com.romanostrechlis.scribe.api.LogScribeOuterClass;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class LogScribeClient {

  private ManagedChannel channel;
  private LogScribeGrpc.LogScribeStub asyncStub;

  public LogScribeClient(String host, int port) {
    this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
  }

  public LogScribeClient(ManagedChannelBuilder<?> channelBuilder) {
    this.channel = channelBuilder.build();
    //blockingStub = RouteGuideGrpc.newBlockingStub(channel);
    this.asyncStub = LogScribeGrpc.newStub(channel);

  }

  public void send(LogScribeOuterClass.LogRequest req) {
    LogScribeOuterClass.LogResponse res = LogScribeOuterClass.LogResponse.newBuilder().build();

    asyncStub.log(req, new StreamObserver<LogScribeOuterClass.LogResponse>() {
      public void onNext(LogScribeOuterClass.LogResponse logResponse) {
        // System.out.println("onNext");
      }
      public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
      }

      public void onCompleted() {
        // System.out.println("onComplete");
      }
    });
  }
}
