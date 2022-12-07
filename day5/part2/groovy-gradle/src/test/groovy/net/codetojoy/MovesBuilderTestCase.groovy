
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class MovesBuilderTestCase {
    def builder = new MovesBuilder()

    @Test
    void testBuildMove_basic() {
        // test
        def result = builder.buildMove('move 1 from 7 to 4')

        assert 1 == result.n
        assert 7 == result.from
        assert 4 == result.to
    }
}
