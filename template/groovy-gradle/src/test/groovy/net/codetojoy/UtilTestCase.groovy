
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class UtilTestCase {
    def util = new Util()

    @Test
    void testIsConfigLine_yes() {
        def line = '                [M]     [W] [M]    '

        // test
        def result = util.isConfigLine(line)

        assertTrue result
    }

    @Test
    void testIsConfigLine_no() {
        def line = ' 1   2   3   4   5   6   7   8   9'

        // test
        def result = util.isConfigLine(line)

        assertFalse result
    }
}
