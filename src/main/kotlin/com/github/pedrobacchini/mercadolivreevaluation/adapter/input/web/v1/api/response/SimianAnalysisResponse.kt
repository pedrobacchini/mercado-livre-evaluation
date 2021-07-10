package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

data class SimianAnalysisResponse(
    @get:JsonProperty(IS_SIMIAN_SNAKE_CASE)
    @Schema(name = IS_SIMIAN_SNAKE_CASE)
    val isSimian: Boolean
) {
    companion object {
        const val IS_SIMIAN_SNAKE_CASE = "is_simian"
    }
}