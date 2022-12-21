
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class KnotTestCase {
    def knot

    @Before
    void setUp() {
        knot = new Knot(new Auditor())
    }

    def p(x, y) {
        new Point(x, y)
    }

    @Test
    void testAreTouching_case_right_column_1() {
        // test
        assertTrue knot.areTouching(p(1,0))
    }

    @Test
    void testAreTouching_case_right_column_2() {
        // test
        assertTrue knot.areTouching(p(1,-1))
    }

    @Test
    void testAreTouching_case_right_column_3() {
        // test
        assertTrue knot.areTouching(p(1,1))
    }

    @Test
    void testAreTouching_case_right_column_4_counter() {
        // test
        assertFalse knot.areTouching(p(1,2))
    }

    @Test
    void testAreTouching_case_left_column_1() {
        // test
        assertTrue knot.areTouching(p(1,0))
    }

    @Test
    void testAreTouching_case_left_column_2() {
        // test
        assertTrue knot.areTouching(p(-1,-1))
    }

    @Test
    void testAreTouching_case_left_column_3() {
        // test
        assertTrue knot.areTouching(p(-1,1))
    }

    @Test
    void testAreTouching_case_left_column_4_counter() {
        // test
        assertFalse knot.areTouching(p(-1,-2))
    }

    @Test
    void testAreTouching_case_same_column_1() {
        // test
        assertTrue knot.areTouching(p(0,1))
    }

    @Test
    void testAreTouching_case_same_column_2() {
        // test
        assertTrue knot.areTouching(p(0,0))
    }

    @Test
    void testAreTouching_case_same_column_3() {
        // test
        assertTrue knot.areTouching(p(0,-1))
    }

    @Test
    void testAreTouching_case_same_column_4_counter() {
        // test
        assertFalse knot.areTouching(p(0,-2))
    }

    @Test
    void testTrack_one_left() {
        // test
        knot.track(p(-1,0))

        assert 0 == knot.location.point.x
        assert 0 == knot.location.point.y
        assert 0 == knot.getNumMoves()
    }

    @Test
    void testTrack_one_right() {
        // test
        knot.track(p(1,0))

        assert 0 == knot.location.point.x
        assert 0 == knot.location.point.y
        assert 0 == knot.getNumMoves()
    }

    @Test
    void testTrack_one_up() {
        // test
        knot.track(p(0,1))

        assert 0 == knot.location.point.x
        assert 0 == knot.location.point.y
        assert 0 == knot.getNumMoves()
    }

    @Test
    void testTrack_one_down() {
        // test
        knot.track(p(0,-1))

        assert 0 == knot.location.point.x
        assert 0 == knot.location.point.y
        assert 0 == knot.getNumMoves()
    }

    @Test
    void testTrack_two_left() {
        // test
        knot.track(p(-1,0))
        knot.track(p(-2,0))

        assert -1 == knot.location.point.x
        assert 0 == knot.location.point.y
        assert 1 == knot.getNumMoves()
    }

    @Test
    void testTrack_two_right() {
        // test
        knot.track(p(1,0))
        knot.track(p(2,0))

        assert 1 == knot.location.point.x
        assert 0 == knot.location.point.y
        assert 1 == knot.getNumMoves()
    }

    @Test
    void testTrack_two_up() {
        // test
        knot.track(p(0,1))
        knot.track(p(0,2))

        assert 0 == knot.location.point.x
        assert 1 == knot.location.point.y
        assert 1 == knot.getNumMoves()
    }

    @Test
    void testTrack_two_down() {
        // test
        knot.track(p(0,-1))
        knot.track(p(0,-2))

        assert 0 == knot.location.point.x
        assert -1 == knot.location.point.y
        assert 1 == knot.getNumMoves()
    }

    // ..        .H
    // .H  ->    .T
    // T.        ..

    @Test
    void testTrack_diagonal_quadrant1() {
        // test
        knot.track(p(1,0))
        knot.track(p(1,1))
        knot.track(p(1,2))

        assert 1 == knot.location.point.x
        assert 1 == knot.location.point.y
        assert 1 == knot.getNumMoves()
    }

    // ..        ..
    // .H  ->    .TH
    // T.        ..

    @Test
    void testTrack_diagonal_quadrant1b() {
        // test
        knot.track(p(1,0))
        knot.track(p(1,1))
        knot.track(p(2,1))

        assert 1 == knot.location.point.x
        assert 1 == knot.location.point.y
        assert 1 == knot.getNumMoves()
    }

    // .T        ..
    // H.  ->    T.
    // ..        H.

    @Test
    void testTrack_diagonal_quadrant3a() {
        // test
        knot.track(p(-1,0))
        knot.track(p(-1,-1))
        knot.track(p(-1,-2))

        assert -1 == knot.location.point.x
        assert -1 == knot.location.point.y
        assert 1 == knot.getNumMoves()
    }

    // .T        ..
    // H.  ->   HT.
    // ..       ...

    @Test
    void testTrack_diagonal_quadrant3b() {
        // test
        knot.track(p(-1,0))
        knot.track(p(-1,-1))
        knot.track(p(-2,-1))

        assert -1 == knot.location.point.x
        assert -1 == knot.location.point.y
        assert 1 == knot.getNumMoves()
    }

    // H.        H.
    // .T  ->    T.
    // ..        ..

    @Test
    void testTrack_diagonal_quadrant4a() {
        // test
        knot.track(p(-1,0))
        knot.track(p(-1,1))
        knot.track(p(-1,2))

        assert -1 == knot.location.point.x
        assert 1 == knot.location.point.y
        assert 1 == knot.getNumMoves()
    }

    // H.       HT.
    // .T  ->    ..
    // ..        ..

    @Test
    void testTrack_diagonal_quadrant4b() {
        // test
        knot.track(p(-1,0))
        knot.track(p(-1,1))
        knot.track(p(-2,1))

        assert -1 == knot.location.point.x
        assert 1 == knot.location.point.y
        assert 1 == knot.getNumMoves()
    }

    // T.        ..
    // .H  ->    .TH
    // ..        ..

    @Test
    void testTrack_diagonal_quadrant2b() {
        // test
        knot.track(p(1,0))
        knot.track(p(1,-1))
        knot.track(p(2,-1))

        assert 1 == knot.location.point.x
        assert -1 == knot.location.point.y
        assert 1 == knot.getNumMoves()
    }

    // T.        ..
    // .H  ->    .T
    // ..        .H

    @Test
    void testTrack_diagonal_quadrant2a() {
        // test
        knot.track(p(1,0))
        knot.track(p(1,-1))
        knot.track(p(1,-2))

        assert 1 == knot.location.point.x
        assert -1 == knot.location.point.y
        assert 1 == knot.getNumMoves()
    }

    /*
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
        assert 1 == tail.getNumMoves()
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
        assert 1 == tail.getNumMoves()
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
        assert 1 == tail.getNumMoves()
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
        assert 1 == tail.getNumMoves()
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
        assert 1 == tail.getNumMoves()
    }
    */
}