package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response

import com.fasterxml.jackson.annotation.JsonProperty

data class SimianAnalysisResponse(
    @get:JsonProperty("is_simian")
    val isSimian: Boolean
)