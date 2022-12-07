
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class StacksBuilderTestCase {
    def builder = new StacksBuilder()

    @Test
    void testIsConfigLine_yes() {
        def line = '                [M]     [W] [M]    '

        // test
        def result = builder.isConfigLine(line)

        assertTrue result
    }

    @Test
    void testIsConfigLine_no() {
        def line = ' 1   2   3   4   5   6   7   8   9'

        // test
        def result = builder.isConfigLine(line)

        assertFalse result
    }

    @Test
    void testGetSubstring_case1() {
        assertEquals '[A]', builder.getSubstring(0, 4, '[A]')
    }

    @Test
    void testGetSubstring_case2() {
        assertEquals '[A]', builder.getSubstring(0, 4, '[A] ')
    }

    @Test
    void testGetSubstring_case3() {
        assertEquals '[B]', builder.getSubstring(1, 4, '[A] [B]')
    }

    @Test
    void testGetSubstring_case4() {
        assertEquals '[B]', builder.getSubstring(1, 4, '[A] [B] ')
    }

    @Test
    void testGetSubstring_case5() {
        assertEquals '[C]', builder.getSubstring(2, 4, '[A] [B] [C]')
    }

    @Test
    void testGetSubstring_case6() {
        assertEquals '[C]', builder.getSubstring(2, 4, '[A] [B] [C] ')
    }

}
