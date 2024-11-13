package pl.sebastianklimas.part3.solution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solutions {
    Path path = Paths.get("src", "main", "java", "pl", "sebastianklimas", "part3", "dictionary.txt");

    public List<String> e1() {
        try {
            List<String> result = Files.readAllLines(path)
                    .stream()
                    .takeWhile(line -> line.matches("^[a-m].*$"))
                    .toList();
            return result;
        } catch (IOException e) {
            System.err.println("File exception occurred");
            return List.of();
        }
    }
    public List<String> e1WithFilter() {
        try {
            List<String> result = Files.readAllLines(path)
                    .stream()
                    .filter(line -> line.matches("^[a-m].*$"))
                    .toList();
            return result;
        } catch (IOException e) {
            System.err.println("File exception occurred");
            return List.of();
        }
    }
    public List<String> e2() {
        try {
            List<String> result = Files.readAllLines(path)
                    .stream()
                    .dropWhile(line -> line.matches("^[a-m].*$"))
                    .toList();
            return result;
        } catch (IOException e) {
            System.err.println("File exception occurred");
            return List.of();
        }
    }
    public List<String> e2WithFilter() {
        try {
            List<String> result = Files.readAllLines(path)
                    .stream()
                    .filter(line -> line.matches("^[n-z].*$"))
                    .toList();
            return result;
        } catch (IOException e) {
            System.err.println("File exception occurred");
            return List.of();
        }
    }
    public Map<String, List<String>> e3() {
        try {
            Map<String, List<String>> result = Files.readAllLines(path)
                    .stream()
                    .collect(Collectors.groupingBy(
                            w -> w.substring(0, 3)
                    ));
            return result;
        } catch (IOException e) {
            System.err.println("File exception occurred");
            return Map.of();
        }
    }
    public List<String> e4() {
        try {
            List<String> result = Files.readAllLines(path)
                    .stream()
                    .filter(w -> w.equalsIgnoreCase(new StringBuilder(w).reverse().toString()))
                    .toList();
            return result;
        } catch (IOException e) {
            System.err.println("File exception occurred");
            return List.of();
        }
    }
    public Map<String, Long> e5() {
        try {
            Map<String, Long> result = Files.readAllLines(path)
                    .stream()
                    .flatMapToInt(String::chars)
                    .mapToObj(Character::toString)
                    .filter(c -> Pattern.compile("[aeiouAEIOU]").matcher(c).find())
                    .collect(Collectors.groupingBy(
                            c -> c,
                            Collectors.counting()
                    ));
            return result;
        } catch (IOException e) {
            System.err.println("File exception occurred");
            return Map.of();
        }
    }
    public List<String> e6() {
        try {
            List<String> result = Files.readAllLines(path)
                    .stream()
                    .filter(w -> w.toLowerCase().startsWith("a"))
                    .filter(w -> w.toLowerCase().endsWith("z"))
                    .toList();
            return result;
        } catch (IOException e) {
            System.err.println("File exception occurred");
            return List.of();
        }
    }
    public String e7() {
        try {
            Optional<String> result = Files.readAllLines(path)
                    .stream()
                    .max(Comparator.comparing(String::length));
            return result.orElse("");
        } catch (IOException e) {
            System.err.println("File exception occurred");
            return "";
        }
    }
}
