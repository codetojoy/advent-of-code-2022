
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

// * test for the small example used in the problem description
// * this is an integration test, not really a unit test, but IMHO fits
//   in between a unit test and running on the full input
//
class RunnerTestCase {
    def runner = new Runner()

    def assertPoint(knotName, x, y) {
        if (knotName == "head") {
            assert x == runner.world.head.location.point.x
            assert y == runner.world.head.location.point.y
        } else if (knotName == "tail") {
            assert x == runner.world.tail.location.point.x
            assert y == runner.world.tail.location.point.y
        } else {
            assert x == runner.world.knotMap[knotName].location.point.x
            assert y == runner.world.knotMap[knotName].location.point.y
        }
    }

    def assertHome(String... knotNames) {
        knotNames.each { knotName -> assertPoint(knotName, 0, 0) }
    }

    @Test
    void testRun_integration_case1() {
        def lines = []
        lines << 'R 5'

        // test
        def result = runner.run(lines)

        assertPoint "head", 5, 0
        assertPoint "knot1", 4, 0
        assertPoint "knot2", 3, 0
        assertPoint "knot3", 2, 0
        assertPoint "knot4", 1, 0
        assertHome "knot5", "knot6", "knot7", "knot8", "tail"

        assert 1 == runner.world.getNumTailPositions()
    }

    @Test
    void testRun_integration_case2() {
        def lines = []
        lines << 'R 5'
        lines << 'U 8'
        /*
        lines << 'L 8'
        lines << 'D 3'
        lines << 'R 17'
        lines << 'D 10'
        lines << 'L 25'
        lines << 'U 20'
        */

        // test
        def result = runner.run(lines)

        assertPoint "head", 5, 8
        assertPoint "knot1", 5, 7
        assertPoint "knot2", 5, 6
        assertPoint "knot3", 5, 5
        assertPoint "knot4", 5, 4
        assertPoint "knot5", 4, 4
        assertPoint "knot6", 3, 3
        assertPoint "knot7", 2, 2
        assertPoint "knot8", 1, 1
        assertHome "tail"

        assert 1 == runner.world.getNumTailPositions()
    }

    @Test
    void testRun_integration_case3() {
        def lines = []
        lines << 'R 5'
        lines << 'U 8'
        lines << 'L 8'
        /*
        lines << 'D 3'
        lines << 'R 17'
        lines << 'D 10'
        lines << 'L 25'
        lines << 'U 20'
        */

        // test
        def result = runner.run(lines)

        assertPoint "head",  -3, 8
        assertPoint "knot1", -2, 8
        assertPoint "knot2", -1, 8
        assertPoint "knot3", 0, 8
        assertPoint "knot4", 1, 8
        assertPoint "knot5", 1, 7
        assertPoint "knot6", 1, 6
        assertPoint "knot7", 1, 5
        assertPoint "knot8", 1, 4
        assertPoint "tail", 1, 3

        assert 4 == runner.world.getNumTailPositions()
    }

    @Test
    void testRun_integration_case4() {
        def lines = []
        lines << 'R 5'
        lines << 'U 8'
        lines << 'L 8'
        lines << 'D 3'
        /*
        lines << 'R 17'
        lines << 'D 10'
        lines << 'L 25'
        lines << 'U 20'
        */

        // test
        def result = runner.run(lines)

        assertPoint "head",  -3, 5
        assertPoint "knot1", -3, 6
        assertPoint "knot2", -2, 7
        assertPoint "knot3", -1, 7
        assertPoint "knot4", 0, 7
        assertPoint "knot5", 1, 7
        assertPoint "knot6", 1, 6
        assertPoint "knot7", 1, 5
        assertPoint "knot8", 1, 4
        assertPoint "tail", 1, 3

        assert 4 == runner.world.getNumTailPositions()
    }

    @Test
    void testRun_integration_case5() {
        def lines = []
        lines << 'R 5'
        lines << 'U 8'
        lines << 'L 8'
        lines << 'D 3'
        lines << 'R 17'
        /*
        lines << 'D 10'
        lines << 'L 25'
        lines << 'U 20'
        */

        // test
        def result = runner.run(lines)

        assertPoint "head",  14, 5
        assertPoint "knot1", 13, 5
        assertPoint "knot2", 12, 5
        assertPoint "knot3", 11, 5
        assertPoint "knot4", 10, 5
        assertPoint "knot5",  9, 5
        assertPoint "knot6",  8, 5
        assertPoint "knot7",  7, 5
        assertPoint "knot8",  6, 5
        assertPoint "tail",   5, 5

        assert 8 == runner.world.getNumTailPositions()
    }

    @Test
    void testRun_integration_case6() {
        def lines = []
        lines << 'R 5'
        lines << 'U 8'
        lines << 'L 8'
        lines << 'D 3'
        lines << 'R 17'
        lines << 'D 10'
        /*
        lines << 'L 25'
        lines << 'U 20'
        */

        // test
        def result = runner.run(lines)

        assertPoint "head",  14, -5
        assertPoint "knot1", 14, -4
        assertPoint "knot2", 14, -3
        assertPoint "knot3", 14, -2
        assertPoint "knot4", 14, -1
        assertPoint "knot5", 14,  0
        assertPoint "knot6", 13, 0
        assertPoint "knot7", 12, 0
        assertPoint "knot8", 11, 0
        assertPoint "tail",  10, 0

        assert 13 == runner.world.getNumTailPositions()
    }

    @Test
    void testRun_integration_case7() {
        def lines = []
        lines << 'R 5'
        lines << 'U 8'
        lines << 'L 8'
        lines << 'D 3'
        lines << 'R 17'
        lines << 'D 10'
        lines << 'L 25'
        /*
        lines << 'U 20'
        */

        // test
        def result = runner.run(lines)

        assertPoint "head",  -11, -5
        assertPoint "knot1", -10, -5
        assertPoint "knot2", -9, -5
        assertPoint "knot3", -8, -5
        assertPoint "knot4", -7, -5
        assertPoint "knot5", -6, -5
        assertPoint "knot6", -5, -5
        assertPoint "knot7", -4, -5
        assertPoint "knot8", -3, -5
        assertPoint "tail",  -2, -5

        assert 25 == runner.world.getNumTailPositions()
    }

    @Test
    void testRun_integration_case8() {
        def lines = []
        lines << 'R 5'
        lines << 'U 8'
        lines << 'L 8'
        lines << 'D 3'
        lines << 'R 17'
        lines << 'D 10'
        lines << 'L 25'
        lines << 'U 20'

        // test
        def result = runner.run(lines)

        assertPoint "head",  -11, 15
        assertPoint "knot1", -11, 14
        assertPoint "knot2", -11, 13
        assertPoint "knot3", -11, 12
        assertPoint "knot4", -11, 11
        assertPoint "knot5", -11, 10
        assertPoint "knot6", -11, 9
        assertPoint "knot7", -11, 8
        assertPoint "knot8", -11, 7
        assertPoint "tail",  -11, 6

        assert 36 == runner.world.getNumTailPositions()
    }
}