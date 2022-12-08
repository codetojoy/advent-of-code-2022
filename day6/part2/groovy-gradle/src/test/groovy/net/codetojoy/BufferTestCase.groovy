
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class BufferTestCase {
    def max = 4
    def buffer = new Buffer(max)

    @Test
    void testAdd_less_than_max() {
        def s = "abc"

        // test
        s.each { buffer.add(it) }

        def result = buffer.getElements()
        assertEquals ['a', 'b', 'c'], result
    }

    @Test
    void testAdd_equal_to_max() {
        def s = "abcd"

        // test
        s.each { buffer.add(it) }

        def result = buffer.getElements()
        assertEquals ['a', 'b', 'c', 'd'], result
    }

    @Test
    void testAdd_more_than_max() {
        def s = "abcdefghijk"

        // test
        s.each { buffer.add(it) }

        def result = buffer.getElements()
        assertEquals ['h', 'i', 'j', 'k'], result
    }

    @Test
    void testGetNumUniqueElements_basic() {
        def s = "abcdefghijk"
        s.each { buffer.add(it) }

        // test
        def result = buffer.getNumUniqueElements()

        assertEquals max, result
    }
}
