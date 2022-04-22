public class Node {
    int num;
    Node left, right; 
    String color; 
    public String toString() {
        if (this.color.equals("red")) return "[" + this.num + "]"; 
        else return this.num + ""; 
    }
    public Node(int num) {
        // this.num = num; 
        // this.left = this.right = null;
        // this.color =  color; 
        this(num, null, null, "red"); 
    }
    private Node(int num, Node left, Node right, String color) {
        this.num = num; 
        this.left = left;
        this.right = right; 
        this.color = color; 
    }
    public Node(int num, Node left, Node right) {
        this(num, left, right, "black");
    }
    public void insert(int num) {
        if (this.num == num) return; 
        else if (this.num > num) {
            if (this.left == null) this.left = new Node(num); 
            else this.left.insert(num); 
        } else {
            if (this.right == null) this.right = new Node(num); 
            else this.right.insert(num); 
        }
    }
    public static boolean westViolation(Node a) {
        if (a.color.equals("black") && a.left != null && // black node with left child
            a.left.color.equals("red") && a.left.right != null && // left child red with right child
            a.left.right.color.equals("red")) return true; // right child of only child is red (like parent)
        return false;
    }
    public static boolean eastViolation(Node a) {
        if (a.color.equals("black") && a.right != null && // black node with right child
            a.right.color.equals("red") && a.right.left != null && // right child red with left child
            a.right.left.color.equals("red")) return true; // left child of only child is red (like parent)
        return false;
    }
    public static Node balance(Node m) {
        if (m.left != null) m.left = Node.balance(m.left); // fix it here ... 
        if (m.right != null) m.right = Node.balance(m.right); // ... or here
        if (Node.westViolation(m)) {
            System.out.println(m.num + " double red! (West)"); 
            int X = m.left.num, Y = m.left.right.num, Z = m.num; 
            Node a = m.left.left, b = m.left.right.left, c = m.left.right.right, d = m.right;
            return new Node(Y, new Node(X, a, b), new Node(Z, c, d), "red"); 
        } else if (Node.eastViolation(m)) {
            System.out.println(m.num + " double red! (East)"); 
            int X = m.num, Y = m.right.left.num, Z = m.right.num; 
            Node a = m.left, b = m.right.left.left, c = m.right.left.right, d = m.right.right;
            return new Node(Y, new Node(X, a, b), new Node(Z, c, d), "red"); 
        } else {
            System.out.println(m.num + " ... is good.");
        }
        return m; 
    }
    public static Node insert(Node a, int num) {
        a.insert(num); 
        System.out.println("Inserting " + num); 
        Utilities.display(a); 
        a = Node.balance(a); 
        Utilities.display(a); 
        a.color = "black";
        Utilities.display(a); 
        System.out.println("-----------------(after inserting " + num + ")----"); 
        return a;
    }
    public static void main(String[] args) {
        Node a = new Node(2, new Node(1), new Node(3)); 
        // Utilities.display(a); 
        System.out.println("Starting with 5"); 
        a = new Node(5); a.color = "black"; Utilities.display(a); 
        a = Node.insert(a, 3);  
        a = Node.insert(a, 4); 
        // a.insert(1); System.out.println("Inserting 1"); Utilities.display(a); 
        a = Node.insert(a, 1); 
        // a.insert(2); System.out.println("Inserting 2"); Utilities.display(a); 
        a = Node.insert(a, 2); 
        a = Node.insert(a, 7); 
        a = Node.insert(a, 6); 
        
    }
}