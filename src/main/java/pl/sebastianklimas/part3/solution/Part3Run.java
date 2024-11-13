package pl.sebastianklimas.part3.solution;

public class Part3Run {
    public static void run() {
        Solutions solutions = new Solutions();

        System.out.println("Exercise 1");
        solutions.e1().forEach(System.out::println);
        System.out.println("Exercise 2");
        solutions.e2().forEach(System.out::println);
        System.out.println("Exercise 3");
        solutions.e3().forEach((starts, list) -> {
            System.out.println(starts + ":");
            list.forEach(l -> System.out.println("\t" + l));
        });
        System.out.println("Exercise 4");
        solutions.e4().forEach(System.out::println);
        System.out.println("Exercise 5");
        solutions.e5().forEach((c, l) -> System.out.println(c + " - " + l));
        System.out.println("Exercise 6");
        solutions.e6().forEach(System.out::println);
        System.out.println("Exercise 7");
        System.out.println(solutions.e7());
    }
}
