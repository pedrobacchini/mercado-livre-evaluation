package com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.converter.toEntity
import com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.entity.SimianAnalysisEntity
import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.application.port.output.SimianAnalysisRepositoryPort
import com.github.pedrobacchini.mercadolivreevaluation.extension.jsonToObject
import org.springframework.stereotype.Repository

@Repository
class SimianAnalysisRepository(
    val dynamoDBMapper: DynamoDBMapper
) : SimianAnalysisRepositoryPort {

    override fun save(simianAnalysis: SimianAnalysis) {

        dynamoDBMapper.save(simianAnalysis.toEntity())
    }

    override fun findSequenceByDna(dna: List<List<Char>>): List<SimianAnalysis.Sequence>? {

        val entity = SimianAnalysisEntity(pk = dna.hashCode().toString())

        val withConsistentRead = DynamoDBQueryExpression<SimianAnalysisEntity>()
            .withHashKeyValues(entity)
            .withConsistentRead(true)

        return dynamoDBMapper.query(SimianAnalysisEntity::class.java, withConsistentRead)
            .map { it.sequences.jsonToObject(listOf<SimianAnalysis.Sequence>().javaClass) }
            .firstOrNull()
    }
}