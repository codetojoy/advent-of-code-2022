
enum Play {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)

    final int value

    private Play(int value) {
        this.value = value
    }
}

class PlayMap {
    def playMap = [:]

    PlayMap() {
        playMap['A'] = Play.ROCK
        playMap['B'] = Play.PAPER
        playMap['C'] = Play.SCISSORS

        playMap['X'] = Play.ROCK
        playMap['Y'] = Play.PAPER
        playMap['Z'] = Play.SCISSORS
    }

    def getPlay(str) {
        playMap[str.toUpperCase()]
    }
}

def buildGame(guideLine) {
    def tokens = guideLine.trim().split(" ")
    def play1 = tokens[0]
    def play2 = tokens[1]
    def plays = new PlayMap()
    def game = new Expando()
    game.'play1' = plays.getPlay(play1)
    game.'play2' = plays.getPlay(play2)
    game
}

assert Play.ROCK == buildGame('A X').'play1'
assert Play.ROCK == buildGame('A X').'play2'
assert Play.PAPER == buildGame('B Y').'play1'
assert Play.PAPER == buildGame('B Y').'play2'
assert Play.SCISSORS == buildGame('C Z').'play1'
assert Play.SCISSORS == buildGame('C Z').'play2'

def buildGames(guideLines) {
    def games = []
    guideLines.eachLine { line ->
        games << buildGame(line)
    }
    games
}

class Referee {
    // [A] = B means B beats A
    def losesToMap = [:]

    Referee() {
        losesToMap[Play.ROCK] = Play.PAPER
        losesToMap[Play.PAPER] = Play.SCISSORS
        losesToMap[Play.SCISSORS] = Play.ROCK
    }

    // does q beat p ?
    def isWinningPlay(def p, def q) {
        losesToMap[p] == q
    }
}

assert false == new Referee().isWinningPlay(Play.ROCK, Play.SCISSORS)
assert false == new Referee().isWinningPlay(Play.ROCK, Play.ROCK)
assert true == new Referee().isWinningPlay(Play.ROCK, Play.PAPER)
assert true == new Referee().isWinningPlay(Play.PAPER, Play.SCISSORS)
assert false == new Referee().isWinningPlay(Play.PAPER, Play.ROCK)
assert false == new Referee().isWinningPlay(Play.PAPER, Play.PAPER)
assert false == new Referee().isWinningPlay(Play.SCISSORS, Play.SCISSORS)
assert true == new Referee().isWinningPlay(Play.SCISSORS, Play.ROCK)
assert false == new Referee().isWinningPlay(Play.SCISSORS, Play.PAPER)

def computeOutcome(play1, play2) {
    // default is loss
    def outcome = 0

    if (play1.value == play2.value) {
        // draw
        outcome = 3
    } else if (new Referee().isWinningPlay(play1, play2)) {
        // play2 beats play1
        outcome = 6
    }

    outcome
}

assert 0 == computeOutcome(Play.ROCK, Play.SCISSORS)
assert 3 == computeOutcome(Play.ROCK, Play.ROCK)
assert 6 == computeOutcome(Play.ROCK, Play.PAPER)
assert 6 == computeOutcome(Play.PAPER, Play.SCISSORS)
assert 0 == computeOutcome(Play.PAPER, Play.ROCK)
assert 3 == computeOutcome(Play.PAPER, Play.PAPER)
assert 3 == computeOutcome(Play.SCISSORS, Play.SCISSORS)
assert 6 == computeOutcome(Play.SCISSORS, Play.ROCK)
assert 0 == computeOutcome(Play.SCISSORS, Play.PAPER)

def computeScores(games) {
    games.collect { game ->
        def play1 = game.'play1'
        def play2 = game.'play2'
        // choice
        def score = play2.value 
        score += computeOutcome(play1, play2) 
        score
    }
}

assert 1 + 3 == computeScores([buildGame('A X')]).sum()
assert 2 + 6 == computeScores([buildGame('A Y')]).sum()
assert 3 + 0 == computeScores([buildGame('A Z')]).sum()
assert 1 + 0 == computeScores([buildGame('B X')]).sum()
assert 2 + 3 == computeScores([buildGame('B Y')]).sum()
assert 3 + 6 == computeScores([buildGame('B Z')]).sum()
assert 1 + 6 == computeScores([buildGame('C X')]).sum()
assert 2 + 0 == computeScores([buildGame('C Y')]).sum()
assert 3 + 3 == computeScores([buildGame('C Z')]).sum()

// ----------------------------------
// -- main 

def guideLines = new File("guide.txt").getText()
def games = buildGames(guideLines)
def scores = computeScores(games)
def total = scores.sum()
println "total: " + total

println "Ready."

