
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class ParserTestCase {
    def parser = new Parser()

    @Test
    void testNoOp_basic() {
        def line = 'noop'

        // test
        def result = parser.parse(line)

        assertTrue result instanceof NoOp
    }

    @Test
    void testAddX_basic() {
        def line = 'addx 5150'

        // test
        def result = parser.parse(line)

        assertTrue result instanceof Add
        assertEquals 5150, result.V
    }

    @Test
    void testAddX_negative() {
        def line = 'addx -2120'

        // test
        def result = parser.parse(line)

        assertTrue result instanceof Add
        assertEquals -2120, result.V
    }
}
