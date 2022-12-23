
package net.codetojoy

class Parser {
    static final def NOOP_STR = 'noop'
    static final def ADDX_REGEX = /^addx\s+(-{0,1}\d+)$/

    def parse(String line) {
        def trimLine = line.trim()

        if (trimLine == NOOP_STR) {
            return new NoOp()
        } else {
            def matcher = (trimLine =~ ADDX_REGEX)
            assert matcher.matches()
            def value = matcher[0][1] as int
            return new Add(V: value)
        }
    }

    def parseAll(lines) {
        lines.collect { parse(it) }
    }
}
