
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class GridTestCase {
    def n = 5
    def grid = new Grid(5)

    @Test
    void testSetGet_basic() {
        grid.set(0, 0, 'a')

        // test
        def result = grid.get(0, 0)

        assertEquals 'a', result
    }

    @Test
    void testSetRow_basic() {
        // test
        grid.setRow(1, [1,2,3,4,5])

        assertEquals 1, grid.get(1,0)
        assertEquals 2, grid.get(1,1)
        assertEquals 3, grid.get(1,2)
        assertEquals 4, grid.get(1,3)
        assertEquals 5, grid.get(1,4)
    }

    def buildKey(row, col, isEdge) {
        return new Point(row: row, col: col, isEdge: true).toString()
    }

    @Test
    void testGetScenicScore_case_1_up() {
        grid.setRow(0, [1,1,1,1,1])
        grid.setRow(1, [1,2,0,0,1])
        grid.setRow(2, [1,0,2,0,1])
        grid.setRow(3, [1,0,0,2,1])
        grid.setRow(4, [1,1,1,1,1])

        def point = new Point(row: 1, col: 1, isEdge: false)

        // test
        def result = grid.getScenicScore(point, Direction.UP)

        assert 1 == result
    }

    @Test
    void testGetScenicScore_case_1_down() {
        grid.setRow(0, [1,1,1,1,1])
        grid.setRow(1, [1,2,0,0,1])
        grid.setRow(2, [1,0,2,0,1])
        grid.setRow(3, [1,0,0,2,1])
        grid.setRow(4, [1,1,1,1,1])

        def point = new Point(row: 1, col: 1, isEdge: false)

        // test
        def result = grid.getScenicScore(point, Direction.DOWN)

        assert 3 == result
    }

    @Test
    void testGetScenicScore_case_1_left() {
        grid.setRow(0, [1,1,1,1,1])
        grid.setRow(1, [1,2,0,0,1])
        grid.setRow(2, [1,0,2,0,1])
        grid.setRow(3, [1,0,0,2,1])
        grid.setRow(4, [1,1,1,1,1])

        def point = new Point(row: 1, col: 1, isEdge: false)

        // test
        def result = grid.getScenicScore(point, Direction.LEFT)

        assert 1 == result
    }

    @Test
    void testGetScenicScore_case_1_right() {
        grid.setRow(0, [1,1,1,1,1])
        grid.setRow(1, [1,2,0,0,1])
        grid.setRow(2, [1,0,2,0,1])
        grid.setRow(3, [1,0,0,2,1])
        grid.setRow(4, [1,1,1,1,1])

        def point = new Point(row: 1, col: 1, isEdge: false)

        // test
        def result = grid.getScenicScore(point, Direction.RIGHT)

        assert 3 == result
    }

    // TODO: parameterized test
    @Test
    void testBuildScenicMap_full() {
        grid.setRow(0, [1,1,1,1,1])
        grid.setRow(1, [1,2,0,0,1])
        grid.setRow(2, [1,0,2,0,1])
        grid.setRow(3, [1,0,0,2,1])
        grid.setRow(4, [1,1,1,1,1])

        // test
        def result = grid.buildScenicMap()

        assert 0 == result[buildKey(0, 0, true)]
        assert 0 == result[buildKey(0, 4, true)]
        assert 0 == result[buildKey(4, 0, true)]
        assert 0 == result[buildKey(4, 4, true)]

        assert 9 == result[buildKey(1, 1, true)]
        assert 16 == result[buildKey(2, 2, true)]
        assert 9 == result[buildKey(3, 3, true)]
    }

    @Test
    void testGetScenicScore_problem_desc_down() {
        grid.setRow(0, [3,0,3,7,3])
        grid.setRow(1, [2,5,5,1,2])
        grid.setRow(2, [6,5,3,3,2])
        grid.setRow(3, [3,3,5,4,9])
        grid.setRow(4, [3,5,3,9,0])

        def point = new Point(row: 3, col: 2, isEdge: false)
        def direction = Direction.DOWN

        // test
        def result = grid.getScenicScore(point, direction)

        assert 1 == result
    }

    @Test
    void testGetScenicScore_problem_desc_up() {
        grid.setRow(0, [3,0,3,7,3])
        grid.setRow(1, [2,5,5,1,2])
        grid.setRow(2, [6,5,3,3,2])
        grid.setRow(3, [3,3,5,4,9])
        grid.setRow(4, [3,5,3,9,0])

        def point = new Point(row: 3, col: 2, isEdge: false)
        def direction = Direction.UP

        // test
        def result = grid.getScenicScore(point, direction)

        assert 2 == result
    }

    @Test
    void testGetScenicScore_problem_desc_right() {
        grid.setRow(0, [3,0,3,7,3])
        grid.setRow(1, [2,5,5,1,2])
        grid.setRow(2, [6,5,3,3,2])
        grid.setRow(3, [3,3,5,4,9])
        grid.setRow(4, [3,5,3,9,0])

        def point = new Point(row: 3, col: 2, isEdge: false)
        def direction = Direction.RIGHT

        // test
        def result = grid.getScenicScore(point, direction)

        assert 2 == result
    }

    @Test
    void testGetScenicScore_problem_desc_left() {
        grid.setRow(0, [3,0,3,7,3])
        grid.setRow(1, [2,5,5,1,2])
        grid.setRow(2, [6,5,3,3,2])
        grid.setRow(3, [3,3,5,4,9])
        grid.setRow(4, [3,5,3,9,0])

        def point = new Point(row: 3, col: 2, isEdge: false)
        def direction = Direction.LEFT

        // test
        def result = grid.getScenicScore(point, direction)

        assert 2 == result
    }

    @Test
    void testFindHighestScenicScore_from_problem_desc() {
        grid.setRow(0, [3,0,3,7,3])
        grid.setRow(1, [2,5,5,1,2])
        grid.setRow(2, [6,5,3,3,2])
        grid.setRow(3, [3,3,5,4,9])
        grid.setRow(4, [3,5,3,9,0])

        // test
        def result = grid.buildScenicMap()

        assert 8 == result[buildKey(3, 2, true)]
    }

    @Test
    void testFindHighestScenicScore_basic() {
        grid.setRow(0, [1,1,1,1,1])
        grid.setRow(1, [1,2,0,0,1])
        grid.setRow(2, [1,0,2,0,1])
        grid.setRow(3, [1,0,0,2,1])
        grid.setRow(4, [1,1,1,1,1])

        // test
        def result = grid.findHighestScenicScore()

        assert 16 == result.score
    }
}
