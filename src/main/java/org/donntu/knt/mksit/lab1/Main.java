package org.donntu.knt.mksit.lab1;

public class Main {
    public static void main(String[] args) {

        String text = "ЛОЛКЕК";

        CompressedText compressedText = HuffmanMy.compress(text);

        System.out.println(compressedText.getCompressedText());

        System.out.println(HuffmanMy.decompress(compressedText));

    }
}
