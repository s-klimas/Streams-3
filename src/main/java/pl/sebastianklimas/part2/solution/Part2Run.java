package pl.sebastianklimas.part2.solution;

public class Part2Run {
    public static void run() {
        Solutions solutions = new Solutions();

        System.out.println("Exercise 1");
        solutions.e1().forEach(System.out::println);
        System.out.println("Exercise 2");
        solutions.e2().forEach(System.out::println);
        System.out.println("Exercise 3");
        System.out.println(solutions.e3().orElseThrow());
        System.out.println("Exercise 4");
        solutions.e4().forEach(System.out::println);
        System.out.println("Exercise 5");
        System.out.println("Counting legs for:");
        System.out.println(solutions.e5());
        System.out.println("Exercise 6");
        solutions.e6().forEach((legs, animals) -> {
            System.out.println(legs);
            animals.forEach(animal -> System.out.println("\t" + animal));
        });
        System.out.println("Exercise 7");
        System.out.println("Counting animals for:");
        solutions.e7().forEach((specie, number) -> System.out.println(specie + " - " + number));
        System.out.println("Exercise 8");
        System.out.println("Counting number of species for list:");
        System.out.println(solutions.e8());
    }
}
