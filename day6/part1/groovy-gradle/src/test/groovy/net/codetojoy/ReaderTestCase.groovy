
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class ReaderTestCase {
    def reader = new Reader()

    @Test
    void testIsFoundMarker_no() {
        def s = "abca"
        reader.read(s)

        // test
        def result = reader.isMarkerFound()

        assertFalse result
    }

    @Test
    void testIsFoundMarker_yes_trivial() {
        def s = "abcd"
        reader.read(s)

        // test
        def result = reader.isMarkerFound()

        assertTrue result
    }

    @Test
    void testIsFoundMarker_no_case1() {
        def s = "nznrnfrfn"
        reader.read(s)

        // test
        def result = reader.isMarkerFound()

        assertFalse result
    }

    @Test
    void testIsFoundMarker_yes_case1() {
        def s = "nznrnfrfnt"
        reader.read(s)

        // test
        def result = reader.isMarkerFound()

        assertTrue result
    }

    @Test
    void testStopIndex_yes_case1() {
        def s = "nznrnfrfntabcdefijk"
        reader.read(s)

        // test
        def result = reader.getStopIndex()

        assertEquals 10, result
    }
}
