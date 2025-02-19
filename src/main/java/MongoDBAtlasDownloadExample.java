import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class MongoDBAtlasDownloadExample {
    public MongoDBAtlasDownloadExample() {

        String pw = System.getenv("dbpw");
        if (pw.length() == 0) {
            throw new RuntimeException("Lösenordet lästes inte in");
        }
        //Skriv in rätt url!

        String uri = "mongodb+srv://isakfolke:" + pw + "@dbteknik.stwun.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            //Get all movies from 1975
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

            // Skriver ut alla filmer
            for (Movie movie : movieList) {
//                System.out.println(movie);
            }

            System.out.println("Antal filmer: "+MovieUtilG.countMovies(movieList));
            System.out.println("Längsta filmen: "+MovieUtilG.longestRuntime(movieList));
            System.out.println("Unika genrer: "+MovieUtilG.uniqueGenres(movieList));
            System.out.println("Skådisar i filmen med högst betyg: "+MovieUtilG.actorsInHighestRated(movieList));
            System.out.println("Filmen med minst antal skådisar: "+MovieUtilG.titleFewestActors(movieList));
            System.out.println("Antal skådisar medverkande i mer än (1) film: "+MovieUtilG.numActorsInMultipleMovies(movieList));
            System.out.println("Skådisen i flest filmer: "+MovieUtilG.actorInMostMovies(movieList));
            System.out.println("Antal unika språk: "+MovieUtilG.uniqueLanguages(movieList));
            System.out.println("Finns det filmer med samma titel: "+MovieUtilG.duplicateTitle(movieList));

            //Här gör du anrop till alla dina funktioner som ska skriva ut svaren på frågorna som
            //efterfrågas i uppgiften
            System.out.println("-----VG-delen-----");
            System.out.println("Antal skådisar medverkande i mer än (1) film: "+MovieUtilVG.numActorsInMultipleMovies(movieList));
            System.out.println("Skådisen i flest filmer: "+MovieUtilVG.actorInMostMovies(movieList));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MongoDBAtlasDownloadExample m = new MongoDBAtlasDownloadExample();
    }
}
