
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

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

        // runner.world.logKnots("case1")

        assertPoint "head", 5, 0
        assertPoint "knot1", 4, 0
        assertPoint "knot2", 3, 0
        assertPoint "knot3", 2, 0
        assertPoint "knot4", 1, 0
        assertHome "knot5", "knot6", "knot7", "knot8", "tail"

        /*
        assert 4 == runner.world.knotMap['knot1'].getNumMoves()
        assert 3 == runner.world.knotMap['knot2'].getNumMoves()
        assert 2 == runner.world.knotMap['knot3'].getNumMoves()
        assert 1 == runner.world.knotMap['knot4'].getNumMoves()
        assert 0 == runner.world.knotMap['knot5'].getNumMoves()
        assert 0 == runner.world.knotMap['knot6'].getNumMoves()
        assert 0 == runner.world.knotMap['knot7'].getNumMoves()
        assert 0 == runner.world.knotMap['knot8'].getNumMoves()
        assert 1 == runner.world.getNumTailPositions()
        */
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

        runner.world.logKnots("case2")

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

        runner.world.logKnots("case3")

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
}