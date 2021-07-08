package com.github.pedrobacchini.mercadolivreevaluation.application.domain

import com.github.pedrobacchini.mercadolivreevaluation.application.port.output.SimianAnalysisRepositoryPort

data class SimianAnalysisStats(
    val countSimianDna: Int,
    val countHumanDna: Int
) {
    companion object {
        fun retrieve(
            simianAnalysisRepositoryPort: SimianAnalysisRepositoryPort
        ): SimianAnalysisStats {
            return SimianAnalysisStats(
                countSimianDna = simianAnalysisRepositoryPort.countSimianAnalysisByResultType("simian"),
                countHumanDna = simianAnalysisRepositoryPort.countSimianAnalysisByResultType("human")
            )
        }
    }
}
