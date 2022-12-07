
package net.codetojoy

class MovesBuilder {
    def buildMove(line) {
        def result = null
        def regex = /.*move (\d+) from (\d+) to (\d+).*/
        def matcher = (line =~ regex)

        if (matcher.matches()) {
            result = new MoveInfo()
            result.n = matcher[0][1] as int
            result.from = matcher[0][2] as int
            result.to = matcher[0][3] as int
        }

        result
    }

    def buildMoves(lines) {
        lines.findResults { buildMove(it) }
    }
}

