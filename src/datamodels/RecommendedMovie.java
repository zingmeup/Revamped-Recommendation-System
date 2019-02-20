package datamodels;

public class RecommendedMovie implements Comparable {
    private int id;
    private double score;

    public RecommendedMovie(int id) {
        this.id = id;
        this.score=0.0;
    }

    public int getId() {
        return id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score= score;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.id,((RecommendedMovie)o).getId());
    }
}
