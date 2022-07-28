package islands;

import java.util.*;

class BiggestIsland {


public static int getIslandSize(int[][] map, int line, int column, int numOfLines, int numOfColumns) {
        int islandSize = 0;
        Queue<int[]> queue = new LinkedList<int[]>();

        queue.add(new int[]{line, column});
        map[line][column] = 0;

        while(queue.size() > 0) {
            islandSize++;
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
        return islandSize;
    }

    public static int countNumberOfIslands(int[][] map) {
        int largestIsland = 0;

        if (map.length == 0) return largestIsland;

        int numOfLines = map.length;
        int numOfColumns = map[0].length;

        for (int line = 0; line < numOfLines; line++) {
            for (int column = 0; column < numOfColumns; column++) {
                if (map[line][column] == 1) {
                    largestIsland = Math.max(largestIsland, getIslandSize(map, line, column, numOfLines, numOfColumns));
                }
            }
        }

        return largestIsland;
    }


    public static void main(String[] args) {
        System.out.println(countNumberOfIslands(new int[][]{
            {0, 1, 1, 1, 0},
            {0, 0, 1, 1, 1},
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
