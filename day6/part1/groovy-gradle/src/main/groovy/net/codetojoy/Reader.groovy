
package net.codetojoy

class Reader {
    def markerFound = false
    def stopIndex = -5150
    def buffer = new ArrayDeque<String>()
    static final NUM_UNIQUE_CHARS = 4

    def processChar(def c, def index) {
        buffer.addFirst(c)
        if (buffer.size() > NUM_UNIQUE_CHARS) {
            buffer.removeLast()
            assert buffer.size() <= NUM_UNIQUE_CHARS
        }
        markerFound = isMarkerFound()

        if (markerFound) {
            stopIndex = index + 1
        }
    }

    def read(def s) {
        def chars = (s as List)
        chars.eachWithIndex { c, index ->
            if (!markerFound) {
                processChar(c, index)
            }
        }
    }

    def getStopIndex() {
        stopIndex
    }

    def isMarkerFound() {
        def set = buffer as Set
        return set.size() == NUM_UNIQUE_CHARS
    }
}
