

import java.awt.*;

public class DrawableNode<E extends Comparable<E>> implements Comparable<DrawableNode<E>> {
    public final static int RADIUS = 20;
    private E data;
    private int x, y;

    public DrawableNode(E data) {
        this.data = data;
        this.x = (int) (Math.random() * 400 + 50);
        this.y = (int) (Math.random() * 400 + 50);
    }

    public E getData() {
        return data;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean contains(int x, int y) {
        return RADIUS >= Math.sqrt(Math.pow(this.x - x + RADIUS, 2) + Math.pow(this.y - y + RADIUS, 2));
    }

    public void draw(Graphics g) {
        this.draw(g, Color.WHITE);
    }

    public void draw(Graphics g, Color c) {
        this.draw(g, c, this.x, this.y);
    }

    public void draw(Graphics g, Color c, int x, int y) {
        g.setColor(c);
        g.fillOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
        g.setColor(Color.WHITE);
        g.drawOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
        Font myFont = new Font("IBM Plex Sans", Font.BOLD, 20); // numbers inside nodes have a certain size
        ((Graphics2D) g).setFont(myFont);
        ((Graphics2D) g).drawString(this.data + "", x - (RADIUS * 1 / 2), y + (RADIUS * 1 / 2));
    }

    public int compareTo(DrawableNode<E> n) {
        return this.data.compareTo(n.getData());
    }

    public String toString() {
        return this.data.toString();
    }
}
