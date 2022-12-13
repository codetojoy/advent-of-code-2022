
package net.codetojoy;

import java.util.*;

class Parser {
    Grid parse(List<String> lines) {
        int n = lines.size();
        var grid = new Grid(n);

        int row = 0;

        for (var line : lines) {
            var intLine = new ArrayList<Integer>();

            for (var ch : line.toCharArray()) {
                intLine.add(Integer.parseInt("" + ch));
            }

            grid.setRow(row, intLine);
            row++;
        }

        return grid;
    }
}
