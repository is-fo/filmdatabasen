import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieUtilGTest {

    List<Movie> movies = new ArrayList<>();

    private void init() {
        movies.add(new Movie(
                "1",
                "Jaws",
                "A giant man-eating shark terrorizes a beach town.",
                List.of("Thriller", "Adventure"),
                124,
                List.of("Roy Scheider", "Robert Shaw", "Richard Dreyfuss", "Jesper Ekstedt"),
                "A small town is terrorized by a monstrous great white shark...",
                List.of("English", "Spanish", "Dutch"),
                new Date(75, Calendar.JUNE, 20), // Deprecated way to set year
                List.of("Steven Spielberg"),
                "PG",
                1975,
                8.0,
                List.of("USA"),
                "movie"
        ));

        movies.add(new Movie(
                "2",
                "One Flew Over the Cuckoo's Nest",
                "A criminal pleads insanity and is sent to a mental institution.",
                List.of("Drama"),
                133,
                List.of("Jack Nicholson", "Louise Fletcher", "Danny DeVito", "Jesper Ekstedt"),
                "A rebellious patient fights against the oppressive system...",
                List.of("English"),
                new Date(75, Calendar.NOVEMBER, 19),
                List.of("Milos Forman"),
                "R",
                1975,
                8.7,
                List.of("USA"),
                "movie"
        ));

        movies.add(new Movie(
                "3",
                "Monty Python and the Holy Grail",
                "King Arthur and his knights embark on a comedic quest for the Holy Grail.",
                List.of("Comedy", "Adventure"),
                91,
                List.of("Graham Chapman", "John Cleese", "Eric Idle"),
                "A parody of the Arthurian legend featuring absurd humor...",
                List.of("English"),
                new Date(75, Calendar.APRIL, 3),
                List.of("Terry Gilliam", "Terry Jones"),
                "PG",
                1975,
                8.2,
                List.of("UK"),
                "movie"
        ));
    }

    @Test
    void countMoviesTest() {
        init();
        System.out.println(movies.size());
        assertEquals(MovieUtilG.countMovies(movies), movies.size());
        assertEquals(MovieUtilG.countMovies(movies), 3);
    }

    @Test
    void longestRunTimeTest() {
        init();
        assertEquals(MovieUtilG.longestRuntime(movies), 133);
    }

    @Test
    void uniqueGenresTest() {
        init();
        assertEquals(4, MovieUtilG.uniqueGenres(movies));
    }

    @Test
    void actorsInHighestRated() {
        init();
        assertEquals(List.of("Jack Nicholson", "Louise Fletcher", "Danny DeVito", "Jesper Ekstedt"), MovieUtilG.actorsInHighestRated(movies));
    }

    @Test
    void titleFewestActors() {
        init();
        assertEquals("Monty Python and the Holy Grail", MovieUtilG.titleFewestActors(movies));
    }

    @Test
    void numActorsInMultipleMovies() {
        init();
        assertEquals(1, MovieUtilG.numActorsInMultipleMovies(movies));
        assertEquals(1, MovieUtilVG.numActorsInMultipleMovies(movies));
    }

    @Test
    void actorInMostMovies() {
        init();
        assertEquals("Jesper Ekstedt", MovieUtilG.actorInMostMovies(movies));
        assertEquals("Jesper Ekstedt", MovieUtilVG.actorInMostMovies(movies));
    }

    @Test
    void uniqueLanguages() {
        init();
        assertEquals(3, MovieUtilG.uniqueLanguages(movies));
    }

    @Test
    void duplicateTitle() {
        init();
        assertEquals(false, MovieUtilG.duplicateTitle(movies));

        List<Movie> dupes = List.of(movies.getFirst(), movies.getFirst());
        assertEquals(true, MovieUtilG.duplicateTitle(dupes));
    }
}
