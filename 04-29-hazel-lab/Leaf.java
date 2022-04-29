
import java.util.Optional;

public class Leaf<E extends Comparable<E>> extends RBT<E> {
    private final RBTColor color;

    public Leaf() {
        this.color = RBTColor.BLACK;
    }

    public Leaf(RBTColor c) {
        this.color = c;
    }

    public boolean isLeaf() {
        return true;
    }

    public Optional<E> getValue() {
        return Optional.empty();
    }

    public Optional<RBT<E>> getLeft() {
        return Optional.empty();
    }

    public Optional<RBT<E>> getRight() {
        return Optional.empty();
    }

    public RBTColor getColor() {
        return this.color;
    }

    public boolean find(E value) {
        return false;
    }

    // leaves are ALWAYS some form of black
    public RBT<E> blackAdd() {
        if (this.color.equals(RBTColor.BLACK)) {
            return new Leaf(RBTColor.DBL_BLACK);
        }

        return this;
    }

    public RBT<E> blackSub() {
        if (this.color.equals(RBTColor.DBL_BLACK)) {
            return new Leaf(RBTColor.BLACK);
        }

        return this;
    }

    public RBT<E> insertNoPreserve(E value) {
        return this.insert(value);
    }

    public RBT<E> insert(E value) {
        return new Node<E>(value, new Leaf<E>(), new Leaf<E>(), RBTColor.RED);
    }

    public Optional<E> maximum() {
        return Optional.empty();
    }

    public RBT<E> remove(E value) {
        return this;
    }

    public RBT<E> removeNoPreserve(E value) {
        return this;
    }

    public int maxDepth() {
        return 0;
    }

    public String toString() {
        return this.color.toString() + ": leaf";
    }

    // always black or double-black
    public boolean isRed() {
        return false;
    }

    public RBT<E> blacken() {
        return this;
    }

    public RBT<E> redden() {
        return this;
    }
}
