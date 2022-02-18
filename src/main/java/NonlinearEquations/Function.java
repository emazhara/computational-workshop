package NonlinearEquations;

public class Function {
    String funcExpr;
    double leftBound;
    double rightBound;

    public Function(double leftBound, double rightBound) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.funcExpr = "f(x) = 5*sin(2x) - sqrt(1-x)";
        //System.out.print(this.funcExpr);
    }

    double getFuncValue(double arg) {
        return 5 * Math.sin(2 * arg) - Math.sqrt(1 - arg);
    }

    double getDerivativeValue(double arg) {
        return 10 * Math.cos(2 * arg) + 1.0 / (2 * Math.sqrt(1 - arg));
    }

    double getSecondDerivativeValue(double arg) {
        return -20 * Math.cos(2 * arg) + 1.0 / (4 * Math.pow(1 - arg, 1.5));
    }

}
