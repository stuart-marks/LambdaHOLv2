package support;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Process all files test/solutions/*.java into
 * corresponding files in test/exercises/*.java.
 */
public class Grind {
    static class Sink implements Consumer<String> {
        boolean removing = false;
        final PrintStream out;
        Sink(PrintStream out) { this.out = out; }
        public void accept(String line) {
            if (line.contains("//BEGINREMOVE")) {
                removing = true;
            } else if (line.contains("//ENDREMOVE")) {
                removing = false;
            } else if (!removing) {
                out.println(line);
            }
        }
    }

    String subst(String line) {
        return line.replace("package solutions;", "package exercises;")
                   .replace("@Test", "@Test @Ignore")
                   .replace("//UNCOMMENT//", "")
                   .replaceFirst("^(.*)//TODO//(.*)$", "$1$2 // TODO");
    }
    
    void processFile(Path input) {
        Path output = Paths.get("test", "exercises")
                           .resolve(input.getName(input.getNameCount() - 1));
        System.out.println(input + " => " + output);
        
        try (Stream<String> lines = Files.lines(input);
             PrintStream out = new PrintStream(output.toFile())) {
            lines.map(this::subst)
                 .forEachOrdered(new Sink(out));
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }
    
    void run() throws IOException {
        Path dir = Paths.get("test", "solutions");
        try (Stream<Path> paths = Files.list(dir)) {
            paths.filter(p -> p.toString().endsWith(".java"))
                 .forEachOrdered(this::processFile);
        }
    }

    public static void main(String[] args) throws IOException {
        new Grind().run();
    }
}
