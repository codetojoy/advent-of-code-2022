
package net.codetojoy

class Tail implements MoveListener {
    def location = new Point(0, 0)
    def moveListener

    def Tail(moveListener) {
        this.moveListener = moveListener
        // initial position counts as a move:
        moveListener.track(location)
    }

    String toString() {
        "location: ${location}"
    }

    @Override
    void track(Point point) {
        def deltaX = point.x - location.x
        def deltaY = point.y - location.y
        println("TRACER Tail new: " + point.toString() + " this: "
                    + location.toString() + " dX: " + deltaX + " dY: " + deltaY)

        if (deltaX == -2 && deltaY == 0) {
            moveLeft(1)
        } else if (deltaX == 2 && deltaY == 0) {
            moveRight(1)
        } else if (deltaX == 0 && deltaY == 2) {
            moveUp(1)
        } else if (deltaX == 0 && deltaY == -2) {
            moveDown(1)
        } else if ((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)) {
            // 1 right, 2 up
            // 2 right, 1 up
            move(location.x + 1, location.y + 1)
        } else if ((deltaX == 1 && deltaY == -2) || (deltaX == 2 && deltaY == -1)) {
            // 1 right, 2 down
            // 2 right, 1 down
            move(location.x + 1, location.y - 1)
        } else if ((deltaX == -1 && deltaY == 2) || (deltaX == -2 && deltaY == 1)) {
            // 1 left, 2 up
            // 2 left, 1 up
            move(location.x - 1, location.y + 1)
        } else if ((deltaX == -1 && deltaY == -2) || (deltaX == -2 && deltaY == -1)) {
            // 1 left, 2 down
            // 2 left, 1 down
            move(location.x - 1, location.y - 1)
        } else {
            println "TRACER Tail no-op"
            // no-op
        }
    }

    @Override
    int getNumMoves() {
        moveListener.getNumMoves()
    }

    def move(x, y) {
        location = new Point(x, y)
        moveListener.track(location)
    }

    def moveLeft(value) {
        move(location.x - value, location.y)
    }

    def moveRight(value) {
        move(location.x + value, location.y)
    }

    def moveUp(value) {
        move(location.x, location.y + value)
    }

    def moveDown(value) {
        move(location.x, location.y - value)
    }
}
