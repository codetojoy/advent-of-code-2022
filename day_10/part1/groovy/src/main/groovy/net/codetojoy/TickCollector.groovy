
package net.codetojoy

class TickCollector implements TickListener {
    def total = 0

    void notify(def X, def cycle, def context) {
        println "TRACER DEBUG c: ${cycle} X: ${X} t: ${total} ctx: ${context}"

        if (cycle == 20) {
            total += X * cycle
            println "TRACER c: ${cycle} X: ${X} t: ${total}"
        } else if (cycle > 20) {
            def deltaCycle = cycle - 20
            if (deltaCycle % 40 == 0) {
                def current = X * cycle
                total += current
                println "TRACER c: ${cycle} X: ${X} cu: ${current} t: ${total}"
            }
        }
    }
}
