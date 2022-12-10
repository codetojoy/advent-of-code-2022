
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class NodeTestCase {
    def parser = new Parser()
    def root = null

    @Before
    void setUp() {
        root = Node.getRoot(doClean: true)
    }

    @Test
    void testGetRoot() {
        assertEquals true, root.isRoot
        assertEquals '/', root.name
    }

    @Test
    void testApply_dir() {
        // test
        root.apply(parser.parse('dir abc'))

        assertEquals 1, root.subDirs.size()
        assertEquals 'abc', root.findSubDirByName('abc').name
        assertEquals root.name, root.findSubDirByName('abc').parent.name
    }

    @Test
    void testApply_file() {
        // test
        root.apply(parser.parse('5150 def'))

        assertEquals 1, root.files.size()
        assertEquals 'def', root.findFileByName('def').name
        assertEquals 5150, root.findFileByName('def').size
        assertEquals root.name, root.findFileByName('def').parent.name
    }

    @Test
    void testApply_cd_random() {
        root.apply(parser.parse('dir foo'))
        root.apply(parser.parse('dir bar'))
        root.apply(parser.parse('dir baz'))

        // test
        root.apply(parser.parse('$ cd foo'))

        assertEquals 'foo', Node.cursor.name
    }

    @Test
    void testApply_cd_up() {
        root.apply(parser.parse('dir foo'))
        root.apply(parser.parse('dir bar'))
        root.apply(parser.parse('dir baz'))
        root.apply(parser.parse('$ cd foo'))

        // test
        root.apply(parser.parse('$ cd ..'))

        assertEquals true, Node.cursor.isRoot
    }

    @Test
    void testApply_cd_root() {
        root.apply(parser.parse('dir foo'))
        root.apply(parser.parse('$ cd foo'))
        root.apply(parser.parse('dir bar'))
        root.apply(parser.parse('$ cd bar'))

        // test
        root.apply(parser.parse('$ cd /'))

        assertEquals true, Node.cursor.isRoot
    }

    @Test
    void testGetPath_basic() {
        root.apply(parser.parse('dir foo'))
        root.apply(parser.parse('$ cd foo'))
        root.apply(parser.parse('dir bar'))
        root.apply(parser.parse('$ cd bar'))
        root.apply(parser.parse('dir baz'))
        def cursor = root.apply(parser.parse('$ cd baz'))

        // test
        def result = Node.getPath(cursor)

        assertEquals '/foo/bar/baz', result
    }
}
