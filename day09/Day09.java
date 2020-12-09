package day09;

import java.io.FileNotFoundException;
import java.util.List;

import day.Day;
import util.Parse;

public class Day09 extends Day {
    @SuppressWarnings("unchecked")
    private List<Long> input = (List<Long>) Parse.listByLine(scanner, true);
    private final int PREAMBLE_LEN = 25;
    private int sumIndex;

    public Day09() throws FileNotFoundException {
        super();
        sumIndex = PREAMBLE_LEN;
    }

    @Override
    public void run() {
        long[] pair = findPair();
        System.out.println(input.get(sumIndex));
    }

    private long[] findPair() {
        boolean found = false;
        long x = 0, y = 0;
        int curr = 0;

        while(!found) {

            for(int i = curr + 1; i < sumIndex; ++i) {
                x = input.get(curr);
                y = input.get(i);

                if(x + y == input.get(sumIndex)) {
                    sumIndex++;
                    curr = sumIndex - PREAMBLE_LEN;
                    break;
                }
                else if(sumIndex - 2 == curr) {
                    found = true;
                    break;
                }            
            }
            if(x + y != input.get(sumIndex - 1))
                curr++;
        }
        return new long[] {x, y};
    }
    
}
