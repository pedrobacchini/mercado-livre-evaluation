package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.controller

import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.SimianApi
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.request.SimianAnalysisRequest
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response.SimianAnalysisResponse
import org.springframework.web.bind.annotation.RestController

@RestController
class SimianController : SimianApi {

    override fun simianAnalysis(
        simianAnalysisRequest: SimianAnalysisRequest
    ): SimianAnalysisResponse {
        return SimianAnalysisResponse(false)
    }
}