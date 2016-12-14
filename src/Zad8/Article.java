package Zad8;

/**
 * Created by Nek on 2016-12-02.
 */
public class Article {
    public String articleText;

    public Article() {
        this.articleText = "";
    }

    public String toString(){
        StringBuilder buffer = new StringBuilder(this.articleText);

        for(int i = 0; i < buffer.length()-3; i++)
            if(buffer.substring(i,i+2).equals("-\n")) {
                buffer.delete(i,i+2);
                while(buffer.charAt(i) != ' ' && buffer.charAt(i) != '.')
                    i++;
                if(buffer.charAt(i) != '.')
                    buffer.insert(i+1,"\n");
            }
        return buffer.toString();
    }



}
