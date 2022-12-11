
package net.codetojoy

class Parser {
    static final def CD_REGEX = /\$ cd (.*)/
    static final def DIR_REGEX = /dir (.*)/
    static final def FILE_REGEX = /(\d+) (.*)/

    def parse(line) {
        def matcher = null

        matcher = (line =~ CD_REGEX)
        if (matcher.matches()) {
            def dir = matcher[0][1]
            return new ChangeDir(dir)
        }

        matcher = (line =~ DIR_REGEX)
        if (matcher.matches()) {
            def dir = matcher[0][1]
            return new DirListing(dir)
        }

        matcher = (line =~ FILE_REGEX)
        if (matcher.matches()) {
            def size = matcher[0][1] as int
            def name = matcher[0][2]
            return new FileListing(name, size)
        }

        return new NoOp()
    }
}
