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
    public static void main(String[] args) {
        Node a = new Node(2, new Node(1), new Node(3)); 
        Utilities.display(a); 
        System.out.println("Starting with 5"); 
        a = new Node(5); a.color = "black"; Utilities.display(a); 
        a.insert(3); System.out.println("Inserting 3"); Utilities.display(a); 
        a.insert(4); System.out.println("Inserting 4"); Utilities.display(a); 
        a.insert(1); System.out.println("Inserting 1"); Utilities.display(a); 
        a.insert(2); System.out.println("Inserting 2"); Utilities.display(a); 
        
    }
}