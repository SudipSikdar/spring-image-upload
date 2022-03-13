package com.self.learning.buckets;

public enum BucketName {

    PROFILE_IMAGE("image-upload");

    private final String bucketName;


    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
