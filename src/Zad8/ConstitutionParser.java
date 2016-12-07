package Zad8;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Nek on 2016-12-02.
 */
public class ConstitutionParser {
	
	public void deleteTrash(BufferedReader in){
		String anotherLine = in.readLine();
		String trash1 = "2009-11-16", trash2 = "©Kancelaria Sejmu";
		while (anotherLine.equals(trash1) || anotherLine.equals(trash2) || anotherLine.length()<=1)
	        anotherLine = in.readLine();
		
	}
    public static void parse(String pathToConstText, Constitution constitution) throws FileNotFoundException {
        try {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(pathToConstText),"UTF8"));
    String anotherLine = in.readLine();
    constitution.chapters = new ArrayList<>();
    do
    {
        if (!anotherLine.equals("2009-11-16") && !anotherLine.equals("©Kancelaria Sejmu") && anotherLine.length()>1)
        constitution.preamble = constitution.preamble + anotherLine + "\n";
        anotherLine = in.readLine();
    }
    while (!anotherLine.equals("Rozdział I"));

    int i = -1, j = 0, k = 1;
    constitution.articles = new ArrayList<>();

    do {
        constitution.chapters.add(new Chapter());
        i++;
        do {
            if (!anotherLine.equals("2009-11-16") && !anotherLine.equals("©Kancelaria Sejmu")) {
                constitution.chapters.get(i).chapterHeading =
                        constitution.chapters.get(i).chapterHeading + anotherLine +"\n";
            }
            anotherLine = in.readLine();
        }
        while (anotherLine.length() < 4 || !anotherLine.substring(0, 4).equals("Art."));


        constitution.chapters.get(i).articleFirst = k;
        do {
            constitution.articles.add(new Article());
            do {
                if (!anotherLine.equals("2009-11-16") && !anotherLine.equals("©Kancelaria Sejmu")) {
                    constitution.articles.get(j).articleText =
                            constitution.articles.get(j).articleText
                                    + anotherLine + "\n";
                }
                anotherLine = in.readLine();
            }
            while (anotherLine != null && (anotherLine.length() < 4 || !anotherLine.substring(0, 4).equals("Art.")) && (anotherLine.length() < 8 || !anotherLine.substring(0, 8).equals("Rozdział")));
            j++;
            k++;
        }
        while (anotherLine != null && (anotherLine.length() < 8 || !anotherLine.substring(0, 8).equals("Rozdział")));
    constitution.chapters.get(i).articleLast = k-1;
    }
    while (anotherLine != null);
    in.close();
} catch (IOException e) {
    e.printStackTrace();
}
        return;
    }
}