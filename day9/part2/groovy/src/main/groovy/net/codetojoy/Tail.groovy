
package net.codetojoy

class Tail extends Knot {
    def Tail(moveListener, verbose = false) {
        super(moveListener, verbose)
    }

    @Override
    int getNumMoves() {
        location.moveListener.getNumMoves()
    }
}