package com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.entity

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey

@DynamoDbBean
data class SimianAnalysisEntity(

    @get:DynamoDbPartitionKey
    var pk: String = "",

    @get:DynamoDbSortKey
    var sk: String = "",

    var sequences: String = ""
)