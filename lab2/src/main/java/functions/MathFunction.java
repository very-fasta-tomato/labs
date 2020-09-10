package functions;

public interface MathFunction {
    default double apply(double x) {
        return x;
    }
}
