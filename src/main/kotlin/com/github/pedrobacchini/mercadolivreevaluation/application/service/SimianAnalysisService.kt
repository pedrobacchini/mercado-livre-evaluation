package com.github.pedrobacchini.mercadolivreevaluation.application.service

import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisUseCase
import org.springframework.stereotype.Service

@Service
class SimianAnalysisService : SimianAnalysisUseCase {

    override fun execute(simianAnalysis: SimianAnalysis) {

        simianAnalysis.analysis()
    }
}