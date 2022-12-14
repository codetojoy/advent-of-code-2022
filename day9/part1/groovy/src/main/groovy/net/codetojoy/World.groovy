
package net.codetojoy

class World {
    def tail = new Tail(new Auditor())
    def head = new Head(tail)

    def apply(Direction direction) {
        head.apply(direction)
    }

    def apply(List<Direction> directions) {
        directions.each { apply(it) }
    }

    def getNumTailPositions() {
        tail.location.moveListener.getNumMoves()
    }

    String toString() {
        "head: ${head} tail: ${tail}"
    }
}
