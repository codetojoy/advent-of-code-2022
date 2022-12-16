
package net.codetojoy

class Auditor implements MoveListener {
    def moves = new HashSet<String>()

    @Override
    void track(Point point) {
        moves << point.toString()
    }

    @Override
    int getNumMoves() {
        moves.size()
    }
}
