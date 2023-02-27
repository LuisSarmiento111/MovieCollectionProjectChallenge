import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        ArrayList<String> actors = MovieDatabaseBuilder.getActorsDB("src/KevinBaconActors.txt");
        ArrayList<SimpleMovie> actorMovies = MovieDatabaseBuilder.getActorMoviesDB("src/KevinBaconActorMovies.txt");
        ArrayList<SimpleMovie> kevinBaconMovies = MovieDatabaseBuilder.getKevinBaconMovies("src/kevinBaconMovies.txt");
        for (int i = 0; i < actorMovies.size(); i++) {
            int maxIndex = i;
            int max = actorMovies.get(i).getActors().size();
            for (int j = i; j < actorMovies.size(); j++) {
                if (actorMovies.get(j).getActors().size() >= max) {
                    maxIndex = j;
                    max = actorMovies.get(j).getActors().size();
                }
            }
            actorMovies.add(i, actorMovies.remove(maxIndex));
        }
        for (int i = 0; i < kevinBaconMovies.size(); i++) {
            int maxIndex = i;
            int max = kevinBaconMovies.get(i).getActors().size();
            for (int j = i; j < kevinBaconMovies.size(); j++) {
                if (kevinBaconMovies.get(j).getActors().size() >= max) {
                    maxIndex = j;
                    max = kevinBaconMovies.get(j).getActors().size();
                }
            }
            kevinBaconMovies.add(i, kevinBaconMovies.remove(maxIndex));
        }


        /*
        String answer = "";
        while (!answer.equals("q")) {
            System.out.println("Enter an actor's name or (q) to quit");
            answer = scanner.nextLine();
            int i = 0;
            while (!answer.equals("q") && i < kevinBaconMovies.size()) {
                if (kevinBaconMovies.get(i).getActors().contains(answer)) {
                    System.out.println(answer + " -> " + kevinBaconMovies.get(i).getTitle() + " -> Kevin Bacon");
                    answer = "q";
                } else if ()
                i++;
            }

        }

         */



    }

}