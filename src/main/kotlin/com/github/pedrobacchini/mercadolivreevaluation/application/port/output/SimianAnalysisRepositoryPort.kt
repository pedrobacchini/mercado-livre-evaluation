package com.github.pedrobacchini.mercadolivreevaluation.application.port.output

import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis

interface SimianAnalysisRepositoryPort {

    fun save(simianAnalysis: SimianAnalysis)
}