package com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Configuration
class DynamoDBConfiguration {

    @Value("\${amazon.dynamodb.endpoint}")
    private lateinit var amazonDynamoDBEndpoint: String

    @Value("\${amazon.region}")
    private lateinit var amazonRegion: String

    @Bean("dynamoDbEnhancedClient")
    @Profile("!prod")
    fun dynamoDbEnhancedClientLocal() =
        DynamoDbEnhancedClient.builder()
            .dynamoDbClient(
                DynamoDbClient.builder()
                    .region(Region.of(amazonRegion))
                    .endpointOverride(URI(amazonDynamoDBEndpoint))
                    .build()
            )
            .build()

    @Bean("dynamoDbEnhancedClient")
    @Profile("prod")
    fun dynamoDbEnhancedClientProduction() =
        DynamoDbEnhancedClient.builder()
            .dynamoDbClient(
                DynamoDbClient.builder()
                    .region(Region.of(amazonRegion))
                    .build()
            )
            .build()
}