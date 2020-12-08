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

    public static List<String> listByLine(Scanner scanner) {
        List<String> lines = new ArrayList<String>();

        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    public static String[] arrayByLine(Scanner scanner) {
        String[] arr = listByLine(scanner).toArray(new String[0]);
        return arr;
    }

}
