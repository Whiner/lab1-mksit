package org.donntu.knt.mksit.lab1;

public class Main {
    public static void main(String[] args) throws Exception {
        String text = FileReader.readFile("input.txt");
        System.out.println("--------------------");
        System.out.println("Text: ");
        System.out.println(text);

        System.out.println("--------------------");

        Huffman huffman = new Huffman();
        HuffmanText encode = huffman.encode(text);

        System.out.println("--------------------");
        System.out.println("Encoded: ");

        System.out.println(encode.getEncoded());

        System.out.println("--------------------");
        System.out.println("Decoded: ");
        System.out.println(huffman.decode(encode.getEncoded(), encode.getNodes()));
    }
}
