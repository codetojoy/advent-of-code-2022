
package net.codetojoy

class Runner {
    static void main(def args) {
        def inputFile = new File(args[0])
        assert inputFile.exists()

        def inputLines = (inputFile as List).collect { it }
        def grid = new Parser().parse(inputLines)

        def numVisible = grid.countVisible()
        println "TRACER num visible: " + numVisible

        println "Ready."
    }
}
