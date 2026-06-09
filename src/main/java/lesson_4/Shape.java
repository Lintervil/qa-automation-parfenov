package lesson_4;

public interface Shape {
    double calculatePerimeter();
    double calculateArea();

    String getFillColor();
    String getBorderColor();

    // Дефолтный метод
    default void printInfo() {
        System.out.println("Фигура: " + this.getClass().getSimpleName());
        System.out.println("Периметр: " + calculatePerimeter());
        System.out.println("Площадь: " + calculateArea());
        System.out.println("Цвет заливки: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
        System.out.println("---------------------------");
    }
}