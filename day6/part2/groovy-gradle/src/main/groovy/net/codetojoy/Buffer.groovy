
package net.codetojoy

class Buffer {
    final def max
    def buffer = new ArrayDeque<String>()

    Buffer(def max) {
        this.max = max
    }

    def add(def c) {
        buffer.addLast(c)
        if (buffer.size() > max) {
            buffer.removeFirst()
            assert buffer.size() == max
        }
    }

    def getElements() {
        (buffer as List)
    }

    def getNumUniqueElements() {
        (buffer as Set).size()
    }
}

