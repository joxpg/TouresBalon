package co.com.puj.aes.proveedor.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
       // (basePackages = "co.com.puj.aes.proveedor.repository")
public class DynamoDBConfiguration {
    @Value("${amazon.access.key}")
    private  String awsAccesKey;

    @Value("${amazon.access.secret.key}")
    private  String awsSecretKey;

    @Value("${amazon.region}")
    private  String awsRegion;

    @Value("${amazon.end-point.url}")
    private  String awsDynamoDBEndPoint;

    @Bean
    public DynamoDBMapper dynamoDBMapper (){
        return new DynamoDBMapper(buildAmazonDynamoDB());

    }
    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                awsDynamoDBEndPoint,awsRegion
                        )
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        awsAccesKey,
                                        awsSecretKey
                                )
                        )
                )
                .build();
    }
}
