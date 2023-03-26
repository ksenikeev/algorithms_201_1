import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Test {
    static int numOfFiles;

    private static void generateFiles() {
        numOfFiles = new Random().nextInt(50, 101);
        FileGenerator generator = new FileGenerator();
        for (int i = 1; i <= numOfFiles; i++) {
            generator.generateFile(i);
        }
    }

    private static void measureAllCharacteristics(Integer[][] graph) {
        BellmanFordAlgorithm algorithm = new BellmanFordAlgorithm();
        long begin = System.nanoTime();
        try {
            algorithm.getLengths(graph, new Random().nextInt(1, graph.length + 1));
        } catch (NegativeCycleException e) {}

        long end = System.nanoTime();
        long expectedComplexity = algorithm.getComplexity();
        long iterations = algorithm.getNumOfIterations();
        long time = end - begin;
        System.out.println("" + time +" " + iterations + " " + expectedComplexity);
    }
    private static void readFiles() {
        generateFiles();
        String rootPath = "files/";
        String path;
        for (int i = 1; i <= numOfFiles; i++) {
            path = rootPath + "test" + i + ".txt";
            try {
                List<String> lines = Files.readAllLines(Paths.get(path));
                int size = lines.size();
                Integer[][] graph = new Integer[size][size];
                for (int j = 0; j < size; j++) {
                    String[] split = lines.get(j).split(", ");
                    for (int k = 0; k < size; k++) {
                        if (!split[k].equals("null")) {
                            graph[j][k] = Integer.parseInt(split[k]);
                        }
                        else graph[j][k] = null;
                    }
                }
                measureAllCharacteristics(graph);
            } catch (IOException e) {}
        }
    }

    public static void main(String[] args) {
        readFiles();
    }
}
