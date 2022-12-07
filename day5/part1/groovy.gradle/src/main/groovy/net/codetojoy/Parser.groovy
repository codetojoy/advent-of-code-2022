
package net.codetojoy

class Parser {
    def isConfigLine(line) {
        def regex = /.*\[[A-Z]\].*/
        def matcher = (line =~ regex)
        matcher.matches()
    }

    def getSubstring(int chunkIndex, int chunkSize, String line) {
        def numChars = line.size()
        def index = chunkIndex * chunkSize
        def begin = index
        def end = index + 4
        if (end > numChars) {
            end = numChars
        }
        def result = line.substring(begin, end).trim()
        // println "TRACER line: '${line}'"
        // println "TRACER i: ${index} b: ${begin} e: ${end} n: ${numChars} result: '${result}'"
        result
    }

    def parseConfigLine(line) {
        def configInfo = new ConfigInfo()
        def chunkIndex = 0
        def chunkSize = 4
        def isDone = false
        def regex = /\[(.)\]/
        while (!isDone) {
            def s = getSubstring(chunkIndex, chunkSize, line)
            def matcher = (s =~ regex)
            if (matcher.matches()) {
                def crate = matcher[0][1]
                configInfo.put(chunkIndex + 1, crate)
            }
            chunkIndex++
            isDone = (chunkIndex * chunkSize > line.size())
        }
        configInfo
    }

    def buildStacks(lines) {
        def stacks = new Stacks()
        def isDone = false
        def i = 0

        while (!isDone) {
            def line = lines[i]

            if (isConfigLine(line)) {
                def configInfo = parseConfigLine(line)
                stacks.applyConfig(configInfo)
            } else {
                isDone = true
            }

            i++
        }

        stacks
    }
}

