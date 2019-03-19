package org.donntu.knt.mksit.lab1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompressedText {
    private Node tree;
    private TreeMap<Character, String> codes = new TreeMap<>();
    private String compressedText;
}
