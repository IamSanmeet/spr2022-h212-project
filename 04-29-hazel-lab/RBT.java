
 
import java.util.Optional;
import java.awt.Color;

public abstract class RBT<E extends Comparable<E>> {
    // color
    public static enum RBTColor {
        RED,
        BLACK,
        NEG_RED,
        DBL_BLACK;

        public RBTColor add1() {
            switch (this) {
            case NEG_RED:
                return RED;
            case RED:
                return BLACK;
            case BLACK:
                return DBL_BLACK;
            default:
                return this;
            }
        }

        public RBTColor sub1() {
            switch (this) {
            case RED:
                return NEG_RED;
            case BLACK:
                return RED;
            case DBL_BLACK:
                return BLACK;
            default:
                return this;
            }
        }

        public Color toAWT() {
            switch (this) {
            case RED:
                return Color.RED;
            case BLACK:
                return Color.BLACK;
            case NEG_RED:
                return Color.BLUE;
            case DBL_BLACK:
                return Color.GRAY;
            default:
                return Color.YELLOW;
            }
        }
    };

    // getters (no setters)
    public abstract Optional<E> getValue();
    public abstract Optional<RBT<E>> getLeft();
    public abstract Optional<RBT<E>> getRight();
    public abstract RBTColor getColor();
    public abstract boolean isRed();
    public boolean isBlack() {
        return !this.isRed();
    }
    public abstract boolean isLeaf();
    public boolean isNode() {
        return !this.isLeaf();
    }

    // helper functions
    public abstract RBT<E> blacken();
    public abstract RBT<E> redden();
    public abstract RBT<E> blackAdd();
    public abstract RBT<E> blackSub();
    public abstract Optional<E> maximum();
    public abstract int maxDepth();
    public abstract RBT<E> insertNoPreserve(E value);
    public abstract RBT<E> removeNoPreserve(E value);

    // utility functions
    public abstract boolean find(E value);
    public abstract RBT<E> insert(E value);
    public abstract RBT<E> remove(E value);
    public abstract String toString();
}