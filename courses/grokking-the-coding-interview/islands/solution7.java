package islands;

import java.util.*;

class CycleInAMatrix {

    public static boolean searchGridCycle(char[][] grid, int numberOfLines, int numberOfColumns, int startLine, int startColumn) {
        boolean[][] visited = new boolean[numberOfLines][numberOfColumns];

        char searchedChar = grid[startLine][startColumn];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{startLine, startColumn});

        while (queue.size() > 0) {
            int[] pos = queue.poll();
            int line = pos[0];
            int column = pos[1];

            if (visited[line][column] == true) continue;

            visited[line][column] = true;
            grid[line][column] = '*';

            int countConnections = 0;
            if (line + 1 < numberOfLines) {
                if (grid[line+1][column] == searchedChar) queue.add(new int[]{line+1, column});
                if (visited[line+1][column]) countConnections += 1;
            } 
            if (line - 1 >= 0) {
                if (grid[line-1][column] == searchedChar) queue.add(new int[]{line-1, column});
                if (visited[line-1][column]) countConnections += 1;
            }
            if (column + 1 < numberOfColumns) {
                if (grid[line][column+1] == searchedChar) queue.add(new int[]{line, column+1});
                if (visited[line][column+1]) countConnections += 1;
            }
            if (column - 1 >= 0) {
                if (grid[line][column-1] == searchedChar) queue.add(new int[]{line, column-1});
                if (visited[line][column-1]) countConnections += 1;
            }

            if (countConnections >= 2) return true;
        }

        return false;
    }

    public static boolean gridHasCycle(char[][] grid) {
        int numberOfLines = grid.length;
        int numberOfColumns = grid[0].length;

        for (int line = 0; line < numberOfLines; line++) {
            for (int column = 0; column < numberOfColumns; column++) {
                if (grid[line][column] != '*') { // '*' will represent visited areas
                    if (searchGridCycle(grid, numberOfLines, numberOfColumns, line, column)) return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(
            gridHasCycle(new char[][] {
                {'a', 'a', 'a', 'a' },
                {'b', 'a', 'c', 'a' },
                {'b', 'a', 'c', 'a' },
                {'b', 'a', 'a', 'a' },
            })
        );
        System.out.println("------------");
        System.out.println(
            gridHasCycle(new char[][] {
                {'a', 'a', 'a', 'a' },
                {'a', 'b', 'b', 'a' },
                {'a', 'b', 'a', 'a' },
                {'a', 'a', 'a', 'c' },
            })
        );
        System.out.println("------------");
        System.out.println(
            gridHasCycle(new char[][] {
                {'a', 'b', 'e', 'b' },
                {'b', 'b', 'c', 'b' },
                {'b', 'c', 'c', 'd' },
                {'d', 'c', 'd', 'd' },
            })
        );
        System.out.println("------------");
        System.out.println(
            gridHasCycle(new char[][] {
                {'b', 'b', 'e', 'b' },
                {'b', 'b', 'c', 'b' },
                {'b', 'c', 'c', 'd' },
                {'d', 'c', 'd', 'd' },
            })
        );
    }
}