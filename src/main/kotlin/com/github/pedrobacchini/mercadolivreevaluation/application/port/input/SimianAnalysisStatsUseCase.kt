package com.github.pedrobacchini.mercadolivreevaluation.application.port.input

import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysisStats

interface SimianAnalysisStatsUseCase {

    fun execute(): SimianAnalysisStats

}
