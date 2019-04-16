package org.donntu.knt.mksit.lab1;

import org.donntu.knt.mksit.lab1.v2.HuffmanCompressorV2;
import org.donntu.knt.mksit.lab1.v2.Node;

public class Main {
    public static void main(String[] args) throws Exception {

        Node tree = HuffmanCompressorV2.compress("files/file.txt", "files/file.txt.huff");
        HuffmanCompressorV2.decompress("files/file.txt.huff", "files/file_decoded.txt", tree);


       /*String text = FileService.readFile("files/file.txt");

        CompressedText compressedText = HuffmanCompressor.compress(text);
        //System.out.println(compressedText.getCompressedText());
        compressedText.printCodes();*/

      /*  FileService.writeFile(compressedText.getCompressedText(), "compressed.bin");

        String decompress = HuffmanCompressor.decompress(compressedText);


        FileService.writeFile(decompress, "decompressed.bmp");*/

    }
}
