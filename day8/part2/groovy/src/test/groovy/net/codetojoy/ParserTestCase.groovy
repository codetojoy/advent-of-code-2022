
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class ParserTestCase {
    def parser = new Parser()

    @Test
    void testParse_basic() {
        def lines = []
        lines << '1234'
        lines << '5678'
        lines << '0123'
        lines << '3210'

        // test
        def grid = parser.parse(lines)

        assert 4 == grid.n
        assert 1 == grid.get(0, 0)
        assert 0 == grid.get(3, 3)
    }
}
