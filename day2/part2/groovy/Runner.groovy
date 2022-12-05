
enum Play {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)

    final int value

    private Play(int value) {
        this.value = value
    }
}

enum Goal {
    LOSE(1),
    DRAW(2),
    WIN(3)

    final int value

    private Goal(int value) {
        this.value = value
    }
}

class InputMap {
    def inputMap = [:]

    InputMap() {
        inputMap['A'] = Play.ROCK
        inputMap['B'] = Play.PAPER
        inputMap['C'] = Play.SCISSORS

        inputMap['X'] = Goal.LOSE
        inputMap['Y'] = Goal.DRAW
        inputMap['Z'] = Goal.WIN
    }

    def fromInput(str) {
        inputMap[str.toUpperCase()]
    }
}

enum Outcome {
    LOSS(0),
    DRAW(3),
    WIN(6)

    final int points

    private Outcome(int points) {
        this.points = points
    }
}

def buildGame(guideLine) {
    def tokens = guideLine.trim().split(" ")
    def play1 = tokens[0]
    def goal = tokens[1]
    def inputs = new InputMap()
    def game = new Expando()
    game.'play1' = inputs.fromInput(play1)
    game.'goal' = inputs.fromInput(goal)
    game
}

assert Play.ROCK == buildGame('A X').'play1'
assert Goal.LOSE == buildGame('A X').'goal'
assert Play.PAPER == buildGame('B Y').'play1'
assert Goal.DRAW == buildGame('B Y').'goal'
assert Play.SCISSORS == buildGame('C Z').'play1'
assert Goal.WIN == buildGame('C Z').'goal'

def buildGames(guideLines) {
    guideLines.collect { buildGame(it) }
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
    def outcome = Outcome.LOSS

    if (play1.value == play2.value) {
        outcome = Outcome.DRAW 
    } else if (new Referee().isWinningPlay(play1, play2)) {
        // play2 beats play1
        outcome = Outcome.WIN
    }

    outcome
}

assert 0 == computeOutcome(Play.ROCK, Play.SCISSORS).points
assert 3 == computeOutcome(Play.ROCK, Play.ROCK).points
assert 6 == computeOutcome(Play.ROCK, Play.PAPER).points
assert 6 == computeOutcome(Play.PAPER, Play.SCISSORS).points
assert 0 == computeOutcome(Play.PAPER, Play.ROCK).points
assert 3 == computeOutcome(Play.PAPER, Play.PAPER).points
assert 3 == computeOutcome(Play.SCISSORS, Play.SCISSORS).points
assert 6 == computeOutcome(Play.SCISSORS, Play.ROCK).points
assert 0 == computeOutcome(Play.SCISSORS, Play.PAPER).points

class GoalToPlayMap {
    def map = [:]

    def buildKey(play, goal) {
        def playStr = play as String
        def goalStr = goal as String
        playStr + ":" + goalStr
    }

    GoalToPlayMap() {
        map[buildKey(Play.ROCK, Goal.LOSE)] = Play.SCISSORS
        map[buildKey(Play.ROCK, Goal.DRAW)] = Play.ROCK
        map[buildKey(Play.ROCK, Goal.WIN)] = Play.PAPER

        map[buildKey(Play.PAPER, Goal.LOSE)] = Play.ROCK
        map[buildKey(Play.PAPER, Goal.DRAW)] = Play.PAPER
        map[buildKey(Play.PAPER, Goal.WIN)] = Play.SCISSORS

        map[buildKey(Play.SCISSORS, Goal.LOSE)] = Play.PAPER
        map[buildKey(Play.SCISSORS, Goal.DRAW)] = Play.SCISSORS
        map[buildKey(Play.SCISSORS, Goal.WIN)] = Play.ROCK
    }

    def computePlayFromGoal(play, goal) {
        map[buildKey(play, goal)]
    }
}

assert Play.SCISSORS == new GoalToPlayMap().computePlayFromGoal(Play.ROCK, Goal.LOSE)
// TODO: etc

def computeScore(play1, goal) {
    def play2 = new GoalToPlayMap().computePlayFromGoal(play1, goal)
    // choice:
    def score = play2.value 
    // game:
    score += computeOutcome(play1, play2).points 
    score
}

def computeScores(games) {
    games.collect { game -> 
        def play1 = game.'play1'
        def goal = game.'goal'
        computeScore(play1, goal) 
    }
}

assert 3 + 0 == computeScores([buildGame('A X')]).sum()
assert 1 + 3 == computeScores([buildGame('A Y')]).sum()
assert 2 + 6 == computeScores([buildGame('A Z')]).sum()
assert 1 + 0 == computeScores([buildGame('B X')]).sum()
assert 2 + 3 == computeScores([buildGame('B Y')]).sum()
assert 3 + 6 == computeScores([buildGame('B Z')]).sum()
assert 2 + 0 == computeScores([buildGame('C X')]).sum()
assert 3 + 3 == computeScores([buildGame('C Y')]).sum()
assert 1 + 6 == computeScores([buildGame('C Z')]).sum()

def isTest = false

if (isTest) {
    println "TRACER terminating"
    System.exit(0)
}

// ----------------------------------
// -- main 

// Note this is not efficient

def guideLines = new File("guide.txt") as List
def games = buildGames(guideLines)
def scores = computeScores(games)
def total = scores.sum()
println "total: " + total

println "Ready."

