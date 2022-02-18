package NonlinearEquations;

public class NewtonMethod {
    static double[] newtonMethod(double leftBound, double rightBound, double inaccuracy, Function function) {
        double previousSolution;
        if(function.getFuncValue(leftBound) * function.getSecondDerivativeValue(leftBound) > 0) previousSolution = leftBound;
        else previousSolution = rightBound;
        double currentSolution = previousSolution -
            function.getFuncValue(previousSolution) / function.getDerivativeValue(previousSolution);

        int counter = 0;
        while(Math.abs(currentSolution - previousSolution) >= inaccuracy) {
            previousSolution = currentSolution;
            currentSolution = previousSolution -
                function.getFuncValue(previousSolution) / function.getDerivativeValue(previousSolution);
            counter++;
        }
        double solution = currentSolution;
        double difference = currentSolution - previousSolution;
        return new double[]{solution, difference, counter};
    }
}
