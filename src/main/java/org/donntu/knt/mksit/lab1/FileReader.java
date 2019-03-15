package org.donntu.knt.mksit.lab1;

import java.io.BufferedReader;
import java.util.Optional;

public class FileReader {
    public static String readFile(String path) throws Exception {
        try(BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(path))) {
            Optional<String> reduce = bufferedReader.lines().reduce((s, s2) -> s.concat("\n" + s2));
            if(reduce.isPresent()) {
                return reduce.get();
            } else {
                throw new Exception("Ошибка чтения файла");
            }
        }
    }
}
