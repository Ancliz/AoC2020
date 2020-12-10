package day09;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import day.Day;
import util.Parse;

public class Day09 extends Day {
    @SuppressWarnings("unchecked")
    private List<Long> input = (List<Long>) Parse.listByLine(scanner, false, true);
    private final int PREAMBLE_LEN = 25;
    private int sumIndex;

    public Day09() throws FileNotFoundException {
        super();
        sumIndex = PREAMBLE_LEN;
    }

    @Override
    public void run() {
        findPair();
        System.out.println(input.get(sumIndex));
        System.out.println("Weakness: " + findEncryptionWeakness());
    }

    private long[] findPair() {
        long x = 0, y = 0;
        int curr = 0;

        for(int i = curr; i < sumIndex; ++i) {
            if(inPrev25(i, input.get(sumIndex) - input.get(i))) {
                sumIndex++;
                curr = sumIndex - PREAMBLE_LEN;
                i = curr - 1;
            }
        }
        return new long[] { x, y };
    }

    private boolean inPrev25(int curr, long num) {
        if(num < 0)
            return false;
        for(int i = curr; i < sumIndex; ++i) {
            if (input.get(i) == num)
                return true;
        }
        return false;
    }

    private long findEncryptionWeakness() {
        List<Long> addends = new ArrayList<Long>();
        long sought = input.get(sumIndex);
        long sum = 0;
        int lower = 0;
        boolean found = false;

        while(!found) {
            for(int i = lower; i < sumIndex; ++i) {
                sum += input.get(i);
                addends.add(input.get(i));
                
                if(sum > sought)
                    break;

                if(sum == sought) {
                    Collections.sort(addends);
                    found = true;
                    break;
                }
            }
            if(!found) {
                lower++;
                sum = 0;
                addends.clear();
            }
        }
        return addends.get(0) + addends.get(addends.size() - 1);
    }
}
