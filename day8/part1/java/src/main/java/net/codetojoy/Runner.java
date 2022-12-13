
package net.codetojoy;

import java.io.*;
import java.util.*;

public class Runner {
    static File processArgs(String... args) {
        File file = null;
        boolean ok = false;

        if ((args != null) && (args.length >= 1)) {
            file = new File(args[0]);
            ok = file.exists();
        }

        if (!ok) { throw new IllegalArgumentException("check arg usage"); }
        return file;
    }

    public static void main(String... args) throws Exception {
        var inputFile = processArgs(args);
        var inputStream = new FileInputStream(inputFile);
        var inputLines = new ArrayList<String>();

        try (var br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputLines.add(line);
            }
        }

        var grid = new Parser().parse(inputLines);

        var numVisible = grid.countVisible();
        System.out.println("TRACER num visible: " + numVisible);

        System.out.println("Ready.");
    }
}
