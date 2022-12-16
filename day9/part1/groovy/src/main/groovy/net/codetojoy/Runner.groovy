
package net.codetojoy

class Runner {
    def world = new World()

    def run(def inputLines) {
        def directions = new Parser().parse(inputLines)
        world.apply(directions)
        println "TRACER world: " + world.toString()
        println "TRACER num tail positions: " + world.getNumTailPositions()
        world.getNumTailPositions()
    }

    static void main(def args) {
        def inputFile = new File(args[0])
        assert inputFile.exists()

        def inputLines = (inputFile as List).collect { it }
        new Runner().run(inputLines)

        println "Ready."
    }
}
