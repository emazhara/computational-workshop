package NonlinearEquations;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.print("Select input type:\n" + "1. Get input from keyboard;\n" + "2. Get input from file\n");
        int inputTypeChoice = scanner.nextInt();
        Task task = null;
        ArrayList<ArrayList<double[]>> answers = null;
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

        System.out.print("Select output type:\n" + "1. Write output to console;\n" + "2. Write output to file.\n");
        int outputTypeChoice = scanner.nextInt();

        var segments = answers.get(0);
        answers.remove(0);
        PrintStream out = null;
        switch (outputTypeChoice) {
            case 1 -> out = System.out;
            case 2 -> {
                System.out.print("Insert the name of the file.\nFile name: ");
                String filename = scanner.next();
                try {
                    out = new PrintStream(filename);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            default -> {
                System.out.print("Incorrect choice!\n");
                return;
            }
        }
        int segmentId = 0;
        for (ArrayList<double[]> answerForOneSegment : answers) {
            int methodId = 1;
            out.print("\nSegment: [" + segments.get(segmentId)[0] + "; " + segments.get(segmentId)[1] + "]\n");
            for (double[] result : answerForOneSegment) {
                out.print("\n" + getMethodName(methodId) + ": \n");
                out.print("Solution: " + result[0] + "\n");
                out.print("x_m - x_{m-1}: " + result[1] + "\n");
                out.print("Number of iterations: " + (int)result[2] + "\n");
                out.print("Discrepancy module: " + task.function.getFuncValue(result[0]) + "\n");
                methodId++;
            }
            out.print("______________________________________");
            segmentId++;
        }
        out.close();
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