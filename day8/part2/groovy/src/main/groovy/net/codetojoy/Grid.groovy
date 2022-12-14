
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

    def getScenicScore(point, direction) {
        def points = Points.getPoints(point, direction, n)
        def value = get(point)
        def isDone = false
        def viewingPoints = points.takeWhile { p ->
            def thisValue = get(p)
            def result = false
            if (!isDone) {
                result = true
                isDone = (thisValue >= value)
            }
            result
        }
        viewingPoints.size()
    }

    def getScenicScore(point) {
        (getScenicScore(point, Direction.RIGHT) * getScenicScore(point, Direction.LEFT)
        * getScenicScore(point, Direction.UP) * getScenicScore(point, Direction.DOWN))
    }

    def buildScenicMap() {
        def scenicMap = [:]

        def points = Points.getAllPoints(n)

        points.each { point ->
            def value = -1

            if (Points.isEdge(n, point)) {
                value = 0
            } else {
                value = getScenicScore(point)
            }
            scenicMap[point.toString()] = value
        }

        scenicMap
    }

    def findHighestScenicScore() {
        def scenicMap = buildScenicMap()
        def resultMap = scenicMap.sort { a, b -> b.value <=> a.value }.take(1)
        assert 1 == resultMap.keySet().size()
        def result = new Expando()
        result.key = (resultMap.keySet() as List)[0]
        result.score = resultMap[result.key]
        result
    }
}
