package org.donntu.knt.mksit.lab1;

public class Main {
    public static void main(String[] args) throws Exception {

        String text = FileService.readFile("v.bmp");

        CompressedText compressedText = HuffmanCompressor.compress(text);

        compressedText.printCodes();

        FileService.writeFile(compressedText.getCompressedText(), "compressed.bin");

        String decompress = HuffmanCompressor.decompress(compressedText);


        FileService.writeFile(decompress, "decompressed.bmp");

    }
}
