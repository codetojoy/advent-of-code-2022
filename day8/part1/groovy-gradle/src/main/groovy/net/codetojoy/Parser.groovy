
package net.codetojoy

class Parser {
    def parse(lines) {
        def n = lines.size()
        def grid = new Grid(n)

        lines.eachWithIndex { line, row ->
            assert n == line.size()
            grid.setRow(row, line)
        }

        grid
    }
}
