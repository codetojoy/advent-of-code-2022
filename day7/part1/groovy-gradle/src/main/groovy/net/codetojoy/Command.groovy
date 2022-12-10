
package net.codetojoy

import groovy.transform.ToString

@ToString
abstract class Command {
    def type
    def payload
}

class NoOp extends Command {
    NoOp() {
        type = CommandType.NO_OP
    }
}

class ChangeDir extends Command {
    ChangeDir(def dir) {
        type = CommandType.CD
        payload = dir
    }
}

class DirListing extends Command {
    DirListing(def dir) {
        type = CommandType.DIR
        payload = dir
    }
}

class FileListing extends Command {
    FileListing(def filename, def size) {
        type = CommandType.FILE
        payload = new Expando()
        payload.name = filename
        payload.size = size
    }
}