
def splitLine(line) {
    def result = new Expando()
    def n = line.size()
    def h = n / 2
    result.'compartmentA' = line[0..h-1]
    result.'compartmentB' = line[h..n-1]
    result
}

assert 'vJrwpWtwJgWr' == splitLine('vJrwpWtwJgWrhcsFMMfFFhFp').'compartmentA'
assert 'hcsFMMfFFhFp' == splitLine('vJrwpWtwJgWrhcsFMMfFFhFp').'compartmentB'

def findErrorItem(line) {
    def numItems = line.size()
    assert 0 == numItems % 2
    def splitLine = splitLine(line)
    def itemsA = splitLine.'compartmentA'.collect { it } as Set
    def itemsB = splitLine.'compartmentB'.collect { it } as Set
    def errorSet = itemsA.intersect(itemsB)
    assert 1 == errorSet.size()
    errorSet[0] as String
}

assert 'p' == findErrorItem('vJrwpWtwJgWrhcsFMMfFFhFp')
assert 'a' == findErrorItem('abcdaxyz')

def computePriority(line) {
    def errorItem = (findErrorItem(line) as Character) as int
    def isUpper =  (errorItem >= 65 && errorItem <= 90)
    def isLower =  (errorItem >= 97 && errorItem <= 122) 
    assert isUpper || isLower

    if (isUpper) {
        return errorItem - 38 
    } else {
        return errorItem - 96  
    }
}

assert 1 == computePriority('abcdaxyz')
assert 26 == computePriority('zbcdzijk')
assert 27 == computePriority('AbcdAxyz')
assert 52 == computePriority('ZbcdZijk')

def computePriorities(lines) {
    def results = []
    lines.eachLine { results << computePriority(it.trim()) }
    results
}

def isTest = false

if (isTest) {
    println "TRACER terminating: isTest OK"
    System.exit(0)
}

// ----------------------------------
// -- main 

def inputLines = new File("input.txt").getText()
def priorities = computePriorities(inputLines)
def total = priorities.sum()
println "total: " + total

println "Ready."

