import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MovieUtilVG {

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
        return groupingByCounting(movies, Movie::cast)
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("Nothing found");
    }

    private static Map<String, Long> groupingByCounting(List<Movie> movies, Function<Movie, List<String>> method) {
        return movies.stream()
                .flatMap(m -> method.apply(m).stream())
                .collect(Collectors.groupingBy(e-> e, Collectors.counting()));
    }

}

