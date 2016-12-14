package Zad8;

import java.io.*;

/**
 * Created by Nek on 2016-12-03.
 */
public class Main {
    public static void main(String[] args){
      try {
          Constitution constitution = new Constitution();
          ConstitutionParser.parse(args[0], constitution);

          int i = 1;
          int j = 243;
          System.out.print(constitution.getArticles(i,j));
       } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Udało się!");
        return;
    }
}
