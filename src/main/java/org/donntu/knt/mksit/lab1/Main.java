package org.donntu.knt.mksit.lab1;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Node tree;
        String textFilename = "files/file.txt";
        String compressedTextFilename = "files/file.txt.huff";
        String decodedTextFilename = "files/file_decoded.txt";

        System.out.println("Кодирование текстового файла");
        System.out.println("----------------------------");
        tree = HuffmanCompressor.compress(textFilename, compressedTextFilename);
        System.out.println("-------------------------------");
        System.out.println("Раскодирование текстового файла");
        System.out.println("-------------------------------");
        HuffmanCompressor.decompress(compressedTextFilename, decodedTextFilename, tree);

        System.out.println("% компрессии = " + CompressQualifier.compressPercent(
                new File(textFilename),
                new File(compressedTextFilename))
        );
        System.out.println("Файлы идентичны = " + CompressQualifier.isUncompressedEqualsSource(
                new File(textFilename),
                new File(decodedTextFilename))
        );

        String jpgFilename = "files/v.bmp";
        String compressedJpgFilename = "files/v.bmp.huff";
        String decodedJpgFilename = "files/v_decoded.bmp";

        System.out.println("Кодирование BMP файла");
        System.out.println("---------------------");
        tree = HuffmanCompressor.compress(jpgFilename, compressedJpgFilename);
        System.out.println("------------------------");
        System.out.println("Раскодирование BMP файла");
        System.out.println("------------------------");
        HuffmanCompressor.decompress(compressedJpgFilename, decodedJpgFilename, tree);

        System.out.println("% компрессии = " + CompressQualifier.compressPercent(
                new File(jpgFilename),
                new File(compressedJpgFilename))
        );
        System.out.println("Файлы идентичны = " + CompressQualifier.isUncompressedEqualsSource(
                new File(jpgFilename),
                new File(decodedJpgFilename))
        );
    }
}
