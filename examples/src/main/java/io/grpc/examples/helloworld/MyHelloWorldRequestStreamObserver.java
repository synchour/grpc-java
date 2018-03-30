package io.grpc.examples.helloworld;

import io.grpc.stub.StreamObserver;

public class MyHelloWorldRequestStreamObserver implements StreamObserver<HelloRequest> {

	StreamObserver<HelloReply> replyObserver;
	String name;
	
	public MyHelloWorldRequestStreamObserver(StreamObserver<HelloReply> replyObserver) {
		this.replyObserver = replyObserver;
	}
	
	@Override
	public void onNext(HelloRequest value) {
		if (name == value.getName()) {
			HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + name).build();
			replyObserver.onNext(reply);
		} else {
			name = value.getName();
			HelloReply reply = HelloReply.newBuilder().setMessage("Hello renamed to " + name).build();
			replyObserver.onNext(reply);
		}
	}

	@Override
	public void onError(Throwable t) {
		
	}

	@Override
	public void onCompleted() {
		this.replyObserver.onCompleted();
	}

}
