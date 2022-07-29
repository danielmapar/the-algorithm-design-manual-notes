package islands;

import java.util.*;

class FloodFill {
    
    public static int[][] getFloodFill(int[][] grid, int[] startingPoint, int newColor) {

        int currentLine = startingPoint[0];
        int currentColumn = startingPoint[1];
        
        int numberOfLines = grid.length;
        int numberOfColumns = grid[0].length;

        int currentColor = grid[currentLine][currentColumn];

        if (newColor == currentColor) return grid;

        Queue<int[]> queue = new LinkedList<int[]>();

        queue.add(new int[]{currentLine, currentColumn});
            
        while (queue.size() > 0) {
            int[] pos = queue.poll();
            int line = pos[0];
            int column = pos[1];

            grid[line][column] = newColor;

            if (line+1 < numberOfLines && grid[line+1][column] == currentColor) {
                queue.add(new int[]{line+1, column});
            }
            if (line-1 >= 0 && grid[line-1][column] == currentColor) {
                queue.add(new int[]{line-1, column});
            }
            if (column+1 < numberOfColumns && grid[line][column+1] == currentColor) {
                queue.add(new int[]{line, column+1});
            }
            if (column-1 >= 0 && grid[line][column-1] == currentColor) {
                queue.add(new int[]{line, column-1});
            }
        }

        return grid;
    }

    public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
    
    public static void main(String[] args) {
        print2D(
            getFloodFill(new int[][] {
                    {0, 1, 1, 1, 0},
                    {0, 0, 0, 1, 1},
                    {0, 1, 1, 1, 0},
                    {0, 1, 1, 0, 0},
                    {0, 0, 0, 0, 0}
                }, 
                new int[]{1, 3},
                2
            )
        );
        System.out.println("--------------");
        print2D(
            getFloodFill(new int[][] {
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0}
                }, 
                new int[]{3, 2},
                5
            )
        );
    }
}