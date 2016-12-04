package Zad8;


import java.util.ArrayList;

/**
 * Created by Nek on 2016-12-02.
 */
public class Constitution {

    public ArrayList<Chapter> chapters;
    public ArrayList<Article> articles;
    public String preamble;

    public String getArticles(int firstArticle, int lastArticle){
        String result = "";
        for(int i = firstArticle - 1; i < lastArticle; i++) {
            result = result + this.articles.get(i).toString();
        }
        return result;
    }

    public String getArticle(int article){
        return this.articles.get(article).toString();
    }

    public String getChapter(int chapter){
        return this.chapters.get(chapter).chapterHeading + getArticles(this.chapters.get(chapter).articleFirst,this.chapters.get(chapter).articleLast);
    }
}
