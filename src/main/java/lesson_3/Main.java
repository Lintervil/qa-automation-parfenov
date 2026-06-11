package lesson_3;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Lesson 3 - Домашнее задание ===\n");

        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599, true);

        productsArray[1] = new Product("iPhone 17 Pro", "15.03.2025",
                "Apple Inc.", "USA", 1299, false);

        productsArray[2] = new Product("Xiaomi 15", "10.01.2025",
                "Xiaomi", "China", 699, true);

        productsArray[3] = new Product("Sony WH-1000XM6", "20.04.2025",
                "Sony", "Japan", 399, false);

        productsArray[4] = new Product("Dell XPS 15", "05.05.2025",
                "Dell Technologies", "USA", 1899, false);

        for (Product product : productsArray) {
            product.printInfo();
        }

        // 3. Работа с парком
        System.out.println("=== Парк аттракционов ===\n");

        Park.Attraction attraction1 = new Park.Attraction(
                "Американские горки", "10:00 - 22:00", 25.0);

        Park.Attraction attraction2 = new Park.Attraction(
                "Колесо обозрения", "09:00 - 23:00", 15.0);

        attraction1.printInfo();
        attraction2.printInfo();
    }
}