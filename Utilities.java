import java.util.*; 

public class Utilities {
    public static void display(Node a) {
        Displaux displaux = displaux(a); 
        for (String line : displaux.lines) 
            System.out.println( line ); 
    }
    public static Displaux displaux(Node a) {
        if (a.right == null && a.left == null) { 
            String line = a.num + ""; 
            int width = line.length(); 
            int height = 1; 
            int middle = width / 2; 
            ArrayList<String> lines = new ArrayList<String>(); 
            lines.add(line); 
            return new Displaux( lines, width, height, middle ); 
        }
        if (a.right == null) { 
            Displaux result = displaux(a.left); 
            ArrayList<String> lines = result.lines; 
            int n = result.width; 
            int p = result.height; 
            int x = result.middle; 
            String s = a.num + ""; 
            int u = s.length(); 
            String firstLine = f(x + 1, ' ') + f(n - x - 1, '_') + s;
            String secondLine = f(x, ' ') + "/" + f(n - x - 1 + u, ' '); 
            ArrayList<String> shiftedLines = new ArrayList<String>();
            for (String line : lines) 
                shiftedLines.add(line + f(u, ' ')); 
            shiftedLines.add(0, secondLine); 
            shiftedLines.add(0, firstLine);
            return new Displaux(shiftedLines, n + u, p + 2, n + u / 2); 
        }
        // Only right child.
        if (a.left == null) { 
            Displaux result = displaux(a.right); 
            ArrayList<String> lines = result.lines; 
            int n = result.width; 
            int p = result.height; 
            int x = result.middle; 
            String s = a.num + ""; 
            int u = s.length(); 
            String firstLine = s + f(x, '_') + f(n - x, ' ');
            String secondLine = f(u + x, ' ') + "\\" + f(n - x - 1, ' '); 
            ArrayList<String> shiftedLines = new ArrayList<String>();
            for (String line : lines) 
                shiftedLines.add(f(u, ' ') + line); 
            shiftedLines.add(0, secondLine); 
            shiftedLines.add(0, firstLine);
            return new Displaux(shiftedLines, n + u, p + 2, u / 2); 
        } 
        // Two children.
        Displaux result = displaux(a.left); 
        ArrayList<String> left = result.lines; 
        int n = result.width; 
        int p = result.height; 
        int x = result.middle; 
        result = displaux(a.right); 
        ArrayList<String> right = result.lines; 
        int m = result.width; 
        int q = result.height; 
        int y = result.middle; 
        String s = a.num + ""; 
        int u = s.length(); 

        String firstLine = f(x + 1, ' ') + f(n - x - 1, '_') + s + f(y, '_') + f(m - y, ' ');
        String secondLine = f(x, ' ') + "/" + f(n - x - 1 + u + y, ' ') + "\\" + f(m - y - 1, ' ');  

        if (p < q) { 
            for (int i = 0; i < q - p; i++) 
                left.add(f(n, ' '));  
        } else if (q < p) { 
            for (int i = 0; i < p - q; i++) 
                right.add(f(m, ' '));  
        } 

        ArrayList<String> lines = new ArrayList<String>();  
        for (int count = 0; count < Math.min(left.size(), right.size()); count++)         
            lines.add( left.get(count) + f(u, ' ') + right.get(count) );     
        lines.add(0, secondLine);
        lines.add(0, firstLine); 
        return new Displaux(lines, n + m + u, Math.max(p, q) + 2, n + u / 2); 

    }

    public static String f(int length, char c) {
        return new String(new char[length]).replace('\0', c);
    }  

    public static ArrayList<String> g(int length, String s) {
        ArrayList<String> result = new ArrayList<String>(); 
        for (int i = 0; i < length; i++) {
            result.add(s); 
        }
        return result;
    }     
}