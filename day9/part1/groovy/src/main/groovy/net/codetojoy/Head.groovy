
package net.codetojoy

class Head {
    def location = new Point(0, 0)
    def moveListener

    def Head(moveListener) {
        this.moveListener = moveListener
        this.moveListener.track(location)
    }

    String toString() {
        "location: ${location}"
    }

    def move(x, y) {
        location = new Point(x, y)
        moveListener.track(location)
    }

    def moveLeft(value) {
        value.times {
            move(location.x - 1, location.y)
        }
    }

    def moveRight(value) {
        value.times {
            move(location.x + 1, location.y)
        }
    }

    def moveUp(value) {
        value.times {
            move(location.x, location.y + 1)
        }
    }

    def moveDown(value) {
        value.times {
            move(location.x, location.y - 1)
        }
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
            case DirectionType.UP:
                moveUp(value)
                break
            case DirectionType.DOWN:
                moveDown(value)
                break
        }
    }
}
