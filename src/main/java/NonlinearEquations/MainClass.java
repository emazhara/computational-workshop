package NonlinearEquations;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
    class Menu {
        String[] menuText;
        int menuPointsCount;
        private Menu(int menuPointsCount) {
            this.menuPointsCount = menuPointsCount;
            this.menuText = new String[this.menuPointsCount];
        }
        private Menu(String[] menuText) {
            this.menuPointsCount = menuText.length;
            this.menuText = menuText;
        }
        void add(String option, int optionNumber) {
            this.menuText[optionNumber] = option;
        }
        void display() {
            for(String option : this.menuText)
                System.out.print(option + "\n");
        }
    }

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        var mc = new MainClass();
        var inputMenu = mc.new Menu(new String[]{"Select input type:",
                "1. Get input from keyboard;", "2. Get input from file"});
        inputMenu.display();
        int inputTypeChoice = scanner.nextInt();
        Task task = null;
        ArrayList<ArrayList<double[]>> answers = null;
        try {
            switch (inputTypeChoice) {
                case 1 -> {
                    task = new Task();
                    answers = task.getResults(inputTypeChoice, "");
                }
                case 2 -> {
                    System.out.print("Insert the name of the file.\nFile name: ");
                    String filename = scanner.next();
                    task = new Task(filename);
                    answers = task.getResults(inputTypeChoice, filename);
                }
                default -> {
                    System.out.print("Incorrect choice!\n");
                    return;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        var outputMenu = mc.new Menu(new String[]{"Select output type:",
                "1. Write output to console;", "2. Write output to file."});
        outputMenu.display();
        int outputTypeChoice = scanner.nextInt();

        try {
            switch (outputTypeChoice) {
                case 1 -> {
                    for (ArrayList<double[]> answerForOneSegment : answers) {
                        int methodId = 1;
                        for (double[] result : answerForOneSegment) {
                            System.out.print("\n" + getMethodName(methodId) + ": \n");
                            System.out.print("Solution: " + result[0] + "\n");
                            System.out.print("x_m - x_{m-1}: " + result[1] + "\n");
                            System.out.print("Number of iterations: " + (int)result[2] + "\n");
                            System.out.print("Discrepancy module: " + task.function.getFuncValue(result[0]) + "\n");
                            methodId++;
                        }
                        System.out.print("______________________________________");
                    }
                }
                case 2 -> {
                    System.out.print("Insert the name of the file.\nFile name: ");
                    String filename = scanner.next();
                    //var file = new File(filename);
                    //TODO
                }
                default -> {
                    System.out.print("Incorrect choice!\n");
                    return;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static String getMethodName(int id) {
        switch(id) {
            case 1 -> {return "Bisection method";}
            case 2 -> {return "Newton method";}
            case 3 -> {return "Modified Newton method";}
            case 4 -> {return "Secant method";}
            default -> {return "";}
        }
    }
}
