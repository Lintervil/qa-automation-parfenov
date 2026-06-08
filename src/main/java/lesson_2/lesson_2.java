package lesson_2;

public class lesson_2 {

    public static void main(String[] args) {
        System.out.println("--- Задание 1 ---");
        printThreeWords();

        System.out.println("\n--- Задание 2 ---");
        checkSumSign();

        System.out.println("\n--- Задание 3 ---");
        printColor();
        System.out.println("\n--- Задание 4 ---");
        compareNumbers();

        System.out.println("\n--- Задание 5 ---");
        int a5 = 5;
        int b5 = 10;
        System.out.println("Сумма " + a5 + " и " + b5 + " в пределах 10-20? " + isSumBetween10And20(a5, b5));

        a5 = 15;
        b5 = 10;
        System.out.println("Сумма " + a5 + " и " + b5 + " в пределах 10-20? " + isSumBetween10And20(a5, b5));

        System.out.println("\n--- Задание 6 ---");
        printPositiveOrNegative(5);
        printPositiveOrNegative(-5);
        printPositiveOrNegative(0);

        System.out.println("\n--- Задание 7 ---");
        System.out.println("-5 отрицательное? " + isNegative(-5));
        System.out.println("5 отрицательное? " + isNegative(5));
        System.out.println("0 отрицательное? " + isNegative(0));

        System.out.println("\n--- Задание 8 ---");
        printStringNTimes("Привет", 3);

        System.out.println("\n--- Задание 9 ---");
        System.out.println("2020 високосный? " + isLeapYear(2020));
        System.out.println("2021 високосный? " + isLeapYear(2021));
        System.out.println("1900 високосный? " + isLeapYear(1900));
        System.out.println("2000 високосный? " + isLeapYear(2000));

        System.out.println("\n--- Задание 10 ---");
        int[] arr10 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.print("До: ");
        printArray(arr10);
        invertArray(arr10);
        System.out.print("После: ");
        printArray(arr10);

        System.out.println("\n--- Задание 11 ---");
        int[] arr11 = fillArrayWith1To100();
        System.out.print("Первые 10 элементов: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(arr11[i] + " ");
        }
        System.out.println("...");
        System.out.print("Последние 10 элементов: ");
        for (int i = 90; i < 100; i++) {
            System.out.print(arr11[i] + " ");
        }
        System.out.println();

        System.out.println("\n--- Задание 12 ---");
        int[] arr12 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.print("До: ");
        printArray(arr12);
        multiplyLessThanSixByTwo(arr12);
        System.out.print("После (числа < 6 умножены на 2): ");
        printArray(arr12);

        System.out.println("\n--- Задание 13 ---");
        int[][] squareArray = createDiagonalArray(5);
        System.out.println("Квадратный массив 5x5 с единицами на главной диагонали:");
        print2DArray(squareArray);

        System.out.println("\n--- Задание 14 ---");
        int[] arr14 = createFilledArray(7, 42);
        System.out.print("Массив длиной 7, заполненный числом 42: ");
        printArray(arr14);
    }

    // 1.
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2.
    public static void checkSumSign() {
        int a = 10;
        int b = -5;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3.
    public static void printColor() {
        int value = 50; // Измените это значение для проверки других условий
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4.
    public static void compareNumbers() {
        int a = 10;
        int b = 20;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5.
    public static boolean isSumBetween10And20(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6.
    public static void printPositiveOrNegative(int number) {
        if (number >= 0) {
            System.out.println(number + " - положительное число");
        } else {
            System.out.println(number + " - отрицательное число");
        }
    }

    // 7.
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 8.
    public static void printStringNTimes(String str, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(str);
        }
    }

    // 9.
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else if (year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    // 10.
    public static void invertArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else if (arr[i] == 1) {
                arr[i] = 0;
            }
        }
    }

    // 11.
    public static int[] fillArrayWith1To100() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    // 12.
    public static void multiplyLessThanSixByTwo(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }

    // 13.
    public static int[][] createDiagonalArray(int size) {
        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    arr[i][j] = 1;
                }
            }
        }
        return arr;
    }

    // 14.
    public static int[] createFilledArray(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }

    public static void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                if (j < arr[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(" ]");
        }
    }
}
