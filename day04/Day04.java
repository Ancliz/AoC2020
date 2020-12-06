package day04;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import day.Day;
import util.Parse;

// Yuck
public class Day04 extends Day {
    private String[] UNPARSED_PASSPORTS;
    private List<HashMap<String, String>> passports;

    public Day04() throws FileNotFoundException {
        super();
        passports = new ArrayList<HashMap<String, String>>();
    }


    @Override
    public void run() {
        parsePassports();
        validatePassports();
        System.out.println("valid passports: " + passports.size());
    }
    
    private void validatePassports() {
        for(Iterator<HashMap<String, String>> iter = passports.iterator(); iter.hasNext();) {
            if(!isValid(iter.next()))
                iter.remove();
        }
    }

    private boolean isValid(HashMap<String, String> passport) {
        boolean valid = true;
        if     (invalidYear(passport.get("byr"), 1920, 2002))       valid = false;
        else if(invalidYear(passport.get("iyr"), 2010, 2020))       valid = false;
        else if(invalidYear(passport.get("eyr"), 2020, 2030))       valid = false;
        else if(invalidHeight(passport.get("hgt")))                 valid = false;
        else if(invalidEyeColour(passport.get("ecl")))              valid = false;
        else if(!passport.get("pid").matches("[0-9]{9}"))           valid = false;
        else if(!passport.get("hcl").matches("#([0-9a-f]*){6}"))    valid = false;
        return valid;
    }

    private boolean invalidEyeColour(String colour) {
        return !(colour.matches("(amb|blu|brn|gry|grn|hzl|oth)"));
    }

    private boolean invalidHeight(String height) {
        boolean valid = false;

        if(height.matches("[0-9]+(cm|in)")) {
            int num = Integer.parseInt(height.substring(0, height.length()-2));

            if     (height.contains("cm") && (150 <= num && num <= 193))    valid = true;
            else if(height.contains("in") && (59 <= num && num <= 76))      valid = true;
        }
        return !valid;
    }

    private boolean invalidYear(String year, int lower, int upper) {
        int y = -1;
        try                            { y = Integer.parseInt(year); }
        catch(NumberFormatException e) { return true;                }
        return !(lower <= y && y <= upper);
    }

    private void parsePassports() {
        UNPARSED_PASSPORTS = (String[]) Parse.groupByBlankLine(scanner).toArray(new String[0]);

        for(String passport : UNPARSED_PASSPORTS) {
            HashMap<String, String> map = new HashMap<String, String>();
            boolean illegal = false;
            String[] tokens = passport.split(" ");

            // If, at most, missing one field
            if(tokens.length > 6) {

                for(String token : tokens) {
                    String[] kv = token.split(":");
                    
                    // If the missing field is not cid
                    if(tokens.length != 8 && kv[0].equals("cid"))   { illegal = true; break; }
                    else                                            { map.put(kv[0], kv[1]); }  
                }
                if(!illegal)
                    passports.add(map);
            }  
        }
    }
}
