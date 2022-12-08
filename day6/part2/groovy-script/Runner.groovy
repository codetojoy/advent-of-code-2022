
import groovy.transform.ToString

@ToString
class State {
    def index = 0
    def buffer = []
    def max = 0

    def apply(def c) {
        buffer << c
        if (buffer.size() > max) {
            assert buffer.size() == max + 1
            buffer = buffer.drop(1)
        }
        index++
        this
    }

    def isDone() {
        if (buffer.size() == max) {
            def set = (buffer as Set)
            return set.size() == max
        } else {
            return false
        }
    }
}

assert ['a'] == new State(max: 3).apply('a').buffer
assert ['a','b'] == new State(max: 3).apply('a').apply('b').buffer
assert ['a','b','c'] == new State(max: 3).apply('a').apply('b').apply('c').buffer
assert ['b','c','d'] == new State(max: 3).apply('a').apply('b').apply('c').apply('d').buffer

assert false == new State(max: 3).apply('a').apply('b').isDone()
assert true == new State(max: 3).apply('a').apply('b').apply('c').isDone()

def isTest = args && (args.findAll { it == '--test' })

if (isTest) {
    println "TRACER terminating: test run is OK"
    System.exit(0)
}

// ----------------------------------
// -- main

def inputLines = (new File("input.txt") as List).collect { it }
assert 1 == inputLines.size()
def inputChars = inputLines[0]

def initState = new State(max: 14)
def state = inputChars.inject(initState) { state, c ->
    if (!state.isDone()) {
        state.apply(c)
    }
    state
}
assert state.isDone()
println "message marker index: " + state.index

println "Ready."

