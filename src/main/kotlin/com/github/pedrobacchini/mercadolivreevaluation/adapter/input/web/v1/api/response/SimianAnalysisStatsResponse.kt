package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response

data class SimianAnalysisStatsResponse(
    val countSimianDna: Int,
    val countHumanDna: Int,
    val ratio: Float?
)
