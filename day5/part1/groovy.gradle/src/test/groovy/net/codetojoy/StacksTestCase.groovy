
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class StacksTestCase {
    def stacks = new Stacks()

    @Test
    void testAddFirst_basic() {
        // test
        stacks.addFirst(2, 'A')

        assertEquals 1, stacks.getStack(2).size()
        assertEquals 'A', stacks.getStack(2).peekFirst()
    }

    @Test
    void testApplyConfig_basic() {
        def configInfo  = new ConfigInfo()
        configInfo.put(1, 'A')
        configInfo.put(2, 'B')

        // test
        stacks.applyConfig(configInfo)

        assertEquals 1, stacks.getStack(1).size()
        assertEquals 1, stacks.getStack(2).size()
    }

    @Test
    void testApplyConfig_multiple_in_one_stack() {
        def configInfo  = new ConfigInfo()
        configInfo.put(1, 'A')
        configInfo.put(2, 'B')

        stacks.applyConfig(configInfo)

        def configInfo2 = new ConfigInfo()
        configInfo2.put(2, 'C')

        // test
        stacks.applyConfig(configInfo2)

        assertEquals 2, stacks.getStack(2).size()
        assertEquals 'B', stacks.getStack(2).peekFirst()
        assertEquals 'C', stacks.getStack(2).peekLast()
    }

    @Test
    void testMoves_basic() {
        stacks.applyConfig(new ConfigInfo().put(1, 'A'))
        stacks.applyConfig(new ConfigInfo().put(1, 'B'))
        stacks.applyConfig(new ConfigInfo().put(1, 'C'))
        stacks.applyConfig(new ConfigInfo().put(2, 'D'))
        stacks.applyConfig(new ConfigInfo().put(2, 'E'))
        stacks.applyConfig(new ConfigInfo().put(2, 'F'))

        def moveInfo = new MoveInfo(n: 3, from: 1, to: 2)

        // test
        stacks.applyMove(moveInfo)

        assertEquals 6, stacks.getStack(2).size()
        assertEquals 'C', stacks.getStack(2).peekFirst()
        assertEquals 'F', stacks.getStack(2).peekLast()
        assertEquals 0, stacks.getStack(1).size()
    }
}
