package lesson_4;

public class Rectangle extends ColoredShape {
    private double length;
    private double width;

    public Rectangle(double length, double width, String fillColor, String borderColor) {
        super(fillColor, borderColor);
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }

    @Override
    public double calculateArea() {
        return length * width;
    }
}