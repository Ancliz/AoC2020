package day03;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import util.Day;

public class Day03 extends Day {
    private List<char[]> lines = new ArrayList<char[]>();
    private int LINE_LEN = 31;
    private int LAST_LINE = 323;


    public Day03() throws FileNotFoundException {
        super();
    }


    @Override
    public void run() {
        int k = 0;

        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine().toCharArray());
            k++;
        }

        char[][] mountain = new char[LAST_LINE][LINE_LEN];

        for(int i = 0; i < LAST_LINE; ++i) {
            mountain[i] = lines.get(i);
        }

        System.out.println("trees multiplied: " + countTreesOnSlope(1, 1, mountain)* countTreesOnSlope(5, 1, mountain) * countTreesOnSlope(7, 1, mountain) *
         countTreesOnSlope(1, 2, mountain) * 171);


    }

    private int countTreesOnSlope(int xInc, int yInc, char[][] mountain) {
        int x = 0;
        int y = 0;
        int trees = 0;

        while(y != LAST_LINE - 1) {
            if(mountain[y % LAST_LINE][x % LINE_LEN] == '#')
                trees++;

            x += xInc;
            y += yInc;
        }
        return trees;
    }
    
}
