
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class ActorTestCase {
    def actor

    @Before
    void setUp() {
        actor = new Actor(new Auditor())
    }

    @Test
    void testMoveLeft_basic() {
        // test
        actor.moveLeft(10)

        assert -10 == actor.location.x
        assert 0 == actor.location.y
        // assert 2 == actor.moveListener.getNumMoves()
    }

    @Test
    void testMoveRight_basic() {
        // test
        actor.moveRight(20)

        assert 20 == actor.location.x
        assert 0 == actor.location.y
        // assert 2 == actor.moveListener.getNumMoves()
    }

    @Test
    void testMoveUp_basic() {
        // test
        actor.moveUp(30)

        assert 0 == actor.location.x
        assert 30 == actor.location.y
        // assert 2 == actor.moveListener.getNumMoves()
    }

    @Test
    void testMoveDown_basic() {
        // test
        actor.moveDown(40)

        assert 0 == actor.location.x
        assert -40 == actor.location.y
        // assert 2 == actor.moveListener.getNumMoves()
    }
}