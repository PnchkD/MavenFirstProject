package by.iba.s3.impl;


import by.iba.exception.ServiceException;
import by.iba.s3.S3Service;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Optional;


@Service
@AllArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 amazonS3;

    @Override
    public PutObjectResult upload(String path,
                                  String fileName,
                                  Optional<Map<String, String>> metadata,
                                  InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();

        metadata.ifPresent(stringStringMap -> {
            if (!stringStringMap.isEmpty())
                stringStringMap.forEach(objectMetadata::addUserMetadata);
        });

        try {
            return amazonS3.putObject(path, fileName, inputStream, objectMetadata);
        } catch (AmazonServiceException ex) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "exception.file_not_uploaded", ex);

        }
    }

    @Override
    public ByteArrayOutputStream downloadFile(String bucketName, String keyName) {
        try {

            S3Object s3object = amazonS3.getObject(new GetObjectRequest(bucketName, keyName));
            InputStream is = s3object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            return outputStream;
        } catch (IOException ex) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "exception.file_not_found", ex);

        } catch (AmazonClientException ex) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "exception.file_download_failed", ex);

        }

    }


    @Override
    public String getPresignedImageUrl(String bucketName, String key, Date expiration) {
        URL url = amazonS3.generatePresignedUrl(bucketName, key, expiration);

        return url.toString();
    }
}

