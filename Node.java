public class Node {
    int num;
    Node left, right; 
    String color;
    Node(int num) {
        this.num = num; 
        this.left = this.right = null;
        this.color = "red"; 
    }
    Node(int num, Node left, Node right) {
        this.num = num; 
        this.left = left;
        this.right = right; 
        this.color = "black"; 
    }
    public static void main(String[] args) {
        Node a = new Node(2, new Node(1), new Node(3)); 
        Utilities.display(a); 
        
    }
}