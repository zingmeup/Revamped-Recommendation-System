package launcher;

import java.util.Scanner;
public class SynergyTest {
    static private void addScore(double trusterScore, double trusteeScore){
        double score=0.0;
        double addition=(2.5-Math.abs(trusterScore-trusteeScore));
        score+=addition;
        System.out.println("Synergy score to add: "+score);
    }
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        boolean exitLoop=false;
        while (!exitLoop){
            String[] input=scan.nextLine().split(" ");
            if (input[0].equals("add")){
                addScore(Double.valueOf(input[1]),Double.valueOf(input[2]));

            }else if(input[0].equals("exit")){
                exitLoop=true;
                System.out.println("exiting...");

            }else{
                System.out.println("unknown command");
            }
        }
    }
}
