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
                "https://example.com/jaws.jpg",
                "A small town is terrorized by a monstrous great white shark...",
                List.of("English"),
                new Date(75, Calendar.JUNE, 20), // Deprecated way to set year
                List.of("Steven Spielberg"),
                "PG",
                Map.of("Wins", 3, "Nominations", 6),
                "2024-02-13T12:00:00Z",
                1975,
                8.0,
                List.of("USA"),
                "movie",
                Map.of("critic", 9.5, "audience", 8.2),
                1500
        ));

        movies.add(new Movie(
                "2",
                "One Flew Over the Cuckoo's Nest",
                "A criminal pleads insanity and is sent to a mental institution.",
                List.of("Drama"),
                133,
                List.of("Jack Nicholson", "Louise Fletcher", "Danny DeVito", "Jesper Ekstedt"),
                "https://example.com/cuckoosnest.jpg",
                "A rebellious patient fights against the oppressive system...",
                List.of("English"),
                new Date(75, Calendar.NOVEMBER, 19),
                List.of("Milos Forman"),
                "R",
                List.of("Wins", 5, "Nominations", 9),
                "2024-02-13T12:00:00Z",
                1975,
                8.7,
                List.of("USA"),
                "movie",
                List.of("critic", 9.3, "audience", 8.5),
                1200
        ));

        movies.add(new Movie(
                "3",
                "Monty Python and the Holy Grail",
                "King Arthur and his knights embark on a comedic quest for the Holy Grail.",
                List.of("Comedy", "Adventure"),
                91,
                List.of("Graham Chapman", "John Cleese", "Eric Idle"),
                "https://example.com/holygrail.jpg",
                "A parody of the Arthurian legend featuring absurd humor...",
                List.of("English"),
                new Date(75, Calendar.APRIL, 3),
                List.of("Terry Gilliam", "Terry Jones"),
                "PG",
                Map.of("Wins", 1, "Nominations", 2),
                "2024-02-13T12:00:00Z",
                1975,
                8.2,
                List.of("UK"),
                "movie",
                List.of("critic", 9.0, "audience", 8.4),
                900
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
    }

    @Test
    void actorInMostMovies() {
        init();
        assertEquals("Jesper Ekstedt", MovieUtilG.actorInMostMovies(movies));
    }
}
