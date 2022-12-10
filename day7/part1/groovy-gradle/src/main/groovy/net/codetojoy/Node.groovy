
package net.codetojoy

import groovy.transform.ToString

class Node {
    // TODO: extract these statics into something less gnarly
    static def cursor = null
    static def root = new Node(name: '/', isRoot: true)

    def parent = null
    def name = ''
    def size = 0
    def files = new ArrayList<Node>()
    def subDirs =  new ArrayList<Node>()
    def isRoot = false

    static def getRoot(def doClean = false) {
        if (doClean) {
            root = new Node(name: '/', isRoot: true)
        }
        cursor = root
        root
    }

    def findSubDirByName(def name) {
        subDirs.find { it.name == name }
    }

    def findFileByName(def name) {
        files.find { it.name == name }
    }

    static def getPath(node) {
        if (node.isRoot) {
            return ""
        } else {
            return getPath(node.parent) + "/" + node.name
        }
    }

    static def traverseForSize(node) {
        def size = node.files*.size.sum()
        node.subDirs.each { subDir ->
            def resultForSubDir = traverseForSize(subDir)
            size += resultForSubDir.size
        }
        def result = new Expando()
        result.path = getPath(node)
        result.size = size
        result
    }

    def apply(def command) {
        if (command.type == CommandType.DIR) {
            def node = new Node(parent: cursor, name: command.payload)
            cursor.subDirs << node
        } else if (command.type == CommandType.FILE) {
            def name = command.payload.name
            def size = command.payload.size
            def node = new Node(parent: cursor, name: name, size: size)
            cursor.files << node
        } else if (command.type == CommandType.CD) {
            def name = command.payload
            if (name == '..') {
                cursor = cursor.parent
            } else if (name == '/') {
                cursor = root
            } else {
                cursor = cursor.findSubDirByName(name)
            }
        }
        assert cursor
        cursor
    }

    @Override
    String toString() {
        def buffer = new StringBuffer()
        buffer.append("name: " + name + "\n")
        buffer.append("files: \n")
        files.each { file ->
            buffer.append("   " + file.size + " " + file.name + "\n")
        }
        buffer.append("subdirs: \n")
        subDirs.each { subDir ->
            buffer.append("   " + subDir.name + "\n")
        }
        buffer.toString()
    }
}
