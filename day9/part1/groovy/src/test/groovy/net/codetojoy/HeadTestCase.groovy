
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class HeadTestCase {
    def head

    @Before
    void setUp() {
        head = new Head(new Auditor())
    }

    @Test
    void testMoveLeft_basic() {
        // test
        head.moveLeft(10)

        assert -10 == head.location.x
        assert 0 == head.location.y
        assert 11 == head.moveListener.getNumMoves()
    }

    @Test
    void testMoveRight_basic() {
        // test
        head.moveRight(20)

        assert 20 == head.location.x
        assert 0 == head.location.y
        assert 21 == head.moveListener.getNumMoves()
    }

    @Test
    void testMoveUp_basic() {
        // test
        head.moveUp(30)

        assert 0 == head.location.x
        assert 30 == head.location.y
        assert 31 == head.moveListener.getNumMoves()
    }

    @Test
    void testMoveDown_basic() {
        // test
        head.moveDown(40)

        assert 0 == head.location.x
        assert -40 == head.location.y
        assert 41 == head.moveListener.getNumMoves()
    }
}