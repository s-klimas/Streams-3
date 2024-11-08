package pl.sebastianklimas.part1.solution;

import pl.sebastianklimas.part1.dao.CountryDao;
import pl.sebastianklimas.part1.dao.InMemoryWorldDao;
import pl.sebastianklimas.part1.domain.*;
import pl.sebastianklimas.part1.service.InMemoryMovieService;
import pl.sebastianklimas.part1.service.MovieService;
import pl.sebastianklimas.part1.util.CountryCitySummaryStatistics;
import pl.sebastianklimas.part1.util.CountrySummaryStatistics;
import pl.sebastianklimas.part1.util.DoubleSummaryGaussianStatistics;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solutions {
    // public
    public record ContinentCityPair(String continent, City city) {}
    // private
    private record DirectorGenreListPair(Director director, List<Genre> genreList) {}
    private record DirectorGenrePair(Director director, Genre genre) {}
    private static final BiConsumer<CountrySummaryStatistics, Country> countryAccumulator = (a, c) -> a.accept(c);
    private static final BinaryOperator<CountrySummaryStatistics> countryCombiner = (l, r) -> { l.combine(r);	return l; };
    private static final Supplier<CountrySummaryStatistics> countrySummaryStatisticsSupplier =
            () -> new CountrySummaryStatistics((l, r) -> Long.compare(l.getPopulation(), r.getPopulation()));
    private static final BiConsumer<CountryCitySummaryStatistics, Country> cityAccumulator = (s, c) -> s.accept(c);
    private static final BinaryOperator<CountryCitySummaryStatistics> cityCombiner = (l, r) -> { l.combine(r); return l; };
    // data
    private static final MovieService movieService = InMemoryMovieService.getInstance();
    private static final CountryDao countryDao = InMemoryWorldDao.getInstance();

    public Map<Director, Long> e1() {
        Map<Director, Long> result = movieService.findAllMovies()
                .stream()
                .map(Movie::getDirectors)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        director -> director,
                        Collectors.counting()
                ));
        return result;
    }
    public Map<String, Optional<ContinentCityPair>> e2() {
        Map<String, Optional<ContinentCityPair>> result = countryDao.findAllCountries()
                .stream()
                .flatMap(country -> country.getCities()
                        .stream()
                        .map(city -> new ContinentCityPair(country.getContinent(), city)))
                .collect(Collectors.groupingBy(
                        ContinentCityPair::continent,
                        Collectors.maxBy(Comparator.comparing(continentCityPair -> continentCityPair.city.getPopulation()))
                ));
        return result;
    }
    public Map<Director, Long> e3() {
        Map<Director, Long> result = movieService.findAllMovies()
                .stream()
                .flatMap(movie -> movie.getDirectors()
                        .stream()
                        .map(director -> new DirectorGenreListPair(director, movie.getGenres())))
                .flatMap(directorGenreListPair -> directorGenreListPair.genreList
                        .stream()
                        .map(genre -> new DirectorGenrePair(directorGenreListPair.director, genre)))
                .distinct()
                .collect(Collectors.groupingBy(
                        DirectorGenrePair::director,
                        Collectors.counting()
                ));
        return result;
    }
    public City e4() {
        Optional<City> result = countryDao.findAllCountries()
                .stream()
                .flatMap(country -> country.getCities()
                        .stream()
                        .filter(city -> city.getId() == country.getCapital()))
                .max(Comparator.comparing(City::getPopulation));
        return result.orElseThrow();
    }
    public Map<String, Optional<ContinentCityPair>> e5() {
        Map<String, Optional<ContinentCityPair>> result = countryDao.findAllCountries()
                .stream()
                .flatMap(country -> country.getCities()
                        .stream()
                        .filter(city -> city.getId() == country.getCapital())
                        .map(city -> new ContinentCityPair(country.getContinent(), city)))
                .collect(Collectors.groupingBy(
                        ContinentCityPair::continent,
                        Collectors.maxBy(Comparator.comparing(continentCityPair -> continentCityPair.city.getPopulation()))
                ));
        return result;
    }
    public List<Country> e6() {
        List<Country> result = countryDao.findAllCountries()
                .stream()
                .sorted(Comparator.comparing(country -> country.getCities().size(), Comparator.reverseOrder()))
                .toList();
        return result;
    }
    public List<Movie> e7() {
        List<Movie> result = movieService.findAllMovies()
                .stream()
                .filter(movie -> movie.getGenres().containsAll(Stream.of("Drama", "Comedy").map(movieService::findGenreByName).toList()) && movie.getGenres().size() == 2)
                .toList();
        return result;
    }
    public Map<Integer, List<Movie>> e8() {
        Map<Integer, List<Movie>> result = movieService.findAllMovies()
                .stream()
                .collect(Collectors.groupingBy(
                        Movie::getYear
                ));
        return result;
    }
    public List<Country> e9() {
        List<Country> result = countryDao.findAllCountries()
                .stream()
                .filter(country -> country.getPopulation() > 0)
                .sorted(Comparator.comparing(country -> country.getPopulation() / country.getSurfaceArea(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return result;
    }
    public Map<String, Optional<Country>> e10() {
        Map<String, Optional<Country>> result = countryDao.findAllCountries()
                .stream()
                .collect(Collectors.groupingBy(
                        Country::getContinent,
                        Collectors.maxBy(Comparator.comparing(Country::getGnp))
                ));
        return result;
    }
    public IntSummaryStatistics e11() {
        IntSummaryStatistics result = countryDao.findAllCountries()
                .stream()
                .mapToInt(Country::getPopulation)
                .summaryStatistics();
        return result;
    }
    public Map<String, IntSummaryStatistics> e12() {
        Map<String, IntSummaryStatistics> result = countryDao.findAllCountries()
                .stream()
                .collect(Collectors.groupingBy(
                        Country::getContinent,
                        Collectors.summarizingInt(Country::getPopulation)
                ));
        return result;
    }
    public List<Country> e13() {
        List<Country> result = countryDao.findAllCountries()
                .stream()
                .filter(country -> country == countryDao.findAllCountries().stream().min(Comparator.comparing(Country::getPopulation)).get()
                        || country == countryDao.findAllCountries().stream().max(Comparator.comparing(Country::getPopulation)).get())
                .collect(Collectors.toList());
        return result;
    }
    public Map<String, CountrySummaryStatistics> e14() {
        Map<String, CountrySummaryStatistics> result = countryDao.findAllCountries()
                .stream()
                .collect(Collectors.groupingBy(
                        Country::getContinent,
                        Collector.of(countrySummaryStatisticsSupplier, countryAccumulator, countryCombiner)));
        return result;
    }
    public Map<String, List<Country>> e15() {
        Map<String, List<Country>> result = countryDao.findAllCountries()
                .stream()
                .sorted(Comparator.comparing(country -> country.getCities().size(), Comparator.reverseOrder()))
                .collect(Collectors.groupingBy(Country::getContinent));
        return result;
    }
    public Map<String, CountryCitySummaryStatistics> e16() {
        Map<String, CountryCitySummaryStatistics> result = countryDao.findAllCountries()
                .stream()
                .collect(Collectors.groupingBy(
                        Country::getName,
                        Collector.of(CountryCitySummaryStatistics::new, cityAccumulator, cityCombiner)));
        return result;
    }
    public DoubleSummaryGaussianStatistics e17() {
        DoubleSummaryGaussianStatistics result = countryDao.findAllCountries()
                .stream()
                .mapToDouble(Country::getGnp)
                .collect(
                        DoubleSummaryGaussianStatistics::new,
                        DoubleSummaryGaussianStatistics::accept,
                        DoubleSummaryGaussianStatistics::combine
                );
        return result;
    }
    public Map.Entry<Integer, Long> e18() {
        Optional<Map.Entry<Integer, Long>> result = movieService.findAllMovies()
                .stream()
                .collect(Collectors.groupingBy(
                        Movie::getYear,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        return result.orElseThrow();
    }
}
