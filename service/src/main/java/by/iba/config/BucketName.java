package by.iba.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {

    FILE_IMAGE("automotive-selection-s3-bucket");
    private final String bucketName;

}

