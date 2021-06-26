package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api

import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.request.SimianAnalysisRequest
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response.SimianAnalysisResponse
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/v1/simian", produces = [APPLICATION_JSON_VALUE])
interface SimianApi {

    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    fun simianAnalysis(
        simianAnalysisRequest: SimianAnalysisRequest
    ): SimianAnalysisResponse
}