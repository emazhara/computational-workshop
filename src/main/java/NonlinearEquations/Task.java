package NonlinearEquations;

import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    double leftBound, rightBound;
    double inaccuracy;
    Function function;

    public Task() {
        var scanner = new Scanner(System.in);
        System.out.print("Insert the search area bounds\n");
        System.out.print("A: ");
        this.leftBound = scanner.nextDouble();
        System.out.print("B: ");
        this.rightBound = scanner.nextDouble();

        System.out.print("Insert the inaccuracy\n");
        System.out.print("Epsilon: ");
        this.inaccuracy = scanner.nextDouble();

        this.function = new Function(this.leftBound, this.rightBound);
    }
    public Task(String filename) {
        var scanner = new Scanner(filename);

        try {
            this.leftBound = scanner.nextDouble();
            this.rightBound = scanner.nextDouble();
            this.inaccuracy = scanner.nextDouble();
            this.function = new Function(this.leftBound, this.rightBound);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<double[]> solutionSeparation(int inputType, String filename) throws Exception {
        int numberOfSegments;
        switch (inputType) {
            case 1 -> {
                var scanner = new Scanner(System.in);
                System.out.print("Insert the number of subsegments\n");
                System.out.print("N: ");
                numberOfSegments = scanner.nextInt();
                while (numberOfSegments <= 1) {
                    System.out.print("Incorrect number, N should be at least 2\n");
                    System.out.print("Insert the number of subsegments\n");
                    System.out.print("N: ");
                    numberOfSegments = scanner.nextInt();
                }
            }
            case 2 -> {
                var scanner = new Scanner(filename);
                numberOfSegments = scanner.nextInt();
            }
            default -> {return null;}
        }

        var answer = new ArrayList<double[]>();
        double tabulationStep = (this.rightBound - this.leftBound) / numberOfSegments;
        double x1 = this.leftBound, x2 = this.leftBound + tabulationStep;
        double y1, y2;
        int counter;
        for(counter = 0; x2 <= this.rightBound;) {
            y1 = this.function.getFuncValue(x1);
            y2 = this.function.getFuncValue(x2);
            if(y1 * y2 <= 0) {
                var properSegment = new double[]{x1, x2};
                answer.add(properSegment);
                counter++;
                System.out.print(counter + ": [" + x1 + ", " + x2 + "]\n");
            }
            x1 = x2;
            x2 = x2 + tabulationStep;
        }
        System.out.print("Number of segments: " + counter + "\n");
        return answer;
    }

    ArrayList<ArrayList<double[]>> getResults(int inputType, String filename) throws Exception {
        var segments = this.solutionSeparation(inputType, filename);
        var answer = new ArrayList<ArrayList<double[]>>();
        for(double[] segment : segments) {
            var answerForOneSegment = new ArrayList<double[]>();
            answerForOneSegment.add(BisectionMethod.bisectionMethod(segment[0], segment[1], this.inaccuracy, this.function));
            answerForOneSegment.add(NewtonMethod.newtonMethod(segment[0], segment[1], this.inaccuracy, this.function));
            answerForOneSegment.add(ModifiedNewtonMethod.modifiedNewtonMethod(segment[0], segment[1], this.inaccuracy, this.function));
            answerForOneSegment.add(SecantMethod.secantMethod(segment[0], segment[1], this.inaccuracy, this.function));
            answer.add(answerForOneSegment);
        }
        return answer;
    }
}
