package com.github.pedrobacchini.mercadolivreevaluation.application.domain

import com.github.pedrobacchini.mercadolivreevaluation.application.port.output.SimianAnalysisRepositoryPort
import java.util.*

data class SimianAnalysis(
    val dna: List<List<Char>>
) {
    private val sequences: MutableList<Sequence> = mutableListOf()
    private val dnaLength: Int = dna.size
    private val calc = Array(dnaLength) { Array(dnaLength) { intArrayOf(1, 1, 1, 1) } }

    companion object {
        private const val MIM_SEQUENCES = 2
        private const val MIM_CHARACTERS_IN_SEQUENCES = 4
        private const val WEST_COUNT_INDEX = 0
        private const val NORTH_WEST_COUNT_INDEX = 1
        private const val NORTH_COUNT_INDEX = 2
        private const val NORTH_EAST_COUNT_INDEX = 3
    }

    data class Sequence(
        val type: Type,
        val directionalPoints: List<DirectionalPoint> = emptyList()
    ) {
        enum class Type {
            HORIZONTAL,
            VERTICAL,
            DIAGONAL_LEFT,
            DIAGONAL_RIGHT
        }

        override fun toString(): String {
            return "Sequence(type=$type, points=$directionalPoints)"
        }
    }

    fun isSimian() = sequences.size >= MIM_SEQUENCES

    fun sequences(): List<Sequence> {
        return Collections.unmodifiableList(sequences)
    }

    fun analysis() {
        dna.forEachIndexed { x, chars ->
            chars.forEachIndexed { y, aChar ->
                westAnalysis(x, y, aChar)
                northWestAnalysis(x, y, aChar)
                northAnalysis(x, y, aChar)
                northEastAnalysis(x, y, aChar)
            }
        }
    }

    private fun westAnalysis(x: Int, y: Int, aChar: Char) {
        if (y - 1 >= 0 && dna[x][y - 1] == aChar) {
            calc[x][y][WEST_COUNT_INDEX] = calc[x][y - 1][WEST_COUNT_INDEX] + 1
            if (calc[x][y][WEST_COUNT_INDEX] >= MIM_CHARACTERS_IN_SEQUENCES)
                sequences.add(
                    Sequence(
                        Sequence.Type.HORIZONTAL,
                        discoverDirectionalPoints(DirectionalPoint(x, y, DirectionalPoint.Direction.WEST))
                    )
                )
        }
    }

    private fun northWestAnalysis(x: Int, y: Int, aChar: Char) {
        if (x - 1 >= 0 && y - 1 >= 0 && dna[x - 1][y - 1] == aChar) {
            calc[x][y][NORTH_WEST_COUNT_INDEX] = calc[x - 1][y - 1][NORTH_WEST_COUNT_INDEX] + 1
            if (calc[x][y][NORTH_WEST_COUNT_INDEX] >= MIM_CHARACTERS_IN_SEQUENCES)
                sequences.add(
                    Sequence(
                        Sequence.Type.DIAGONAL_LEFT,
                        discoverDirectionalPoints(DirectionalPoint(x, y, DirectionalPoint.Direction.NORTH_WEST))
                    )
                )
        }
    }

    private fun northAnalysis(x: Int, y: Int, aChar: Char) {
        if (x - 1 >= 0 && dna[x - 1][y] == aChar) {
            calc[x][y][NORTH_COUNT_INDEX] = calc[x - 1][y][NORTH_COUNT_INDEX] + 1
            if (calc[x][y][NORTH_COUNT_INDEX] >= MIM_CHARACTERS_IN_SEQUENCES)
                sequences.add(
                    Sequence(
                        Sequence.Type.VERTICAL,
                        discoverDirectionalPoints(DirectionalPoint(x, y, DirectionalPoint.Direction.NORTH))
                    )
                )
        }
    }

    private fun northEastAnalysis(x: Int, y: Int, aChar: Char) {
        if (x - 1 >= 0 && y + 1 < dnaLength && dna[x - 1][y + 1] == aChar) {
            calc[x][y][NORTH_EAST_COUNT_INDEX] = calc[x - 1][y + 1][NORTH_EAST_COUNT_INDEX] + 1
            if (calc[x][y][NORTH_EAST_COUNT_INDEX] >= MIM_CHARACTERS_IN_SEQUENCES)
                sequences.add(
                    Sequence(
                        Sequence.Type.DIAGONAL_RIGHT,
                        discoverDirectionalPoints(DirectionalPoint(x, y, DirectionalPoint.Direction.NORTH_EAST))
                    )
                )
        }
    }

    private fun discoverDirectionalPoints(directionalPoint: DirectionalPoint): List<DirectionalPoint> {
        val directionalPoints: MutableList<DirectionalPoint> = arrayListOf(directionalPoint.copy())
        while (directionalPoint.hasNext(dnaLength) && directionalPoints.size < MIM_CHARACTERS_IN_SEQUENCES)
            directionalPoints.add(directionalPoint.next())
        directionalPoints.reverse()
        return directionalPoints
    }

    fun save(simianAnalysisRepositoryPort: SimianAnalysisRepositoryPort) {
        simianAnalysisRepositoryPort.save(this)
    }
}
