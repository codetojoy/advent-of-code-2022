
def parseLineInfo(line) {
    def regex = /(\d+)-(\d+),(\d+)-(\d+)/
    def matcher = (line =~ regex)
    assert matcher.matches()
    def info = new Expando()
    info.rangeAStart = matcher[0][1] as int
    info.rangeAEnd = matcher[0][2] as int
    info.rangeBStart = matcher[0][3] as int
    info.rangeBEnd = matcher[0][4] as int
    info
}

assert 20 == parseLineInfo('20-81,21-80').rangeAStart
assert 81 == parseLineInfo('20-81,21-80').rangeAEnd
assert 21 == parseLineInfo('20-81,21-80').rangeBStart
assert 80 == parseLineInfo('20-81,21-80').rangeBEnd

def buildLineInfo(line) {
    def parseInfo = parseLineInfo(line)
    def a1 = parseInfo.rangeAStart
    def a2 = parseInfo.rangeAEnd
    def b1 = parseInfo.rangeBStart
    def b2 = parseInfo.rangeBEnd
    def info = new Expando()
    info.rangeA = a1..a2
    info.rangeB = b1..b2
    info 
}

assert 20 == buildLineInfo('20-81,21-80').rangeA.from
assert 81 == buildLineInfo('20-81,21-80').rangeA.to
assert 21 == buildLineInfo('20-81,21-80').rangeB.from
assert 80 == buildLineInfo('20-81,21-80').rangeB.to

// true if X contains Y
def doesContain(rangeX, rangeY) {
    return (rangeY.from >= rangeX.from) && (rangeY.to <= rangeX.to)
}

assert true == doesContain(5..5,5..5)
assert true == doesContain(5..15,5..8)
assert true == doesContain(0..8,5..8)
assert false == doesContain(0..8,8..9)

def findOverlap(line) {
    def lineInfo = buildLineInfo(line)
    def rangeA = lineInfo.rangeA
    def rangeB = lineInfo.rangeB
    def result = null
    if (doesContain(rangeA, rangeB) || doesContain(rangeB, rangeA)) {
        result = true 
    }
    result
}

assert true == findOverlap('5-5,5-5')
assert true == findOverlap('5-15,5-8')
assert true == findOverlap('5-8,5-15')

def findOverlaps(lines) {
    lines.findResults { findOverlap(it.trim()) }
}

def isTest = false

if (isTest) {
    println "TRACER terminating: isTest OK"
    System.exit(0)
}

// ----------------------------------
// -- main 

def inputLines = new File("input.txt") as List
def overlaps = findOverlaps(inputLines)
def total = overlaps.size()
println "total: " + total

println "Ready."

