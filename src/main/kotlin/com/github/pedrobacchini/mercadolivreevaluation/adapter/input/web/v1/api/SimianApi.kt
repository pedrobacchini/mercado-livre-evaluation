package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api

import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.request.SimianAnalysisRequest
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response.SimianAnalysisResponse
import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.response.SimianAnalysisStatsResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Tag(name = "Simian Analysis")
@RequestMapping("/v1/simian", produces = [APPLICATION_JSON_VALUE])
interface SimianApi {

    @Operation(summary = SIMIAN_ANALYSIS_SUMMARY, description = SIMIAN_ANALYSIS_DESCRIPTION)
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = SIMIAN_ANALYSIS_RESPONSE_DESCRIPTION, content = [(Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    array = (ArraySchema(schema = Schema(implementation = SimianAnalysisResponse::class)))
                ))]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])
        ]
    )
    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    fun simianAnalysis(
        @Valid @RequestBody simianAnalysisRequest: SimianAnalysisRequest
    ): SimianAnalysisResponse


    @Operation(summary = SIMIAN_ANALYSIS_STATS_SUMMARY, description = SIMIAN_ANALYSIS_STATS_DESCRIPTION)
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Simian analysis stats", content = [(Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    array = (ArraySchema(schema = Schema(implementation = SimianAnalysisStatsResponse::class)))
                ))]
            )
        ]
    )
    @GetMapping("/stats")
    fun stats(): SimianAnalysisStatsResponse

    companion object {
        const val SIMIAN_ANALYSIS_SUMMARY = "Start simian analysis"
        const val SIMIAN_ANALYSIS_DESCRIPTION = "Provide an HTTP POST endpoint \"/v1/simian\". " +
                "This endpoint will be as a parameter, a JSON with a DNA sequence (Array of Strings), " +
                "where each element of this array represents a row of a square table of (NxN), " +
                "As in the example below: " +
                "{\"dna\": [\"CTAGGTCG\",\"TATGCATC\",\"CAATGCTA\",\"ATCATGAG\",\"GTAGATCT\",\"TTTTCAGC\",\"CAGGTCGT\",\"GCCCCTAG\"]}"
        const val SIMIAN_ANALYSIS_RESPONSE_DESCRIPTION = "Analysis result"
        const val SIMIAN_ANALYSIS_STATS_SUMMARY = "Retrieve simian analysis stats"
        const val SIMIAN_ANALYSIS_STATS_DESCRIPTION = "Provide another endpoint \"/v1/simian/stats\" " +
                "that responds to an HTTP GET. The answer should be a Json that returns the DNA checks statistics, " +
                "where it should report the quantity of ape DNA’s, quantity of human DNA’s, and the ratio of simians " +
                "to the human population. The following is an example of the answer: " +
                "{\"count_simian_dna\": 40, \"count_human_dna\": 100: \"ratio\": 0.4}"
    }
}