package ee.ivkhkdev;


import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.impl.ConsoleInput;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Input scanner = new ConsoleInput(new Scanner(System.in));
        App app = new App(scanner);
        app.run();
    }
}
