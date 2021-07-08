package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.controller

import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisStatsUseCase
import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisUseCase
import com.github.pedrobacchini.mercadolivreevaluation.helper.dummyObject
import com.github.pedrobacchini.mercadolivreevaluation.helper.validSimianAnalysisRequest
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test

internal class SimianControllerTest {

    private val simianAnalysisUseCase: SimianAnalysisUseCase = mock()
    private val simianAnalysisStatsUseCase: SimianAnalysisStatsUseCase = mock()

    private val simianController = SimianController(simianAnalysisUseCase, simianAnalysisStatsUseCase)

    @Test
    fun `given that simian analysis request, it should call SimianAnalysisUseCase`() {

        val validSimianAnalysisRequest = validSimianAnalysisRequest()

        simianController.simianAnalysis(validSimianAnalysisRequest)

        verify(simianAnalysisUseCase, times(1)).execute(any())
        verify(simianAnalysisStatsUseCase, never()).execute()
    }

    @Test
    fun `it should call SimianAnalysisStatsUseCase`() {
        whenever(simianAnalysisStatsUseCase.execute()).thenReturn(dummyObject())

        simianController.stats()

        verify(simianAnalysisUseCase, never()).execute(any())
        verify(simianAnalysisStatsUseCase, times(1)).execute()
    }
}