
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class TailTestCase {
    def tail

    @Before
    void setUp() {
        tail = new Tail(new Auditor())
    }

    def p(x, y) {
        new Point(x, y)
    }

    @Test
    void testTrack_one_left() {
        // test
        tail.track(p(-1,0))

        assert 0 == tail.location.point.x
        assert 0 == tail.location.point.y
        assert 1 == tail.getNumMoves()
    }

    @Test
    void testTrack_one_right() {
        // test
        tail.track(p(1,0))

        assert 0 == tail.location.point.x
        assert 0 == tail.location.point.y
        assert 1 == tail.getNumMoves()
    }

    @Test
    void testTrack_one_up() {
        // test
        tail.track(p(0,1))

        assert 0 == tail.location.point.x
        assert 0 == tail.location.point.y
        assert 1 == tail.getNumMoves()
    }

    @Test
    void testTrack_one_down() {
        // test
        tail.track(p(0,-1))

        assert 0 == tail.location.point.x
        assert 0 == tail.location.point.y
        assert 1 == tail.getNumMoves()
    }

    @Test
    void testTrack_two_left() {
        // test
        tail.track(p(-1,0))
        tail.track(p(-2,0))

        assert -1 == tail.location.point.x
        assert 0 == tail.location.point.y
        assert 2 == tail.getNumMoves()
    }

    @Test
    void testTrack_two_right() {
        // test
        tail.track(p(1,0))
        tail.track(p(2,0))

        assert 1 == tail.location.point.x
        assert 0 == tail.location.point.y
        assert 2 == tail.getNumMoves()
    }

    @Test
    void testTrack_two_up() {
        // test
        tail.track(p(0,1))
        tail.track(p(0,2))

        assert 0 == tail.location.point.x
        assert 1 == tail.location.point.y
        assert 2 == tail.getNumMoves()
    }

    @Test
    void testTrack_two_down() {
        // test
        tail.track(p(0,-1))
        tail.track(p(0,-2))

        assert 0 == tail.location.point.x
        assert -1 == tail.location.point.y
        assert 2 == tail.getNumMoves()
    }

    // ..        .H
    // .H  ->    .T
    // T.        ..

    @Test
    void testTrack_diagonal_case1() {
        // test
        tail.track(p(1,0))
        tail.track(p(1,1))
        tail.track(p(1,2))

        assert 1 == tail.location.point.x
        assert 1 == tail.location.point.y
        assert 2 == tail.getNumMoves()
    }

    // ..        ..
    // .H  ->    .TH
    // T.        ..

    @Test
    void testTrack_diagonal_case1b() {
        // test
        tail.track(p(1,0))
        tail.track(p(1,1))
        tail.track(p(2,1))

        assert 1 == tail.location.point.x
        assert 1 == tail.location.point.y
        assert 2 == tail.getNumMoves()
    }

    // T.        ..
    // .H  ->    .T
    // ..        .H

    @Test
    void testTrack_diagonal_case2() {
        // test
        tail.track(p(1,0))
        tail.track(p(1,-1))
        tail.track(p(1,-2))

        assert 1 == tail.location.point.x
        assert -1 == tail.location.point.y
        assert 2 == tail.getNumMoves()
    }

    // T.        ..
    // .H  ->    .TH
    // ..        ..

    @Test
    void testTrack_diagonal_case2b() {
        // test
        tail.track(p(1,0))
        tail.track(p(1,-1))
        tail.track(p(2,-1))

        assert 1 == tail.location.point.x
        assert -1 == tail.location.point.y
        assert 2 == tail.getNumMoves()
    }

    // ..        H.
    // H.  ->    T.
    // .T        ..

    @Test
    void testTrack_diagonal_case3() {
        // test
        tail.track(p(-1,0))
        tail.track(p(-1,1))
        tail.track(p(-1,2))

        assert -1 == tail.location.point.x
        assert 1 == tail.location.point.y
        assert 2 == tail.getNumMoves()
    }

    // ..        ..
    // H.  ->   HT.
    // .T        ..

    @Test
    void testTrack_diagonal_case3b() {
        // test
        tail.track(p(-1,0))
        tail.track(p(-1,1))
        tail.track(p(-2,1))

        assert -1 == tail.location.point.x
        assert 1 == tail.location.point.y
        assert 2 == tail.getNumMoves()
    }

    // .T        ..
    // H.  ->    T.
    // ..        H.

    @Test
    void testTrack_diagonal_case4() {
        // test
        tail.track(p(-1,0))
        tail.track(p(-1,-1))
        tail.track(p(-1,-2))

        assert -1 == tail.location.point.x
        assert -1 == tail.location.point.y
        assert 2 == tail.getNumMoves()
    }

    // .T        ..
    // H.  ->   HT.
    // ..        H.

    @Test
    void testTrack_diagonal_case4b() {
        // test
        tail.track(p(-1,0))
        tail.track(p(-1,-1))
        tail.track(p(-2,-1))

        assert -1 == tail.location.point.x
        assert -1 == tail.location.point.y
        assert 2 == tail.getNumMoves()
    }
}