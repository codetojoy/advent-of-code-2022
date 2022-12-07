
import groovy.transform.ToString

@ToString 
class MoveInfo {
    def n
    def from
    def to
}

@ToString
class ConfigInfo {
    // e.g. [2] -> 'M'
    def infoMap = [:]

    def keys() {
        infoMap.keySet()
    }

    def get(index) {
        infoMap[index]
    }

    def put(index, item) {
        infoMap[index] = item
        this
    }
}

@ToString
class Stacks {
    def stacks = [:].withDefault { key -> new ArrayDeque<String>() }

    def getTops() {
        def results = []
        def keys = stacks.keySet().sort()
        println "TRACER keys: " + keys
        keys.each { key -> 
            def stack = getStack(key)
            if (stack.isEmpty()) {
                results << " ERROR "
            } else {
                results << stack.peekFirst()
            }
        }
        return results
    }

    def addFirst(index, item) {
        def stack = stacks[index]
        stack.addFirst(item)
        this
    }

    def getStack(index) {
        stacks[index]
    }

    def applyConfig(configInfo) {
        configInfo.keys().each { key ->
            def value = configInfo.get(key)
            stacks[key].addLast(value)
        }
        this
    }

    def applyMove(moveInfo) {
        def n = moveInfo.n
        def src = getStack(moveInfo.from)
        def dest = getStack(moveInfo.to)
        n.times { 
            def crate = src.removeFirst()
            dest.addFirst(crate)
        }
    }

    def applyMoves(moves) {
        moves.each { applyMove(it) }
    }
}

assert 1 == new Stacks().addFirst(2,'A').getStack(2).size()
assert 'A' == new Stacks().addFirst(2,'A').getStack(2).peekFirst()

def testConfigInfo1 = new ConfigInfo()
testConfigInfo1.put(1, 'A')
testConfigInfo1.put(2, 'B')

assert 1 == new Stacks().applyConfig(testConfigInfo1).getStack(1).size()
assert 1 == new Stacks().applyConfig(testConfigInfo1).getStack(2).size()

def testStacks = new Stacks()
testStacks.applyConfig(testConfigInfo1)
def testConfigInfo2 = new ConfigInfo()
testConfigInfo2.put(2, 'C')

assert 2 == testStacks.applyConfig(testConfigInfo2).getStack(2).size()
assert 'B' == testStacks.applyConfig(testConfigInfo2).getStack(2).peekFirst()
assert 'C' == testStacks.applyConfig(testConfigInfo2).getStack(2).peekLast()

def testMoves() {
    def stacks = new Stacks()
    stacks.applyConfig(new ConfigInfo().put(1, 'A'))
    stacks.applyConfig(new ConfigInfo().put(1, 'B'))
    stacks.applyConfig(new ConfigInfo().put(1, 'C'))
    stacks.applyConfig(new ConfigInfo().put(2, 'D'))
    stacks.applyConfig(new ConfigInfo().put(2, 'E'))
    stacks.applyConfig(new ConfigInfo().put(2, 'F'))
    def moveInfo = new MoveInfo(n: 3, from: 1, to: 2)
    
    // test
    stacks.applyMove(moveInfo)

    assert 6 == stacks.getStack(2).size()
    assert 'C' == stacks.getStack(2).peekFirst()
    assert 'F' == stacks.getStack(2).peekLast()
    assert 0 == stacks.getStack(1).size()
}

testMoves()

def isConfigLine(line) {
    def regex = /.*\[[A-Z]\].*/
    def matcher = (line =~ regex)
    matcher.matches()
}

def testLine1 = '                [M]     [W] [M]    '
def testLine2 = ' 1   2   3   4   5   6   7   8   9'

assert true == isConfigLine(testLine1)
assert false == isConfigLine(testLine2)

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

assert '[A]' == getSubstring(0, 4, '[A]')
assert '[A]' == getSubstring(0, 4, '[A] ')
assert '[B]' == getSubstring(1, 4, '[A] [B]')
assert '[B]' == getSubstring(1, 4, '[A] [B] ')
assert '[C]' == getSubstring(2, 4, '[A] [B] [C]')
assert '[C]' == getSubstring(2, 4, '[A] [B] [C] ')

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

assert 'A' == parseConfigLine('[A]').get(1)
assert 'B' == parseConfigLine('[A] [B] ').get(2)
assert 'C' == parseConfigLine('        [C]        ').get(3)
assert 'M' == parseConfigLine(testLine1).get(5)
assert 'W' == parseConfigLine(testLine1).get(7)
assert 'M' == parseConfigLine(testLine1).get(8)

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


def buildMove(line) {
    def result = null
    def regex = /.*move (\d+) from (\d+) to (\d+).*/
    def matcher = (line =~ regex)

    if (matcher.matches()) {
        result = new MoveInfo()        
        result.n = matcher[0][1] as int
        result.from = matcher[0][2] as int
        result.to = matcher[0][3] as int
    }

    result
}

assert 1 == buildMove('move 1 from 7 to 4').n
assert 7 == buildMove('move 1 from 7 to 4').from
assert 4 == buildMove('move 1 from 7 to 4').to

def buildMoves(lines) {
    lines.findResults { buildMove(it) }
}

def isTest = false

if (isTest) {
    println "TRACER terminating: isTest OK"
    System.exit(0)
}

// ----------------------------------
// -- main 

def inputLines = (new File("input.txt") as List).collect { it } 
def stacks = buildStacks(inputLines)
// println "TRACER stacks: " + stacks
def moves = buildMoves(inputLines)
// println "TRACER moves: " + moves
stacks.applyMoves(moves)
def tops = stacks.getTops()
println "TRACER tops: "
tops.each { print it }
println "" 

println "Ready."

