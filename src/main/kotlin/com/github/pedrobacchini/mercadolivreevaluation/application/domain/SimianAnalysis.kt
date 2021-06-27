package com.github.pedrobacchini.mercadolivreevaluation.application.domain

data class SimianAnalysis(
    val dna: List<List<Char>>
) {
    var isSimian = false
    val sequences: List<Sequence> = listOf()

    data class Sequence(
        val type: Type
    ) {
        enum class Type {
            HORIZONTAL,
            VERTICAL,
            DIAGONAL_LEFT,
            DIAGONAL_RIGHT
        }
    }

    fun analysis() {
        isSimian = true
    }
}
