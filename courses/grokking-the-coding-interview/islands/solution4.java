package islands;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class NumberOfClosedIslands {

    public static int findClosedIsland(int[][] map, int line, int column, int numOfLines, int numOfColumns) {
        Queue<int[]> queue = new LinkedList<int[]>();

        queue.add(new int[]{line, column});
        map[line][column] = 0;

        int isIsland = 1;

        while(queue.size() > 0) {
            int[] pos = queue.poll();
            int currLine = pos[0];
            int currColumn = pos[1];

            if (currLine+1 == numOfLines || currLine-1 < 0 || currColumn+1 == numOfColumns || currColumn-1 < 0) {
                // We will keep navigating the island no matter if it is not a "closed island".
                // That will guarantee we visit the island completely (no revisiting).
                isIsland = 0;
            }

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
        return isIsland;
    }

    public static int countNumberOfIslands(int[][] map) {
        int numberOfIslands = 0;

        if (map.length == 0) return numberOfIslands;

        int lineLength = map.length;
        int columnLength = map[0].length;

        for (int line = 0; line < lineLength; line++) {
            for (int column = 0; column < columnLength; column++) {
                if (map[line][column] == 1) {
                    numberOfIslands += findClosedIsland(map, line, column, lineLength, columnLength);
                }
            }
        }

        return numberOfIslands;
    }


    public static void main(String[] args) {
        System.out.println(countNumberOfIslands(new int[][]{
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 1, 1, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0}
        }));

        System.out.println("-----------");
        System.out.println(countNumberOfIslands(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0}
        }));
    }
}