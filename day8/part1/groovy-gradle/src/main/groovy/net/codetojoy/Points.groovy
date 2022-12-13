
package net.codetojoy

class Points {
    static def assertInRange(n, val) {
        assert val >= 0 && val <= n - 1
    }

    static def assertSafe(n, row, col) {
        assertInRange(n, row)
        assertInRange(n, col)
    }

    static def isEdge(n, row, col) {
        row == 0 || row == (n - 1) || col == 0 || col == (n - 1)
    }

    static def isEdge(n, point) {
        def row = point.row
        def col = point.col
        row == 0 || row == (n - 1) || col == 0 || col == (n - 1)
    }

    static def getAllPoints(n) {
        def points = []
        n.times { row ->
            n.times { col ->
                assertSafe(n, row, col)
                def isEdge = isEdge(n, row, col)
                points << new Point(row: row, col: col, isEdge: isEdge)
            }
        }
        points
    }

    static def getPoints(point, direction, n) {
        def points = []

        if (direction == Direction.RIGHT) {
            def i = point.row
            def j = point.col + 1
            while (j < n) {
                points << new Point(row: i, col: j, isEdge: isEdge(n, i, j))
                j++
            }
        } else if (direction == Direction.LEFT) {
            def i = point.row
            def j = point.col - 1
            while (j >= 0) {
                points << new Point(row: i, col: j, isEdge: isEdge(n, i, j))
                j--
            }
        } else if (direction == Direction.UP) {
            def i = point.row - 1
            def j = point.col
            while (i >= 0) {
                points << new Point(row: i, col: j, isEdge: isEdge(n, i, j))
                i--
            }
        } else if (direction == Direction.DOWN) {
            def i = point.row + 1
            def j = point.col
            while (i < n) {
                points << new Point(row: i, col: j, isEdge: isEdge(n, i, j))
                i++
            }
        }

        points
    }
}
