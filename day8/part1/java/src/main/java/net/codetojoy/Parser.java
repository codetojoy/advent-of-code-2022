
package net.codetojoy;

import java.util.*;

class Parser {
    Grid parse(List<String> lines) {
        int n = lines.size();
        var grid = new Grid(n);

        int row = 0;

        for (var line : lines) {
            var intLine = new ArrayList<Integer>();
            var chars = line.toCharArray();

            for (var c : chars) {
                intLine.add(Integer.parseInt("" + c));
            }

            grid.setRow(row, intLine);
            row++;
        }

        return grid;
    }
}
