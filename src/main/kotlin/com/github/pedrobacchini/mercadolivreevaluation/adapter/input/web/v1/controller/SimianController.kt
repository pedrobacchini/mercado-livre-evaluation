package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.controller

import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.SimianApi
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.request.SimianAnalysisRequest
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response.SimianAnalysisResponse
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.converter.toDomain
import org.springframework.web.bind.annotation.RestController

@RestController
class SimianController : SimianApi {

    override fun simianAnalysis(
        simianAnalysisRequest: SimianAnalysisRequest
    ): SimianAnalysisResponse {
        val toDomain = simianAnalysisRequest.toDomain()
        return SimianAnalysisResponse(false)
    }
}