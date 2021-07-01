package com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DynamoDBConfiguration {

    @Value("\${amazon.dynamodb.endpoint:http://localhost:4566}")
    private lateinit var amazonDynamoDBEndpoint: String

    @Value("\${amazon.region:us-east-1}")
    private lateinit var amazonRegion: String

    @Value("\${amazon.aws.accesskey:mel}")
    private lateinit var amazonAWSAccessKey: String

    @Value("\${amazon.aws.secretKey:mel}")
    private lateinit var amazonAWSSecretKey: String

    @Bean
    fun amazonDynamoDB() =
        DynamoDBMapper(
            AmazonDynamoDBClientBuilder.standard()
                .withCredentials(
                    AWSStaticCredentialsProvider(
                        BasicAWSCredentials(
                            amazonAWSAccessKey,
                            amazonAWSSecretKey
                        )
                    )
                )
                .withEndpointConfiguration(EndpointConfiguration(amazonDynamoDBEndpoint, amazonRegion))
                .build()
        )
}