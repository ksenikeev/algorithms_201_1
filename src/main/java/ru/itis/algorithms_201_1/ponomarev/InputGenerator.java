package ru.itis.algorithms_201_1.ponomarev;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputGenerator {
    public static void main(String[] args) throws IOException {
        Random rng = new Random();

        for (int i = 100; i <= 10000; i += 100) {
            String inputNum = String.format("%1$3s", i / 100).replace(' ', '0');
            Path path = Paths.get("src/main/java/ru/itis/algorithms_201_1/ponomarev/res/input_" + inputNum);
            path.toFile().createNewFile();
            List<String> lines = Stream.generate(rng::nextInt)
                    .limit(i)
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            Files.write(path, lines);
        }
    }
}
