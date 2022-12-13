
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class PointsTestCase {
    def points = new Points()

    @Test
    void testGetAllPoints_basic() {
        def n = 4

        // test
        def points = Points.getAllPoints(n)

        assert 16 == points.size()
    }

    @Test
    void testGetPoints_right() {
        def n = 4
        def point = new Point(1, 1, false)

        // test
        def points = Points.getPoints(point, Direction.RIGHT, n)

        assert 2 == points.size()
        points.each { p -> assert 1 == p.row }
        points.each { p -> assert 1 < p.col }
    }

    @Test
    void testGetPoints_left() {
        def n = 4
        def point = new Point(1, 1, false)

        // test
        def points = Points.getPoints(point, Direction.LEFT, n)

        assert 1 == points.size()
        points.each { p -> assert 1 == p.row }
        points.each { p -> assert 1 > p.col }
    }

    @Test
    void testGetPoints_up() {
        def n = 4
        def point = new Point(1, 1, false)

        // test
        def points = Points.getPoints(point, Direction.UP, n)

        assert 1 == points.size()
        points.each { p -> assert 1 > p.row }
        points.each { p -> assert 1 == p.col }
    }

    @Test
    void testGetPoints_down() {
        def n = 4
        def point = new Point(1, 1, false)

        // test
        def points = Points.getPoints(point, Direction.DOWN, n)

        assert 2 == points.size()
        points.each { p -> assert 1 < p.row }
        points.each { p -> assert 1 == p.col }
    }
}
