package launcher;

import datamodels.Movie;
import loggerAPI.Log;

import java.io.IOException;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        Log.visibility=true;
        Scanner scan=new Scanner(System.in);
        System.out.print((char) 27 + "[36m" +"Enter the user for analysis: ");
        Engine engine=new Engine(scan.nextInt());
        System.out.print((char) 27 + "[36m" +"Enter number of movies to recommend: ");
        int num=scan.nextInt();
        ArrayList<Movie> recommendation=engine.getRecommendation(num);
        System.out.println("----------------------------------------------" +
                "\nRecommended top "+num+" movies: \n");
        for (Movie m:recommendation){
            System.out.println(m);
        }
    }
}