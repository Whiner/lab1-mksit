package org.donntu.knt.mksit.lab1;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class HuffmanCompressor {

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

            Map<Byte, Integer> byteIntegerMap = new HashMap<>();
            System.out.println("Считает символы");
            while (file.getFilePointer() < file.length()) {
                final byte read = file.readByte();
                if (nodes
                        .stream()
                        .noneMatch(characterCount ->
                                read == characterCount.get_byte())
                ) {
                    byteIntegerMap.merge(read, 1, Integer::sum);
                }
            }

            byteIntegerMap.forEach((key, value) -> nodes.add(new Node(key, value)));

            System.out.println("Формирует дерево");
            while (nodes.size() > 1) {
                Node left = nodes.poll();
                Node right = nodes.poll();
                nodes.add(new Node(left, Objects.requireNonNull(right)));
            }

            Map<Byte, String> codes = new HashMap<>();

            Node node = nodes.poll();
            System.out.println("Генерирует коды");
            generateCodes(node, codes, "");
            file.seek(0);
            System.out.println("Печатает в файл");
            while (file.getFilePointer() < file.length()) {
                byte b = file.readByte();
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
                RandomAccessFile reader = new RandomAccessFile(sourceFilename, "r");
                FileOutputStream writer = new FileOutputStream(destFilename)
        ) {
            byte b;
            while (reader.getFilePointer() < reader.length()) {
                b = reader.readByte();
                byte byteFromCode = getByteFromCode(b, tree, reader);
                writer.write(byteFromCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static byte getByteFromCode(Byte character, Node tree, RandomAccessFile reader) throws IOException {

        if (character.equals((byte) 48) && tree.getLeft() != null) {
            try {
                return getByteFromCode(reader.readByte(), tree.getLeft(), reader);
            } catch (IOException e) {
                return tree.getLeft().get_byte();
            }
        } else if (character.equals((byte) 49) && tree.getRight() != null) {
            try {
                return getByteFromCode(reader.readByte(), tree.getRight(), reader);
            } catch (IOException e) {
                return tree.getRight().get_byte();
            }
        } else {
            reader.seek(reader.getFilePointer() - 1);
            return tree.get_byte();
        }
    }
}
