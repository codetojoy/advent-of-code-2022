
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

    @Test
    void testFindDirForDeletion_basic() {
        def maxSize = 10_000
        def targetSize = 5_000
        def pathMap = [:]
        pathMap['/'] = new SizeInfo(path: '/', size: 6000)
        pathMap['/a'] = new SizeInfo(path: '/a', size: 1000)
        pathMap['/b'] = new SizeInfo(path: '/b', size: 2000)
        pathMap['/c'] = new SizeInfo(path: '/c', size: 3000)

        // test
        def result = sizeFilter.findDirForDeletion(pathMap, maxSize, targetSize)

        assertEquals 1000, result.size
    }
}
