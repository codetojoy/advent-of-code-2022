
package net.codetojoy

class Runner {
    static void main(def args) {
        def inputFile = new File(args[0])
        assert inputFile.exists()

        def inputLines = (inputFile as List).collect { it }
        def parser = new Parser()

        try {
            def commands = inputLines.findResults { parser.parse(it) }

            def root = Node.getRoot()
            def i = 1
            commands.each {
                // println "TRACER i: ${i++} c: ${it}"
                root.apply(it)
            }
            println "TRACER root:"
            println root
        } catch (Error ex) {
            System.err.println "TRACER caught ex: " + ex.message
        }

        println "Ready."
    }
}
