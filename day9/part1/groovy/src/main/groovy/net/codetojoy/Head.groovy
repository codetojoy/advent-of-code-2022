
package net.codetojoy

class Head {
    def location

    def Head(moveListener) {
        this.location = new Location(moveListener)
    }

    String toString() {
        "location: ${location}"
    }

    def apply(direction) {
        def type = direction.type
        def value = direction.value

        switch (type) {
            case DirectionType.LEFT:
                location.moveLeft(value)
                break
            case DirectionType.RIGHT:
                location.moveRight(value)
                break
            case DirectionType.UP:
                location.moveUp(value)
                break
            case DirectionType.DOWN:
                location.moveDown(value)
                break
        }
    }
}
