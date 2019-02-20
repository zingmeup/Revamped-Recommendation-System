package datamodels;

public class Movie implements Comparable {
    private int id;
    private double rating;

    public Movie(int id, double rating) {
        this.id = id;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }
    @Override
    public int compareTo(Object o) {
        return Double.compare(this.rating,((Movie)o).getRating());
    }

    @Override
    public String toString() {
        return (char) 27 + "[30m" +"Movie: "+this.getId()+" Score: "+this.rating;
    }
}
