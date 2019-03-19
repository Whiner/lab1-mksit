package org.donntu.knt.mksit.lab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Optional;

public class FileService {
    public static String readFile(String path) throws Exception {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            Optional<String> reduce = bufferedReader.lines().reduce((s, s2) -> s.concat("\n" + s2));
            if(reduce.isPresent()) {
                return reduce.get();
            } else {
                throw new Exception("Ошибка чтения файла");
            }
        }
    }

    public static void writeFile(String text, String path) throws Exception {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
           bufferedWriter.write(text);
        }
    }
}
