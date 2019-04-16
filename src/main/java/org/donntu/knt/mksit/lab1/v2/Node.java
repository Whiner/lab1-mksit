package org.donntu.knt.mksit.lab1.v2;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Node implements Comparable<Node> {
    private String name;
    private byte _byte;
    private int count;
    private Node left;
    private Node right;

    public Node(byte _byte, int count) {
        this.count = count;
        this._byte = _byte;
        this.name = String.valueOf(_byte);
        left = null;
        right = null;
    }

    public Node(Node left, Node right) {
        this.count = left.count + right.count;
        if (left.count < right.count) {
            this.right = right;
            this.left = left;
        } else {
            this.right = left;
            this.left = right;
        }
        name = this.left.name + "'" + this.right.name;
    }

    @Override
    public int compareTo(Node o) {
        if(this.getCount() < o.getCount()) {
            return 1;
        } else if (o.getCount() == this.getCount()) {
            return 0;
        }
        return -1;
    }
}
