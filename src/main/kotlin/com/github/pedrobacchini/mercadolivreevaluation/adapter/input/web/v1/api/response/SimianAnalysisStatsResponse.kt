package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response

import io.swagger.v3.oas.annotations.media.Schema

data class SimianAnalysisStatsResponse(
    @field:Schema(description = COUNT_SIMIAN_DESCRIPTION)
    val countSimianDna: Int,
    @field:Schema(description = COUNT_HUMAN_DESCRIPTION)
    val countHumanDna: Int,
    @field:Schema(description = RATION_DESCRIPTION)
    val ratio: Float?
) {
    companion object {
        const val COUNT_SIMIAN_DESCRIPTION = "DNA counter of analyzed simians"
        const val COUNT_HUMAN_DESCRIPTION = "DNA counter of analyzed humans"
        const val RATION_DESCRIPTION = "Proportion of simians to humans"
    }
}
