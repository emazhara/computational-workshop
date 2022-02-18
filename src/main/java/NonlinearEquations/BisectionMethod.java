package NonlinearEquations;

public class BisectionMethod {
    static double[] bisectionMethod(double leftBound, double rightBound, double inaccuracy, Function function) {
        int counter = 0;
        while(rightBound - leftBound >= 2 * inaccuracy) {
            double pivot = (rightBound + leftBound) / 2;
            if(function.getFuncValue(pivot) * function.getFuncValue(rightBound) <= 0) leftBound = pivot;
            else rightBound = pivot;
            counter++;
        }
        double solution = (leftBound + rightBound) / 2;
        double difference = (rightBound - leftBound) / 2;
        return new double[]{solution, difference, counter};
    }
}
