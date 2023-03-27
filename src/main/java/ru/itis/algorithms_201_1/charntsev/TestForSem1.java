package charntsev;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestForSem1 {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 50; i++) {
            Random rng = new Random();
            List<String> list = Stream.generate(rng::nextInt).limit(100 * (i + 1)).map(String::valueOf).collect(Collectors.toList());
            Path p = Paths.get("input/" + i);
            Files.write(p, list);
        }
        List<String> lines = new ArrayList<>();
        List<GrahamAlgorithm.Point> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Path p = Paths.get("input/" + i);
            int[] l = Files.lines(p).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < l.length / 2; j++) {
                list.add(j, new GrahamAlgorithm.Point(l[j * 2], l[j * 2 + 1]));
            }
            long start = System.nanoTime();
            GrahamAlgorithm gr = new GrahamAlgorithm(list);
            gr.buildHull();
            long finish = System.nanoTime();

            long time = (finish - start);
            String str = "" + l.length + "," + time + "," + gr.getCount();
            lines.add(str);
        }
        Path p = Paths.get("output");
        Files.write(p,lines);
    }
}
