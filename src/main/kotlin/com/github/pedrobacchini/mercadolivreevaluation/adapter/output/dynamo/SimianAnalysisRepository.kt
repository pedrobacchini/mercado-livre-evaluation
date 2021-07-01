package com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.converter.toEntity
import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.application.port.output.SimianAnalysisRepositoryPort
import org.springframework.stereotype.Repository

@Repository
class SimianAnalysisRepository(
    val dynamoDBMapper: DynamoDBMapper
) : SimianAnalysisRepositoryPort {

    override fun save(simianAnalysis: SimianAnalysis) {

        dynamoDBMapper.save(simianAnalysis.toEntity())
    }
}