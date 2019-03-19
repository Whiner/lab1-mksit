package org.donntu.knt.mksit.lab1;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Optional;

public class FileService {
    public static String readFile(String path) throws Exception {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), "Cp1252"))
        ) {
            Optional<String> reduce = bufferedReader.lines().reduce((s, s2) -> s.concat("\n" + s2));
            if (reduce.isPresent()) {
                return reduce.get();
            } else {
                throw new Exception("Ошибка чтения файла");
            }
        }
    }

    public static void writeFile(String text, String path) throws Exception {
        ImageOutputStream stream = new FileImageOutputStream(new File(path));
        stream.write(text.getBytes(Charset.forName("Cp1252")));
    }
}
