package day06;

import java.io.FileNotFoundException;
import java.util.List;

import day.Day;
import util.Parse;

public class Day06 extends Day {
    List<String> groupAnswers;

    public Day06() throws FileNotFoundException {
        super();
        groupAnswers = Parse.groupByBlankLine(scanner);
    }

    @Override
    public void run() {
        int anyone = 0, everyone = 0;

        for(String ans : groupAnswers) {
            anyone += countUniqueCharacters(ans);
            everyone += countCommonCharacters(ans);
        }
        System.out.println("A yes: " + anyone);
        System.out.println("All yes: " + everyone);
    }

    private int countUniqueCharacters(String s) {
        return (int) s.chars().distinct().count() - 1;
    }

    private int countCommonCharacters(String s) {
        String[] tokens = s.trim().split(" ");
        char[] chars = tokens[0].toCharArray();
        int common = 0;

        for(char c : chars) {
            if(inAllTokens(tokens, 1, c))
                common++;
        }
        return common;
    }
    
    private boolean inAllTokens(String[] tokens, int i, char c) {
        if(tokens.length == i)          return true;
        if(tokens[i].indexOf(c) != -1)  return inAllTokens(tokens, ++i, c);
        else                            return false;
    }
}
