
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

        log("knot6 ${isQuadrant1} ${isQuadrant2} ${isQuadrant3} ${isQuadrant4} ${deltaX} ${deltaY}")

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
            log("knot6 ${newX} ${newY}")
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
            assert deltaX != deltaY
            if (absDeltaX > absDeltaY) {
                // 3b
                newX = point.x + 1
                newY = point.y
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

        if (deltaX == -2 && deltaY == 0) {
            location.moveLeft(1)
        } else if (deltaX == 2 && deltaY == 0) {
            location.moveRight(1)
        } else if (deltaX == 0 && deltaY == 2) {
            location.moveUp(1)
        } else if (deltaX == 0 && deltaY == -2) {
            location.moveDown(1)
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
        log("knot6 hello")
        if (areTouching(point)) {
            log("knot6 are touching")
            return
        }

        def deltaX = point.x - location.point.x
        def deltaY = point.y - location.point.y
        def isMove = false
        def isPlanar = (deltaX == 0 || deltaY == 0)

        if (isPlanar) {
            log("knot6 planar")
            isMove = handlePlanar(point, deltaX, deltaY)
        } else {
            log("knot6 non-planar")
            isMove = handleNonPlanar(point, deltaX, deltaY)
        }

        if (isMove) {
            auditor.track(location.point)
        }
    }

    // @Override
    void track_old(Point point) {
        def deltaX = point.x - location.point.x
        def deltaY = point.y - location.point.y
        // println("TRACER Tail new: " + point.toString() + " this: "
        //            + location.toString() + " dX: " + deltaX + " dY: " + deltaY)

        def isMove = true

        // 4 | 1
        // -----
        // 3 | 2

        def isQuadrant1 = (deltaX > 0) && (deltaY > 0)
        def isQuadrant2 = (deltaX > 0) && (deltaY < 0)
        def isQuadrant3 = (deltaX < 0) && (deltaY < 0)
        def isQuadrant4 = (deltaX < 0) && (deltaY > 0)
        def scalar = Math.max(Math.abs(deltaX), Math.abs(deltaY)) - 1

        /*
        if (deltaX ==3 || deltaY ==3) {
            throw new IllegalStateException("bingo")
        }

        if (deltaX == -2 && deltaY == 0) {
            location.moveLeft(1)
        } else if (deltaX == 2 && deltaY == 0) {
            location.moveRight(1)
        } else if (deltaX == 0 && deltaY == 2) {
            location.moveUp(1)
        } else if (deltaX == 0 && deltaY == -2) {
            location.moveDown(1)
        } else if ((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)) {
            // 1 right, 2 up
            // 2 right, 1 up
            location.moveBy(1, 1)
        } else if ((deltaX == 1 && deltaY == -2) || (deltaX == 2 && deltaY == -1)) {
            // 1 right, 2 down
            // 2 right, 1 down
            location.moveBy(1, -1)
        } else if ((deltaX == -1 && deltaY == 2) || (deltaX == -2 && deltaY == 1)) {
            // 1 left, 2 up
            // 2 left, 1 up
            location.moveBy(-1, 1)
        } else if ((deltaX == -1 && deltaY == -2) || (deltaX == -2 && deltaY == -1)) {
            // 1 left, 2 down
            // 2 left, 1 down
            location.moveBy(-1, -1)
        } else {
            // no-op
            isMove = false
        }
        */

        if (isMove) {
            auditor.track(location.point)
        }
    }
}
