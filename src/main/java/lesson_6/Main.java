package lesson_6;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Домашнее задание 2.6 — Lesson 6 \n");

        //Студенты
        Set<Student> students = new HashSet<>();

        students.add(new Student("Иванов", "А-101", 2,
                Map.of("Математика", 4, "Физика", 3, "История", 5)));

        students.add(new Student("Петров", "А-101", 2,
                Map.of("Математика", 2, "Физика", 2, "История", 3)));

        students.add(new Student("Сидорова", "Б-202", 3,
                Map.of("Математика", 5, "Физика", 4, "Программирование", 5)));

        students.add(new Student("Козлова", "А-101", 2,
                Map.of("Математика", 3, "Физика", 3, "История", 2)));

        System.out.println("Исходный список студентов:");
        students.forEach(System.out::println);

        removeLowPerformers(students);
        System.out.println("\nПосле удаления студентов со средним баллом < 3:");
        students.forEach(System.out::println);

        transferToNextCourse(students);
        System.out.println("\nПосле перевода на следующий курс:");
        students.forEach(System.out::println);

        System.out.println("\nСтуденты на 3 курсе:");
        printStudents(students, 3);

        System.out.println("\n Задание 2: Телефонный справочник ");
        PhoneDirectory pd = new PhoneDirectory();
        pd.add("Иванов", "+7-901-123-45-67");
        pd.add("Иванов", "+7-903-987-65-43");
        pd.add("Петров", "+7-905-555-33-22");
        pd.add("Сидорова", "+7-999-111-22-33");

        System.out.println(pd);
    }

    public static void removeLowPerformers(Set<Student> students) {
        students.removeIf(s -> s.getAverageGrade() < 3);
    }

    public static void transferToNextCourse(Set<Student> students) {
        students.forEach(s -> {
            if (s.getAverageGrade() >= 3) {
                s.nextCourse();
            }
        });
    }

    public static void printStudents(Set<Student> students, int course) {
        students.stream()
                .filter(s -> s.getCourse() == course)
                .map(Student::getName)
                .forEach(System.out::println);
    }
}