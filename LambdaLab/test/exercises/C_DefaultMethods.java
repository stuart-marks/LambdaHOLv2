package exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This set of exercises covers new default methods on
 * the Collections and related APIs.
 */
public class C_DefaultMethods {

    /**
     * Given a list of StringBuilders, modify each StringBuilder
     * in-place by appending the string "new" to each one.
     */
    @Test @Ignore
    public void c1_appendNew() {
        List<StringBuilder> sbList = Arrays.asList(
            new StringBuilder("alfa"),
            new StringBuilder("bravo"),
            new StringBuilder("charlie"));

        // TODO write code to modify sbList

        assertEquals(Arrays.asList("alfanew", "bravonew", "charlienew"),
                sbList.stream()
                      .map(StringBuilder::toString)
                      .collect(Collectors.toList()));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use Iterable.forEach().
    // </editor-fold>


    /**
     * Remove the words that have odd lengths from the list.
     */
    @Test @Ignore
    public void c2_removeOddLengthWords() {
        List<String> list = new ArrayList<>(Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot"));

        // TODO write code to modify list

        assertEquals(Arrays.asList("alfa", "echo"), list);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use Collection.removeIf().
    // </editor-fold>


    /**
     * Replace every word in the list with its upper case equivalent.
     */
    @Test @Ignore
    public void c3_upcaseAllWords() {
        List<String> list = Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot");

        // TODO code to modify list

        assertEquals(Arrays.asList("ALFA", "BRAVO", "CHARLIE", "DELTA", "ECHO", "FOXTROT"),
                     list);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use List.replaceAll().
    // </editor-fold>


    /**
     * Given a map whose keys are Integers and whose values are StringBuilders,
     * append to each StringBuilder the string representation of its corresponding
     * Integer key. This should mutate each StringBuilder value in-place.
     */
    @Test @Ignore
    public void c4_appendToMapValues() {
        Map<Integer, StringBuilder> map = new TreeMap<>();
        map.put(1, new StringBuilder("alfa"));
        map.put(2, new StringBuilder("bravo"));
        map.put(3, new StringBuilder("charlie"));

        // TODO write code to modify map

        assertEquals(3, map.size());
        assertTrue(map.values().stream().allMatch(x -> x instanceof StringBuilder));
        assertEquals("alfa1",    map.get(1).toString());
        assertEquals("bravo2",   map.get(2).toString());
        assertEquals("charlie3", map.get(3).toString());
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use Map.forEach().
    // </editor-fold>


    /**
     * Given a list of words, populate a map whose keys are the lengths of
     * each word, and whose values are list of words with that length.
     */
    @Test @Ignore
    public void c5_mapOfListOfStringsByLength() {
        List<String> list = Arrays.asList(
            "aardvark", "bison", "capybara",
            "alligator", "bushbaby", "chimpanzee",
            "avocet", "bustard", "capuchin");
        Map<Integer, List<String>> result = new TreeMap<>();

        // TODO write code to populate result

        assertEquals(Arrays.asList(5, 6, 7, 8, 9, 10), new ArrayList<>(result.keySet()));
        assertEquals(Arrays.asList("bison"), result.get(5));
        assertEquals(Arrays.asList("avocet"), result.get(6));
        assertEquals(Arrays.asList("bustard"), result.get(7));
        assertEquals(Arrays.asList("aardvark", "capybara", "bushbaby", "capuchin"), result.get(8));
        assertEquals(Arrays.asList("alligator"), result.get(9));
        assertEquals(Arrays.asList("chimpanzee"), result.get(10));
    }
    // <editor-fold defaultstate="collapsed">
    // Use Map.computeIfAbsent() within Iterable.forEach().
    // </editor-fold>

    /**
     * Given a list of words, populate a map whose keys are the initial characters of
     * each word, and whose values are the concatenation of the words with that
     * initial character. When concatenating the words, they should be
     * separated by a colon (':').
     */
    @Test @Ignore
    public void c6_mapOfStringByInitialCharacter() {
        List<String> list = Arrays.asList(
            "aardvark", "bison", "capybara",
            "alligator", "bushbaby", "chimpanzee",
            "avocet", "bustard", "capuchin");
        Map<Character, String> result = new TreeMap<>();

        // TODO write code to populate result

        assertEquals(Arrays.asList('a', 'b', 'c'), new ArrayList<>(result.keySet()));
        assertEquals("aardvark:alligator:avocet",    result.get('a'));
        assertEquals("bison:bushbaby:bustard",       result.get('b'));
        assertEquals("capybara:chimpanzee:capuchin", result.get('c'));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use Map.merge() within Iterable.forEach().
    // </editor-fold>
}
