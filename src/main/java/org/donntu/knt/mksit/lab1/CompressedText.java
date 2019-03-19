package org.donntu.knt.mksit.lab1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompressedText {
    private Node tree;
    private Map<Character, String> codes = new HashMap<>();
    private String compressedText;

    public void printCodes() {
        for (Map.Entry<Character, String> characterStringEntry : codes.entrySet()) {
            System.out.println(characterStringEntry.getKey() + " : " + characterStringEntry.getValue());
        }
    }

}
