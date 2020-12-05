package day05;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import day.Day;

public class Day05 extends Day {

    public Day05() throws FileNotFoundException {
        super();
    }

    @Override
    public void run() {
        int seat = 0;
        List<Integer> seats = new ArrayList<Integer>();

        while(scanner.hasNext()) {
            seats.add(Integer.parseInt(scanner.next().replaceAll("F|L", "0").replaceAll("B|R", "1"), 2));
        }
        Collections.sort(seats);

        for(int i = 0; i < seats.size(); ++i) {
            if(seats.get(i) + 1 != seats.get(i+1)) {
                seat = seats.get(i) + 1;
                break;
            }
        }
        System.out.println("Seat: " + seat);
    }
    
}
