package day07;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import day.Day;

public class Day07 extends Day {
    private HashMap<String, HashMap<String, Integer>> bags;
    private final String SOUGHT_BAG = "shiny gold";

    public Day07() throws FileNotFoundException {
        super();
        bags = new HashMap<String, HashMap<String, Integer>>();
    }


    @Override
    public void run() {
        parseBags();
        int count[] = {0};

        bags.forEach((bag, innerBag) -> {
            if(canContain(innerBag))
                count[0]++;
        });
        
        System.out.println("count: " + count[0]);
        System.out.print("Bag count: " + (countBags(bags.get(SOUGHT_BAG), 1) - 1));
        
    }
    
    private boolean canContain(HashMap<String, Integer> currentBag) {
        boolean contains = false;
        if(currentBag.containsKey(SOUGHT_BAG))  contains = true;
        else {
            for(Iterator<String> iter = currentBag.keySet().iterator(); iter.hasNext();) {
                contains = canContain(bags.get(iter.next()));
                if(contains)
                    break;
            }
        }
        return contains;
    }

    private int countBags(HashMap<String, Integer> currentBag, int noOfBag) {
        int innerCount = 0;

        for(Iterator<Map.Entry<String, Integer>> iter = currentBag.entrySet().iterator(); iter.hasNext();) {
            Entry<String, Integer> next = iter.next();
            int noOfNext = noOfBag * next.getValue();
            innerCount += countBags(bags.get(next.getKey()), noOfNext);
        }
        return innerCount + noOfBag;
    }

    private void parseBags() {
        while(scanner.hasNextLine()) {
            HashMap<String, Integer> innerBagMapping = new HashMap<String, Integer>();
            String[] bag = scanner.nextLine().split(" bags contain ");
            String[] contents = bag[1].split(", ");
            int quan = 0;
            Scanner sc;
            
            for(String innerBag : contents) {
                sc = new Scanner(innerBag);

                try {
                    quan = sc.nextInt();
                    String bagName = sc.nextLine().replaceFirst(" ", "").replaceFirst("\\.", "").replaceFirst(" bags*", "");
                    innerBagMapping.put(bagName, quan);
                } catch(InputMismatchException e) {}
                sc.close();
            }
            bags.put(bag[0], innerBagMapping);
        }
    }
}