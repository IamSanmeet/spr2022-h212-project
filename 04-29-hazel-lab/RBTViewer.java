 
import javax.swing.*;
import java.awt.*;

public class RBTViewer<E extends Comparable<E>> extends JComponent {
    private static final long serialVersionUID = -1116988341169822806L;
    private RBT<DrawableNode<E>> graph;
    private static final int SCALE = 5;

    public RBTViewer(RBT<DrawableNode<E>> graph) {
        this.graph = graph;
    }

    public void insert(DrawableNode<E> node) {
        graph = graph.insert(node);
        System.out.println(graph.toString());
        this.repaint();
    }

    public void remove(DrawableNode<E> node) {
        graph = graph.remove(node);
        System.out.println(graph.toString());
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        this.paintNode(this.graph, g, 400, 20, this.graph.maxDepth());
    }

    private void paintNode(RBT<DrawableNode<E>> bst, Graphics g, int x, int y, int depth) {
        bst.getValue().ifPresent(node -> {
            node.draw(g, bst.getColor().toAWT(), x, y);
            int newY = y + 40;
            bst.getLeft().ifPresent(left -> {
                int newX = x - 20 - (SCALE * (int) Math.pow(depth, 2));
                if (left.isNode()) {
                    g.setColor(Color.BLACK);
                    Util.arrow(g, x, y, newX, newY, 5, 5);
                }
                paintNode(left, g, newX, newY, depth - 1);
            });
            bst.getRight().ifPresent(right -> {
                int newX = x + 20 + (SCALE * (int) Math.pow(depth, 2));
                if (right.isNode()) {
                    g.setColor(Color.BLACK);
                    Util.arrow(g, x, y, newX, newY, 5, 5);
                }
                paintNode(right, g, newX, newY, depth - 1);
            });
        });
    }
}