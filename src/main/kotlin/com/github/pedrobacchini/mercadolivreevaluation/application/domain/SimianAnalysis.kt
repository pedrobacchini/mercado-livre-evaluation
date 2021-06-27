package com.github.pedrobacchini.mercadolivreevaluation.application.domain

data class SimianAnalysis(
    val dna: List<List<Char>>
) {
    private val sequences: MutableList<Sequence> = mutableListOf()
    private val dnaLength: Int = dna.size
    private val calc = Array(dnaLength) { Array(dnaLength) { IntArray(4) } }

    companion object {
        private const val MIM_SEQUENCES = 2
        private const val MIM_CHARACTERS_IN_SEQUENCES = 3
        private const val WEST_COUNT_INDEX = 0
        private const val NORTH_WEST_COUNT_INDEX = 1
        private const val NORTH_COUNT_INDEX = 2
        private const val NORTH_EAST_COUNT_INDEX = 3
    }

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

    fun isSimian() = sequences.size >= MIM_SEQUENCES

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
                sequences.add(Sequence(Sequence.Type.HORIZONTAL))
        }
    }

    private fun northWestAnalysis(x: Int, y: Int, aChar: Char) {
        if (x - 1 >= 0 && y - 1 >= 0 && dna[x - 1][y - 1] == aChar) {
            calc[x][y][NORTH_WEST_COUNT_INDEX] = calc[x - 1][y - 1][NORTH_WEST_COUNT_INDEX] + 1
            if (calc[x][y][NORTH_WEST_COUNT_INDEX] >= MIM_CHARACTERS_IN_SEQUENCES)
                sequences.add(Sequence(Sequence.Type.DIAGONAL_LEFT))
        }
    }

    private fun northAnalysis(x: Int, y: Int, aChar: Char) {
        if (x - 1 >= 0 && dna[x - 1][y] == aChar) {
            calc[x][y][NORTH_COUNT_INDEX] = calc[x - 1][y][NORTH_COUNT_INDEX] + 1
            if (calc[x][y][NORTH_COUNT_INDEX] >= MIM_CHARACTERS_IN_SEQUENCES)
                sequences.add(Sequence(Sequence.Type.VERTICAL))
        }
    }

    private fun northEastAnalysis(x: Int, y: Int, aChar: Char) {
        if (x - 1 >= 0 && y + 1 < dnaLength && dna[x - 1][y + 1] == aChar) {
            calc[x][y][NORTH_EAST_COUNT_INDEX] = calc[x - 1][y + 1][NORTH_EAST_COUNT_INDEX] + 1
            if (calc[x][y][NORTH_EAST_COUNT_INDEX] >= MIM_CHARACTERS_IN_SEQUENCES)
                sequences.add(Sequence(Sequence.Type.DIAGONAL_RIGHT))
        }
    }
}
