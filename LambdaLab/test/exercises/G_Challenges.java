package exercises;

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
    @Test @Ignore
    public void ex26_denormalizeMap() {
        Map<Integer, List<String>> input = new HashMap<>();
        input.put(4, Arrays.asList("ibex", "hedgehog", "wombat"));
        input.put(6, Arrays.asList("ant", "beetle", "cricket"));
        input.put(8, Arrays.asList("octopus", "spider", "squid"));
        input.put(10, Arrays.asList("crab", "lobster", "scorpion"));
        input.put(750, Arrays.asList("millipede"));

        List<String> result = null; // TODO

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
     * Invert a "multi-map". (P. Sandoz)
     *
     * Given a Map<X, Set<Y>>, convert it to Map<Y, Set<X>>.
     */
    @Test @Ignore
    public void ex27_invertMultiMap() {
        Map<String, List<Integer>> input = new HashMap<>();
        input.put("a", Arrays.asList(1, 2));
        input.put("b", Arrays.asList(2, 3));
        input.put("c", Arrays.asList(1, 3));
        input.put("d", Arrays.asList(1, 4));
        input.put("e", Arrays.asList(2, 4));
        input.put("f", Arrays.asList(3, 4));

        Map<Integer, List<String>> result = null; // TODO

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
    @Test @Ignore
    public void ex28_selectLongerThanPreceding() {
        List<String> input = Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel");

        List<String> result = null; // TODO

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
    @Test @Ignore
    public void ex29_concatenateAdjacent() {
        List<String> input = Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot");

        List<String> result = null; // TODO

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
    @Test @Ignore
    public void ex30_selectLongestWords() {
        List<String> input = Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel");

        List<String> result = null; // TODO

        assertEquals("[charlie, foxtrot]", result.toString());
    }

    /**
     * Select the longest words from the input stream. That is, select the words
     * whose lengths are equal to the maximum word length. For this exercise,
     * you must compute the result in a single pass over the input stream.
     *
     * XXX - compare to ex30
     */
    @Test @Ignore
    public void ex31_selectLongestWordsOnePass() {
        Stream<String> input = Stream.of(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel");

        List<String> result = null; // TODO

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
    @Test @Ignore
    public void ex32_partitionIntoSublists() {
        List<String> input = Arrays.asList(
            "alfa", "bravo", ":charlie", "delta", ":echo", ":foxtrot", "golf", "hotel");

        List<List<String>> result = null; // TODO

        assertEquals("[[alfa, bravo], [:charlie, delta], [:echo], [:foxtrot, golf, hotel]]",
                     result.toString());
    }

    /**
     * Given a stream of integers, compute separate sums of the even and odd values
     * in this stream. Since the input is a stream, this necessitates making a single
     * pass over the input.
     */
    @Test @Ignore
    public void ex33_separateOddEvenSums() {
        IntStream input = new Random(987523).ints(20, 0, 100);

        int sumEvens = 0; // TODO
        int sumOdds  = 0; // TODO

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
    @Test @Ignore
    public void ex34_splitCharacterRuns() {
        String input = "aaaaabbccccdeeeeeeaaafff";

        List<String> result = null; // TODO

        assertEquals("[aaaaa, bb, cccc, d, eeeeee, aaa, fff]", result.toString());
    }

    /**
     * Given a string, find the substring containing the longest run of consecutive,
     * equal characters.
     *
     * XXX - compare to ex34
     */
    @Test @Ignore
    public void ex35_longestCharacterRuns() {
        String input = "aaaaabbccccdeeeeeeaaafff";

        String result = null; // TODO

        assertEquals("eeeeee", result);
    }

    /**
     * Given a parallel stream of strings, collect them into a collection in reverse order.
     * Since the stream is parallel, you MUST write a proper combiner function in order to get
     * the correct result.
     */
    @Test @Ignore
    public void ex36_reversingCollector() {
        Stream<String> input =
            IntStream.range(0, 100).mapToObj(String::valueOf).parallel();

        Collection<String> result =
            input.collect(Collector.of(null, null, null));
            // TODO fill in collector functions above


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
     * elements are that value), and return it in an OptionalInt. If there
     * is no majority value, return an empty OptionalInt.
     */

    OptionalInt majority(int[] array) {
        return null; // TODO
    }

    @Test @Ignore
    public void ex37_majority() {
        int[] array1 = { 3, 3, 4, 2, 4, 4, 2, 4, 4 };
        int[] array2 = { 3, 3, 4, 2, 4, 4, 2, 4 };

        OptionalInt result1 = majority(array1);
        OptionalInt result2 = majority(array2);

        assertEquals(OptionalInt.of(4), result1);
        assertFalse(result2.isPresent());
    }
}
