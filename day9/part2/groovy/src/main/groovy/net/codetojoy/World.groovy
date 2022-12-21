
package net.codetojoy

class World {
    def tail
    def head
    def knotMap = [:]

    def World() {
        tail = new Tail(new Auditor())
        def knot8 = new Knot(tail)
        def knot7 = new Knot(knot8)
        def knot6 = new Knot(knot7)
        def knot5 = new Knot(knot6)
        def knot4 = new Knot(knot5)
        def knot3 = new Knot(knot4)
        def knot2 = new Knot(knot3)
        def knot1 = new Knot(knot2)
        head = new Head(knot1)

        knotMap['knot1'] = knot1
        knotMap['knot2'] = knot2
        knotMap['knot3'] = knot3
        knotMap['knot4'] = knot4
        knotMap['knot5'] = knot5
        knotMap['knot6'] = knot6
        knotMap['knot7'] = knot7
        knotMap['knot8'] = knot8
    }

    def apply(Direction direction) {
        head.apply(direction)
    }

    def apply(List<Direction> directions) {
        directions.each { apply(it) }
    }

    def getNumTailPositions() {
        // add initial state as a position
        tail.location.moveListener.getNumMoves() + 1
    }

    def logKnots(banner) {
        def keys = knotMap.sort()*.key
        println "TRACER --------- ${banner} ----------"
        keys.each { key ->
            def knot = knotMap[key]
            println "TRACER ${key}"
            knot.logPoints()
        }
    }

    String toString() {
        "head: ${head} tail: ${tail}"
    }
}
