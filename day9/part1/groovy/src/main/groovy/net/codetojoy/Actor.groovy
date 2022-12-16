
package net.codetojoy

class Actor {
    def location = new Point(0, 0)
    def moveListener

    def Actor(moveListener) {
        this.moveListener = moveListener
        moveListener.track(location)
    }

    String toString() {
        "location: ${location}" //  auditor-num: ${auditor.getNumMoves()}"
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

    def apply(direction) {
        def type = direction.type
        def value = direction.value

        switch (type) {
            case DirectionType.LEFT:
                moveLeft(value)
                break
            case DirectionType.RIGHT:
                moveRight(value)
                break
        }
    }
}
