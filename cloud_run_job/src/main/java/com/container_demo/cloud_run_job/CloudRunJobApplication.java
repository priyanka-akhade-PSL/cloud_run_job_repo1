package com.container_demo.cloud_run_job;

import com.google.cloud.storage.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class CloudRunJobApplication implements CommandLineRunner {

    String fileName = "samplefile.json";
    String bucketName = "test-bucket-search-conversation";

    public static void main(String[] args) {
        SpringApplication.run(CloudRunJobApplication.class, args);
    }

    private void uploadFileToBucket() throws IOException {
//		logger.info("Uploading file to GCS Bucket...");
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, Files.readAllBytes(Paths.get("samplefile.json")));
        System.out.println(blob);
//		logger.info("File has been uploaded!");
    }

    @Override
    public void run(String... args) throws Exception {
        uploadFileToBucket();
    }
}
