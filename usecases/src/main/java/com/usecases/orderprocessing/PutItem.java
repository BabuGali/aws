package com.usecases.orderprocessing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;

public class PutItem {

	public static void main(String[] args) {
		System.out.println("====creating dynamoDB object");
		
		/*AWSCredentials awsCredentials = null;
		awsCredentials = new ProfileCredentialsProvider("awsprogrammer").getCredentials();

		
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion("us-east-2").build();
		       */ 
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIAIR5JQIPPN5SXNDJA", "2lV2tqy3hglgqjmdsdrhxuleqqaKrv7E/A6MWUr3"))).withRegion("us-east-2").build();
		
		
		DynamoDB dynamoDB = new DynamoDB(client);

		 Table table = dynamoDB.getTable("Users");
	        try {

	            Item item = new Item().withPrimaryKey("id", "14").withString("Title", "Book 120 Title")
	                .withString("ISBN", "120-1111111111")
	                .withStringSet("Authors", new HashSet<String>(Arrays.asList("Author12", "Author22")))
	                .withNumber("Price", 20).withString("Dimensions", "8.5x11.0x.75").withNumber("PageCount", 500)
	                .withBoolean("InPublication", false).withString("ProductCategory", "Book");
	            table.putItem(item);

	            item = new Item().withPrimaryKey("id", "1213").withString("Title", "Book 121 Title")
	                .withString("ISBN", "121-1111111111")
	                .withStringSet("Authors", new HashSet<String>(Arrays.asList("Author21", "Author 22")))
	                .withNumber("Price", 20).withString("Dimensions", "8.5x11.0x.75").withNumber("PageCount", 500)
	                .withBoolean("InPublication", true).withString("ProductCategory", "Book");
	            table.putItem(item);

	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	            System.err.println("Create items failed.");
	            System.err.println(e.getMessage());

	        }
	}

}
