package org.donntu.knt.mksit.lab1;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class Node implements Comparable<Node> {
    private String name;
    private char character;
    private int count;
    private Node left;
    private Node right;

    public Node(char character, int count) {
        this.count = count;
        this.character = character;
        this.name = String.valueOf(character);
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
        name = this.left.name + this.right.name;
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
