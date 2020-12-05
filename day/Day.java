package day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Day {
    protected Scanner scanner;

    abstract public void run();

    public Day() throws FileNotFoundException {
        scanner = new Scanner(new File(this.getClass().getSimpleName().toLowerCase() + "/input"));
    }

}
