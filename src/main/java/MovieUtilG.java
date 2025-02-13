import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
        List<String> actors = movies.stream()
                .flatMap(m -> m.cast().stream())
                .collect(Collectors.toList());

        return actors.size() - (int) actors.stream().distinct().count();
    }

    static String actorInMostMovies(List<Movie> movies) {
        return movies.stream()
                .flatMap(m -> m.cast().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("Nothing found");


    }

}
