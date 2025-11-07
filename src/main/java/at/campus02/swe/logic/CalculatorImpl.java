package at.campus02.swe.logic;


import at.campus02.swe.Calculator;
import at.campus02.swe.CalculatorException;

import java.util.Stack;

public class CalculatorImpl implements Calculator {

    private Stack<Double> stack_ = new Stack<Double>();

    @Override
    public double perform(Operation op) throws CalculatorException {

        if (op == Operation.sin || op == Operation.cos) {
            if (stack_.size() < 1)
                throw new CalculatorException("Not enough numbers on stack");
            return (op == Operation.sin) ? Math.sin(pop()) : Math.cos(pop());
        }

        if (stack_.size() < 2)
            throw new CalculatorException("Not enough numbers on stack");

        if (op == Operation.dotproduct) {
            return findScalarProduct();
        }

        double b = pop();
        double a = pop();

        switch (op) {
            case add:
                return a + b;
            case sub:
                return a - b;
            case div:
                double c = a / b;
                if (Double.isInfinite(c))
                    throw new CalculatorException("Division by zero");
                return c;
            case mul:
                return a * b;
            case mod:
                return a % b;
            case sin:
                return Math.sin(pop());
            case cos:
                return Math.cos(pop());
        }
        return 0;
    }

    private double findScalarProduct() throws CalculatorException {
        int n = (int) pop();

        if (n <= 0) {
            throw new CalculatorException("Vector size must be positive");
        }

        if (stack_.size() < 2 * n) {
            throw new CalculatorException("Not enough numbers on stack for dot product");
        }

        double[] bVector = new double[n];
        double[] aVector = new double[n];

        for (int i = n - 1; i >= 0; i--) bVector[i] = pop();
        for (int i = n - 1; i >= 0; i--) aVector[i] = pop();

        double result = 0;

        for (int i = 0; i < n; i++) result += aVector[i] * bVector[i];

        push(result);
        return result;
    }

    @Override
    public double pop() throws CalculatorException {
        if (stack_.isEmpty())
            throw new CalculatorException();
        return stack_.pop();
    }

    @Override
    public void push(double v) {
        stack_.push(v);
    }

    @Override
    public void clear() {
        stack_.clear();
    }

}
