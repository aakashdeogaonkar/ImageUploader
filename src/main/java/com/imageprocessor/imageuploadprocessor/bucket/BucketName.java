package com.imageprocessor.imageuploadprocessor.bucket;

public enum BucketName {

	PROFILE_IMAGE("image-upload-springboot-react");
	
	private final String bucketName;
	
	BucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
	public String getBucketName() {
		return bucketName;
	}
}
