package org.donntu.knt.mksit.lab1;

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

    private String encode(HuffmanText huffmanText) {
        calculateCharIntervals(huffmanText.getNodes());
        buildTree(huffmanText.getNodes());
        generateCodes(huffmanText.getNodes().peek(), "");

        printCodes();
        String decoded = huffmanText.getDecoded();
        String encoded = "";
        TreeMap<Character, String> codes = huffmanText.getCodes();
        for (int i = 0; i < decoded.length(); i++) {
            encoded = encoded.concat(codes.get(decoded.charAt(i)));
        }
        return encoded;
    }

    private boolean clear() {



        return false;

    }


    private void buildTree(PriorityQueue<Node> vector) {
        while (vector.size() > 1) {
            vector.add(new Node(vector.poll(), vector.poll()));
        }
    }

    private void printCodes() {
        System.out.println("--- Printing Codes ---");
        codes.forEach((k, v) -> System.out.println("'" + k + "' : " + v));
    }

    private void calculateCharIntervals(PriorityQueue<Node> vector) {
        System.out.println("-- intervals --");
        int[] ASCII = new int[128];
        for (int i = 0; i < text.length(); i++) {
            ASCII[text.charAt(i)]++;
        }

        for (int i = 0; i < ASCII.length; i++) {
            if (ASCII[i] > 0) {
                vector.add(new Node(ASCII[i] / (text.length() * 1.0), ((char) i) + ""));
                System.out.println("'" + ((char) i) + "' : " + ASCII[i] / (text.length() * 1.0));
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
