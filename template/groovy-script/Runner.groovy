
def isTest = args && (args.findAll { it == '--test' }) 

if (isTest) {
    println "TRACER terminating: test run is OK"
    System.exit(0)
}

// ----------------------------------
// -- main 

def inputLines = (new File("input.txt") as List).collect { it }

println "Ready."

