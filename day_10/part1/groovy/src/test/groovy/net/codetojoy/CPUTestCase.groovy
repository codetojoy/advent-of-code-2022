
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class CPUTestCase {

    @Test
    void testTick_noop() {
        def instructions = [new NoOp()]
        def instructionIter = instructions.iterator()
        def cpu = new CPU(instructionIter: instructionIter)

        // test
        cpu.tick()

        assertEquals 1, cpu.instructionState.X
        assertEquals 1, cpu.cycle
    }

    @Test
    void testTick_add() {
        def instructions = [new Add(V: 5149)]
        def instructionIter = instructions.iterator()
        def cpu = new CPU(instructionIter: instructionIter)

        // test
        cpu.tick()

        assertEquals 5150, cpu.instructionState.X
        assertEquals 2, cpu.cycle
    }
}
