package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.converter

import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.request.SimianAnalysisRequest
import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis

fun SimianAnalysisRequest.toDomain(): SimianAnalysis {
    return SimianAnalysis(this.dna.map { it.toList() })
}