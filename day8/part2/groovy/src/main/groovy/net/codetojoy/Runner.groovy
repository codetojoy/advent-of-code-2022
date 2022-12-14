
package net.codetojoy

class Runner {
    static void main(def args) {
        def inputFile = new File(args[0])
        assert inputFile.exists()

        def inputLines = (inputFile as List).collect { it }
        def grid = new Parser().parse(inputLines)

        def highestScenicScore = grid.findHighestScenicScore()
        println "TRACER high score: " + highestScenicScore.score
        println "TRACER point: " + highestScenicScore.key

        println "Ready."
    }
}
