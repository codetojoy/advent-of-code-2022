
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class GridTestCase {
    int n = 5;
    Grid grid = new Grid(5);

    @Test
    void testSetGet_basic() {
        grid.set(0, 0, 5150);

        // test
        int result = grid.get(0, 0);

        assertEquals(5150, result);
    }

    @Test
    void testSetRow_basic() {
        // test
        grid.setRow(1, [1,2,3,4,5]);

        assertEquals 1, grid.get(1,0);
        assertEquals 2, grid.get(1,1);
        assertEquals 3, grid.get(1,2);
        assertEquals 4, grid.get(1,3);
        assertEquals 5, grid.get(1,4);
    }

    @Test
    void testCountVisible_only_edges() {
        grid.setRow(0, [1,1,1,1,1])
        grid.setRow(1, [1,0,0,0,1])
        grid.setRow(2, [1,0,0,0,1])
        grid.setRow(3, [1,0,0,0,1])
        grid.setRow(4, [1,1,1,1,1])

        // test
        def result = grid.countVisible()

        assert 16 == result
    }

    @Test
    void testCountVisible_full() {
        grid.setRow(0, [1,1,1,1,1])
        grid.setRow(1, [1,2,0,0,1])
        grid.setRow(2, [1,0,2,0,1])
        grid.setRow(3, [1,0,0,2,1])
        grid.setRow(4, [1,1,1,1,1])

        // test
        def result = grid.countVisible()

        assert 19 == result
    }
}
