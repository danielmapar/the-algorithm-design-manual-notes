package islands;

import java.util.ArrayDeque;
import java.util.Deque;

class NumberOfIslands {

    public static void searchGrid(int[][] map, int line, int column, int lineLength, int columnLength) {
        map[line][column] = 0;
        if (line+1 < lineLength && map[line+1][column] == 1) {
            searchGrid(map, line+1, column, lineLength, columnLength);
        }
        if (line-1 >= 0 && map[line-1][column] == 1) {
            searchGrid(map, line-1, column, lineLength, columnLength);
        }
        if (column+1 < columnLength && map[line][column+1] == 1) {
            searchGrid(map, line, column+1, lineLength, columnLength);
        }
        if (column-1 >= 0 && map[line][column-1] == 1) {
            searchGrid(map, line, column-1, lineLength, columnLength);
        }
    }

    public static void searchGridV2(int[][] map, int line, int column, int numOfLines, int numOfColumns) {
        Deque<int[]> queue = new ArrayDeque<int[]>();

        queue.add(new int[]{line, column});
        map[line][column] = 0;

        while(queue.size() > 0) {
            int[] pos = queue.poll();
            int currLine = pos[0];
            int currColumn = pos[1];

            if (currLine+1 < numOfLines && map[currLine+1][currColumn] == 1) {
                queue.add(new int[]{currLine+1, currColumn});
                map[currLine+1][currColumn] = 0;
            }
            if (currLine-1 >= 0 && map[currLine-1][currColumn] == 1) {
                queue.add(new int[]{currLine-1, currColumn});
                map[currLine-1][currColumn] = 0;
            }
            if (currColumn+1 < numOfColumns && map[currLine][currColumn+1] == 1) {
                queue.add(new int[]{currLine, currColumn+1});
                map[currLine][currColumn+1] = 0;
            }
            if (currColumn-1 >= 0 && map[currLine][currColumn-1] == 1) {
                queue.add(new int[]{currLine, currColumn-1});
                map[currLine][currColumn-1] = 0;
            }
        }
    }

    public static int countNumberOfIslands(int[][] map) {
        int numberOfIslands = 0;

        if (map.length == 0) return numberOfIslands;

        int lineLength = map.length;
        int columnLength = map[0].length;

        for (int line = 0; line < lineLength; line++) {
            for (int column = 0; column < columnLength; column++) {
                if (map[line][column] == 1) {
                    // searchGrid(map, line, column, lineLength, columnLength);
                    searchGridV2(map, line, column, lineLength, columnLength);
                    numberOfIslands++;
                }
            }
        }

        return numberOfIslands;
    }


    public static void main(String[] args) {
        System.out.println(countNumberOfIslands(new int[][]{
            {0, 1, 1, 1, 0},
            {0, 0, 0, 1, 1},
            {0, 1, 1, 1, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0}
        }));

        System.out.println(countNumberOfIslands(new int[][]{
            {1, 0, 1, 0, 0},
            {1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0}
        }));

        System.out.println(countNumberOfIslands(new int[][]{
            {1, 1, 1, 0, 0},
            {0, 1, 0, 0, 1},
            {0, 0, 1, 1, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0}
        }));
    }
}