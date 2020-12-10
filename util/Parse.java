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

    public static List<?> listByLine(Scanner scanner, boolean isInt, boolean isLong) {
        List<String> lines = new ArrayList<String>();
        List<Long> linesLong = new ArrayList<Long>();
        List<Integer> linesInt = new ArrayList<Integer>();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if     (isLong)     linesLong.add(Long.parseLong(line));
            else if(isInt)      linesInt.add(Integer.parseInt(line));
            else                lines.add(line);
        }
        return isLong ? linesLong : (isInt ? linesInt : lines);
    }

    public static String[] arrayByLine(Scanner scanner, boolean isInt, boolean isLong) {
        String[] arr = listByLine(scanner, isInt, isLong).toArray(new String[0]);
        return arr;
    }

}
