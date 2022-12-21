
package net.codetojoy

class Knot implements MoveListener {
    def location
    def auditor = new Auditor()
    def verbose

    def Knot(moveListener, verbose = false) {
        this.location = new Location(moveListener)
        this.verbose = verbose
    }

    String toString() {
        "location: ${location}"
    }

    int getNumMoves() {
        auditor.getNumMoves()
    }

    def logPoints() {
        auditor.logPoints()
    }

    def handleNonPlanar(point, deltaX, deltaY) {
        def isMove = false
        // 4 | 1
        // --.--
        // 3 | 2
        assert deltaX != 0 || deltaY != 0

        def isQuadrant1 = (deltaX > 0) && (deltaY > 0)
        def isQuadrant2 = (deltaX > 0) && (deltaY < 0)
        def isQuadrant3 = (deltaX < 0) && (deltaY < 0)
        def isQuadrant4 = (deltaX < 0) && (deltaY > 0)

        def absDeltaX = Math.abs(deltaX)
        def absDeltaY = Math.abs(deltaY)

        def newX = -1
        def newY = -1

        if (isQuadrant1) {
            if (deltaX > deltaY) {
                newX = point.x - 1
                newY = point.y
            } else if (deltaX == deltaY) {
                newX = point.x - 1
                newY = point.y - 1
            } else {
                newX = point.x
                newY = point.y - 1
            }
            isMove = true
        } else if (isQuadrant2) {
            assert deltaX != deltaY
            if (absDeltaX > absDeltaY) {
                // 2b
                newX = point.x - 1
                newY = point.y
            } else {
                // 2a
                newX = point.x
                newY = point.y + 1
            }
            isMove = true
        } else if (isQuadrant3) {
            // assert deltaX != deltaY
            if (absDeltaX > absDeltaY) {
                // 3b
                newX = point.x + 1
                newY = point.y
            } else if (deltaX == deltaY) {
                newX = point.x + 1
                newY = point.y + 1
            } else {
                // 3a
                newX = point.x
                newY = point.y + 1
            }
            isMove = true
        } else if (isQuadrant4) {
            assert deltaX != deltaY
            if (absDeltaX > absDeltaY) {
                // 4b
                newX = point.x + 1
                newY = point.y
            } else {
                // 4a
                newX = point.x
                newY = point.y - 1
            }
            isMove = true
        }

        if (isMove) {
            location.move(newX, newY)
        }

        isMove
    }

    def handlePlanar(point, deltaX, deltaY) {
        assert deltaX == 0 || deltaY == 0
        def isMove = true

        def absDeltaX = Math.abs(deltaX)
        def absDeltaY = Math.abs(deltaY)

        if (deltaX < 0 && deltaY == 0) {
            location.moveLeft(absDeltaX - 1)
        } else if (deltaX > 0 && deltaY == 0) {
            location.moveRight(absDeltaX - 1)
        } else if (deltaX == 0 && deltaY > 0) {
            location.moveUp(absDeltaY - 1)
        } else if (deltaX == 0 && deltaY < 0) {
            location.moveDown(absDeltaY - 1)
        } else {
            isMove = false
        }

        isMove
    }

    def areTouching(Point point) {
        def absDeltaX = Math.abs(point.x - location.point.x)
        def absDeltaY = Math.abs(point.y - location.point.y)
        Math.max(absDeltaX, absDeltaY) <= 1
    }

    void log(msg) {
        if (verbose) {
            println "TRACER ${msg}"
        }
    }

    @Override
    void track(Point point) {
        if (areTouching(point)) {
            return
        }

        def deltaX = point.x - location.point.x
        def deltaY = point.y - location.point.y
        def isMove = false
        def isPlanar = (deltaX == 0 || deltaY == 0)

        if (isPlanar) {
            isMove = handlePlanar(point, deltaX, deltaY)
        } else {
            isMove = handleNonPlanar(point, deltaX, deltaY)
        }

        if (isMove) {
            auditor.track(location.point)
        }
    }
}
