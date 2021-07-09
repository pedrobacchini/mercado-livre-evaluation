package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.controller

import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysisStats
import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisStatsUseCase
import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisUseCase
import com.github.pedrobacchini.mercadolivreevaluation.helper.validSimianAnalysisRequest
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class SimianControllerTest {

    private val simianAnalysisUseCase: SimianAnalysisUseCase = mock()
    private val simianAnalysisStatsUseCase: SimianAnalysisStatsUseCase = mock()

    private val simianController = SimianController(simianAnalysisUseCase, simianAnalysisStatsUseCase)

    data class SimianAnalysisStatsVerificationTest(
        val expectedCountSimianDna: Int,
        val expectedCountHumanDna: Int,
        val expectedRation: Float?
    )

    @Test
    fun `given that simian analysis request, it should call SimianAnalysisUseCase`() {

        val validSimianAnalysisRequest = validSimianAnalysisRequest()

        simianController.simianAnalysis(validSimianAnalysisRequest)

        verify(simianAnalysisUseCase, times(1)).execute(any())
        verify(simianAnalysisStatsUseCase, never()).execute()
    }

    @ParameterizedTest
    @MethodSource("generateSimianAnalysisStats")
    fun `it should call SimianAnalysisStatsUseCase`(
        verificationTest: SimianAnalysisStatsVerificationTest
    ) {
        val expectedSimianAnalysis = SimianAnalysisStats(
            countSimianDna = verificationTest.expectedCountSimianDna,
            countHumanDna = verificationTest.expectedCountHumanDna
        )
        whenever(simianAnalysisStatsUseCase.execute()).thenReturn(expectedSimianAnalysis)

        val stats = simianController.stats()

        assertEquals(verificationTest.expectedCountSimianDna, stats.countSimianDna)
        assertEquals(verificationTest.expectedCountHumanDna, stats.countHumanDna)
        assertEquals(verificationTest.expectedRation, stats.ratio)
        verify(simianAnalysisUseCase, never()).execute(any())
        verify(simianAnalysisStatsUseCase, times(1)).execute()
    }

    companion object {
        @JvmStatic
        fun generateSimianAnalysisStats(): Stream<Arguments> {

            val expectedResult1 = SimianAnalysisStatsVerificationTest(
                expectedCountSimianDna = 4,
                expectedCountHumanDna = 10,
                expectedRation = 0.4f
            )

            val expectedResult2 = SimianAnalysisStatsVerificationTest(
                expectedCountSimianDna = 0,
                expectedCountHumanDna = 0,
                expectedRation = null
            )

            return Stream.of(
                Arguments.arguments(expectedResult1),
                Arguments.arguments(expectedResult2)
            )
        }
    }
}