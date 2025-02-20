import com.mongodb.Function;

import java.util.*;
import java.util.stream.Collectors;

public class MovieUtilVG {

    static Integer numActorsInMultipleMovies(List<Movie> movies) {
        return
                groupingByCounting(movies, Movie::cast)
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

    private static <T> Map<T, Long> groupingByCounting(
            List<Movie> movies,
            Function<Movie, List<T>> method) {
        return movies.stream()
                .flatMap(m -> method.apply(m).stream())
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

}