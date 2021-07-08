package com.github.pedrobacchini.mercadolivreevaluation.application.service

import com.github.pedrobacchini.mercadolivreevaluation.application.port.output.SimianAnalysisRepositoryPort
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SimianAnalysisStatsServiceTest {

    private val simianAnalysisRepositoryPort: SimianAnalysisRepositoryPort = mock()

    private val simianAnalysisStatsService = SimianAnalysisStatsService(simianAnalysisRepositoryPort)

    @Test
    fun `call use case, it should execute retrieve stats successfully`() {

        whenever(simianAnalysisRepositoryPort.countSimianAnalysisByResultType(any()))
            .thenReturn(5).thenReturn(10)

        val execute = simianAnalysisStatsService.execute()

        assertEquals(execute.countSimianDna, 5)
        assertEquals(execute.countHumanDna, 10)
        verify(simianAnalysisRepositoryPort, times(2)).countSimianAnalysisByResultType(any())
    }
}