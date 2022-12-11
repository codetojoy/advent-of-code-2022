
package net.codetojoy

class SizeFilter {
    static def findTotal(pathMap, threshold) {
        def total = pathMap.findAll { path, sizeInfo ->
                        (sizeInfo.size <= threshold)
                    }.collect { path, sizeInfo ->
                        sizeInfo.size
                    }.sum()
        total
    }

    static def findDirForDeletion(pathMap, maxSize, targetSize) {
        def rootSize = pathMap['/'].size
        def freeSize = maxSize - rootSize
        def targetDelta = targetSize - freeSize
        def candidateDirs = pathMap.findAll { path, sizeInfo ->
                        (sizeInfo.size >= targetDelta)
                    }
        def candidateDir = candidateDirs.min { it.value.size }
        candidateDir.value.path
    }
}
