package data;

import datamodels.OtherUser;
import datamodels.RecommendedMovie;
import datamodels.User;

import java.util.TreeSet;

public class AppData {
    static AppData appData;
    User user;
    TreeSet<OtherUser> otherUsers;
    TreeSet<RecommendedMovie> recommendedMovieTreeSet;
    AppData(){
        user=new User();
        otherUsers=new TreeSet<>();
        recommendedMovieTreeSet=new TreeSet<>();
    }
    public static AppData getInstance(){
        if (appData==null) appData=new AppData();
        return appData;
    }

    public TreeSet<RecommendedMovie> getRecommendedMovieTreeSet() {
        return recommendedMovieTreeSet;
    }

    public User getUser() {
        return user;
    }

    public TreeSet<OtherUser> getOtherUsers() {
        return otherUsers;
    }
}
