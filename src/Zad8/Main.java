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

          int i = (int) args[1].charAt(0);
          System.out.print(constitution.getArticle(i));
       } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Udało się!");
        return;
    }
}
