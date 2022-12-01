
def processLine(dataLine) {
    def result = new Expando()
    def trimLine = dataLine.trim()
    def isEmpty = trimLine.isEmpty()
    result.'isEmpty' = isEmpty
    result.'value' = isEmpty ? 0 : trimLine as int
    result
}

assert true == processLine('\n').'isEmpty'
assert 0 == processLine('\n').'value'
assert false == processLine('5150\n').'isEmpty'
assert 5150 == processLine('5150\n').'value'

def getElfData(dataLines) { 
    def result = []
    if (dataLines && (! dataLines.isEmpty())) {
        def thisResult = []
        dataLines.eachLine { dataLine ->
            def dataInfo = processLine(dataLine)
            if (dataInfo.'isEmpty') {
                result << thisResult
                thisResult = []
            } else {
                thisResult << dataInfo.'value'
            }
        }
        result << thisResult
    } 

    return result
}

assert [] == getElfData(null)
assert [] == getElfData('')
assert [[10]] == getElfData('10\n')
assert [[10,20,30]] == getElfData('10\n20\n30\n')
assert [[11,22],[33,44]] == getElfData('11\n22\n\n33\n44\n')
assert [[1],[2],[3],[4]] == getElfData('1\n\n2\n\n3\n\n4\n')

def buildElves(elfData) {
    def elves = elfData.withIndex().collect { data, index ->
        def elf = new Expando()
        elf.'ord' = index + 1
        elf.'data' = data
        elf.'sum' = data.sum() 
        elf
    }
    return elves
}

assert 1 == buildElves([[100,200,300]]).size() 
assert 1 == buildElves([[100,200,300]])[0].'ord'
assert [100,200,300] == buildElves([[100,200,300]])[0].'data'
assert 600 == buildElves([[100,200,300]])[0].'sum'

def dataLines = new File("data.txt").getText()
def elfData = getElfData(dataLines)
def elves = buildElves(elfData)
def max = elves.sort { it.sum }.reverse()[0]
println max

println "Ready."

