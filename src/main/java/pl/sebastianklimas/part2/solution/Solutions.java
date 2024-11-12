package pl.sebastianklimas.part2.solution;

import pl.sebastianklimas.part2.domain.*;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solutions {
    private final List<Animal> animals= List.of(new Spider(), new Cat(), new Fish("Free Willy"), new Cat("Tekir"), new Fish("Jaws"), new Spider());
    private final Random random = new Random();
    private static final Supplier<Animal> spiderCreator = Spider::new;
    private static final Supplier<Animal> catCreator = () -> new Cat("Flame");
    private static final Supplier<Animal> fishCreator = () -> new Fish("Fish");
    private static final List<Supplier<Animal>> suppliers = Arrays.asList(spiderCreator, catCreator, fishCreator);

    public List<Animal> e1() {
        List<Animal> result = animals.stream()
                .filter(animal -> !(animal instanceof Pet))
                .toList();
        return result;
    }
    public List<Animal> e2() {
        List<Animal> result = animals.stream()
                .filter(animal -> animal instanceof Pet)
                .toList();
        return result;
    }
    public Optional<Animal> e3() {
        Optional<Animal> result = animals.stream()
                .max(Comparator.comparing(Animal::getLegs));
        return result;
    }
    public List<Animal> e4() {
        List<Animal> result = IntStream.generate(() -> random.nextInt(suppliers.size()))
                .mapToObj(suppliers::get)
                .map(Supplier::get)
                .limit(5)
                .toList();
        return result;
    }
    public int e5() {
        int result = e4().stream()
                .peek(animal -> System.out.println("\t" + animal.getClass().getSimpleName()))
                .mapToInt(Animal::getLegs)
                .sum();
        return result;
    }
    public Map<Integer, List<Animal>> e6() {
        Map<Integer, List<Animal>> result = e4().stream()
                .collect(Collectors.groupingBy(
                        Animal::getLegs
                ));
        return result;
    }
    public Map<String, Long> e7() {
        Map<String, Long> result = e4().stream()
                .peek(animal -> System.out.println("\t" + animal.getClass().getSimpleName()))
                .collect(Collectors.groupingBy(
                        animal -> animal.getClass().getSimpleName(),
                        Collectors.counting()
                ));
        return result;
    }
    public long e8() {
        long result = e4().stream()
                .map(animal -> animal.getClass().getSimpleName())
                .peek(System.out::println)
                .distinct()
                .count();
        return result;
    }
}
