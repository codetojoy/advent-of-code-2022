
package net.codetojoy

class Parser {
    static final def regex = /(?i)([DLRU])\s+(\d+)/

    def parse(String line) {
        def matcher = line =~ regex
        assert matcher.matches()
        def direction = matcher[0][1]
        def value = matcher[0][2] as int
        switch (direction) {
            case "D":
                return new Direction(DirectionType.DOWN, value)
            case "L":
                return new Direction(DirectionType.LEFT, value)
            case "R":
                return new Direction(DirectionType.RIGHT, value)
            case "U":
                return new Direction(DirectionType.UP, value)
        }
    }

    def parse(List<String> lines) {
        lines.collect { parse(it) }
    }
}
