
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class InstructionTestCase {

    @Test
    void testNoOp_basic() {
        def instruction = new NoOp()

        // test
        def result = instruction.apply(5150)

        assertEquals InstructionStatus.COMPLETE, result.status
    }

    @Test
    void testAdd_cycle1() {
        def instruction = new Add(V: 5100)

        // test
        def result = instruction.apply(50)

        assertEquals InstructionStatus.PENDING, result.status
        assertEquals 50, result.X
    }

    @Test
    void testAdd_cycle2() {
        def instruction = new Add(V: 5100)
        instruction.apply(50)

        // test
        def result = instruction.apply(50)

        assertEquals InstructionStatus.COMPLETE, result.status
        assertEquals 5150, result.X
    }
}
