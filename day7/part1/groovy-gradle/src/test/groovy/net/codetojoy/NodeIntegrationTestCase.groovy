
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class NodeIntegrationTestCase {
    def parser = new Parser()
    def root = Node.getRoot(doClean: true)
    def cursor

    @Before
    void setUp() {
        root.apply(parser.parse('$ cd /'))
        root.apply(parser.parse('$ ls'))
        root.apply(parser.parse('dir a'))
        root.apply(parser.parse('14848514 b.txt'))
        root.apply(parser.parse('8504156 c.dat'))
        root.apply(parser.parse('dir d'))
        root.apply(parser.parse('$ cd a'))
        root.apply(parser.parse('$ ls'))
        root.apply(parser.parse('dir e'))
        root.apply(parser.parse('29116 f'))
        root.apply(parser.parse('2557 g'))
        root.apply(parser.parse('62596 h.lst'))
        root.apply(parser.parse('$ cd e'))
        root.apply(parser.parse('$ ls'))
        root.apply(parser.parse('584 i'))
        root.apply(parser.parse('$ cd ..'))
        root.apply(parser.parse('$ cd ..'))
        root.apply(parser.parse('$ cd d'))
        root.apply(parser.parse('$ ls'))
        root.apply(parser.parse('4060174 j'))
        root.apply(parser.parse('8033020 d.log'))
        root.apply(parser.parse('5626152 d.ext'))
        root.apply(parser.parse('7214296 k'))
    }

    @Test
    void testCanary() {
        assertEquals true, root.isRoot
        assertEquals '/', root.name
    }

    @Test
    void testWalkDir_root() {
        // ls /
        cursor = root.apply(parser.parse('$ cd /'))

        assertNotNull cursor.findSubDirByName('a')
        assertEquals 14848514, cursor.findFileByName('b.txt').size
        assertEquals 8504156, cursor.findFileByName('c.dat').size
        assertNotNull cursor.findSubDirByName('d')
        /*
        root.apply(parser.parse('dir a'))
        root.apply(parser.parse('14848514 b.txt'))
        root.apply(parser.parse('8504156 c.dat'))
        root.apply(parser.parse('dir d'))
        */
    }

    @Test
    void testWalkDir_root_a() {
        // ls /a
        root.apply(parser.parse('$ cd /'))
        root.apply(parser.parse('$ cd a'))
        cursor = root.apply(parser.parse('$ ls'))

        assertNotNull cursor.findSubDirByName('e')
        assertEquals 29116, cursor.findFileByName('f').size
        assertEquals 2557, cursor.findFileByName('g').size
        assertEquals 62596, cursor.findFileByName('h.lst').size
        /*
        root.apply(parser.parse('29116 f'))
        root.apply(parser.parse('dir e'))
        root.apply(parser.parse('2557 g'))
        root.apply(parser.parse('62596 h.lst'))
        */
    }

    @Test
    void testWalkDir_root_a_e() {
        // ls /a/e
        root.apply(parser.parse('$ cd /'))
        root.apply(parser.parse('$ cd a'))
        root.apply(parser.parse('$ cd e'))
        cursor = root.apply(parser.parse('$ ls'))

        assertEquals 584, cursor.findFileByName('i').size
        /*
        root.apply(parser.parse('584 i'))
        */
    }

    @Test
    void testWalkDir_root_a_e_up_up_d() {
        // ls /a/e
        root.apply(parser.parse('$ cd /'))
        root.apply(parser.parse('$ cd a'))
        root.apply(parser.parse('$ cd e'))
        root.apply(parser.parse('$ cd ..'))
        root.apply(parser.parse('$ cd ..'))
        root.apply(parser.parse('$ cd d'))
        cursor = root.apply(parser.parse('$ ls'))

        assertEquals 4060174, cursor.findFileByName('j').size
        assertEquals 8033020, cursor.findFileByName('d.log').size
        assertEquals 5626152, cursor.findFileByName('d.ext').size
        assertEquals 7214296, cursor.findFileByName('k').size
        /*
        root.apply(parser.parse('4060174 j'))
        root.apply(parser.parse('8033020 d.log'))
        root.apply(parser.parse('5626152 d.ext'))
        root.apply(parser.parse('7214296 k'))
        */
    }
}
