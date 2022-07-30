package islands;

import java.util.*;

class IslandPerimeter {

    public static int getIslandPerimeter(int[][] grid) {
        int perimeter = 0;
        int numberOfLines = grid.length;
        int numberOfColumns = grid[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();

        for (int line = 0; line < numberOfLines; line++) {
            for (int column = 0; column < numberOfColumns; column++) {
                if (grid[line][column] == 1) {
                    queue.add(new int[]{line, column});
                    break;
                }
            }
            if (queue.size() > 0) break;
        }

        int[][] visited = new int[numberOfLines][numberOfColumns];

        while(queue.size() > 0) {
            int[] pos = queue.poll();
            int line = pos[0];
            int column = pos[1];

            grid[line][column] = 0; 
            if (visited[line][column] == 1) continue;
            visited[line][column] = 1;

            int localPerimeter = 4;

            if (line + 1 < numberOfLines) {
                if (grid[line+1][column] == 1 || visited[line+1][column] == 1) localPerimeter--;
                if (grid[line+1][column] == 1) queue.add(new int[]{line+1, column});
            }
            if (line - 1 >= 0) {
                if (grid[line-1][column] == 1 || visited[line-1][column] == 1) localPerimeter--;
                if (grid[line-1][column] == 1) queue.add(new int[]{line-1, column});
            }
            if (column + 1 < numberOfColumns) {
                if (grid[line][column+1] == 1 || visited[line][column+1] == 1) localPerimeter--;
                if (grid[line][column+1] == 1) queue.add(new int[]{line, column+1});
            }
            if (column - 1 >= 0) {
                if (grid[line][column-1] == 1 || visited[line][column-1] == 1) localPerimeter--;
                if (grid[line][column-1] == 1) queue.add(new int[]{line, column-1});
            }
            perimeter += localPerimeter;
        }

        return perimeter;
    }

    public static void main(String[] args) {
        System.out.println(getIslandPerimeter(new int[][]{
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0}
        }));
        System.out.println("------------");
        System.out.println(getIslandPerimeter(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 0, 0}
        }));
        System.out.println("------------");
        System.out.println(getIslandPerimeter(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        }));
        System.out.println("------------");
        System.out.println(getIslandPerimeter(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        }));
    }
}