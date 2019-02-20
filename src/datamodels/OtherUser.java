package datamodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OtherUser implements Comparable {
    private int id;
    private Map<Double, ArrayList<Integer>> watchedMap;

    public OtherUser(int id) {
        this.id = id;
        this.watchedMap = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public Map<Double, ArrayList<Integer>> getWatchedMap() {
        return watchedMap;
    }
    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.id,((OtherUser)o).id);
    }
}
