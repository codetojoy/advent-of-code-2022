
package net.codetojoy

enum DirectionType {
    LEFT,
    RIGHT,
    UP,
    DOWN
}

class Direction {
    final def type
    final def value

    Direction(def type, def value) {
        this.type = type
        this.value = value
    }

    String toString() {
        "type: ${type} value: ${value}"
    }
}
