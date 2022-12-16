
package net.codetojoy

class Point {
    final def x
    final def y

    Point(x, y) {
        this.x = x
        this.y = y
    }

    @Override
    String toString() {
        "x: ${x} y: ${y}"
    }
}
