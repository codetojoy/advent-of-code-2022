
package net.codetojoy

import groovy.transform.ToString

@ToString
class ConfigInfo {
    // e.g. [2] -> 'M'
    def infoMap = [:]

    def keys() {
        infoMap.keySet()
    }

    def get(index) {
        infoMap[index]
    }

    def put(index, item) {
        infoMap[index] = item
        this
    }
}

