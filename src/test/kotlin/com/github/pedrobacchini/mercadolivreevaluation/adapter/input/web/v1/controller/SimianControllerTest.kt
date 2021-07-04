package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.controller

import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisUseCase
import com.github.pedrobacchini.mercadolivreevaluation.helper.validSimianAnalysisRequest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

internal class SimianControllerTest {

    private val simianAnalysisUseCase: SimianAnalysisUseCase = mock()

    private val simianController = SimianController(simianAnalysisUseCase)

    @Test
    fun `given that simian analysis request, it should call SimianAnalysisUseCase`() {

        val validSimianAnalysisRequest = validSimianAnalysisRequest()

        simianController.simianAnalysis(validSimianAnalysisRequest)

        verify(simianAnalysisUseCase, times(1)).execute(any())
    }
}