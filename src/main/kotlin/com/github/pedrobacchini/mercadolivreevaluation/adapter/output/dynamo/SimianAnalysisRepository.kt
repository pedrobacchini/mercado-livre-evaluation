package com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo

import com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.converter.toEntity
import com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.entity.SimianAnalysisEntity
import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.application.port.output.SimianAnalysisRepositoryPort
import com.github.pedrobacchini.mercadolivreevaluation.extension.jsonToObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional

@Repository
class SimianAnalysisRepository(
    @Value("\${amazon.aws.dynamo.table}")
    private val tableName: String,
    dynamoDbEnhancedClient: DynamoDbEnhancedClient
) : SimianAnalysisRepositoryPort {

    val dynamoTable: DynamoDbTable<SimianAnalysisEntity> = dynamoDbEnhancedClient
        .table(tableName, TableSchema.fromBean(SimianAnalysisEntity::class.java))

    companion object {
        private const val SkIndex = "SkIndex"
    }

    private val logger: Logger = LoggerFactory.getLogger(SimianAnalysis::class.java)

    override fun save(simianAnalysis: SimianAnalysis) {

        logger.info("Starting process to save a simian analysis with dna:[{}]", simianAnalysis.dna)

        dynamoTable.putItem(simianAnalysis.toEntity())

        logger.info("Done process to save a simian analysis with dna:[{}]", simianAnalysis.dna)
    }

    override fun findSequenceByDna(dna: List<List<Char>>): List<SimianAnalysis.Sequence>? {

        logger.info("Starting process to find sequence by dna:[{}]", dna)

        return dynamoTable
            .getItem(Key.builder().partitionValue(dna.hashCode().toString()).build())
            .sequences.jsonToObject(mutableListOf<SimianAnalysis.Sequence>().javaClass)
            .also { logger.info("Done process to find a sequence by dna:[{}] found Sequence:[{}]", dna, it) }
    }

    override fun countSimianAnalysisByResultType(resultType: String): Int {
        logger.info("Starting process to count simian analysis resultType:[{}]", resultType)

        return dynamoTable
            .index(SkIndex)
            .query { r -> r.queryConditional(QueryConditional.keyEqualTo { k -> k.partitionValue(resultType) }) }
            .count()
    }
}