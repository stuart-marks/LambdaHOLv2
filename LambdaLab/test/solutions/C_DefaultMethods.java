package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    @Test
    public void c01_appendNew() {
        List<StringBuilder> sbList = List.of(
            new StringBuilder("alfa"),
            new StringBuilder("bravo"),
            new StringBuilder("charlie"));

        //UNCOMMENT//// TODO write code to modify sbList
        //BEGINREMOVE
        sbList.forEach(s -> s.append("new"));
        //ENDREMOVE

        assertEquals(List.of("alfanew", "bravonew", "charlienew"),
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
    @Test
    public void c02_removeOddLengthWords() {
        List<String> list = new ArrayList<>(Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot"));

        //UNCOMMENT//// TODO write code to modify list
        //BEGINREMOVE
        list.removeIf(s -> (s.length() & 1) == 1);
        // Alternatively:
        // list.removeIf(s -> (s.length() % 2) != 0);
        //ENDREMOVE

        assertEquals(List.of("alfa", "echo"), list);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use Collection.removeIf().
    // </editor-fold>


    /**
     * Replace every word in the list with its upper case equivalent.
     */
    @Test
    public void c03_upcaseAllWords() {
        List<String> list = Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot");

        //UNCOMMENT//// TODO code to modify list
        //BEGINREMOVE
        list.replaceAll(String::toUpperCase);
        // Alternatively:
        // list.replaceAll(s -> s.toUpperCase());
        //ENDREMOVE

        assertEquals(List.of("ALFA", "BRAVO", "CHARLIE", "DELTA", "ECHO", "FOXTROT"),
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
    @Test
    public void c04_appendToMapValues() {
        Map<Integer, StringBuilder> map = new TreeMap<>();
        map.put(1, new StringBuilder("alfa"));
        map.put(2, new StringBuilder("bravo"));
        map.put(3, new StringBuilder("charlie"));

        //UNCOMMENT//// TODO write code to modify map
        //BEGINREMOVE
        map.forEach((k, v) -> v.append(k));
        //ENDREMOVE

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
     * Given a map whose keys are Integers and whose values are Strings,
     * append to each String the string representation of its corresponding
     * Integer key.
     */
    @Test
    public void c05_replaceMapValues() {
        Map<Integer, String> map = new TreeMap<>();
        map.put(1, "alfa");
        map.put(2, "bravo");
        map.put(3, "charlie");

        //UNCOMMENT//// TODO write code to modify map
        //BEGINREMOVE
        map.replaceAll((key, value) -> value + key);
        //ENDREMOVE

        assertEquals(Map.of(1, "alfa1",
                            2, "bravo2",
                            3, "charlie3"),
                     map);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use Map.replaceAll().
    // </editor-fold>


    /**
     * Given a list of words, populate a map whose keys are the lengths of
     * each word, and whose values are list of words with that length.
     */
    @Test
    public void c06_mapOfListOfStringsByLength() {
        List<String> list = List.of(
            "aardvark", "bison", "capybara",
            "alligator", "bushbaby", "chimpanzee",
            "avocet", "bustard", "capuchin");
        Map<Integer, List<String>> result = new TreeMap<>();

        //UNCOMMENT//// TODO write code to populate result
        //BEGINREMOVE
        list.forEach(s -> result.computeIfAbsent(s.length(), key -> new ArrayList<>()).add(s));
        //ENDREMOVE

        assertEquals(Map.of( 5, List.of("bison"),
                             6, List.of("avocet"),
                             7, List.of("bustard"),
                             8, List.of("aardvark", "capybara", "bushbaby", "capuchin"),
                             9, List.of("alligator"),
                            10, List.of("chimpanzee")),
                     result);
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
    @Test
    public void c07_mapOfStringByInitialCharacter() {
        List<String> list = List.of(
            "aardvark", "bison", "capybara",
            "alligator", "bushbaby", "chimpanzee",
            "avocet", "bustard", "capuchin");
        Map<Character, String> result = new TreeMap<>();

        //UNCOMMENT//// TODO write code to populate result
        //BEGINREMOVE
        list.forEach(s -> result.merge(s.charAt(0), s, (s1, s2) -> s1 + ":" + s2));
        //ENDREMOVE

        assertEquals(Map.of('a', "aardvark:alligator:avocet",
                            'b', "bison:bushbaby:bustard",
                            'c', "capybara:chimpanzee:capuchin"),
                     result);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use Map.merge() within Iterable.forEach().
    // </editor-fold>


    /**
     * For some reason the provided map doesn't have mappings for all the keys. This
     * is a problem, because if we call get() on a key that isn't present, it returns
     * null, and we need to add checks to protect against NullPointerException.
     * Write code to ensure that all missing keys are mapped to the empty string.
     */
    @Test
    public void c08_mapWithMissingValues() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map = new HashMap<>(Map.of("a", "alfa",
                                                       "b", "bravo",
                                                       "c", "charlie",
                                                       "d", "delta"));

        //UNCOMMENT//// TODO write code to fix the map
        //BEGINREMOVE
        keys.forEach(key -> map.putIfAbsent(key, ""));
        //ENDREMOVE

        assertEquals(Map.of("a", "alfa",
                            "b", "bravo",
                            "c", "charlie",
                            "d", "delta",
                            "e", "",
                            "f", "",
                            "g", ""),
                     map);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the Map.putIfAbsent() default method.
    // </editor-fold>


    /**
     * In the previous example, we added map entries that had a default value.
     * We've now determined that's incorrect, and we want to undo that. This
     * time, we want to remove the entry if the value is the empty string.
     */
    @Test
    public void c09_mapRemoveEntriesWithEmptyValues() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map = new HashMap<>(Map.of("a", "alfa",
                                                       "b", "bravo",
                                                       "c", "charlie",
                                                       "d", "delta",
                                                       "e", "",
                                                       "f", "",
                                                       "g", ""));

        //UNCOMMENT//// TODO write code to fix the map
        //BEGINREMOVE
        keys.forEach(key -> map.remove(key, ""));
        //ENDREMOVE

        assertEquals(Map.of("a", "alfa",
                            "b", "bravo",
                            "c", "charlie",
                            "d", "delta"),
                     map);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the two-arg Map.remove() default method.
    // </editor-fold>


    /**
     * We need another way to deal with the problem of the previous example.
     * Instead of removing entries whose value is the empty string, we want
     * to replace the empty-string values with a value that's the key itself.
     * Write the code to do that.
     */
    @Test
    public void c10_mapReplaceEmptyValues() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map = new HashMap<>(Map.of("a", "alfa",
                                                       "b", "bravo",
                                                       "c", "charlie",
                                                       "d", "delta",
                                                       "e", "",
                                                       "f", "",
                                                       "g", ""));

        //UNCOMMENT//// TODO write code to fix the map
        //BEGINREMOVE
        keys.forEach(key -> map.replace(key, "", key));
        //ENDREMOVE

        assertEquals(Map.of("a", "alfa",
                            "b", "bravo",
                            "c", "charlie",
                            "d", "delta",
                            "e", "e",
                            "f", "f",
                            "g", "g"),
                map);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the Map.replace() default method that takes 3 arguments.
    // </editor-fold>


    /**
     * We are still dealing with a map with missing entries. For entries that
     * are present, we want to convert the value to upper case; and for keys
     * that are not present, we want to add an entry where the value is the
     * same as the key.
     */
    @Test
    public void c11_computeWithMissingEntries() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map = new HashMap<>(Map.of("a", "alfa",
                                                       "b", "bravo",
                                                       "c", "charlie",
                                                       "d", "delta"));

        //UNCOMMENT//// TODO write code transform the map
        //BEGINREMOVE
        keys.forEach(key -> map.compute(key, (k, v) -> v == null ? k : v.toUpperCase()));
        //ENDREMOVE

        assertEquals(Map.of("a", "ALFA",
                            "b", "BRAVO",
                            "c", "CHARLIE",
                            "d", "DELTA",
                            "e", "e",
                            "f", "f",
                            "g", "g"),
                map);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the Map.compute() default method, and read the Javadoc carefully
    // regarding the mappings that aren't present.
    // </editor-fold>


    /**
     * The map now has several entries, some with valid values, and some
     * with values that are the empty string. This time, we want to convert
     * the non-empty values to upper case, but we want to remove the entries
     * for which the values are the empty string.
     */
    @Test
    public void c12_computeAndRemoveSomeEntries() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map = new HashMap<>(Map.of("a", "alfa",
                                                       "b", "bravo",
                                                       "c", "charlie",
                                                       "d", "delta",
                                                       "e", "",
                                                       "f", "",
                                                       "g", ""));

        //UNCOMMENT//// TODO write code transform the map
        //BEGINREMOVE
        keys.forEach(key -> map.compute(key, (k, v) -> v.isEmpty() ? null : v.toUpperCase()));
        //ENDREMOVE

        assertEquals(Map.of("a", "ALFA",
                            "b", "BRAVO",
                            "c", "CHARLIE",
                            "d", "DELTA"),
                map);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the Map.compute() default method, read the Javadoc carefully
    // for the handling of null values returned from the function.
    // </editor-fold>
}