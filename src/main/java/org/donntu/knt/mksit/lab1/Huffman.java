package org.donntu.knt.mksit.lab1;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Huffman {

    public String decode(String text, PriorityQueue<Node> nodes) {
        String decoded = "";
        Node node = nodes.peek();
        int i = 0;
        while (i < text.length()) {
            Node tmpNode = node;
            if (tmpNode == null) {
                break;
            }
            while (tmpNode.getLeft() != null && tmpNode.getRight() != null && i < text.length()) {
                if (text.charAt(i) == '1') {
                    tmpNode = tmpNode.getRight();
                } else {
                    tmpNode = tmpNode.getLeft();
                }
                i++;
            }
            if (tmpNode.getCharacter().length() == 1) {
                decoded = decoded.concat(tmpNode.getCharacter());
            } else {
                System.out.println("Input not Valid");
            }
        }
        return decoded;
    }

    public HuffmanText encode(String text) {
        HuffmanText huffmanText = new HuffmanText();

        calculateCharIntervals(text, huffmanText);
        buildTree(huffmanText);
        generateCodes(huffmanText.getNodes().peek(), huffmanText.getCodes(), "");

        String encoded = "";
        TreeMap<Character, String> codes = huffmanText.getCodes();
        for (int i = 0; i < text.length(); i++) {
            encoded = encoded.concat(codes.get(text.charAt(i)));
        }

        huffmanText.setEncoded(encoded);
        return huffmanText;
    }


    private void buildTree(HuffmanText huffmanText) {
        PriorityQueue<Node> nodes = huffmanText.getNodes();
        while (nodes.size() > 1) {
            nodes.add(new Node(nodes.poll(), Objects.requireNonNull(nodes.poll())));
        }
    }

    public void printCodes(TreeMap<Character, String> codes) {
        codes.forEach((k, v) -> System.out.println("'" + k + "' : " + v));
    }

    private void calculateCharIntervals(String text, HuffmanText huffmanText) {
        int[] ASCII = new int[128];
        for (int i = 0; i < text.length(); i++) {
            ASCII[text.charAt(i)]++;
        }
        PriorityQueue<Node> nodes = huffmanText.getNodes();
        for (int i = 0; i < ASCII.length; i++) {
            if (ASCII[i] > 0) {
                double value = ASCII[i] / (text.length() * 1.0);
                nodes.add(new Node(value, ((char) i) + ""));
                System.out.println("'" + String.valueOf(((char) i)).replaceAll("\n", "\\n") + "' : " + value);
            }
        }
    }

    private void generateCodes(Node node, TreeMap<Character, String> codes, String s) {
        if (node != null) {
            if (node.getRight() != null) {
                generateCodes(node.getRight(), codes, s + "1");
            }

            if (node.getLeft() != null) {
                generateCodes(node.getLeft(), codes,s + "0");
            }

            if (node.getLeft() == null && node.getRight() == null) {
                codes.put(node.getCharacter().charAt(0), s);
            }
        }
    }
}
