package com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.entity

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "Analysis")
data class SimianAnalysisEntity(

    @DynamoDBHashKey(attributeName = "pk")
    var pk: String = "",

    @DynamoDBRangeKey(attributeName = "sk")
    var sk: String = "",

    @DynamoDBAttribute(attributeName = "sequences")
    var sequences: String = ""
)