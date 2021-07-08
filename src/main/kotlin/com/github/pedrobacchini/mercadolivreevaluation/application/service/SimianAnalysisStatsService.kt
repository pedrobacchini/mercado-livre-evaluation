package com.github.pedrobacchini.mercadolivreevaluation.application.service

import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysisStats
import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisStatsUseCase
import com.github.pedrobacchini.mercadolivreevaluation.application.port.output.SimianAnalysisRepositoryPort
import org.springframework.stereotype.Service

@Service
class SimianAnalysisStatsService(
    val simianAnalysisRepositoryPort: SimianAnalysisRepositoryPort
) : SimianAnalysisStatsUseCase {

    override fun execute(): SimianAnalysisStats {

        return SimianAnalysisStats.retrieve(simianAnalysisRepositoryPort)
    }
}