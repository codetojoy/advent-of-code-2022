
package net.codetojoy

interface TickListener {
    void notify(def X, def cycle, def context)
}

class DefaultTickListener implements TickListener {
    void notify(def X, def cycle, def context) {
        println "TRACER cycle: ${cycle} X: ${X}"
    }
}
