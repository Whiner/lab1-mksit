package org.donntu.knt.mksit.lab1;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class Node {
    private Node left;
    private Node right;
    private double value;
    private String character;

    public Node(double value, String character) {
        this.value = value;
        this.character = character;
        left = null;
        right = null;
    }

    public Node(Node left, Node right) {
        this.value = left.value + right.value;
        character = left.character + right.character;
        if (left.value < right.value) {
            this.right = right;
            this.left = left;
        } else {
            this.right = left;
            this.left = right;
        }
    }
}
