import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class Movie {
   private ArrayList<String> titles;
   private ArrayList<String> casts;
   private ArrayList<String> directors;
   private ArrayList<String> overviews;
   private ArrayList<Integer> runtimes;
   private ArrayList<Double> ratings;
   private Scanner scan;

   public Movie() {
      ArrayList<String> temp = new ArrayList<>();
      titles = new ArrayList<>();
      casts = new ArrayList<>();
      directors = new ArrayList<>();
      overviews = new ArrayList<>();
      runtimes = new ArrayList<>();
      ratings = new ArrayList<>();
      try {
         File myFile = new File("src\\movies_data.csv");
         Scanner fileScanner = new Scanner(myFile);
         while (fileScanner.hasNext()) {
            String data = fileScanner.nextLine();
            String[] splitData = data.split(",");
            titles.add(splitData[0]);
            casts.add(splitData[1]);
            directors.add(splitData[2]);
            overviews.add(splitData[3]);
            runtimes.add(Integer.parseInt(splitData[4]));
            ratings.add(Double.parseDouble(splitData[5]));
         }
         titles.remove(0);
         casts.remove(0);
         directors.remove(0);
         overviews.remove(0);
         runtimes.remove(0);
         ratings.remove(0);
      } catch (IOException exception) {
         System.out.println(exception.getMessage());
      }
   }

   public ArrayList<String> getTitles() {
      return titles;
   }

   public ArrayList<String> getCasts() {
      return casts;
   }

   public ArrayList<String> getDirectors() {
      return directors;
   }

   public ArrayList<String> getOverviews() {
      return overviews;
   }

   public ArrayList<Integer> getRuntimes() {
      return runtimes;
   }

   public ArrayList<Double> getRatings() {
      return ratings;
   }
}
