
package net.codetojoy;

import java.util.*;

public class Grid {
    int n;
    int[][] grid;

    Grid(int n) {
        this.n = n;
        grid = new int[n][n];
    }

    int get(int row, int col) {
        return grid[row][col];
    }

    int get(Point point) {
        return get(point.row(), point.col());
    }

    void set(int row, int col, int value) {
        grid[row][col] = value;
    }

    void setRow(int row, List<Integer> values) {
        int index = 0;
        for (int value : values) {
            set(row, index, value);
            index++;
        }
    }

    boolean isVisible(Point point, Direction direction) {
        var maxValue = -1;
        for (var p : Points.getPoints(point, direction, n)) {
            var value = get(p);
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue < get(point);
    }

    boolean isVisible(Point point) {
        return (isVisible(point, Direction.RIGHT) || isVisible(point, Direction.LEFT)
        || isVisible(point, Direction.UP) || isVisible(point, Direction.DOWN));
    }

    int countVisible() {
        int count = 0;

        for (var point : Points.getAllPoints(n)) {
            if (Points.isEdge(n, point) || isVisible(point)) {
                count++;
            }
        }

        return count;
    }
}
