package islands;

import java.util.*;

class NumberOfDistinctIslands {

    public static void findClosedIsland(int[][] map, int line, int column, int numOfLines, int numOfColumns, Set<String> islandPatterns) {
        Queue<int[]> queue = new LinkedList<int[]>();

        queue.add(new int[]{line, column});
        map[line][column] = 0;

        StringBuilder pattern = new StringBuilder();

        while(queue.size() > 0) {
            int[] pos = queue.poll();
            int currLine = pos[0];
            int currColumn = pos[1];

            if (currLine+1 < numOfLines && map[currLine+1][currColumn] == 1) {
                queue.add(new int[]{currLine+1, currColumn});
                map[currLine+1][currColumn] = 0;
                pattern.append("D"); // Down
            }
            if (currLine-1 >= 0 && map[currLine-1][currColumn] == 1) {
                queue.add(new int[]{currLine-1, currColumn});
                map[currLine-1][currColumn] = 0;
                pattern.append("U"); // Up
            }
            if (currColumn+1 < numOfColumns && map[currLine][currColumn+1] == 1) {
                queue.add(new int[]{currLine, currColumn+1});
                map[currLine][currColumn+1] = 0;
                pattern.append("R"); // Right
            }
            if (currColumn-1 >= 0 && map[currLine][currColumn-1] == 1) {
                queue.add(new int[]{currLine, currColumn-1});
                map[currLine][currColumn-1] = 0;
                pattern.append("L"); // Left
            }
        }

        islandPatterns.add(pattern.toString());
    }

    public static int countNumberOfUniqueIslands(int[][] map) {
        Set<String> islandPatterns = new HashSet<String>();

        if (map.length == 0) return islandPatterns.size();

        int lineLength = map.length;
        int columnLength = map[0].length;

        for (int line = 0; line < lineLength; line++) {
            for (int column = 0; column < columnLength; column++) {
                if (map[line][column] == 1) {
                    findClosedIsland(map, line, column, lineLength, columnLength, islandPatterns);
                }
            }
        }
        return islandPatterns.size();
    }



    public static void main(String[] args) {
        System.out.println(countNumberOfUniqueIslands(new int[][]{
            {1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 0, 1},
            {0, 1, 1, 0, 1}
        }));

        System.out.println("-----------");
        System.out.println(countNumberOfUniqueIslands(new int[][]{
            {1, 1, 0, 1},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {1, 1, 0, 0},
            {0, 1, 1, 0}
        }));
    }
}
