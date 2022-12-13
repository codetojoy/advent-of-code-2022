
package net.codetojoy

class Grid {
    def n
    def grid

    Grid(n) {
        this.n = n
        grid = []
        n.times { grid << new ArrayList(n) }
    }

    def get(row, col) {
        Points.assertSafe(n, row, col)
        return grid[row][col]
    }

    def get(point) {
        get(point.row, point.col)
    }

    def set(row, col, value) {
        Points.assertSafe(n, row, col)
        grid[row][col] = value
    }

    def setRow(row, values) {
        assert row >= 0 && row <= n - 1
        assert values.size() == n
        values.eachWithIndex { value, index -> set(row, index, value as int) }
    }

    def isVisible(point, direction) {
        def maxPoint = Points.getPoints(point, direction, n).max { get(it) }
        get(maxPoint) < get(point)
    }

    def isVisible(point) {
        (isVisible(point, Direction.RIGHT) || isVisible(point, Direction.LEFT)
        || isVisible(point, Direction.UP) || isVisible(point, Direction.DOWN))
    }

    def countVisible() {
        def count = 0

        def points = Points.getAllPoints(n)

        points.each { point ->
            if (Points.isEdge(n, point) || isVisible(point)) {
                count++
            }
        }

        count
    }
}
