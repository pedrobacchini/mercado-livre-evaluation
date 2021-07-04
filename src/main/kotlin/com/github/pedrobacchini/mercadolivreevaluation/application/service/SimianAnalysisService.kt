package com.github.pedrobacchini.mercadolivreevaluation.application.service

import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisUseCase
import com.github.pedrobacchini.mercadolivreevaluation.application.port.output.SimianAnalysisRepositoryPort
import org.springframework.stereotype.Service

@Service
class SimianAnalysisService(
    val simianAnalysisRepositoryPort: SimianAnalysisRepositoryPort
) : SimianAnalysisUseCase {

    override fun execute(simianAnalysis: SimianAnalysis) {

        simianAnalysis.apply {

            if (retrieveAnalysis(simianAnalysisRepositoryPort)) return

            analysis()

            save(simianAnalysisRepositoryPort)
        }
    }
}