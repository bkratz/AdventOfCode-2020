package de.birgitkratz.aoc2020.day03

fun processWorld1(world: World): Long =
    treeCount(world, Slope(1, 3))

fun processWorld2(world: World): Long =
    listOf(Slope(1, 1), Slope(1, 3), Slope(1, 5), Slope(1, 7), Slope(2, 1))
        .map { treeCount(world, it) }
        .fold(1) { acc, cur -> acc * cur }

internal fun treeCount(world: World, slope: Slope): Long {
    val maxX = world.count() - 1
    val path = path(slope, maxX)
    return path.filter { pos -> world.hasTreeAt(pos) }.count().toLong()
}

typealias World = List<String>

fun World.hasTreeAt(pos: Pos): Boolean {
    val width = this[0].count()
    return this[pos.x][pos.y % width] == '#'
}

data class Pos(
    val x: Int,
    val y: Int
)

operator fun Pos.plus(slope: Slope): Pos =
    Pos(this.x + slope.x, this.y + slope.y)

typealias Slope = Pos

fun path(slope: Slope, maxX: Int): List<Pos> {
    return generateSequence(Pos(0, 0)) { pos -> pos + slope }
        .takeWhile { pos -> pos.x <= maxX }
        .toList()
}

