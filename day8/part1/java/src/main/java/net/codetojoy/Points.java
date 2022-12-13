
package net.codetojoy;

import java.util.*;
import java.util.stream.*;

class Points {
    static void assertInRange(int n, int val) {
        if (! (val >= 0 && val <= n - 1)) {
            throw new IllegalStateException("illegal values");
        }
    }

    static void assertSafe(int n, int row, int col) {
        assertInRange(n, row);
        assertInRange(n, col);
    }

    static boolean isEdge(int n, int row, int col) {
        return row == 0 || row == (n - 1) || col == 0 || col == (n - 1);
    }

    static boolean isEdge(int n, Point point) {
        return isEdge(n, point.row(), point.col());
    }

    static List<Point> getAllPoints(int n) {
        var points = new ArrayList<Point>();

        IntStream.range(0, n).forEach((row) -> {
            IntStream.range(0, n).forEach((col) -> {
                assertSafe(n, row, col);
                points.add(new Point(row, col, isEdge(n, row, col)));
            });
        });

        return points;
    }

    static List<Point> getPoints(Point point, Direction direction, int n) {
        var points = new ArrayList<Point>();

        switch (direction) {
            case RIGHT -> {
                int i = point.row();
                int j = point.col() + 1;
                while (j < n) {
                    points.add(new Point(i, j, isEdge(n, i, j)));
                    j++;
                }
            }
            case LEFT -> {
                int i = point.row();
                int j = point.col() - 1;
                while (j >= 0) {
                    points.add(new Point(i, j, isEdge(n, i, j)));
                    j--;
                }
            }
            case UP -> {
                int i = point.row() - 1;
                int j = point.col();
                while (i >= 0) {
                    points.add(new Point(i, j, isEdge(n, i, j)));
                    i--;
                }
            }
            case DOWN -> {
                int i = point.row() + 1;
                int j = point.col();
                while (i < n) {
                    points.add(new Point(i, j, isEdge(n, i, j)));
                    i++;
                }
            }
        }

        return points;
    }
}
