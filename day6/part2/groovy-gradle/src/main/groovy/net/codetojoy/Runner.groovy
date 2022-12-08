
package net.codetojoy

class Runner {
    static void main(def args) {
        def inputFile = new File(args[0])
        assert inputFile.exists()

        def inputLines = (inputFile as List).collect { it }
        assert 1 == inputLines.size()

        def reader = new Reader()
        reader.read(inputLines[0])

        if (reader.isMessageMarkerFound()) {
            def stopIndex = reader.getMessageStopIndex()
            println "TRACER message marker reached at index: " + stopIndex
        }

        println "Ready."
    }
}
