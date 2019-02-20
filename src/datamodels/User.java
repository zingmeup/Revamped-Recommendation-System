package datamodels;

import java.util.*;

public class User {
    private int id;
    private Hashtable<Integer, Double> watched;
    private TreeSet<SynergyUser> synergyUserTree;
    private ArrayList<Movie> recommended;
    public TreeSet<SynergyUser> getSynergyUserTree() {
        return synergyUserTree;
    }
    public User() {
        this.id = 0;
        watched=new Hashtable<>();
        synergyUserTree =new TreeSet<>();
        recommended=new ArrayList<>();
    }

    public ArrayList<Movie> getRecommended() {
        return recommended;
    }

    public Hashtable<Integer, Double> getWatched() {
        return watched;
    }
    public void setId(int id) {
        this.id = id;
    }
}
