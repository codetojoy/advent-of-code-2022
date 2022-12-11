
package net.codetojoy

class Runner {
    static void printDirForDeletion(root) {
        def pathMap = Node.traverseForSize(root)
        def maxSize = 70_000_000
        def targetSize = 30_000_000
        def sizeInfo = new SizeFilter().findDirForDeletion(pathMap, maxSize, targetSize)
        println "TRACER dir: " + sizeInfo.path + " size: " + sizeInfo.size
    }

    static void main(def args) {
        def inputFile = new File(args[0])
        assert inputFile.exists()

        try {
            def inputLines = (inputFile as List).collect { it }
            def parser = new Parser()
            def root = Node.getRoot()

            def commands = inputLines.findResults { parser.parse(it) }
                                     .each { root.apply(it) }
            printDirForDeletion(root)

        } catch (Error ex) {
            System.err.println "TRACER caught ex: " + ex.message
        }

        println "Ready."
    }
}
