package datamodels;

public class SynergyUser implements Comparable {
    private int toId;
    private double score;
    public int getToId() {
        return toId;
    }
    public double getScore() {
        return score;
    }
    public void addScore(double trusterScore, double trusteeScore){
        double addition=(2.5-Math.abs(trusterScore-trusteeScore));
        score+=addition;
    }

    public SynergyUser(int toId) {
        this.toId = toId;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.toId,((SynergyUser)o).toId);
    }

}
