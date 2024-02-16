import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class Movie {
   private String title;
   private String cast;
   private String director;
   private String overview;
   private int runtime;
   private double rating;
   private Scanner scan;

   public Movie(String title, String cast, String director, String overview, int runtime, double rating) {
      ArrayList<String> temp = new ArrayList<>();
      this.title = title;
      this.cast = cast;
      this.director = director;
      this.overview = overview;
      this.runtime = runtime;
      this.rating = rating;
   }

   public String getTitles() {
      return title;
   }

   public String getCasts() {
      return cast;
   }

   public String getDirectors() {
      return director;
   }

   public String getOverviews() {
      return overview;
   }

   public int getRuntimes() {
      return runtime;
   }

   public double getRatings() {
      return rating;
   }
}
