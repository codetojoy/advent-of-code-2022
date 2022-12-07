
package net.codetojoy

import groovy.transform.ToString

@ToString
class Stacks {
    def stacks = [:].withDefault { key -> new ArrayDeque<String>() }

    def getTops() {
        def results = []
        def keys = stacks.keySet().sort()

        keys.each { key -> 
            def stack = getStack(key)
            if (stack.isEmpty()) {
                results << " ERROR "
            } else {
                results << stack.peekFirst()
            }
        }
        return results
    }

    def addFirst(index, item) {
        def stack = stacks[index]
        stack.addFirst(item)
        this
    }

    def getStack(index) {
        stacks[index]
    }

    def applyConfig(configInfo) {
        configInfo.keys().each { key ->
            def value = configInfo.get(key)
            stacks[key].addLast(value)
        }
        this
    }

    def applyMove(moveInfo) {
        def src = getStack(moveInfo.from)
        def dest = getStack(moveInfo.to)
        def crates = new ArrayDeque<String>() 

        moveInfo.n.times { 
            def crate = src.removeFirst()
            crates.addFirst(crate)
        }

        crates.each { dest.addFirst(it) }
    }

    def applyMoves(moves) {
        moves.each { applyMove(it) }
    }
}

