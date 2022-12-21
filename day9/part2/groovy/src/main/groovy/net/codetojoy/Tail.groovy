
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

/*
class Tail implements MoveListener {
    def location

    def Tail(moveListener) {
        this.location = new Location(moveListener)
    }

    String toString() {
        "location: ${location}"
    }

    @Override
    void track(Point point) {
        def deltaX = point.x - location.point.x
        def deltaY = point.y - location.point.y
        // println("TRACER Tail new: " + point.toString() + " this: "
        //            + location.toString() + " dX: " + deltaX + " dY: " + deltaY)

        if (deltaX == -2 && deltaY == 0) {
            location.moveLeft(1)
        } else if (deltaX == 2 && deltaY == 0) {
            location.moveRight(1)
        } else if (deltaX == 0 && deltaY == 2) {
            location.moveUp(1)
        } else if (deltaX == 0 && deltaY == -2) {
            location.moveDown(1)
        } else if ((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)) {
            // 1 right, 2 up
            // 2 right, 1 up
            location.moveBy(1, 1)
        } else if ((deltaX == 1 && deltaY == -2) || (deltaX == 2 && deltaY == -1)) {
            // 1 right, 2 down
            // 2 right, 1 down
            location.moveBy(1, -1)
        } else if ((deltaX == -1 && deltaY == 2) || (deltaX == -2 && deltaY == 1)) {
            // 1 left, 2 up
            // 2 left, 1 up
            location.moveBy(-1, 1)
        } else if ((deltaX == -1 && deltaY == -2) || (deltaX == -2 && deltaY == -1)) {
            // 1 left, 2 down
            // 2 left, 1 down
            location.moveBy(-1, -1)
        } else {
            // no-op
        }
    }

    @Override
    int getNumMoves() {
        location.moveListener.getNumMoves()
    }
}
*/