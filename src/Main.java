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
        ArrayList<String> kevinBaconFriends = MovieDatabaseBuilder.getActorsDB("src/KevinBaconActors.txt");
        ArrayList<SimpleMovie> actorMovies = MovieDatabaseBuilder.getActorMoviesDB("src/KevinBaconActorMovies.txt");
        ArrayList<SimpleMovie> kevinBaconMovies = MovieDatabaseBuilder.getKevinBaconMovies("src/kevinBaconMovies.txt");
        Collections.sort(kevinBaconFriends);
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

        String answer = "";
        while (!answer.equals("q")) {
            System.out.println("Enter an actor's name or (q) to quit");
            answer = scanner.nextLine();
            int i = 0;
            while (!answer.equals("q") && i < kevinBaconMovies.size()) {
                if (kevinBaconMovies.get(i).getActors().contains(answer) && !answer.equals("q")) {
                    System.out.println(answer + " -> " + kevinBaconMovies.get(i).getTitle() + " -> Kevin Bacon");
                    answer = "q";
                }
                i++;
            }
            i = 0;
            while (!answer.equals("q") && i < actorMovies.size()) {
                SimpleMovie actorMovie = actorMovies.get(i);
                if (actorMovie.getActors().contains(answer)) {
                    for (int j = 0; j < kevinBaconFriends.size() && !answer.equals("q"); j++) {
                        String actor = kevinBaconFriends.get(j);
                        if (actorMovies.get(i).getActors().contains(actor)) {
                            for (SimpleMovie movie : kevinBaconMovies) {
                                if (movie.getActors().contains(actor) && !answer.equals("q")) {
                                    System.out.println(answer + " -> " + actorMovie.getTitle() + " -> " + actor + " -> " + movie.getTitle() + " -> Kevin Bacon");
                                    answer = "q";
                                }
                            }
                        }
                    }
                }
                i++;
            }
            i = 0;
            ArrayList<SimpleMovie> moviesActorIn = new ArrayList<SimpleMovie>();
            for (SimpleMovie movie : movies) {
                if (movie.getActors().contains(answer)) {
                    moviesActorIn.add(movie);
                }
            }
            ArrayList<String> friendsWithActor = new ArrayList<String>();
            for (SimpleMovie movie : moviesActorIn) {
                for (int j = 0; j < movie.getActors().size(); j++) {
                    if (!moviesActorIn.contains(movie.getActors().get(j)) ) {
                        friendsWithActor.add(movie.getActors().get(j));
                    }
                }
            }
            friendsWithActor.remove(answer);
            while (!answer.equals("q") && i < moviesActorIn.size()) {
                for (int j = 0; j < friendsWithActor.size(); j++) {
                        for (int k = 0; k < actorMovies.size(); k++) {
                            SimpleMovie actorMovie = actorMovies.get(k);
                            if (actorMovie.getActors().contains(friendsWithActor.get(j))) {
                                SimpleMovie movieFromActor = null;
                                int movieIndex = 0;
                                while (movieFromActor == null) {
                                    if (moviesActorIn.get(movieIndex).getActors().contains(friendsWithActor.get(j))) {
                                        movieFromActor = moviesActorIn.get(movieIndex);
                                    }
                                    movieIndex++;
                                }
                                for (int l = 0; l < kevinBaconFriends.size() && !answer.equals("q"); l++) {
                                    String actor = kevinBaconFriends.get(l);
                                    if (actorMovies.get(k).getActors().contains(actor)) {
                                        for (SimpleMovie movie : kevinBaconMovies) {
                                            if (movie.getActors().contains(actor) && !answer.equals("q")) {
                                                System.out.println(answer + " -> " + movieFromActor.getTitle() + " -> " + friendsWithActor.get(j) + " -> " + actorMovie.getTitle() + " -> " + actor + " -> " + movie.getTitle() + " -> Kevin Bacon");
                                                answer = "q";
                                            }
                                        }
                                    }
                                }
                        }
                    }
                }
                i++;
            }
        }
    }
}