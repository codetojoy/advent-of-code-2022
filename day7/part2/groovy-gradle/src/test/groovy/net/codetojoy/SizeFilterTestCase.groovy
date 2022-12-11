
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class SizeFilterTestCase {
    def sizeFilter = new SizeFilter()

    @Test
    void testFindTotal_basic() {
        def threshold = 400
        def pathMap = [:]
        pathMap['/'] = new SizeInfo(size: 1000)
        pathMap['/a'] = new SizeInfo(size: 100)
        pathMap['/b'] = new SizeInfo(size: 700)
        pathMap['/c'] = new SizeInfo(size: 200)

        // test
        def result = sizeFilter.findTotal(pathMap, threshold)

        assertEquals 300, result
    }
}
