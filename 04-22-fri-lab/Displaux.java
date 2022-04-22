// Displaux.java

import java.util.ArrayList; 

public class Displaux { // I could have called this type (class) a Tuple 
    public ArrayList<String> lines;
    public int width, height, middle; 
    public Displaux(ArrayList<String> lines, int width, int height, int middle) {
        this.lines = lines; 
        this.width = width; 
        this.height = height;
        this.middle = middle; 
    }
}