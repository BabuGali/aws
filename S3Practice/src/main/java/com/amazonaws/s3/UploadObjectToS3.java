package com.amazonaws.s3;

import java.io.File;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class UploadObjectToS3 {

	public static void main(String[] args) {
		try {
		AWSCredentials awsCredentials=null;
		awsCredentials = new ProfileCredentialsProvider("awslambdauser").getCredentials();

		AmazonS3 amazonS3  = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion("us-east-2").build();
		String bucketName="awslambdauser-bucket";
		String filePath="/home/babu/babu/personal/aws/lambda/babu.jpg";
		String key="hellowlambda.txt";
		PutObjectRequest putObjectRequest= new PutObjectRequest(bucketName,key,new File(filePath));
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType("plain/text");
		objectMetadata.addUserMetadata("key", "value");
		amazonS3.putObject(putObjectRequest);
		}catch(AmazonServiceException e) {
			System.out.println("===============");
			e.printStackTrace();
			
		}catch(SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
			System.out.println("========11=======");
            e.printStackTrace();
        }
	}

}
