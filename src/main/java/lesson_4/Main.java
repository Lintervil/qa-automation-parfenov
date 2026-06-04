package lesson_4;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Lesson 4 - Домашнее задание ===\n");

        // ===================== ЗАДАНИЕ 1 =====================
        System.out.println("=== Задание 1 — Животные ===\n");

        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");
        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Барсик");
        Cat cat3 = new Cat("Васька");

        dog1.run(300);
        dog1.swim(5);
        cat1.run(150);
        cat1.swim(3);

        System.out.println("\nВсего животных: " + Animal.getAnimalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());

        // Кормление котов
        System.out.println("\n=== Кормление котов ===");
        Bowl bowl = new Bowl(20);

        Cat[] cats = {cat1, cat2, cat3};
        for (Cat cat : cats) {
            cat.eat(bowl, 7);
        }

        System.out.println("\nСытость котов:");
        for (Cat cat : cats) {
            System.out.println(cat.getName() + " — " + (cat.isFull() ? "Сыт" : "Голоден"));
        }

        bowl.addFood(30);

        //ЗАДАНИЕ 2
        System.out.println("\n\n=== Задание 2 — Геометрические фигуры ===\n");

        Circle circle = new Circle(5.0, "Красный", "Чёрный");
        Rectangle rectangle = new Rectangle(10.0, 6.0, "Синий", "Белый");
        Triangle triangle = new Triangle(3.0, 4.0, 5.0, "Зелёный", "Жёлтый");

        circle.printInfo();
        rectangle.printInfo();
        triangle.printInfo();
    }
}