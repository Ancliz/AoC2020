package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parse {

    public static List<String> groupByBlankLine(Scanner scanner) {
        List<String> groups = new ArrayList<String>();
        String line = "";

        while(scanner.hasNextLine()) {
            String group = "";
            line = scanner.nextLine();

            while(!line.equals("")) {
                group += line + " ";
                
                if(!scanner.hasNextLine())  break; 
                else                        line = scanner.nextLine(); 
            }
            groups.add(group);
        }
        return groups;
    }

}
