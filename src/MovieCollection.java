import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class MovieCollection {
    private Scanner scan;
    private ArrayList<Movie> movies;
    private ArrayList<String> casts;

    public MovieCollection() {
        movies = new ArrayList<>();
        casts = new ArrayList<>();
        start();
    }

    private void start() {
        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                Movie movie = new Movie(splitData[0], splitData[1], splitData[2], splitData[3], Integer.parseInt(splitData[4]), Double.parseDouble(splitData[5]));
                movies.add(movie);
                int count = 0;
                ArrayList<String> people = new ArrayList<>();
                while (count < splitData[1].length()) {
                    String str = "";
                    while (splitData[1].charAt(count) != '|' && count < splitData[1].length() - 1) {
                        str += splitData[1].charAt(count);
                        count++;
                    }
                    if (count == splitData[1].length() - 1) {
                        str += splitData[1].charAt(splitData[1].length() - 1);
                    }
                    people.add(str);
                    count++;
                }
                for (String person : people) {
                    if (!casts.contains(person)) {
                        casts.add(person);
                    }
                }
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
                System.out.println("** Press Enter to Return to Main Menu **");
                scan.nextLine();
            } else if (menuOption.equals("c")) {
                searchCast();
                System.out.println("** Press Enter to Return to Main Menu **");
                scan.nextLine();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }

    private void searchTitles() {
        System.out.print("Enter title term: ");
        String title = scan.nextLine();
        ArrayList<Movie> listOfTitles = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitles().toLowerCase().contains(title.toLowerCase())) {
                listOfTitles.add(movies.get(i));
            }
        }
        if (listOfTitles.isEmpty()) {
            System.out.println("No movie titles match that search term!");
            System.out.println();
        } else {
            learnMore(listOfTitles);
        }
    }

    private void searchCast() {
        System.out.print("Enter a person to search for (first or last name): ");
        String name = scan.nextLine();
        ArrayList<String> listOfNames = new ArrayList<>();
        for (int i = 0; i < casts.size(); i++) {
            if (casts.get(i).toLowerCase().contains(name.toLowerCase())) {
                listOfNames.add(casts.get(i));
            }
        }
        if (listOfNames.isEmpty()) {
            System.out.println("No results match your search");
            System.out.println();
        } else {
            for (int i = 0; i < listOfNames.size(); i++) {
                int j = i - 1;
                String min = listOfNames.get(i);
                while (j >= 0 && listOfNames.get(j).compareTo(min) > 0) {
                    listOfNames.set(j + 1, listOfNames.get(j));
                    j--;
                }
                listOfNames.set(j + 1, min);
            }
            for (int i = 0; i < listOfNames.size(); i++) {
                System.out.println(i + 1 + ". " + listOfNames.get(i));
            }
            System.out.println("Which would you like to see all movies for?");
            System.out.print("Enter number: ");
            int option = scan.nextInt();
            scan.nextLine();
            ArrayList<Movie> listOfTitles = new ArrayList<>();
            for (int i = 0; i < movies.size(); i++) {
                if (movies.get(i).getCasts().toLowerCase().contains(listOfNames.get(option - 1).toLowerCase())) {
                    listOfTitles.add(movies.get(i));
                }
            }
            learnMore(listOfTitles);
    }   }

    private void learnMore(ArrayList<Movie> listOfTitles) {
        for (int i = 0; i < listOfTitles.size(); i++) {
            int index = i;
            String min = listOfTitles.get(i).getTitles();
            Movie minMovie = listOfTitles.get(i);
            for (int j = i + 1; j < listOfTitles.size(); j++) {
                if (listOfTitles.get(j).getTitles().compareTo(min) < 0) {
                    min = listOfTitles.get(j).getTitles();
                    minMovie = listOfTitles.get(j);
                    index = j;
                }
            }
            Movie temp = listOfTitles.get(i);
            listOfTitles.set(i, minMovie);
            listOfTitles.set(index, temp);
        }
        for (int i = 0; i < listOfTitles.size(); i++) {
            System.out.println(i + 1 + ". " + listOfTitles.get(i).getTitles());
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int option = scan.nextInt() - 1;
        scan.nextLine();
        System.out.println();
        System.out.println("Title: " + listOfTitles.get(option).getTitles());
        System.out.println("Runtime: " + listOfTitles.get(option).getRuntimes() + " minutes");
        System.out.println("Directed by: " + listOfTitles.get(option).getDirectors());
        System.out.println("Cast: " + listOfTitles.get(option).getCasts());
        System.out.println("Overview: " + listOfTitles.get(option).getOverviews());
        System.out.println("User rating: " + listOfTitles.get(option).getRatings());
        System.out.println();
    }
}
