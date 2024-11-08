package pl.sebastianklimas;

import pl.sebastianklimas.part1.solution.Solutions;

public class Main {
    public static void main(String[] args) {
        Solutions solutions = new Solutions();

        System.out.println("Exercise 1");
        solutions.e1().forEach(((director, numberOfMovies) -> System.out.println(director.getName() + " - " + numberOfMovies)));
        System.out.println("Exercise 2:");
        solutions.e2().forEach(((continent, continentCityPair) -> {
            if (continentCityPair.isPresent()) System.out.println(continent + " " + continentCityPair.get().city());
            else System.out.println();
        }));
        System.out.println("Exercise 3:");
        solutions.e3().forEach(((director, genreCount) -> System.out.println(director.getName() + " - " + genreCount)));
        System.out.println("Exercise 4:");
        System.out.println(solutions.e4());
        System.out.println("Exercise 5:");
        solutions.e5().forEach((continent, continentCityPair) -> {
            if (continentCityPair.isPresent()) System.out.println(continent + " " + continentCityPair.get().city());
            else System.out.println();
        });
        System.out.println("Exercise 6:");
        solutions.e6().forEach(System.out::println);
        System.out.println("Exercise 7:");
        solutions.e7().forEach(System.out::println);
        System.out.println("Exercise 8:");
        solutions.e8().forEach((year, movies) -> {
            System.out.println(year);
            movies.forEach(movie -> System.out.println("    " + movie));
        });
        System.out.println("Exercise 9:");
        solutions.e9().forEach(System.out::println);
        System.out.println("Exercise 10:");
        solutions.e10().forEach((continent, country) -> System.out.println(continent + " - " + country.orElseThrow()));
        System.out.println("Exercise 11:");
        System.out.println(solutions.e11());
        System.out.println("Exercise 12:");
        solutions.e12().forEach((continent, summary) -> System.out.println(continent + " - " + summary));
        System.out.println("Exercise 13:");
        solutions.e13().forEach(System.out::println);
        System.out.println("Exercise 14:");
        solutions.e14().forEach((continent, summary) -> System.out.println(continent + " - " + summary));
        System.out.println("Exercise 15:");
        solutions.e15().forEach((continent, countries) -> {
            System.out.println(continent);
            countries.forEach(country -> System.out.println("    " + country));
        });
        System.out.println("Exercise 16:");
        solutions.e16().forEach((city, summary) -> System.out.println(city + " - " + summary));
        System.out.println("Exercise 17:");
        System.out.println(solutions.e17());
        System.out.println("Exercise 18:");
        System.out.println(solutions.e18());
    }
}