package org.donntu.knt.mksit.lab1;

import lombok.Data;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

@Data
public class HuffmanText {
    private PriorityQueue<Node> nodes = new PriorityQueue<>(Comparator.comparingDouble(Node::getValue));
    private TreeMap<Character, String> codes = new TreeMap<>();
    private String encoded;
    private String decoded;

    public static HuffmanText encoded(String text) {
        HuffmanText huffmanText = new HuffmanText();
        huffmanText.setEncoded(text);
        return huffmanText;
    }

    public static HuffmanText decoded(String text) {
        HuffmanText huffmanText = new HuffmanText();
        huffmanText.setDecoded(text);
        return huffmanText;
    }

}
