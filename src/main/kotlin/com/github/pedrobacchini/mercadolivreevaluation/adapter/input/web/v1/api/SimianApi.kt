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

    @Operation(summary = "Start simian analysis")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Analysis result", content = [(Content(
                    mediaType = "application/json",
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


    @Operation(summary = "Retrieve simian analysis stats")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Simian analysis stats", content = [(Content(
                    mediaType = "application/json",
                    array = (ArraySchema(schema = Schema(implementation = SimianAnalysisStatsResponse::class)))
                ))]
            )
        ]
    )
    @GetMapping("/stats")
    fun stats(): SimianAnalysisStatsResponse
}