package NonlinearEquations;

public class SecantMethod {
    static double[] secantMethod(double leftBound, double rightBound, double inaccuracy, Function function) {
        double firstApproximation = leftBound;
        double secondApproximation = rightBound;
        double currentApproximation = secondApproximation -
            (function.getFuncValue(secondApproximation) /
            (function.getFuncValue(secondApproximation) - function.getFuncValue(firstApproximation)) *
            (secondApproximation - firstApproximation));

        int counter = 0;
        while(Math.abs(secondApproximation - firstApproximation) >= inaccuracy) {
            firstApproximation = secondApproximation;
            secondApproximation = currentApproximation;
            currentApproximation = secondApproximation -
                (function.getFuncValue(secondApproximation) /
                (function.getFuncValue(secondApproximation) - function.getFuncValue(firstApproximation)) *
                (secondApproximation - firstApproximation));
            counter++;
        }
        double solution = secondApproximation;
        double difference = secondApproximation - firstApproximation;
        return new double[]{solution, difference, counter};
    }
}
