/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homecommerce.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@SuppressWarnings("deprecation")
public class AwsS3Upload {
	public static final String SUFFIX = "/";

	public String putObject(String fileNames, InputStream itemFile, long size) {
		String FileUrl = "";
		try {

			// credentials object identifying user for authentication
			// user must have AWSConnector and AmazonS3FullAccess for
			// this example to work
			AWSCredentials credentials = new BasicAWSCredentials("AKIAQVM7CSLGN56QMALU",
					"z8M300lFBaJ6SjDqd5cnWawY6dMeOAqL++mB6jD1");
			// AWSCredentials credentials = new
			// BasicAWSCredentials("AKIAIGNPVGAR6UAP636Q","VJxJKw9BbpYhRgD+PMoGBKXBIpzHr9HfSvLe4E6H");

			// create a client connection based on credentials
			
			AmazonS3 s3client = new AmazonS3Client(credentials);

			// create bucket - name must be unique for all S3 users
			String bucketName = "elearningprojectfilesnew";

			boolean flag = false;
			// list buckets
			for (Bucket bucket : s3client.listBuckets()) {
				System.out.println(" - " + bucket.getName());
				if (bucketName.equalsIgnoreCase(bucket.getName())) {
					flag = true;
				}
			}
			if (!flag) {
				s3client.createBucket(bucketName);
			}
			// create folder into bucket
			String folderName = "files";
			createFolder(bucketName, folderName, s3client);

			// upload file to folder and set it to public
			String fileName = folderName + SUFFIX + fileNames;

			ObjectMetadata om = new ObjectMetadata();
			om.setContentLength(size);
			String ext = "png";
			String keyName = fileName + '.' + ext;

			s3client.putObject(new PutObjectRequest(bucketName, keyName, itemFile, om));
			s3client.setObjectAcl(bucketName, keyName, CannedAccessControlList.PublicRead);

			URL url = s3client.getUrl(bucketName, fileName + ".png");
			System.out.println(url.toExternalForm());
			FileUrl = url.toExternalForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FileUrl;

	}

	
	public String putObjectWithExtension(String fileNames, InputStream itemFile, long size) {
		String FileUrl = "";
		try {

			// credentials object identifying user for authentication
			// user must have AWSConnector and AmazonS3FullAccess for
			// this example to work
			AWSCredentials credentials = new BasicAWSCredentials("AKIAQVM7CSLGDK2GMAEM",
					"rwHee8nhKz1jjFX7oPBCj9nDwsZ/3W4DYq5jcd9a");

			// AWSCredentials credentials = new
			// BasicAWSCredentials("AKIAIGNPVGAR6UAP636Q","VJxJKw9BbpYhRgD+PMoGBKXBIpzHr9HfSvLe4E6H");
			// create a client connection based on credentials
			AmazonS3 s3client = new AmazonS3Client(credentials);

			// create bucket - name must be unique for all S3 users
			String bucketName = "omkarecommercebucket2";

			boolean flag = false;
			// list buckets
			for (Bucket bucket : s3client.listBuckets()) {
				System.out.println(" - " + bucket.getName());
				if (bucketName.equalsIgnoreCase(bucket.getName())) {
					flag = true;
				}
			}
			if (!flag) {
				s3client.createBucket(bucketName);
			}
			// create folder into bucket
			String folderName = "files";
			createFolder(bucketName, folderName, s3client);

			// upload file to folder and set it to public
			String fileName = folderName + SUFFIX + fileNames;

			ObjectMetadata om = new ObjectMetadata();
			om.setContentLength(size);
//			String ext = extension;
//			String keyName = fileName + '.' + ext;

			String keyName = fileName;
			
			s3client.putObject(new PutObjectRequest(bucketName, keyName, itemFile, om));
			s3client.setObjectAcl(bucketName, keyName, CannedAccessControlList.PublicRead);

			URL url = s3client.getUrl(bucketName, keyName);
			System.out.println(url.toExternalForm());
			FileUrl = url.toExternalForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FileUrl;

	}

	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + SUFFIX, emptyContent,
				metadata);
		// send request to S3 to create folder
		client.putObject(putObjectRequest);
	}

	public static void main(String[] args) throws FileNotFoundException {
		File initialFile = new File("/Users/apple/Downloads/IPOS Report.xlsx");
		System.out.println(initialFile);
		// InputStream targetStream = new FileInputStream(initialFile);
		/*
		 * InputStream targetStream =
		 * CommonUtils.decodeBase64ToInputStream(CommonUtils.encodeFileToBase64(
		 * initialFile)); AwsS3Upload a1 = new AwsS3Upload(); // String
		 * fileNames,InputStream itemFile,long size, String extension
		 * a1.putObjectWithExtension("test", targetStream, initialFile.length(),
		 * FilenameUtils.getExtension(initialFile.getName()));
		 */
	}

}
