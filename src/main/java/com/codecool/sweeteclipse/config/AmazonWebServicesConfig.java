package com.codecool.sweeteclipse.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonWebServicesConfig {

    @Value("${amazon.accessKey}")
    private String amazonAccessKey;

    @Value("${amazon.secretKey}")
    private String amazonSecretKey;

    @Value("${amazon.s3.region}")
    private String amazonS3Region;


    @Bean
    public AmazonS3 getS3InterfaceBean() {
        AWSCredentials appCredentials = new BasicAWSCredentials(amazonAccessKey, amazonSecretKey);

        // AWS can get credentials that expire (e.g. key management systems)
        // The CredentialsProvider interface implementation has a No-Op in place of a refresh() in the static case
        AWSStaticCredentialsProvider simpleWayToGetAppCredentials = new AWSStaticCredentialsProvider(appCredentials);

        return AmazonS3ClientBuilder.standard()
                .withRegion(amazonS3Region)
                .withCredentials(simpleWayToGetAppCredentials)
                .build();
    }

}
