package lesson_5;

public class ArrayProcessor {

    public static int processArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {

        // Проверка размера
        if (arr == null || arr.length != 4) {
            throw new MyArraySizeException("Массив должен быть размером 4x4");
        }

        for (String[] row : arr) {
            if (row == null || row.length != 4) {
                throw new MyArraySizeException("Каждая строка массива должна содержать 4 элемента");
            }
        }

        int sum = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j].trim());
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(
                            String.format("Неверные данные в ячейке [%d][%d]: '%s'", i, j, arr[i][j])
                    );
                }
            }
        }
        return sum;
    }
}