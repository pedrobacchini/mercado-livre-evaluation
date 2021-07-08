package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.controller

import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.SimianApi
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.request.SimianAnalysisRequest
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response.SimianAnalysisResponse
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response.SimianAnalysisStatsResponse
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.converter.toDomain
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.converter.toResponse
import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisStatsUseCase
import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController

@RestController
class SimianController(
    private val simianAnalysisUseCase: SimianAnalysisUseCase,
    private val simianAnalysisStatsUseCase: SimianAnalysisStatsUseCase
) : SimianApi {

    private val logger: Logger = LoggerFactory.getLogger(SimianController::class.java)

    override fun simianAnalysis(
        simianAnalysisRequest: SimianAnalysisRequest
    ): SimianAnalysisResponse {

        logger.info("Starting process to simian analysis with dna:[{}]", simianAnalysisRequest.dna)

        return simianAnalysisRequest.toDomain()
            .apply { simianAnalysisUseCase.execute(this) }
            .also {
                logger.info(
                    "Done process to simian analysis with dna:[{}] and isSimian:[{}]",
                    it.dna,
                    it.isSimian()
                )
            }
            .toResponse()
    }

    override fun stats(): SimianAnalysisStatsResponse {
        logger.info("Starting process to retrieve simian analysis stats")

        return simianAnalysisStatsUseCase.execute()
            .also { logger.info("Done process to retrieve simian analysis stats") }
            .toResponse()
    }
}