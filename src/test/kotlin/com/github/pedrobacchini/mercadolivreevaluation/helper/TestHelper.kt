package com.github.pedrobacchini.mercadolivreevaluation.helper

import com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.request.SimianAnalysisRequest
import com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.entity.SimianAnalysisEntity
import com.github.pedrobacchini.mercadolivreevaluation.application.domain.DirectionalPoint
import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.extension.objectToJson
import org.jeasy.random.EasyRandom

inline fun <reified T> dummyObject(): T = EasyRandom().nextObject(T::class.java)

fun validSimianAnalysisRequest() =
    SimianAnalysisRequest(
        dna = listOf(
            "TTGAGA",
            "CTATGC",
            "TATTGT",
            "AGATGG",
            "CCCCTA",
            "TCACTG"
        )
    )

fun validSimianAnalysis() =
    SimianAnalysis(
        dna = listOf(
            listOf('T', 'T', 'G', 'A', 'G', 'A'),
            listOf('C', 'T', 'A', 'T', 'G', 'C'),
            listOf('T', 'A', 'T', 'T', 'G', 'T'),
            listOf('A', 'G', 'A', 'T', 'G', 'G'),
            listOf('C', 'C', 'C', 'C', 'T', 'A'),
            listOf('T', 'C', 'A', 'C', 'T', 'G'),
        )
    ).apply { this.analysis() }

fun validHumanSimianAnalysis() =
    SimianAnalysis(
        dna = listOf(
            listOf('A', 'T', 'G', 'C', 'G', 'A'),
            listOf('C', 'A', 'G', 'T', 'G', 'C'),
            listOf('T', 'T', 'A', 'T', 'T', 'T'),
            listOf('A', 'G', 'A', 'C', 'G', 'A'),
            listOf('G', 'C', 'G', 'T', 'C', 'A'),
            listOf('T', 'C', 'A', 'C', 'T', 'G'),
        )
    ).apply { this.analysis() }

fun validSimianAnalysisEntity(): SimianAnalysisEntity {
    val sequence = SimianAnalysis.Sequence(
        type = SimianAnalysis.Sequence.Type.DIAGONAL_RIGHT,
        directionalPoints = listOf(
            DirectionalPoint(0, 1, DirectionalPoint.Direction.NORTH),
            DirectionalPoint(0, 2, DirectionalPoint.Direction.NORTH)
        )
    )
    return SimianAnalysisEntity(
        pk = "-1134965503",
        sk = "simian",
        sequences = listOf(sequence).objectToJson()
    )
}
