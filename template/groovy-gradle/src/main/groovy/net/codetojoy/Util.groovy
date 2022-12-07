
package net.codetojoy

class Util {
    static final def CONFIG_REGEX = /.*\[[A-Z]\].*/

    def isConfigLine(line) {
        def matcher = (line =~ CONFIG_REGEX)
        matcher.matches()
    }
}
