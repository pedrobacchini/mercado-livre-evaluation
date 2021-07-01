package com.github.pedrobacchini.mercadolivreevaluation.application.domain

data class DirectionalPoint(
    var x: Int,
    var y: Int,
    val direction: Direction
) {

    enum class Direction {
        WEST,
        NORTH_WEST,
        NORTH,
        NORTH_EAST
    }

    fun hasNext(length: Int): Boolean {
        return when (direction) {
            Direction.WEST -> y - 1 >= 0
            Direction.NORTH_WEST -> x - 1 >= 0 && y - 1 >= 0
            Direction.NORTH -> x - 1 >= 0
            Direction.NORTH_EAST -> x - 1 >= 0 && y + 1 < length
        }
    }

    fun next(): DirectionalPoint {
        when (direction) {
            Direction.WEST -> --y
            Direction.NORTH_WEST -> {
                --x
                --y
            }
            Direction.NORTH -> --x
            Direction.NORTH_EAST -> {
                --x
                ++y
            }
        }
        return this.copy()
    }

    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }
}