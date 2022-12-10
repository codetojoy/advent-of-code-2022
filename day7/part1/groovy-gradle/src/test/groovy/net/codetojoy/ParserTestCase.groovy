
package net.codetojoy

import static org.junit.Assert.*
import org.junit.*

class ParserTestCase {
    def parser = new Parser()

    @Test
    void testParse_cd_root_type() {
        def line = '$ cd /'

        // test
        def result = parser.parse(line)

        assertEquals 'cd', result.type
    }

    @Test
    void testParse_cd_root_payload() {
        def line = '$ cd /'

        // test
        def result = parser.parse(line)

        assertEquals '/', result.payload
    }

    @Test
    void testParse_cd_random_type() {
        def line = '$ cd abc'

        // test
        def result = parser.parse(line)

        assertEquals 'cd', result.type
    }

    @Test
    void testParse_cd_random_payload() {
        def line = '$ cd abc'

        // test
        def result = parser.parse(line)

        assertEquals 'abc', result.payload
    }

    @Test
    void testDirListing_type() {
        def line = 'dir xyz'

        // test
        def result = parser.parse(line)

        assertEquals 'dir', result.type
    }

    @Test
    void testDirListing_payload() {
        def line = 'dir xyz'

        // test
        def result = parser.parse(line)

        assertEquals 'xyz', result.payload
    }

    @Test
    void testFileListing_type() {
        def line = '114478 gtpgsvv.jch'

        // test
        def result = parser.parse(line)

        assertEquals 'file', result.type
    }

    @Test
    void testFileListing_payload() {
        def line = '114478 gtpgsvv.jch'

        // test
        def result = parser.parse(line)

        assertEquals 'gtpgsvv.jch', result.payload.name
        assertEquals 114478, result.payload.size
    }
}
