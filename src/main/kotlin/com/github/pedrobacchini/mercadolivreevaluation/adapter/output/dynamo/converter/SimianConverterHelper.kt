package com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.converter

import com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.entity.SimianAnalysisEntity
import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.extension.objectToJson

fun SimianAnalysis.toEntity() =
    SimianAnalysisEntity(
        pk = this.dna.toString(),
        sk = if (this.isSimian()) "simian" else "human",
        sequences = this.sequences().objectToJson()
    )