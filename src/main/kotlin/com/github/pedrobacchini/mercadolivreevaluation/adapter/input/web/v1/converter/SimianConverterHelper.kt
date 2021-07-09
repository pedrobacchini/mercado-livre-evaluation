package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.converter

import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.request.SimianAnalysisRequest
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response.SimianAnalysisResponse
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response.SimianAnalysisStatsResponse
import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysisStats

fun SimianAnalysisRequest.toDomain() = SimianAnalysis(this.dna.map { it.toList() })

fun SimianAnalysis.toResponse() = SimianAnalysisResponse(this.isSimian())

fun SimianAnalysisStats.toResponse() = SimianAnalysisStatsResponse(
    countSimianDna = countSimianDna,
    countHumanDna = countHumanDna,
    ratio = if(countHumanDna != 0) (countSimianDna.toFloat()/countHumanDna) else null
)