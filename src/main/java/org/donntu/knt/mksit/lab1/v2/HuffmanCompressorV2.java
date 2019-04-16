package org.donntu.knt.mksit.lab1.v2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class HuffmanCompressorV2 {

    public static Node compress(String inputFilename, String outputFilename) {

        try (
                RandomAccessFile file = new RandomAccessFile(inputFilename, "r");
                BufferedOutputStream bufferedWriter = new BufferedOutputStream(new FileOutputStream(outputFilename))
        ) {

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

            byte b;
            while ((b = (byte) file.read()) != -1) {
                final byte read = b;
                if (nodes
                        .stream()
                        .noneMatch(characterCount ->
                                read == characterCount.get_byte())
                ) {
                    int currentPosition = (int) file.getFilePointer();
                    int count = 1;

                    while ((b = (byte) file.read()) != -1) {
                        if (b == read) {
                            count++;
                        }
                    }
                    file.seek(currentPosition);

                    nodes.add(new Node(read, count));
                }
            }

            while (nodes.size() > 1) {
                Node left = nodes.poll();
                Node right = nodes.poll();
                nodes.add(new Node(left, Objects.requireNonNull(right)));
            }

            Map<Byte, String> codes = new HashMap<>();

            Node node = nodes.poll();
            generateCodes(node, codes, "");
            file.seek(0);
            while ((b = (byte) file.read()) != -1) {
                bufferedWriter.write(codes.get(b).getBytes());
            }
            return node;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static void generateCodes(Node node, Map<Byte, String> codes, String s) {
        if (node != null) {
            if (node.getRight() != null) {
                generateCodes(node.getRight(), codes, s + "1");
            }

            if (node.getLeft() != null) {
                generateCodes(node.getLeft(), codes, s + "0");
            }

            if (node.getLeft() == null && node.getRight() == null) {
                codes.put(node.get_byte(), s);
            }
        }
    }

    public static void decompress(String sourceFilename, String destFilename, Node tree) {

        try (
                BufferedReader reader = new BufferedReader(new FileReader(sourceFilename));
                BufferedWriter writer = new BufferedWriter(new FileWriter(destFilename))
        ) {
            byte b;
            while ((b = (byte) reader.read()) != -1) {
                writer.write(getByteFromCode(b, tree, reader));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static byte getByteFromCode(Byte character, Node tree, BufferedReader reader) throws IOException {
        if (character == (byte) -1) {
            return tree.get_byte();
        }
        if (character.equals((byte) 48) && tree.getLeft() != null) {
            return getByteFromCode((byte) reader.read(), tree.getLeft(), reader);
        } else if (character.equals((byte) 49) && tree.getRight() != null) {
            return getByteFromCode((byte) reader.read(), tree.getRight(), reader);
        } else {
            return tree.get_byte();
        }
    }
}
