package org.donntu.knt.mksit.lab1;

import java.util.*;

public class HuffmanMy {

    public static CompressedText compress(String text) {
        PriorityQueue<Node> nodes = new PriorityQueue<>(
                (o1, o2) -> {
                    if (o1.getCount() > o2.getCount()) {
                        return 1;
                    } else if (o1.getCount() == o2.getCount()) {
                        return 0;
                    }
                    return -1;
                }
        );

        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (nodes
                    .stream()
                    .noneMatch(characterCount ->
                            c == characterCount.getCharacter())
            ) {
                int count = 0;
                for (int j = i; j < chars.length; j++) {
                    if (c == chars[j]) {
                        count++;
                    }
                }
                nodes.add(new Node(c, count));
            }
        }

        while (nodes.size() > 1) {
            Node left = nodes.poll();
            Node right = nodes.poll();
            nodes.add(
                    new Node(
                            left,
                            Objects.requireNonNull(right)));
        }

        TreeMap<Character, String> codes = new TreeMap<>();

        Node node = nodes.poll();
        generateCodes(node, codes, "");

        String encoded = "";
        for (int i = 0; i < text.length(); i++) {
            encoded = encoded.concat(codes.get(text.charAt(i)));
        }

        return new CompressedText(node, codes, encoded);
    }

    public static String decompress(CompressedText compressedText) {
        Node tree = compressedText.getTree();
        String text = compressedText.getCompressedText();

        char[] chars = text.toCharArray();
        ArrayDeque<Character> compressed = new ArrayDeque<>();
        for (char c : chars) {
            compressed.addLast(c);
        }
        String decompressedText = "";
        while (!compressed.isEmpty()) {
            decompressedText = decompressedText.concat(
                    String.valueOf(getCharacterFromCode(compressed, tree))
            );
        }

        return decompressedText;
    }

    private static Character getCharacterFromCode(Queue<Character> compressed, Node tree) {
        Character c = compressed.peek();
        if(c == null) {
            return tree.getCharacter();
        }
        if (c.equals('0') && tree.getLeft() != null) {
            compressed.poll();
            return getCharacterFromCode(compressed, tree.getLeft());
        } else if (c.equals('1') && tree.getRight() != null) {
            compressed.poll();
            return getCharacterFromCode(compressed, tree.getRight());
        } else {
            return tree.getCharacter();
        }
    }

    private static void generateCodes(Node node, TreeMap<Character, String> codes, String s) {
        if (node != null) {
            if (node.getRight() != null) {
                generateCodes(node.getRight(), codes, s + "1");
            }

            if (node.getLeft() != null) {
                generateCodes(node.getLeft(), codes, s + "0");
            }

            if (node.getLeft() == null && node.getRight() == null) {
                codes.put(node.getCharacter(), s);
            }
        }
    }
}