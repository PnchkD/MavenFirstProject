package by.iba.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@AllArgsConstructor
public class AmazonConfig {

    private final Environment env;

    @Bean
    public AmazonS3 amazonS3() {


        //amazonbadcreds
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                Objects.requireNonNull(env.getProperty("AWS_ACCESS_KEY")),
                env.getProperty("AWS_SECRET_KEY")
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_WEST_1)
                .build();
    }

}

