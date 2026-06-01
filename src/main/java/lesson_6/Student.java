package lesson_6;

import java.util.*;

public class Student {
    private String name;
    private String group;
    private int course;
    private Map<String, Integer> grades;

    public Student(String name, String group, int course, Map<String, Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = new HashMap<>(grades);
    }

    public String getName() { return name; }
    public String getGroup() { return group; }
    public int getCourse() { return course; }

    public double getAverageGrade() {
        if (grades.isEmpty()) return 0.0;
        return grades.values().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    public void nextCourse() {
        this.course++;
    }

    @Override
    public String toString() {
        return name + " | Группа: " + group + " | Курс: " + course +
                " | Средний балл: " + String.format("%.2f", getAverageGrade());
    }
}