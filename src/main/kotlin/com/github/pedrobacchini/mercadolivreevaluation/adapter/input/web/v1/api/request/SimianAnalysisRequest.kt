package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.request

import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.constraint.NoNullElements
import javax.validation.constraints.NotEmpty

data class SimianAnalysisRequest(
    @field:NotEmpty
    @field:NoNullElements
    val dna: List<String>
)