package solutions;

/*
 * LAMBDA PROGRAMMING LABORATORY
 *
 * For each exercise, develop a solution using Java SE 8 Lambda/Streams
 * and remove the @Ignore tag. Then run the tests.
 *
 * In NetBeans, Ctrl-F6 will run the project's tests, which default to
 * the unsolved exercises (as opposed to the solutions). Alt-F6 [PC] or
 * or Cmd-F6 [Mac] will run just the tests in the currently selected file.
 *
 * Several of the exercises read data from a text file. The field named
 * "reader" is a BufferedReader which will be opened for you on the text file.
 * In any exercise that refers to reading from the text file, you can simply
 * use the "reader" variable without worry about opening or closing it.
 * This is setup by JUnit using the @Before and @After methods at the bottom of
 * this file. The text file is "SonnetI.txt" (Shakespeare's first sonnet) which
 * is located at the root of this NetBeans project.
 */

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class G_Challenges {

    /**
     * Denormalize this map. The input is a map whose keys are the number of legs of an animal
     * and whose values are lists of names of animals. Run through the map and generate a
     * "denormalized" list of strings describing the animal, with the animal's name separated
     * by a colon from the number of legs it has. The ordering in the output list is not
     * considered significant.
     *
     * Input is Map<Integer, List<String>>:
     *   { 4=["ibex", "hedgehog", "wombat"],
     *     6=["ant", "beetle", "cricket"],
     *     ...
     *   }
     *
     * Output should be a List<String>:
     *   [ "ibex:4",
     *     "hedgehog:4",
     *     "wombat:4",
     *     "ant:6",
     *     "beetle:6",
     *     "cricket:6",
     *     ...
     *   ]
     */
    @Test
    public void ex26_denormalizeMap() {
        Map<Integer, List<String>> input = new HashMap<>();
        input.put(4, Arrays.asList("ibex", "hedgehog", "wombat"));
        input.put(6, Arrays.asList("ant", "beetle", "cricket"));
        input.put(8, Arrays.asList("octopus", "spider", "squid"));
        input.put(10, Arrays.asList("crab", "lobster", "scorpion"));
        input.put(750, Arrays.asList("millipede"));

        //TODO//List<String> result = null;
        //BEGINREMOVE

        // Simple solution: use Map.forEach to iterate over each entry,
        // and use a nested List.forEach to iterate over each list entry,
        // and accumulate values into the result list.

        List<String> result = new ArrayList<>();
        input.forEach((legs, names) ->
                          names.forEach(name -> result.add(name + ":" + legs)));

        // Alternative solution: stream over map entries, and use flatMap to generate
        // Animal instances for each animal name with the given number of legs. This
        // is more complicated, but it's a more general technique, and it can be run
        // in parallel.

//        List<String> result =
//            input.entrySet().stream()
//                 .flatMap(entry -> entry.getValue().stream()
//                                        .map(name -> name + ":" + entry.getKey()))
//                 .collect(toList());

        //ENDREMOVE

        assertEquals(13, result.size());
        assertTrue(result.contains("ibex:4"));
        assertTrue(result.contains("hedgehog:4"));
        assertTrue(result.contains("wombat:4"));
        assertTrue(result.contains("ant:6"));
        assertTrue(result.contains("beetle:6"));
        assertTrue(result.contains("cricket:6"));
        assertTrue(result.contains("octopus:8"));
        assertTrue(result.contains("spider:8"));
        assertTrue(result.contains("squid:8"));
        assertTrue(result.contains("crab:10"));
        assertTrue(result.contains("lobster:10"));
        assertTrue(result.contains("scorpion:10"));
        assertTrue(result.contains("millipede:750"));
    }
    // Hint 1:
    // <editor-fold defaultstate="collapsed">
    // There are several ways to approach this. You could use a stream of map keys,
    // a stream of map entries, or nested forEach() methods.
    // </editor-fold>
    // Hint 2:
    // <editor-fold defaultstate="collapsed">
    // If you use streams, consider using Stream.flatMap().
    // </editor-fold>


// ========================================================
// NEW FOR 2016
// ========================================================

    /**
     * Invert a "multi-map". (From an idea by Paul Sandoz)
     *
     * Given a Map<X, Set<Y>>, convert it to Map<Y, Set<X>>.
     * Each set member of the input map's values becomes a key in
     * the result map. Each key in the input map becomes a set member
     * of the values of the result map. In the input map, an item
     * may appear in the value set of multiple keys. In the result
     * map, that item will be a key, and its value set will be
     * its corresopnding keys from the input map.
     *
     * In this case the input is Map<String, Set<Integer>>
     * and the result is Map<Integer, Set<String>>.
     *
     * For example, if the input map is
     *     {p=[10, 20], q=[20, 30]}
     * then the result map should be
     *     {10=[p], 20=[p, q], 30=[q]}
     * irrespective of ordering. Note that the Integer 20 appears
     * in the value sets for both p and q in the input map. Therefore,
     * in the result map, there should be a mapping with 20 as the key
     * and p and q as its value set.
     */
    @Test
    public void ex27_invertMultiMap() {
        Map<String, Set<Integer>> input = new HashMap<>();
        input.put("a", new HashSet<>(Arrays.asList(1, 2)));
        input.put("b", new HashSet<>(Arrays.asList(2, 3)));
        input.put("c", new HashSet<>(Arrays.asList(1, 3)));
        input.put("d", new HashSet<>(Arrays.asList(1, 4)));
        input.put("e", new HashSet<>(Arrays.asList(2, 4)));
        input.put("f", new HashSet<>(Arrays.asList(3, 4)));

        //TODO//Map<Integer, Set<String>> result = null;
        //BEGINREMOVE
        Map<Integer, Set<String>> result =
            input.entrySet().stream()
                 .flatMap(e -> e.getValue().stream()
                                .map(v -> new AbstractMap.SimpleEntry<>(e.getKey(), v)))
                 .collect(Collectors.groupingBy(Map.Entry::getValue,
                                                Collectors.mapping(Map.Entry::getKey,
                                                                   Collectors.toSet())));

        // Alternatively:
        // Map<Integer, Set<String>> result =
        //     input.entrySet().stream()
        //          .flatMap(e -> e.getValue().stream()
        //                         .map(v -> new AbstractMap.SimpleEntry<>(v, e.getKey())))
        //          .collect(Collectors.toMap(
        //                       e -> e.getKey(),
        //                       e -> new HashSet<>(Arrays.asList(e.getValue())),
        //                       (set1, set2) -> { set1.addAll(set2); return set1; }
        //                 ));

        //ENDREMOVE

        assertEquals(new HashSet<>(Arrays.asList("a", "c", "d")), result.get(1));
        assertEquals(new HashSet<>(Arrays.asList("a", "b", "e")), result.get(2));
        assertEquals(new HashSet<>(Arrays.asList("b", "c", "f")), result.get(3));
        assertEquals(new HashSet<>(Arrays.asList("d", "e", "f")), result.get(4));
        assertEquals(4, result.size());
    }


    /**
     * Select from the input list each word that longer than the immediately
     * preceding word. Include the first word, since it is longer than the
     * (nonexistent) word that precedes it.
     *
     * XXX - compare to ex11
     */
    @Test
    public void ex28_selectLongerThanPreceding() {
        List<String> input = Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel");

        //TODO//List<String> result = null;
        //BEGINREMOVE
        List<String> result =
            IntStream.range(0, input.size())
                .filter(pos -> pos == 0 || input.get(pos-1).length() < input.get(pos).length())
                .mapToObj(pos -> input.get(pos))
                .collect(Collectors.toList());
        //ENDREMOVE

        assertEquals("[alfa, bravo, charlie, foxtrot, hotel]", result.toString());
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Instead of a stream of words (Strings), run an IntStream of positions.
    // </editor-fold>


    /**
     * Generate a list of words that is the concatenation of each adjacent
     * pair of words in the input list. For example, if the input is
     *     [x, y, z]
     * the output should be
     *     [xy, yz]
     *
     * XXX - compare to ex11
     */
    @Test
    public void ex29_concatenateAdjacent() {
        List<String> input = Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot");

        //TODO//List<String> result = null;
        //BEGINREMOVE
        List<String> result =
            IntStream.range(1, input.size())
                .mapToObj(pos -> input.get(pos-1) + input.get(pos))
                .collect(Collectors.toList());
        //ENDREMOVE

        assertEquals("[alfabravo, bravocharlie, charliedelta, deltaecho, echofoxtrot]",
                     result.toString());
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Instead of a stream of words (Strings), run an IntStream of positions.
    // </editor-fold>

    /**
     * Select the longest words from the input list. That is, select the words
     * whose lengths are equal to the maximum word length. For this exercise,
     * it's easiest to perform two passes over the input list.
     *
     * XXX - compare to ex09 and ex10
     */
    @Test
    public void ex30_selectLongestWords() {
        List<String> input = Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel");

        //TODO//List<String> result = null;
        //BEGINREMOVE
        int max = input.stream()
                       .mapToInt(String::length)
                       .max()
                       .getAsInt();

        List<String> result = input.stream()
                                   .filter(s -> s.length() == max)
                                   .collect(Collectors.toList());
        //ENDREMOVE

        assertEquals("[charlie, foxtrot]", result.toString());
    }

    /**
     * Select the longest words from the input stream. That is, select the words
     * whose lengths are equal to the maximum word length. For this exercise,
     * you must compute the result in a single pass over the input stream.
     *
     * XXX - compare to ex30
     */
    @Test
    public void ex31_selectLongestWordsOnePass() {
        Stream<String> input = Stream.of(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel");

        //TODO//List<String> result = null;
        //BEGINREMOVE

        List<String> result = new ArrayList<>();
        input.forEachOrdered(s -> {
            if (result.isEmpty()) {
                result.add(s);
            } else {
                int reslen = result.get(0).length();
                int curlen = s.length();
                if (curlen > reslen) {
                    result.clear();
                    result.add(s);
                } else if (curlen == reslen) {
                    result.add(s);
                }
            }
        });
        //ENDREMOVE

        assertEquals("[charlie, foxtrot]", result.toString());
    }

    /**
     * Create a list of non-overlapping sublists of the input list, where each
     * sublist (except for the first one) starts with a word whose first character is a ":".
     * For example, given the input list
     *     [w, x, :y, z]
     * the output should be
     *     [[w, x], [:y, z]]
     */
    @Test
    public void ex32_partitionIntoSublists() {
        List<String> input = Arrays.asList(
            "alfa", "bravo", ":charlie", "delta", ":echo", ":foxtrot", "golf", "hotel");

        //TODO//List<List<String>> result = null;
        //BEGINREMOVE

        List<Integer> bounds =
            IntStream.rangeClosed(0, input.size())
                     .filter(i -> i == 0 || i == input.size() || input.get(i).startsWith(":"))
                     .boxed()
                     .collect(Collectors.toList());

        List<List<String>> result =
            IntStream.range(1, bounds.size())
                     .mapToObj(i -> input.subList(bounds.get(i-1), bounds.get(i)))
                     .collect(Collectors.toList());

        //ENDREMOVE

        assertEquals("[[alfa, bravo], [:charlie, delta], [:echo], [:foxtrot, golf, hotel]]",
                     result.toString());
    }

    /**
     * Given a stream of integers, compute separate sums of the even and odd values
     * in this stream. Since the input is a stream, this necessitates making a single
     * pass over the input.
     */
    @Test
    public void ex33_separateOddEvenSums() {
        IntStream input = new Random(987523).ints(20, 0, 100);

        //TODO//int sumEvens = 0;
        //TODO//int sumOdds  = 0;
        //BEGINREMOVE

        Map<Boolean, Integer> sums =
            input.boxed()
                 .collect(Collectors.partitioningBy(i -> (i & 1) == 1,
                                                    Collectors.summingInt(i -> i)));
        int sumEvens = sums.get(false);
        int sumOdds  = sums.get(true);
        //ENDREMOVE

        assertEquals(516, sumEvens);
        assertEquals(614, sumOdds);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use Collectors.partitioningBy().
    // </editor-fold>

    /**
     * Given a string, split it into a list of strings consisting of
     * consecutive characters from the original string. Note: this is
     * similar to Python's itertools.groupby function, but it differs
     * from Java's Collectors.groupingBy() collector.
     *
     * XXX - compare to ex32
     */
    @Test
    public void ex34_splitCharacterRuns() {
        String input = "aaaaabbccccdeeeeeeaaafff";

        //TODO//List<String> result = null;
        //BEGINREMOVE

        List<Integer> bounds =
            IntStream.rangeClosed(0, input.length())
                     .filter(i -> i == 0 || i == input.length() ||
                                  input.charAt(i-1) != input.charAt(i))
                     .boxed()
                     .collect(Collectors.toList());

        List<String> result =
            IntStream.range(1, bounds.size())
                     .mapToObj(i -> input.substring(bounds.get(i-1), bounds.get(i)))
                     .collect(Collectors.toList());

        //ENDREMOVE

        assertEquals("[aaaaa, bb, cccc, d, eeeeee, aaa, fff]", result.toString());
    }

    /**
     * Given a string, find the substring containing the longest run of consecutive,
     * equal characters.
     *
     * XXX - compare to ex34
     */
    @Test
    public void ex35_longestCharacterRuns() {
        String input = "aaaaabbccccdeeeeeeaaafff";

        //TODO//String result = null;
        //BEGINREMOVE

        List<Integer> bounds =
            IntStream.rangeClosed(0, input.length())
                     .filter(i -> i == 0 || i == input.length() ||
                                  input.charAt(i-1) != input.charAt(i))
                     .boxed()
                     .collect(Collectors.toList());

        String result =
            IntStream.range(1, bounds.size())
                     .boxed()
                     .max((i, j) -> Integer.compare(bounds.get(i) - bounds.get(i-1),
                                                    bounds.get(j) - bounds.get(j-1)))
                     .map(i -> input.substring(bounds.get(i-1), bounds.get(i)))
                     .get();

        //ENDREMOVE

        assertEquals("eeeeee", result);
    }

    /**
     * Given a parallel stream of strings, collect them into a collection in reverse order.
     * Since the stream is parallel, you MUST write a proper combiner function in order to get
     * the correct result.
     */
    @Test
    public void ex36_reversingCollector() {
        Stream<String> input =
            IntStream.range(0, 100).mapToObj(String::valueOf).parallel();

        //UNCOMMENT//Collection<String> result =
        //UNCOMMENT//    input.collect(Collector.of(null, null, null));
        //UNCOMMENT//    // TODO fill in collector functions above

        //BEGINREMOVE

        Collection<String> result =
            input.collect(Collector.of(ArrayDeque::new,
                                       ArrayDeque::addFirst,
                                       (d1, d2) -> { d2.addAll(d1); return d2; }));

        //ENDREMOVE

        assertEquals(
            IntStream.range(0, 100)
                     .map(i -> 99 - i)
                     .mapToObj(String::valueOf)
                     .collect(Collectors.toList()),
            new ArrayList<>(result));
    }

    /**
     * Given an array of int, find the int value that occurs a majority
     * of times in the array (that is, strictly more than half of the
     * elements are that value), and return that int value in an OptionalInt.
     * Note, return the majority int value, not the number of times it occurs.
     * If there is no majority value, return an empty OptionalInt.
     */

    OptionalInt majority(int[] array) {
        //TODO//return null;
        //BEGINREMOVE
        Map<Integer, Long> map =
            Arrays.stream(array)
                  .boxed()
                  .collect(Collectors.groupingBy(x -> x,
                                                 Collectors.counting()));

        return map.entrySet().stream()
                  .filter(e -> e.getValue() > array.length / 2)
                  .mapToInt(Map.Entry::getKey)
                  .findAny();
        //ENDREMOVE
    }

    @Test
    public void ex37_majority() {
        int[] array1 = { 13, 13, 24, 35, 24, 24, 35, 24, 24 };
        int[] array2 = { 13, 13, 24, 35, 24, 24, 35, 24 };

        OptionalInt result1 = majority(array1);
        OptionalInt result2 = majority(array2);

        assertEquals(OptionalInt.of(24), result1);
        assertFalse(result2.isPresent());
    }

    /**
     * Write a method that takes an IntFunction and returns a Supplier.
     * An IntFunction takes an int as an argument and returns some object.
     * A Supplier takes no arguments and returns some object. The object
     * type in this case is a Shoe that has a single attribute, its size.
     * The goal is to write a lambda expression that uses the IntFunction
     * and size values provided as arguments, and that returns a Supplier
     * that embodies both of them. This is an example of a functional
     * programming concept called "partial application."
     */
    Supplier<Shoe> makeShoeSupplier(IntFunction<Shoe> ifunc, int size) {
        //TODO//return null;
        //BEGINREMOVE
        return () -> ifunc.apply(size);
        //ENDREMOVE
    }

    static class Shoe {
        final int size;
        public Shoe(int size) { this.size = size; }
        public int hashCode() { return size ^ 0xcafebabe; }
        public boolean equals(Object other) {
            return (other instanceof Shoe) && this.size == ((Shoe)other).size;
        }
    }

    @Test
    public void ex38_shoemaker() {
        Supplier<Shoe> sup1 = makeShoeSupplier(Shoe::new, 9);
        Supplier<Shoe> sup2 = makeShoeSupplier(Shoe::new, 13);

        Shoe shoe1 = sup1.get();
        Shoe shoe2 = sup1.get();
        Shoe shoe3 = sup2.get();
        Shoe shoe4 = sup2.get();

        assertTrue(shoe1 != shoe2);
        assertTrue(shoe3 != shoe4);
        assertEquals(new Shoe(9), shoe1);
        assertEquals(shoe1, shoe2);
        assertEquals(new Shoe(13), shoe3);
        assertEquals(shoe3, shoe4);
    }
}