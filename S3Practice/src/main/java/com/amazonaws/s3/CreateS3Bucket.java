package com.amazonaws.s3;

import java.util.UUID;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;

public class CreateS3Bucket {

	public static void main(String[] args) {

		AWSCredentials awsCredentials = null;

		awsCredentials = new ProfileCredentialsProvider("awsdevloper").getCredentials();

		AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion("us-east-2").build();

		System.out.println(" No of s3 buckets : " + amazonS3.listBuckets().size());
		
		System.out.println("creating bucket..");
		
		String bucketName="mytestbucket"+UUID.randomUUID();
		System.out.println("bucket name : "+bucketName);
		CreateBucketRequest bucketRequest =new CreateBucketRequest(bucketName, "us-east-2");
		Bucket bucket =amazonS3.createBucket(bucketRequest);
		
		System.out.println("bucket Name : "+bucket.getName());		
		System.out.println("bucket owner : "+bucket.getOwner());
		bucket.setName("shanmuk1aswe3r");
		System.out.println("bucket Name : "+bucket.getName());
		
		System.out.println("deleting bucket..");
		//amazonS3.deleteBucket(bucketName);
		
		
		
		

	}

}
