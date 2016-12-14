package Zad8;

import java.io.BufferedReader;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nek on 2016-12-02.
 */
public class ConstitutionParser {

    private static void transformIntoString(BufferedReader in, ArrayList<String> ConstStringList) throws IOException{
        String anotherLine;
        do{
            anotherLine = in.readLine();
            ConstStringList.add(anotherLine);
        }
        while (anotherLine != null);
    }

	private static void deleteTrash(ArrayList<String> constStringList) {
		String trash1 = "2009-11-16", trash2 = "©Kancelaria Sejmu";
		String anotherLine;
		int size = constStringList.size();
		for(int i = 0; i < size-1; i++){
		    anotherLine = constStringList.get(i);
		    if (anotherLine.equals(trash1) || anotherLine.equals(trash2) || anotherLine.length()<=1){
                constStringList.remove(i);
                i--;
                size--;
		    }
		}
	}

	private static void putChaptersAndArticles(ArrayList<String> constStringList, Constitution c) {
        String anotherLine = null;
        for(int i = 0; i < constStringList.size(); i++){
            anotherLine = constStringList.get(i);
            if (anotherLine != null && anotherLine.length() > 4 && anotherLine.substring(0, 4).equals("Art."))
                putArticle(i,c,constStringList);

            if (anotherLine != null && anotherLine.length() > 8 && anotherLine.substring(0, 8).equals("Rozdział"))
                putChapter(i, c, constStringList);
        }

    }

    private static void putChapter(int i, Constitution c, ArrayList<String> constStringList) {
        c.chapters.add(new Chapter());
        c.chapters.get(c.chapters.size()-1).chapterHeading = constStringList.get(i) +"\n" + constStringList.get(i+1) +"\n";
    }

    private static void putArticle(int i, Constitution c, ArrayList<String> constStringList) {
        c.articles.add(new Article());
        c.articles.get(c.articles.size()-1).articleText = "";
        do {
            c.articles.get(c.articles.size() - 1).articleText =
                    c.articles.get(c.articles.size() - 1).articleText
                            + constStringList.get(i) + "\n";
            i++;
        }
        while( constStringList.get(i) != null && (constStringList.get(i).length() < 4 || !constStringList.get(i).substring(0, 4).equals("Art.")) &&
                (constStringList.get(i).length() < 8 || !constStringList.get(i).substring(0, 8).equals("Rozdział")));
    }

    private static void putArticleNumberInChapters(ArrayList<String> constStringList, Constitution c){
        int countArt = 0;
        int countChapt = 0;
        for(String anotherLine : constStringList) {
            if (anotherLine != null && anotherLine.length() > 4 && anotherLine.substring(0, 4).equals("Art."))
                countArt++;
            if (anotherLine != null && anotherLine.length() > 8 && anotherLine.substring(0, 8).equals("Rozdział")){
                c.chapters.get(countChapt).articleFirst = countArt+1;
                if(countChapt > 0) c.chapters.get(countChapt-1).articleLast = countArt;
                countChapt++;
            }
        }
        c.chapters.get(countChapt-1).articleLast = countArt;
    }


    public static void parse(String pathToConstText, Constitution constitution) throws FileNotFoundException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(pathToConstText),"UTF8"));
            constitution.chapters = new ArrayList<>();
            constitution.articles = new ArrayList<>();
            ArrayList<String> constStringList = new ArrayList<>();

            transformIntoString(in,constStringList);

            in.close();

            deleteTrash(constStringList);

            putChaptersAndArticles(constStringList,constitution);

            putArticleNumberInChapters(constStringList,constitution);

} catch (IOException e) {
    e.printStackTrace();
}
        return;
    }
}