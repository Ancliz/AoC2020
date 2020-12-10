package day10;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import day.Day;
import util.Parse;

public class Day10 extends Day {
    @SuppressWarnings("unchecked")
    private List<Integer> ratings = (List<Integer>) Parse.listByLine(scanner, true, false);
    Map<Integer, Long> visited = new HashMap<Integer, Long>();

    public Day10() throws FileNotFoundException {
        super();
        ratings.add(0);
        Collections.sort(ratings);
        ratings.add(ratings.get((ratings.size()-1)) + 3);
    }

    @Override
    public void run() {
        int[] diff = countDiff();
        long count = countPaths(0);
        System.out.println("Diff: " + diff[0] * diff[1] + "\nPaths: " + count);
    }
    
    private int[] countDiff() {
        int diff_one = 0, diff_thr = 0;

        for(int i = 0; i < ratings.size() - 1; ++i) {
            int diff = ratings.get(i+1) - ratings.get(i);
            if     (1 == diff) diff_one++;
            else if(3 == diff) diff_thr++;
        }
        return new int[] {diff_one, diff_thr};
    }
    
    private long countPaths(int i) {
        long paths = 0;
        if(i == ratings.size() - 1)     return ++paths;
        if(visited.containsKey(i))      return visited.get(i);
        
        for(int j = i + 1; j < ratings.size(); ++j) {
            if(ratings.get(j) - ratings.get(i) <= 3)
                paths += countPaths(j);
        }
        visited.put(i, paths);
        return paths;
    }

}