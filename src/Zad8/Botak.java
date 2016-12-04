package Zad8;

/**
 * Created by Nek on 2016-12-04.
 */
public class Botak {
    public static void main(String args[]){
        System.out.print(("A\nr\nt.").substring(0,2));
        StringBuffer buffer = new StringBuffer("A\nr\nt.");
        buffer.delete(1,2);
        buffer.delete(2,3);
        System.out.println(buffer.toString());
    }
}
