
package net.codetojoy

class Location {
    def point = new Point(0, 0)
    def moveListener

    def Location(moveListener) {
        this.moveListener = moveListener
        this.moveListener.track(point)
    }

    String toString() {
        "point: ${point}"
    }

    def move(x, y) {
        point = new Point(x, y)
        moveListener.track(point)
    }

    def moveLeft(value) {
        value.times {
            move(point.x - 1, point.y)
        }
    }

    def moveRight(value) {
        value.times {
            move(point.x + 1, point.y)
        }
    }

    def moveUp(value) {
        value.times {
            move(point.x, point.y + 1)
        }
    }

    def moveDown(value) {
        value.times {
            move(point.x, point.y - 1)
        }
    }
}
