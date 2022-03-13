package com.self.learning.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {

    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(String path, String fileName,
                     Optional<Map<String, String>> optionalMetaData, InputStream stream) {

        ObjectMetadata metaData = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(metaData::addUserMetadata);
            }
        });

        try {
            s3.putObject(path, fileName, stream, metaData);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to save the file in AWS s3", e);
        }

    }

    public byte[] download(String path, String key) {
        try {
            S3Object object = s3.getObject(path, key);
            return object.getObjectContent().readAllBytes();
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("failed to download");
        }
    }
}
