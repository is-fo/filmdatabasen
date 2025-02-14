import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MovieUtilG {
    static Long countMovies(List<Movie> movies) {
        return movies.stream()
                .count();
    }

    static Integer longestRuntime(List<Movie> movies) {
        return movies.stream()
                .mapToInt(Movie::runtime)
                .summaryStatistics()
                .getMax();
    }

    static Long uniqueGenres(List<Movie> movies) {
        return movies.stream()
                .flatMap(m -> m.genres()
                        .stream())
                .distinct()
                .count();
    }

    static List<String> actorsInHighestRated(List<Movie> movies) {
        return movies.stream()
                .max(Comparator.comparingDouble(Movie::rating))
                .map(Movie::cast)
                .get();
    }

    static String titleFewestActors(List<Movie> movies) {
        return movies.stream()
                .min(Comparator.comparingInt(m -> m.cast().size()))
                .map(Movie::title)
                .orElse("no movies found");

    }

    static Integer numActorsInMultipleMovies(List<Movie> movies) {
        return movies.stream()
                .flatMap(m -> m.cast().stream())
                .collect(Collectors.groupingBy(a -> a, Collectors.counting()))
                .values()
                .stream()
                .filter(v -> v > 1)
                .collect(Collectors.toList())
                .size();
    }

    static String actorInMostMovies(List<Movie> movies) {
        return movies.stream()
                .flatMap(m -> m.cast().stream())
                .collect(Collectors.groupingBy(a -> a, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("Nothing found");
    }

    static Integer uniqueLanguages(List<Movie> movies) {
        return (int) movies.stream()
                .flatMap(m -> m.languages().stream())
                .distinct()
                .count();
    }

    static Boolean duplicateTitle(List<Movie> movies) {
        return !movies.stream()
                .map(Movie::title)
                .collect(Collectors.groupingBy(t -> t, Collectors.counting()))
                .values()
                .stream()
                .filter(v -> v > 1)
                .collect(Collectors.toList())
                .isEmpty();
    }

}
