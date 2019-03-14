package org.donntu.knt.mksit.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        Huffman huffman = new Huffman();
        int decision = 1;
        huffman.handlingDecision(scanner, decision);
    }
}
