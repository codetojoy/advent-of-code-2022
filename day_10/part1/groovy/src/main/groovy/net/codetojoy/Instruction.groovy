
package net.codetojoy

import groovy.transform.*

enum InstructionStatus {
    PENDING,
    COMPLETE
}

// TODO: rename this
class InstructionState {
    def status
    def X
}

interface Instruction {
    InstructionState apply(def X)
}

class NoOp implements Instruction {
    InstructionState apply(def X) {
        new InstructionState(status: InstructionStatus.COMPLETE, X: X)
    }
}

@ToString
class Add implements Instruction {
    def cycleCount = 0
    def V

    InstructionState apply(def X) {
        def result
        assert cycleCount == 0 || cycleCount == 1
        if (cycleCount == 0) {
            result = new InstructionState(status: InstructionStatus.PENDING, X: X)
        } else {
            result = new InstructionState(status: InstructionStatus.COMPLETE, X: X + V)
        }
        cycleCount++
        result
    }
}
