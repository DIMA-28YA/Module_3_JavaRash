package org.example.lesson_3_Junit;

public class Calculator {

    public int add(int a, int b) throws CalculationException {
        long result = (long) a + (long) b;
        if (isHigherMinValue(result) && isLessMaxValue(result)) {
            return (int) result;

        }
        try {
            throw new CalculationException("kfjkwfk");
        } catch (CalculationException e) {
            throw new RuntimeException(e);
        }
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int diy(int a, int b) {
        return a / b;
    }

    private boolean isLessMaxValue(long number) {
        return number < Integer.MAX_VALUE;
    }

    private boolean isHigherMinValue(long number) {
        return number < Integer.MIN_VALUE;

    }
}
