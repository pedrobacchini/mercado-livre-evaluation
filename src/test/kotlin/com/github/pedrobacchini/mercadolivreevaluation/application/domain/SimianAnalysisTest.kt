package com.github.pedrobacchini.mercadolivreevaluation.application.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class SimianAnalysisTest {

    data class SimianAnalysisVerificationTest(
        val simianAnalysis: SimianAnalysis,
        val sequencesCount: Int,
        val result: Boolean
    )

    @ParameterizedTest
    @MethodSource("generateValidSimianAnalysis")
    fun `given that simian analysis, it should execute analysis successfully`(
        verification: SimianAnalysisVerificationTest
    ) {
        verification.simianAnalysis.analysis()

        assertEquals(verification.sequencesCount, verification.simianAnalysis.sequences().size)
        assertEquals(verification.result, verification.simianAnalysis.isSimian())
    }

    companion object {
        @JvmStatic
        fun generateValidSimianAnalysis(): Stream<Arguments> {

            val v1x1Equal = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('T')
                    )
                ),
                sequencesCount = 0,
                result = false
            )

            val v2x2Equal = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('T', 'T'),
                        listOf('T', 'T')
                    )
                ),
                sequencesCount = 0,
                result = false
            )

            val v3x3Equal = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('T', 'T', 'T'),
                        listOf('T', 'T', 'T'),
                        listOf('T', 'T', 'T')
                    )
                ),
                sequencesCount = 0,
                result = false
            )

            val v4x4Equal = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('T', 'T', 'T', 'T'),
                        listOf('T', 'T', 'T', 'T'),
                        listOf('T', 'T', 'T', 'T'),
                        listOf('T', 'T', 'T', 'T')
                    )
                ),
                sequencesCount = 10,
                result = true
            )

            val v5x5Equal = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('T', 'T', 'T', 'T', 'T'),
                        listOf('T', 'T', 'T', 'T', 'T'),
                        listOf('T', 'T', 'T', 'T', 'T'),
                        listOf('T', 'T', 'T', 'T', 'T'),
                        listOf('T', 'T', 'T', 'T', 'T')
                    )
                ),
                sequencesCount = 28,
                result = true
            )

            val v4x4SameSequences = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('A', 'C', 'T', 'G'),
                        listOf('A', 'C', 'T', 'G'),
                        listOf('A', 'C', 'T', 'G'),
                        listOf('A', 'C', 'T', 'G')
                    )
                ),
                sequencesCount = 4,
                result = true
            )

            val v5x5SameSequences = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('A', 'C', 'T', 'G', 'A'),
                        listOf('A', 'C', 'T', 'G', 'A'),
                        listOf('A', 'C', 'T', 'G', 'A'),
                        listOf('A', 'C', 'T', 'G', 'A'),
                        listOf('A', 'C', 'T', 'G', 'A')
                    )
                ),
                sequencesCount = 10,
                result = true
            )

            val v4x4Rotation = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('A', 'C', 'T', 'G'),
                        listOf('G', 'A', 'C', 'T'),
                        listOf('C', 'T', 'G', 'A'),
                        listOf('T', 'G', 'A', 'C')
                    )
                ),
                sequencesCount = 0,
                result = false
            )

            val v5x5Rotation = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('A', 'C', 'T', 'G', 'A'),
                        listOf('G', 'A', 'C', 'T', 'G'),
                        listOf('C', 'T', 'G', 'A', 'C'),
                        listOf('T', 'G', 'A', 'C', 'T'),
                        listOf('G', 'A', 'C', 'T', 'G')
                    )
                ),
                sequencesCount = 0,
                result = false
            )

            val vHorizontal = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('A', 'T', 'G', 'C', 'G', 'A'),
                        listOf('C', 'A', 'G', 'T', 'G', 'C'),
                        listOf('T', 'T', 'C', 'T', 'T', 'T'),
                        listOf('A', 'G', 'A', 'A', 'A', 'A'),
                        listOf('G', 'C', 'G', 'T', 'C', 'A'),
                        listOf('T', 'C', 'A', 'C', 'T', 'G')
                    )
                ),
                sequencesCount = 1,
                result = false
            )

            val vVertical = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('A', 'T', 'G', 'C', 'G', 'A'),
                        listOf('C', 'A', 'G', 'T', 'G', 'C'),
                        listOf('T', 'A', 'A', 'T', 'T', 'T'),
                        listOf('A', 'A', 'A', 'C', 'G', 'A'),
                        listOf('G', 'A', 'G', 'T', 'C', 'A'),
                        listOf('T', 'C', 'A', 'C', 'T', 'G')
                    )
                ),
                sequencesCount = 1,
                result = false
            )

            val vDiagonalLeft = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('A', 'T', 'G', 'A', 'G', 'A'),
                        listOf('C', 'A', 'A', 'T', 'G', 'C'),
                        listOf('T', 'A', 'A', 'T', 'T', 'T'),
                        listOf('A', 'G', 'A', 'C', 'G', 'A'),
                        listOf('G', 'C', 'G', 'T', 'C', 'A'),
                        listOf('T', 'C', 'A', 'C', 'T', 'G')
                    )
                ),
                sequencesCount = 1,
                result = false
            )

            val vDiagonalRight = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('A', 'T', 'G', 'C', 'G', 'A'),
                        listOf('C', 'A', 'G', 'T', 'G', 'C'),
                        listOf('T', 'T', 'A', 'T', 'T', 'T'),
                        listOf('A', 'G', 'A', 'A', 'G', 'A'),
                        listOf('G', 'C', 'G', 'T', 'C', 'A'),
                        listOf('T', 'C', 'A', 'C', 'T', 'G')
                    )
                ),
                sequencesCount = 1,
                result = false
            )

            val v6x6Random1 = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('T', 'T', 'G', 'A', 'G', 'A'),
                        listOf('C', 'T', 'A', 'T', 'G', 'C'),
                        listOf('T', 'A', 'T', 'T', 'G', 'T'),
                        listOf('A', 'G', 'A', 'T', 'G', 'G'),
                        listOf('C', 'C', 'C', 'C', 'T', 'A'),
                        listOf('T', 'C', 'A', 'C', 'T', 'G')
                    )
                ),
                sequencesCount = 5,
                result = true
            )

            val v6x6Random2 = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('C', 'T', 'G', 'A', 'G', 'A'),
                        listOf('C', 'T', 'A', 'T', 'G', 'C'),
                        listOf('T', 'A', 'T', 'T', 'G', 'T'),
                        listOf('A', 'G', 'A', 'G', 'G', 'G'),
                        listOf('C', 'C', 'C', 'C', 'T', 'A'),
                        listOf('T', 'C', 'A', 'C', 'T', 'G')
                    )
                ),
                sequencesCount = 3,
                result = true
            )

            val v6x6Random3 = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('A', 'T', 'G', 'C', 'G', 'A'),
                        listOf('C', 'A', 'G', 'T', 'G', 'C'),
                        listOf('T', 'T', 'A', 'T', 'T', 'T'),
                        listOf('A', 'G', 'A', 'C', 'G', 'A'),
                        listOf('G', 'C', 'G', 'T', 'C', 'A'),
                        listOf('T', 'C', 'A', 'C', 'T', 'G')
                    )
                ),
                sequencesCount = 0,
                result = false
            )

            val v6x6Random4 = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('T', 'T', 'G', 'A', 'G', 'A'),
                        listOf('C', 'C', 'A', 'G', 'T', 'C'),
                        listOf('T', 'A', 'G', 'A', 'G', 'T'),
                        listOf('A', 'G', 'A', 'C', 'A', 'G'),
                        listOf('C', 'T', 'T', 'T', 'T', 'A'),
                        listOf('T', 'C', 'A', 'C', 'T', 'G')
                    )
                ),
                sequencesCount = 5,
                result = true
            )

            val v6x6Random5 = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('C', 'T', 'G', 'G', 'G', 'A'),
                        listOf('A', 'C', 'T', 'G', 'A', 'C'),
                        listOf('T', 'A', 'C', 'A', 'G', 'T'),
                        listOf('A', 'T', 'A', 'C', 'G', 'G'),
                        listOf('C', 'G', 'T', 'T', 'G', 'A'),
                        listOf('C', 'C', 'A', 'T', 'G', 'G')
                    )
                ),
                sequencesCount = 5,
                result = true
            )

            val v6x6Random6 = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('C', 'T', 'G', 'A', 'G', 'C'),
                        listOf('A', 'C', 'T', 'A', 'T', 'C'),
                        listOf('T', 'A', 'C', 'T', 'G', 'T'),
                        listOf('C', 'T', 'A', 'T', 'C', 'G'),
                        listOf('A', 'G', 'C', 'T', 'G', 'A'),
                        listOf('C', 'A', 'A', 'T', 'C', 'G')
                    )
                ),
                sequencesCount = 1,
                result = false
            )

            val v6x6Random7 = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('C', 'T', 'G', 'G', 'G', 'A'),
                        listOf('A', 'C', 'T', 'G', 'A', 'C'),
                        listOf('T', 'A', 'G', 'C', 'T', 'T'),
                        listOf('G', 'A', 'A', 'C', 'A', 'G'),
                        listOf('C', 'G', 'T', 'T', 'G', 'A'),
                        listOf('C', 'C', 'A', 'T', 'G', 'G')
                    )
                ),
                sequencesCount = 0,
                result = false
            )

            val v6x6Random8 = SimianAnalysisVerificationTest(
                simianAnalysis = SimianAnalysis(
                    dna = listOf(
                        listOf('C', 'T', 'A', 'G', 'G', 'T', 'C', 'G'),
                        listOf('T', 'A', 'T', 'G', 'C', 'A', 'T', 'C'),
                        listOf('C', 'A', 'A', 'T', 'G', 'C', 'T', 'A'),
                        listOf('A', 'T', 'C', 'A', 'T', 'G', 'A', 'G'),
                        listOf('G', 'T', 'A', 'G', 'A', 'T', 'C', 'T'),
                        listOf('T', 'T', 'T', 'T', 'C', 'A', 'G', 'C'),
                        listOf('C', 'A', 'G', 'G', 'T', 'C', 'G', 'T'),
                        listOf('G', 'C', 'C', 'C', 'C', 'T', 'A', 'G')
                    )
                ),
                sequencesCount = 6,
                result = true
            )

            return Stream.of(
                Arguments.arguments(v1x1Equal),
                Arguments.arguments(v2x2Equal),
                Arguments.arguments(v3x3Equal),
                Arguments.arguments(v4x4Equal),
                Arguments.arguments(v5x5Equal),
                Arguments.arguments(v4x4Rotation),
                Arguments.arguments(v5x5Rotation),
                Arguments.arguments(v4x4SameSequences),
                Arguments.arguments(v5x5SameSequences),
                Arguments.arguments(vHorizontal),
                Arguments.arguments(vVertical),
                Arguments.arguments(vDiagonalLeft),
                Arguments.arguments(vDiagonalRight),
                Arguments.arguments(v6x6Random1),
                Arguments.arguments(v6x6Random2),
                Arguments.arguments(v6x6Random3),
                Arguments.arguments(v6x6Random4),
                Arguments.arguments(v6x6Random5),
                Arguments.arguments(v6x6Random6),
                Arguments.arguments(v6x6Random7),
                Arguments.arguments(v6x6Random8)
            )
        }
    }
}