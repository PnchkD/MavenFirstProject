package by.iba.s3;

import com.amazonaws.services.s3.model.PutObjectResult;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public interface S3Service {
    /**
     * method for uploading files in s3 bucket
     *
     * @param path     - full path to folder in s3 (example: String.format("%s/%s/%s", bucketName, folder, innerFolder))
     * @param fileName - name of our file which used as a key, usually consists of unique id and original fileName
     * @param metadata - file metadata, such as content type, etc
     * @return a PutObjectResult object containing the information returned by Amazon S3 for the newly created object.
     **/
    PutObjectResult upload(String path, String fileName, Optional<Map<String, String>> metadata, InputStream inputStream);

    ByteArrayOutputStream downloadFile(String bucketName, String keyName);

    String getPresignedImageUrl(final String bucketName, final String key, final Date expiration);

}
