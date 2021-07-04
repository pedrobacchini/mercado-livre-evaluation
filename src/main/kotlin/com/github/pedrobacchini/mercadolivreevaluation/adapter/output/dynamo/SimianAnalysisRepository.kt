package com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.converter.toEntity
import com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.entity.SimianAnalysisEntity
import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.application.port.output.SimianAnalysisRepositoryPort
import com.github.pedrobacchini.mercadolivreevaluation.extension.jsonToObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class SimianAnalysisRepository(
    val dynamoDBMapper: DynamoDBMapper
) : SimianAnalysisRepositoryPort {

    private val logger: Logger = LoggerFactory.getLogger(SimianAnalysis::class.java)

    override fun save(simianAnalysis: SimianAnalysis) {

        logger.info("Starting process to save a simian analysis with dna:[{}]", simianAnalysis.dna)

        dynamoDBMapper.save(simianAnalysis.toEntity())

        logger.info("Done process to save a simian analysis with dna:[{}]", simianAnalysis.dna)
    }

    override fun findSequenceByDna(dna: List<List<Char>>): List<SimianAnalysis.Sequence>? {

        logger.info("Starting process to find sequence by dna:[{}]", dna)

        val entity = SimianAnalysisEntity(pk = dna.hashCode().toString())

        val withConsistentRead = DynamoDBQueryExpression<SimianAnalysisEntity>()
            .withHashKeyValues(entity)
            .withConsistentRead(true)

        return dynamoDBMapper.query(SimianAnalysisEntity::class.java, withConsistentRead)
            .map { it.sequences.jsonToObject(mutableListOf<SimianAnalysis.Sequence>().javaClass) }
            .firstOrNull()
            .also { logger.info("Done process to find a sequence by dna:[{}] found Sequence:[{}]", dna, it) }
    }
}