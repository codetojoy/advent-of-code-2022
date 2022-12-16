
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class LocationTestCase {
    def location

    @Before
    void setUp() {
        location = new Location(new Auditor())
    }

    @Test
    void testMoveLeft_basic() {
        // test
        location.moveLeft(10)

        assert -10 == location.point.x
        assert 0 == location.point.y
        assert 11 == location.moveListener.getNumMoves()
    }

    @Test
    void testMoveRight_basic() {
        // test
        location.moveRight(20)

        assert 20 == location.point.x
        assert 0 == location.point.y
        assert 21 == location.moveListener.getNumMoves()
    }

    @Test
    void testMoveUp_basic() {
        // test
        location.moveUp(30)

        assert 0 == location.point.x
        assert 30 == location.point.y
        assert 31 == location.moveListener.getNumMoves()
    }

    @Test
    void testMoveDown_basic() {
        // test
        location.moveDown(40)

        assert 0 == location.point.x
        assert -40 == location.point.y
        assert 41 == location.moveListener.getNumMoves()
    }
}
