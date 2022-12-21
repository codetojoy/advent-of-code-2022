
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class ParserTestCase {
    def parser = new Parser()

    @Test
    void testParse_left() {
        def lines = []
        lines << 'L 11'

        // test
        def result = parser.parse(lines)

        assert 1 == result.size()
        assert DirectionType.LEFT == result[0].type
        assert 11 == result[0].value
    }

    @Test
    void testParse_right() {
        def lines = []
        lines << 'R 22'

        // test
        def result = parser.parse(lines)

        assert 1 == result.size()
        assert DirectionType.RIGHT == result[0].type
        assert 22 == result[0].value
    }

    @Test
    void testParse_up() {
        def lines = []
        lines << 'U 33'

        // test
        def result = parser.parse(lines)

        assert 1 == result.size()
        assert DirectionType.UP == result[0].type
        assert 33 == result[0].value
    }

    @Test
    void testParse_down() {
        def lines = []
        lines << 'D 44'

        // test
        def result = parser.parse(lines)

        assert 1 == result.size()
        assert DirectionType.DOWN == result[0].type
        assert 44 == result[0].value
    }
}
