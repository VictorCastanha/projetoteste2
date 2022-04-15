package grpc.newService;

import java.io.IOException;

import grpc.newService.newServiceGrpc.newServiceImplBase;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class NewServer {

	private Server server;
	public static void main(String[] args) throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		
		NewServer ourServer = new NewServer();
		ourServer.start();
		
	}
	private void start() throws IOException, InterruptedException {
		System.out.println("Starting gRPC Server");
		int port = 50051;
		
		server = ServerBuilder.forPort(port).addService((BindableService) new NewServerImpl()).build().start();
		System.out.println("Server running on port: " + port );
		
		server.awaitTermination();

	}
	//Extend abstract base class for our own implementation
	//abstract class is a class that I can extend and also implements 
	static class NewServerImpl extends newServiceImplBase{
		
		@Override
		public void getFirstString(containsString request, StreamObserver<containsString> responseObserver) {
			//Find out what was the content of the message sent by the client
			String firstString = request.getFirstString();
			System.out.println("Our first String is: " + firstString);
			
			//Now build up our response
			containsString.Builder responseBuilder = containsString.newBuilder();
			
			responseBuilder.setFirstString("Our First Response String is:  " + firstString);
			
			responseObserver.onNext(responseBuilder.build());
			responseObserver.onCompleted();
			
		}
		
		@Override
		public void getFirstInt(containsString request, StreamObserver<newResponse> responseObserver) {
			//Find out what was the content of the message sent by the client
			String firstString = request.getFirstString();
			System.out.println("Our first String is: " + firstString);
			
			//Now build up our response
			newResponse.Builder responseBuilder = newResponse.newBuilder();
			
			responseBuilder.setFirstInt(25);
			
			responseObserver.onNext(responseBuilder.build());
			responseObserver.onCompleted();
			
		}
		
	
		
	}

}
