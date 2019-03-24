package com.usecases.orderprocessing;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class SendOrderToSQS {

	public static void main(String[] args) {
		AWSCredentials awsCredentials = null;
		awsCredentials = new ProfileCredentialsProvider("awsprogrammer").getCredentials();

		String queueUrl = "https://sqs.us-east-2.amazonaws.com/319781117849/OrderQ";
		final AmazonSQS sqs = AmazonSQSClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion("us-east-2").build();
		SendMessageRequest sendMessageRequest = new SendMessageRequest(queueUrl, "1");
		System.out.println("---sending message to Q");
		sqs.sendMessage(sendMessageRequest);
		System.out.println("---Message Sent");

		/*ReceiveMessageRequest rmr = new ReceiveMessageRequest(queueUrl);
		ReceiveMessageResult result=sqs.receiveMessage(rmr);
		List<Message> msgs=result.getMessages();
		for (Message message : msgs) {
			System.out.println("=="+message.getBody());
		}*/
	}
	

}
