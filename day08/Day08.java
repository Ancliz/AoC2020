package day08;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import day.Day;
import util.Parse;

public class Day08 extends Day {
    private String[] instructions = Parse.arrayByLine(scanner);
    private String[] instr_copy = instructions.clone();
    private Map<Integer, Boolean> completedInstructions;
    private int accumulator;
    int ip;
    
    public Day08() throws FileNotFoundException {
        super();
        accumulator = 0;
        ip = 0;
        completedInstructions = new HashMap<Integer, Boolean>();
    }

    @Override
    public void run() {
        int index = 0;
        boolean finished = false;

        while(!finished) {

            if(changeElement(index)) {
            
                if(!isLoop()) {
                    if(ip == instructions.length)
                        finished = true; 
                }
                instr_copy = instructions.clone();
            }
            index++;
        }
        System.out.println("termination - " + accumulator);
    }

    private int doInstruction(String[] instruction, int next) {
        String op = instruction[0];
        int num = Integer.parseInt(instruction[1]);

        switch(op) {
            case "nop": next++;                     break;
            case "acc": accumulator += num; next++; break;
            case "jmp": next += num;                break;
        }
        return next;
    }

    private boolean isLoop() {
        completedInstructions.clear();
        accumulator = 0;
        ip = 0;

        while(!completedInstructions.containsKey(ip)) {
            completedInstructions.put(ip, true);
            try                                     { ip = doInstruction(instr_copy[ip].split(" "), ip); }
            catch(ArrayIndexOutOfBoundsException e) { return false;                                      }
        }
        return true;
    }

    private boolean changeElement(int i) {
        String instruction[] = instructions[i].split(" ");

        if(!instruction[0].equals("acc")) {
            instr_copy[i] = (instruction[0].equals("jmp") ? "nop " : "jmp ") + instruction[1];
            return true;
        }
        return false;
    }

}