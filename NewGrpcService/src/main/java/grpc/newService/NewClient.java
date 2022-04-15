package grpc.newService;

import java.util.concurrent.TimeUnit;

import grpc.newService.newServiceGrpc.newServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class NewClient {

	public static void main(String[] args) throws InterruptedException {
		
		//Build a channel - a channel connects the client to the server 
		int port = 50051;
		String host = "localhost";
		
		ManagedChannel newChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		
		//code above is completely generic
		
		//now build our message
		//Note that contains string is in the same package so we dont need to do an import
		containsString  cString = containsString.newBuilder().setFirstString("test").build();
		
		//Create a stub, pass the channel to the stub
		//the stub is specific to the service 
		//the stub is a local representation of our remote object
		
		newServiceBlockingStub bstub = newServiceGrpc.newBlockingStub(newChannel);
	
		//Now we can actually call the service and get a response back
		//The service has methods (rpcs) which we can call 
	
		containsString response = bstub.getFirstString(cString);
		System.out.println(response.getFirstString());
		
		//lets call the other rpc that we have on the service
		
		newResponse responseMsg = bstub.getFirstInt(cString);
		System.out.println("Now print out our response from the getFirstInt rpc: " + responseMsg.getFirstInt());
		
		//Clean up: shutdown the channel
		newChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
		
	}

}
