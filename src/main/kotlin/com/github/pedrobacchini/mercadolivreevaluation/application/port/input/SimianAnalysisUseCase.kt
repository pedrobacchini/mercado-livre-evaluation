package com.github.pedrobacchini.mercadolivreevaluation.application.port.input

import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis

interface SimianAnalysisUseCase {

    fun execute(simianAnalysis: SimianAnalysis)
}