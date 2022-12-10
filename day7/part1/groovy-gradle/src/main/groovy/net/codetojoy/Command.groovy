
package net.codetojoy

import groovy.transform.ToString

@ToString
abstract class Command {
    def type
    def payload
}

class NoOp extends Command {
    NoOp() {
        type = 'no-op'
    }
}

class ChangeDir extends Command {
    ChangeDir(def dir) {
        type = 'cd'
        payload = dir
    }
}

class DirListing extends Command {
    DirListing(def dir) {
        type = 'dir'
        payload = dir
    }
}

class FileListing extends Command {
    FileListing(def filename, def size) {
        type = 'file'
        payload = new Expando()
        payload.name = filename
        payload.size = size
    }
}