import org.bson.Document;

import java.util.Date;
import java.util.List;

public record Movie(String _id,
                    String title,
                    String plot,
                    List<String> genres,
                    Integer runtime,
                    List<String> cast,
                    String poster,
                    String fullplot,
                    List<String> languagues,
                    Date released,
                    List<String> directors,
                    String rated,
                    Object awards,
                    String lastupdated,
                    Integer year,
                    Double rating,
                    List<String> countries,
                    String type,
                    Object tomatoes,
                    Integer num_mflix_comments
                    ) {


     // Method to convert MongoDB document to Movie object

    public static Movie fromDocument(Document doc) {
        return new Movie(
                doc.getObjectId("_id").toString(),
                doc.getString("title"),
                doc.getString("plot"),
                doc.getList("genres", String.class),
                doc.getInteger("runtime"),
                doc.getList("cast", String.class),
                doc.getString("poster"),
                doc.getString("fullplot"),
                doc.getList("languagues", String.class),
                doc.getDate("released"),
                doc.getList("directors", String.class),
                doc.getString("rated"),
                doc.get("awards"),
                doc.getString("lastupdated"),
                doc.getInteger("year"),
                doc.get("imdb", Document.class) != null ? doc.get("imdb", Document.class).getDouble("rating") : 0.0,
                doc.getList("countries", String.class),
                doc.getString("type"),
                doc.get("tomatoes"),
                doc.getInteger("num_mflix_comments")
        );
    }
}
