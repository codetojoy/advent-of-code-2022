
package net.codetojoy

class Runner {
    static void printTotal(root) {
        def pathMap = Node.traverseForSize(root)
        def total = pathMap.findAll { path, sizeInfo ->
                        (sizeInfo.size <= 100_000)
                    }.collect { path, sizeInfo ->
                        sizeInfo.size
                    }.sum()
        println "TRACER total: " + total
    }

    static void main(def args) {
        def inputFile = new File(args[0])
        assert inputFile.exists()

        def inputLines = (inputFile as List).collect { it }
        def parser = new Parser()

        try {
            def root = Node.getRoot()

            def commands = inputLines.findResults { parser.parse(it) }
                                     .each { root.apply(it) }
            printTotal(root)

        } catch (Error ex) {
            System.err.println "TRACER caught ex: " + ex.message
        }

        println "Ready."
    }
}
