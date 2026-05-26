package lesson_5;

public class Main {

    public static void main(String[] args) {

        String[][] goodArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = ArrayProcessor.processArray(goodArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        String[][] badSize = new String[3][4];
        try {
            ArrayProcessor.processArray(badSize);
        } catch (MyArraySizeException e) {
            System.err.println("Поймано MyArraySizeException: " + e.getMessage());
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }

        String[][] badData = {
                {"1", "2", "3", "4"},
                {"5", "6", "abc", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        try {
            ArrayProcessor.processArray(badData);
        } catch (MyArrayDataException e) {
            System.err.println("Поймано MyArrayDataException: " + e.getMessage());
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }

        generateAndCatchIndexOutOfBounds();
    }

    private static void generateAndCatchIndexOutOfBounds() {
        System.out.println("\n Демонстрация ArrayIndexOutOfBoundsException");
        int[] array = {10, 20, 30};
        try {
            System.out.println(array[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Поймано ArrayIndexOutOfBoundsException: " + e.getMessage());
            e.printStackTrace();
        }
    }
}