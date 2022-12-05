
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

def findBadge(group) {
    assert 3 == group.size()
    def itemsA = (group[0] as List) as Set
    def itemsB = (group[1] as List) as Set
    def itemsC = (group[2] as List) as Set
    def badgeSet = itemsA.intersect(itemsB).intersect(itemsC)
    assert 1 == badgeSet.size()
    badgeSet[0]
}

assert 'a' == findBadge(['abc','axy','ast'])

def computePriority(group) {
    def badge = (findBadge(group) as Character) as int
    def isUpper =  (badge >= 65 && badge <= 90)
    def isLower =  (badge >= 97 && badge <= 122) 
    assert isUpper || isLower

    if (isUpper) {
        return badge - 38 
    } else {
        return badge - 96  
    }
}

assert 1 == computePriority(['abc','axy','ast'])

def computeGroups(lines) {
    def groups = []
    assert lines.size() % 3 == 0

    def group = []
    lines.eachWithIndex { line, index ->
        def newGroup = index > 0 && index % 3 == 0    
        if (newGroup) {
            groups << group
            group = [] 
        }
        group << line
    }
    groups << group
    groups
}

assert 1 == computeGroups(['a','b','c']).size()
assert 3 == computeGroups(['a','b','c'])[0].size()
assert 2 == computeGroups(['a','b','c','d','e','f']).size()

def computePriorities(lines) {
    computeGroups(lines).collect { group -> computePriority(group) }
}

def isTest = false

if (isTest) {
    println "TRACER terminating: isTest OK"
    System.exit(0)
}

// ----------------------------------
// -- main 

def inputLines = new File("input.txt") as List
def priorities = computePriorities(inputLines)
def total = priorities.sum()
println "total: " + total

println "Ready."

