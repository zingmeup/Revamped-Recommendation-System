package launcher;

import data.AppData;
import datamodels.Movie;
import datamodels.OtherUser;
import datamodels.RecommendedMovie;
import datamodels.SynergyUser;
import loggerAPI.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Engine {

    Engine(int i) throws IOException {
        AppData.getInstance().getUser().setId(i);
        Log.v("Engine", "Reading Data");
        String fileName = "E:/Java/Movie 2/src/data/ratings";
        BufferedReader dataBuffer = new BufferedReader(new FileReader(fileName));
        String dataLine = dataBuffer.readLine();
        Log.v("Engine", "Analyzing  Movies for user " + i);
        Hashtable<Integer, Double> watched = AppData.getInstance().getUser().getWatched();
        while (dataLine != null) {
            int userid = Integer.valueOf(dataLine.split(" ")[0]), movieid = Integer.valueOf(dataLine.split(" ")[1]);
            double rating = Double.valueOf(dataLine.split(" ")[2]);
            if (userid == i) {
                watched.put(movieid, rating);
            } else {
                storeToOtherUsersTree(dataLine.split(" "));
            }
            dataLine = dataBuffer.readLine();
        }
        Log.i("Movies/Rating watched/rated by User " + i, +watched.size() + " movies\n" + watched.toString());
        dataBuffer = new BufferedReader(new FileReader(fileName));
        dataLine = dataBuffer.readLine();
        Log.v("Engine", "Scoring similar Users..");
        Log.v("Engine", "Mapping other analyzable users Details..");
        while (dataLine != null) {
            mapMoviesOfOtheruserInTree(dataLine, i);
            dataLine = dataBuffer.readLine();
        }

        Log.v("Engine", "Scoring Movies Based On Similar Users..");
        scoreMoviesBasedOnSimilarUsers();
        Log.i("Number of recommendable movies: ", String.valueOf(AppData.getInstance().getRecommendedMovieTreeSet().size()));
        Log.v("Engine", "Ordering Movies Based On Score..");
        orderMoviesBasedOnScore();
    }

    private void mapMoviesOfOtheruserInTree(String dataLine, int i) {
        int userid = Integer.valueOf(dataLine.split(" ")[0]), movieid = Integer.valueOf(dataLine.split(" ")[1]);
        double rating = Double.valueOf(dataLine.split(" ")[2]);
        if (!(userid == i)) {
            if (AppData.getInstance().getUser().getWatched().containsKey(movieid)) {
                if (!AppData.getInstance().getUser().getSynergyUserTree().contains(new SynergyUser(userid))) {
                    AppData.getInstance().getUser().getSynergyUserTree().add(new SynergyUser(userid));
                }
                Iterator itr = AppData.getInstance().getUser().getSynergyUserTree().iterator();
                boolean found = false;
                while (itr.hasNext() && !found) {
                    SynergyUser syn = ((SynergyUser) (itr.next()));
                    if (syn.getToId() == userid) {
                        found = true;
                        syn.addScore(AppData.getInstance().getUser().getWatched().get(movieid), rating);
                    }
                }
            }
        }
    }

    private void orderMoviesBasedOnScore() {
        Iterator recTSItr = AppData.getInstance().getRecommendedMovieTreeSet().iterator();
        while (recTSItr.hasNext()) {
            RecommendedMovie recommendedMovie = (RecommendedMovie) recTSItr.next();
            AppData.getInstance().getUser().getRecommended().add(new Movie(recommendedMovie.getId(), recommendedMovie.getScore()));
        }
        Collections.sort(AppData.getInstance().getUser().getRecommended());
    }

    private void scoreMoviesBasedOnSimilarUsers() {
        TreeSet<SynergyUser> sytree = AppData.getInstance().getUser().getSynergyUserTree();
        Iterator iterator = sytree.iterator();
        while (iterator.hasNext()) {
            SynergyUser syUser = (SynergyUser) iterator.next();
            if (syUser.getScore() > 0) {
                Iterator otherItr = AppData.getInstance().getOtherUsers().iterator();
                boolean found = false;
                while (otherItr.hasNext() && !found) {
                    OtherUser ou = (OtherUser) otherItr.next();
                    if (ou.getId() == syUser.getToId()) {
                        found = true;
                        Set<Double> ratingKeys = ou.getWatchedMap().keySet();
                        Double[] rat = new Double[ratingKeys.size()];
                        Iterator ratingsKeysItr = ratingKeys.iterator();
                        int ratindex = 0;
                        while (ratingsKeysItr.hasNext()) {
                            Double rating = (Double) ratingsKeysItr.next();
                            rat[ratindex++] = rating;
                        }
                        for (Double d : rat) {
                            ArrayList<Integer> listForR = ou.getWatchedMap().get(d);
                            for (Integer movie : listForR) {
                                if (!AppData.getInstance().getUser().getWatched().containsKey(movie)) {
                                    if (!AppData.getInstance().getRecommendedMovieTreeSet().contains(new RecommendedMovie(movie))) {
                                        AppData.getInstance().getRecommendedMovieTreeSet().add(new RecommendedMovie(movie));
                                    }
                                    Iterator it = AppData.getInstance().getRecommendedMovieTreeSet().iterator();
                                    boolean movieFound = false;
                                    while (it.hasNext() && !movieFound) {
                                        RecommendedMovie rMovie = (RecommendedMovie) it.next();
                                        if (rMovie.getId() == movie) {
                                            movieFound = true;
                                            double currentScore = rMovie.getScore();
                                            rMovie.setScore(currentScore + (d * syUser.getScore()));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    ArrayList<Movie> getRecommendation(int num) {
        ArrayList<Movie> recommendedMovies = new ArrayList<>(num);
        int size = AppData.getInstance().getUser().getRecommended().size();
        for (int i = size - 1; i >= size - num; i--) {
            recommendedMovies.add(AppData.getInstance().getUser().getRecommended().get(i));
        }
        return recommendedMovies;
    }

    private void storeToOtherUsersTree(String[] attr) {
        OtherUser u = new OtherUser(Integer.parseInt(attr[0]));
        if (!AppData.getInstance().getOtherUsers().contains(u)) {
            AppData.getInstance().getOtherUsers().add(u);
        }
        Iterator i = AppData.getInstance().getOtherUsers().iterator();
        boolean found = false;
        while (i.hasNext() && !found) {
            OtherUser im = (OtherUser) i.next();
            if (im.getId() == Integer.parseInt(attr[0])) {
                if (!im.getWatchedMap().containsKey(Double.parseDouble(attr[2]))) {
                    im.getWatchedMap().put(Double.parseDouble(attr[2]), new ArrayList<Integer>());
                }
                im.getWatchedMap().get(Double.parseDouble(attr[2])).add(Integer.valueOf(attr[1]));
                found = true;
            }
        }
    }
}
