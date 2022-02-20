package NonlinearEquations;

public class ModifiedNewtonMethod {
    static double[] modifiedNewtonMethod(double leftBound, double rightBound, double inaccuracy, Function function) {
        double previousSolution;
        if(function.getFuncValue(leftBound) * function.getSecondDerivativeValue(leftBound) > 0) previousSolution = leftBound;
        else previousSolution = rightBound;
        double constDerivative = function.getDerivativeValue(previousSolution);
        double currentSolution = previousSolution - (function.getFuncValue(previousSolution) / constDerivative);

        int counter = 0;
        while(Math.abs(currentSolution - previousSolution) >= inaccuracy) {
            previousSolution = currentSolution;
            currentSolution = previousSolution - (function.getFuncValue(previousSolution) / constDerivative);
            counter++;
        }
        double solution = currentSolution;
        double difference = currentSolution - previousSolution;
        return new double[]{solution, difference, counter};
    }
}
