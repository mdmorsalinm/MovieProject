import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class MovieCollection {
    private Scanner scan;
    private ArrayList<Movie> movies;

    public MovieCollection() {
        movies = new ArrayList<>();
        start();
    }

    private void start() {
        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            int count = 0;
            while (fileScanner.hasNext()) {
                if (count > 0) {
                    String data = fileScanner.nextLine();
                    String[] splitData = data.split(",");
                    Movie movie = new Movie(splitData[0], splitData[1], splitData[2], splitData[3], Integer.parseInt(splitData[4]), Double.parseDouble(splitData[5]));
                    movies.add(movie);
                }
                count++;
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        scan = new Scanner(System.in);
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }

    private void searchTitles() {
        System.out.println("Enter title term: ");
        String title = scan.nextLine();
        ArrayList<String> listOfTitles = new ArrayList<>();
        ArrayList<String> titles = movie.getTitles();
        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).toLowerCase().contains(title.toLowerCase())) {
                listOfTitles.add(titles.get(i));
            }
        }
        for (int i = 0; i < listOfTitles.size(); i++) {
            int index = i;
            String min = listOfTitles.get(i);
            for (int j = i + 1; j < listOfTitles.size(); j++) {
                if (listOfTitles.get(j).compareTo(min) < 0) {
                    min = listOfTitles.get(j);
                    index = j;
                }
            }
            String temp = listOfTitles.get(i);
            listOfTitles.set(i, min);
            listOfTitles.set(index, temp);
        }
        for (int i = 0; i < listOfTitles.size(); i++) {
            System.out.println(i + 1 + " " + listOfTitles.get(i));
        }

        System.out.println("Which movie would you like to know more about?");
        int option = scan.nextInt();
        int index = titles.indexOf(listOfTitles.get(option));
        scan.nextLine();
        System.out.println();
        System.out.println("Title: " + listOfTitles.get(index));
        System.out.println("Runtime: " + movie.getRuntimes().get(index));
        System.out.println("Directed by: " + movie.getDirectors().get(index));
        System.out.println("Cast: " + movie.getCasts().get(index));
        System.out.println("Overview: " + movie.getOverviews().get(index));
    }

    private void searchCast() {

    }
}
