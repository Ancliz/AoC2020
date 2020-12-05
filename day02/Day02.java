package day02;

import java.io.FileNotFoundException;
import util.Day;

public class Day02 extends Day {

    public Day02() throws FileNotFoundException {
        super();
    }

    public void run() {
        int valid = 0;
        while(scanner.hasNextLine()) {
            String[] tokens = scanner.nextLine().split(" ");
            int idx1 = Integer.parseInt(tokens[0].split("-")[0]) - 1;
            int idx2 = Integer.parseInt(tokens[0].split("-")[1]) - 1;

            char c = tokens[1].split(":")[0].charAt(0);

            if(tokens[2].charAt(idx1) == c || tokens[2].charAt(idx2) == c)
                if(!(tokens[2].charAt(idx1) == c && tokens[2].charAt(idx2) == c))
                    valid++;
        }
        System.out.println("valid: " + valid);
    }

}