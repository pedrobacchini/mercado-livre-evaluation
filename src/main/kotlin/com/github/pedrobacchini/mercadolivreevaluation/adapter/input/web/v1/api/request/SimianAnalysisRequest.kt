package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.request

import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.constraint.ListElementsSameSize
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.constraint.ListPattern
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.constraint.NoNullElements
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty

data class SimianAnalysisRequest(
    @field:NotEmpty
    @field:NoNullElements
    @field:ListElementsSameSize
    @field:ListPattern(regexp = "^[ACGT]*\$")
    @field:Schema(description = DNA_DESCRIPTION, example = DNA_EXAMPLE, pattern = DNA_PATTERN)
    val dna: List<String>
) {
    companion object {
        const val DNA_DESCRIPTION = "DNA sequence for analysis."
        const val DNA_EXAMPLE =
            "[\"CTAGGTCG\",\"TATGCATC\",\"CAATGCTA\",\"ATCATGAG\"" +
                    ",\"GTAGATCT\",\"TTTTCAGC\",\"CAGGTCGT\",\"GCCCCTAG\"]"
        const val DNA_PATTERN =
            "List cannot be empty. No element in the list must be null. " +
                    "All elements must be the same size. Only ACGT characters are accepted"
    }
}