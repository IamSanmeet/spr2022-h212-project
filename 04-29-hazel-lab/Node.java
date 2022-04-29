 
import java.util.Optional;

public class Node<E extends Comparable<E>> extends RBT<E> {
    protected final E value;
    protected final RBT<E> left, right;
    private final RBTColor color;

    public Node(E value, RBT<E> left, RBT<E> right, RBTColor color) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.color = color;
    }

    public boolean isLeaf() {
        return false;
    }

    public Optional<E> getValue() {
        return Optional.of(this.value);
    }

    public Optional<RBT<E>> getLeft() {
        return Optional.of(this.left);
    }

    public Optional<RBT<E>> getRight() {
        return Optional.of(this.right);
    }

    public boolean isRed() {
        return this.color.equals(RBTColor.RED);
    }

    public RBTColor getColor() {
        return this.color;
    }

    public boolean find(E value) {
        if (this.value.equals(value)) {
            return true;
        } else if (this.value.compareTo(value) > 0) {
            return this.left.find(value);
        } else {
            return this.right.find(value);
        }
    }

    public RBT<E> blackAdd() {
        return new Node<>(this.value, this.left, this.right, this.color.add1());
    }

    public RBT<E> blackSub() {
        return new Node<>(this.value, this.left, this.right, this.color.sub1());
    }

    protected boolean isSomeBlack() {
        return this.color.equals(RBTColor.BLACK) || this.color.equals(RBTColor.DBL_BLACK);
    }

    public RBT<E> balance() {
        if (this.isSomeBlack() && this.left.isNode() && this.left.isRed()) {
            Node<E> leftN = (Node) this.left;

            // case 1, case 2
            if (leftN.left.isNode() && leftN.left.isRed()) {
                Node<E> leftLeftN = (Node) leftN.left;

                return new Node<>(leftN.value,
                                  new Node<>(leftLeftN.value, leftLeftN.left, leftLeftN.right, RBTColor.BLACK),
                                  new Node<>(this.value, leftN.right, this.right, RBTColor.BLACK),
                                  this.color.sub1());
            } else if (leftN.right.isNode() && leftN.isRed()) {
                Node<E> leftRightN = (Node) leftN.right;

                return new Node<>(leftRightN.value,
                                  new Node<>(leftN.value, leftN.left, leftRightN.left, RBTColor.BLACK),
                                  new Node<>(this.value, leftRightN.right, this.right, RBTColor.BLACK),
                                  this.color.sub1());
            }
        }

        // case 3, case 4
        if (this.isSomeBlack() && this.right.isNode() && this.right.isRed()) {
            Node<E> rightN = (Node) this.right;

            if (rightN.left.isNode() && rightN.left.isRed()) {
                Node<E> rightLeftN = (Node) rightN.left;

                return new Node<>(rightLeftN.value,
                                  new Node<>(this.value, this.left, rightLeftN.left, RBTColor.BLACK),
                                  new Node<>(rightN.value, rightLeftN.right, rightN.right, RBTColor.BLACK),
                                  this.color.sub1());
            } else if (rightN.right.isNode() && rightN.right.isRed()) {
                Node<E> rightRightN = (Node) rightN.right;

                return new Node<>(rightN.value,
                                  new Node<>(this.value, this.left, rightN.left, RBTColor.BLACK),
                                  new Node<>(rightRightN.value, rightRightN.left, rightRightN.right, RBTColor.BLACK),
                                  this.color.sub1());
            }
        }

        // added case, handling negative reds:
        //      zBB                       yB
        //      / \                      / \
        //   xNR   e                    xB zB
        //   /  \           --->       / \ / \
        //  wB  yB                    wR c d e
        // / \  / \                  / \
        // a b  c d                  a b
        // and w requires rebalancing
        if (this.color.equals(RBTColor.DBL_BLACK) && this.left.isNode()
            && this.left.getColor().equals(RBTColor.NEG_RED)) {
            Node<E> leftN = (Node) this.left;

            if (leftN.left.isNode() && leftN.left.isBlack()
                && leftN.right.isNode() && leftN.right.isBlack()) {
                Node<E> leftRightN = (Node) leftN.right;

                return new Node<>(leftRightN.value,
                                  new Node<>(leftN.value,
                                             leftN.left.redden(),
                                             leftRightN.left,
                                             RBTColor.BLACK).balance(),
                                  new Node<>(this.value, leftRightN.right, this.right, RBTColor.BLACK),
                                  RBTColor.BLACK);
            }
        }

        // finally, the symmetric case...
        if (this.color.equals(RBTColor.DBL_BLACK) && this.right.isNode()
            && this.right.getColor().equals(RBTColor.NEG_RED)) {
            Node<E> rightN = (Node) this.right;

            if (rightN.left.isNode() && rightN.left.isBlack()
                && rightN.right.isNode() && rightN.right.isBlack()) {
                Node<E> rightLeftN = (Node) rightN.left;

                return new Node<>(rightLeftN.value,
                                  new Node<>(this.value, this.left, rightLeftN.left, RBTColor.BLACK),
                                  new Node<>(rightN.value,
                                             rightLeftN.right,
                                             rightN.right.redden(),
                                             RBTColor.BLACK).balance(),
                                  RBTColor.BLACK);
            }
        }

        // otherwise, do nothing
        return this;
    }

    public RBT<E> insert(E value) {
        return this.insertNoPreserve(value).blacken();
    }

    public RBT<E> insertNoPreserve(E value) {
        if (this.value.equals(value)) {
            // do not duplicate
            return this;
        } else if (this.value.compareTo(value) > 0) {
            return (new Node<E>(this.value,
                                this.left.insertNoPreserve(value),
                                this.right, this.color)).balance();
        } else {
            return (new Node<E>(this.value, this.left,
                                this.right.insertNoPreserve(value),
                                this.color)).balance();
        }
    }

    public RBT<E> blacken() {
        return new Node<>(this.value, this.left, this.right, RBTColor.BLACK);
    }

    public RBT<E> redden() {
        return new Node<>(this.value, this.left, this.right, RBTColor.RED);
    }

    public Optional<E> maximum() {
        if (this.right.isLeaf()) {
            return Optional.of(this.value);
        } else {
            return this.right.maximum();
        }
    }

    public RBT<E> bubble() {
        // if we have a double-black child, we gotta get rid of it (even if it's just a leaf)
        if (this.left.getColor().equals(RBTColor.DBL_BLACK)
            || this.right.getColor().equals(RBTColor.DBL_BLACK)) {
            // add 1 to this color, subtract 1 to children, then balance
            return new Node<>(this.value, this.left.blackSub(),
                              this.right.blackSub(), this.color.add1()).balance();
        }

        return this;
    }

    public RBT<E> removeNoPreserve(E value) {
        if (this.value.compareTo(value) == 0) {
            if (this.left.isLeaf() && this.right.isLeaf()) {
                if (this.isRed()) {
                    return new Leaf<>(RBTColor.BLACK);
                } else {
                    return new Leaf<>(RBTColor.DBL_BLACK);
                }
            } else if (this.left.isLeaf()) {
                if (this.right.isRed() || this.isRed()) {
                    return this.right.blacken();
                } else {
                    Node<E> w = (Node) this.right;

                    return new Node<>(w.value, w.left, w.right, RBTColor.DBL_BLACK).bubble();
                }
            } else if (this.right.isLeaf()) {
                if (this.left.isRed() || this.isRed()) {
                    return this.left.blacken();
                } else {
                    Node<E> w = (Node) this.left;

                    return new Node<>(w.value, w.left, w.right, RBTColor.DBL_BLACK).bubble();
                }
            } else {
                // both left and right are nodes, so we can unwrap
                E maximum = this.left.maximum().get();
                return new Node<E>(maximum, this.left.removeNoPreserve(maximum), this.right, this.color).bubble();
            }
        } else if (this.value.compareTo(value) > 0) {
            return new Node<E>(this.value, this.left.removeNoPreserve(value), this.right, this.color).bubble();
        } else {
            return new Node<E>(this.value, this.left, this.right.removeNoPreserve(value), this.color).bubble();
        }
    }

    public RBT<E> remove(E value) {
        return this.removeNoPreserve(value).blacken();
    }

    public int maxDepth() {
        return 1 + Math.max(this.left.maxDepth(), this.right.maxDepth());
    }

    public String toString() {
        return this.color.toString() + ": " + "(" +
            this.left.toString() + " . " + this.value.toString() + " . " +  this.right.toString() + ")";
    }
}