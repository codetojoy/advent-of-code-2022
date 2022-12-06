
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

def doesIntersect(int p, Range r) {
    return p >= r.from && p <= r.to
}

assert false == doesIntersect(5,6..10)
assert true == doesIntersect(6,6..10)
assert true == doesIntersect(8,6..10)
assert true == doesIntersect(10,6..10)
assert false == doesIntersect(11,6..10)

// true if X intersects Y
def doesIntersect(Range rangeX, Range rangeY) {
    return doesIntersect(rangeX.to, rangeY) || doesIntersect(rangeX.from, rangeY)
}

assert false == doesIntersect(0..5,6..10)
assert true == doesIntersect(0..7,6..10)
assert true == doesIntersect(6..10,6..10)
assert true == doesIntersect(8..12,6..10)
assert true == doesIntersect(10..15,6..10)
assert false == doesIntersect(11..15,6..10)

def findOverlap(line) {
    def lineInfo = buildLineInfo(line)
    def rangeA = lineInfo.rangeA
    def rangeB = lineInfo.rangeB
    doesIntersect(rangeA, rangeB) || doesIntersect(rangeB, rangeA) ? true : null
}

assert null == findOverlap('0-5,6-10')
assert true == findOverlap('0-7,6-10')
assert true == findOverlap('6-10,6-10')
assert true == findOverlap('8-12,6-10')
assert true == findOverlap('10-15,6-10')
assert null == findOverlap('11-15,6-10')

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

