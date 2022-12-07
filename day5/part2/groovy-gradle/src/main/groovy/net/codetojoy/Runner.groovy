
package net.codetojoy

class Runner {
    static void main(def args) {
        def inputFile = new File(args[0])
        assert inputFile.exists()
        
        def inputLines = (inputFile as List).collect { it }

        def stacks = new StacksBuilder().buildStacks(inputLines)
        def moves = new MovesBuilder().buildMoves(inputLines)
        stacks.applyMoves(moves)

        def tops = stacks.getTops()
        println "TRACER tops: "
        tops.each { print it }
        println ""

        println "Ready."
    }
}
