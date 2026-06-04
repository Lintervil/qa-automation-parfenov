package lesson_6;

import java.util.*;

public class PhoneDirectory {
    private Map<String, Set<String>> directory = new HashMap<>();

    public void add(String surname, String phone) {
        directory.computeIfAbsent(surname, k -> new HashSet<>()).add(phone);
    }

    public Set<String> get(String surname) {
        return directory.getOrDefault(surname, Collections.emptySet());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Телефонный справочник:\n");
        directory.forEach((surname, phones) ->
                sb.append(surname).append(": ").append(phones).append("\n"));
        return sb.toString();
    }
}