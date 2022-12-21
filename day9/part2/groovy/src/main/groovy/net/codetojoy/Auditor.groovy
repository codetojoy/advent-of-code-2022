
package net.codetojoy

class Auditor implements MoveListener {
    def moves = []

    @Override
    void track(Point point) {
        moves << point.toString()
    }

    @Override
    int getNumMoves() {
        (moves as Set).size()
    }

    def logPoints() {
        if (moves) {
            println "{"
            moves.each { move ->
                println "    " + move
            }
            println "}"
        }
    }
}
