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

    public static List<?> listByLine(Scanner scanner, boolean isInt) {
        List<String> lines = new ArrayList<String>();
        List<Long> linesLong = new ArrayList<Long>();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(isInt)   linesLong.add(Long.parseLong(line));
            else        lines.add(line);
        }
        return isInt ? linesLong : lines;
    }

    public static String[] arrayByLine(Scanner scanner, boolean isInt) {
        String[] arr = listByLine(scanner, isInt).toArray(new String[0]);
        return arr;
    }

}
