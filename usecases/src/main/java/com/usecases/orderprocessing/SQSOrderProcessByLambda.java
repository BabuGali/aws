package com.usecases.orderprocessing;

import java.util.Arrays;
import java.util.HashSet;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;

public class SQSOrderProcessByLambda implements RequestHandler<SQSEvent, Void> {

	public Void handleRequest(SQSEvent event, Context context) {
		for (SQSMessage msg : event.getRecords()) {

			System.out.println("=====================" + new String(msg.getBody()));

			String id = new String(msg.getBody());
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIAIR5JQIPPN5SXNDJA",
							"2lV2tqy3hglgqjmdsdrhxuleqqaKrv7E/A6MWUr3")))
					.withRegion("us-east-2").build();

			//
			DynamoDB dynamoDB = new DynamoDB(client);

			Table table = dynamoDB.getTable("Users");
			try {

				Item item = new Item().withPrimaryKey("id", id).withString("Title", "Book 120 Title")
						.withString("ISBN", "120-1111111111")
						.withStringSet("Authors", new HashSet<String>(Arrays.asList("Author12", "Author22")))
						.withNumber("Price", 20).withString("Dimensions", "8.5x11.0x.75").withNumber("PageCount", 500)
						.withBoolean("InPublication", false).withString("ProductCategory", "Book");
				table.putItem(item);

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Create items failed.");
				System.err.println(e.getMessage());

			}
			// delete the message
			System.out.println("Deleting a message.\n");
			String queueUrl = "https://sqs.us-east-2.amazonaws.com/319781117849/OrderQ";
			final AmazonSQS sqs = AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(
					new BasicAWSCredentials("AKIAIR5JQIPPN5SXNDJA", "2lV2tqy3hglgqjmdsdrhxuleqqaKrv7E/A6MWUr3")))
					.withRegion("us-east-2").build();

			final String messageReceiptHandle = msg.getReceiptHandle();
			sqs.deleteMessage(new DeleteMessageRequest(queueUrl, messageReceiptHandle));
		}
		return null;
	}

}
