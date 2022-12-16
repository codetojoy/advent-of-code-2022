
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class RunnerTestCase {
    def runner = new Runner()

    @Test
    void testRun_integration() {
        def lines = []
        lines << 'R 4'
        lines << 'U 4'
        lines << 'L 3'
        lines << 'D 1'
        lines << 'R 4'
        lines << 'D 1'
        lines << 'L 5'
        lines << 'R 2'

        // test
        def result = runner.run(lines)

        assert 13 == result
    }
}
