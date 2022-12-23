
package net.codetojoy

class CPU {
    def cycle = 0
    def tickListener = new DefaultTickListener()
    def instructionIter
    def instructionState = new InstructionState(status: InstructionStatus.COMPLETE, X: 1)
    // seed with a non-null instruction, not executed
    def currentInstruction = Optional.of(new NoOp())

    def getInstruction() {
        if (instructionState.status == InstructionStatus.PENDING) {
            return currentInstruction
        }

        if (instructionIter.hasNext()) {
            return Optional.of(instructionIter.next())
        } else {
            return Optional.empty()
        }
    }

    def tick() {
        while (currentInstruction.isPresent()) {
            currentInstruction = getInstruction()

            if (currentInstruction.isPresent()) {
                cycle++
                def context = currentInstruction.get().toString()
                tickListener.notify(instructionState.X, cycle, context)
                instructionState = currentInstruction.get().apply(instructionState.X)
            }
        }
    }
}
