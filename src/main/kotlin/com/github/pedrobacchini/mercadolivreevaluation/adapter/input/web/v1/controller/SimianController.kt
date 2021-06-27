package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.controller

import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.SimianApi
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.request.SimianAnalysisRequest
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response.SimianAnalysisResponse
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.converter.toDomain
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.converter.toResponse
import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisUseCase
import org.springframework.web.bind.annotation.RestController

@RestController
class SimianController(
    private val simianAnalysisUseCase: SimianAnalysisUseCase
) : SimianApi {

    override fun simianAnalysis(
        simianAnalysisRequest: SimianAnalysisRequest
    ): SimianAnalysisResponse {
        val simianAnalysis = simianAnalysisRequest.toDomain()
        simianAnalysisUseCase.execute(simianAnalysis)
        return simianAnalysis.toResponse()
    }
}