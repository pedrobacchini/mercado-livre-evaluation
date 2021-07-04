package com.github.pedrobacchini.mercadolivreevaluation.application.service

import com.github.pedrobacchini.mercadolivreevaluation.application.port.output.SimianAnalysisRepositoryPort
import com.github.pedrobacchini.mercadolivreevaluation.helper.dummyObject
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test

internal class SimianAnalysisServiceTest {

    private val simianAnalysisRepositoryPort: SimianAnalysisRepositoryPort = mock()

    private val simianAnalysisService = SimianAnalysisService(simianAnalysisRepositoryPort)

    @Test
    fun `given that new simian analysis, it should execute analysis successfully`() {
        whenever(simianAnalysisRepositoryPort.findSequenceByDna(any())).thenReturn(null)

        simianAnalysisService.execute(dummyObject())

        verify(simianAnalysisRepositoryPort, times(1)).findSequenceByDna(any())
        verify(simianAnalysisRepositoryPort, times(1)).save(any())
    }

    @Test
    fun `given that already execute simian analysis, it should retrieve analysis successfully`() {
        whenever(simianAnalysisRepositoryPort.findSequenceByDna(any())).thenReturn(listOf(dummyObject()))

        simianAnalysisService.execute(dummyObject())

        verify(simianAnalysisRepositoryPort, times(1)).findSequenceByDna(any())
        verify(simianAnalysisRepositoryPort, never()).save(any())
    }
}