package lesson_7;

public class Factorial {

    public long calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число не может быть отрицательным");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}