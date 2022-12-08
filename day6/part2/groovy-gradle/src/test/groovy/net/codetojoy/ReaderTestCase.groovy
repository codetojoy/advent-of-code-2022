
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class ReaderTestCase {
    def reader = new Reader()

    @Test
    void testIsFoundPacketMarker_no() {
        def s = "abca"
        reader.read(s)

        // test
        def result = reader.isPacketMarkerFound()

        assertFalse result
    }

    @Test
    void testIsFoundPacketMarker_yes_trivial() {
        def s = "abcd"
        reader.read(s)

        // test
        def result = reader.isPacketMarkerFound()

        assertTrue result
    }

    @Test
    void testIsFoundPacketMarker_no_case1() {
        def s = "nznrnfrfn"
        reader.read(s)

        // test
        def result = reader.isPacketMarkerFound()

        assertFalse result
    }

    @Test
    void testIsFoundPacketMarker_yes_case1() {
        def s = "nznrnfrfnt"
        reader.read(s)

        // test
        def result = reader.isPacketMarkerFound()

        assertTrue result
    }

    @Test
    void testPacketStopIndex_yes_case1() {
        def s = "nznrnfrfntabcdefijk"
        reader.read(s)

        // test
        def result = reader.getPacketStopIndex()

        assertEquals 10, result
    }

    @Test
    void testMessageStopIndex_yes_case1() {
        def s = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
        reader.read(s)

        // test
        def result = reader.getMessageStopIndex()

        assertEquals 29, result
    }
}
