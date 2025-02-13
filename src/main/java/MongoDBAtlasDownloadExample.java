import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class MongoDBAtlasDownloadExample {
    public MongoDBAtlasDownloadExample() {

        String pw = System.getenv("dbpw");
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

            System.out.println(MovieUtilG.countMovies(movieList));
            System.out.println(MovieUtilG.longestRuntime(movieList));
            System.out.println(MovieUtilG.uniqueGenres(movieList));

            //Här gör du anrop till alla dina funktioner som ska skriva ut svaren på frågorna som
            //efterfrågas i uppgiften


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MongoDBAtlasDownloadExample m = new MongoDBAtlasDownloadExample();
    }
}
