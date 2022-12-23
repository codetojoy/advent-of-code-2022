
package net.codetojoy

class Runner {
    static void main(def args) {
        def inputFile = new File(args[0])
        assert inputFile.exists()

        def inputLines = (inputFile as List).collect { it }
        def instructions = new Parser().parseAll(inputLines)
        def tickCollector = new TickCollector()

        def cpu = new CPU(tickListener: tickCollector, instructionIter: instructions.iterator())
        cpu.tick()

        println "TRACER total: " + tickCollector.total

        println "Ready."
    }
}
