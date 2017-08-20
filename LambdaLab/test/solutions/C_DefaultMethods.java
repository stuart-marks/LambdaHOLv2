package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

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
    public void c1_appendNew() {
        List<StringBuilder> sbList = Arrays.asList(
            new StringBuilder("alfa"),
            new StringBuilder("bravo"),
            new StringBuilder("charlie"));

        //UNCOMMENT//// TODO write code to modify sbList
        //BEGINREMOVE
        sbList.forEach(s -> s.append("new"));
        //ENDREMOVE

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
    @Test
    public void c2_removeOddLengthWords() {
        List<String> list = new ArrayList<>(Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot"));

        //UNCOMMENT//// TODO write code to modify list
        //BEGINREMOVE
        list.removeIf(s -> (s.length() & 1) == 1);
        // Alternatively:
        // list.removeIf(s -> (s.length() % 2) != 0);
        //ENDREMOVE

        assertEquals(Arrays.asList("alfa", "echo"), list);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use Collection.removeIf().
    // </editor-fold>


    /**
     * Replace every word in the list with its upper case equivalent.
     */
    @Test
    public void c3_upcaseAllWords() {
        List<String> list = Arrays.asList(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot");

        //UNCOMMENT//// TODO code to modify list
        //BEGINREMOVE
        list.replaceAll(String::toUpperCase);
        // Alternatively:
        // list.replaceAll(s -> s.toUpperCase());
        //ENDREMOVE

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
    @Test
    public void c4_appendToMapValues() {
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
    public void c4b_replaceMapValues() {
        Map<Integer, String> map = new TreeMap<>();
        map.put(1, "alfa");
        map.put(2, "bravo");
        map.put(3, "charlie");

        //UNCOMMENT//// TODO write code to modify map
        //BEGINREMOVE
        map.replaceAll((key, value) -> value + key);
        //ENDREMOVE

        assertEquals(3, map.size());
        assertEquals("alfa1",    map.get(1));
        assertEquals("bravo2",   map.get(2));
        assertEquals("charlie3", map.get(3));
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
    public void c5_mapOfListOfStringsByLength() {
        List<String> list = Arrays.asList(
            "aardvark", "bison", "capybara",
            "alligator", "bushbaby", "chimpanzee",
            "avocet", "bustard", "capuchin");
        Map<Integer, List<String>> result = new TreeMap<>();

        //UNCOMMENT//// TODO write code to populate result
        //BEGINREMOVE
        list.forEach(s -> result.computeIfAbsent(s.length(), key -> new ArrayList<>()).add(s));
        //ENDREMOVE

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
    @Test
    public void c6_mapOfStringByInitialCharacter() {
        List<String> list = Arrays.asList(
            "aardvark", "bison", "capybara",
            "alligator", "bushbaby", "chimpanzee",
            "avocet", "bustard", "capuchin");
        Map<Character, String> result = new TreeMap<>();

        //UNCOMMENT//// TODO write code to populate result
        //BEGINREMOVE
        list.forEach(s -> result.merge(s.charAt(0), s, (s1, s2) -> s1 + ":" + s2));
        //ENDREMOVE

        assertEquals(Arrays.asList('a', 'b', 'c'), new ArrayList<>(result.keySet()));
        assertEquals("aardvark:alligator:avocet",    result.get('a'));
        assertEquals("bison:bushbaby:bustard",       result.get('b'));
        assertEquals("capybara:chimpanzee:capuchin", result.get('c'));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use Map.merge() within Iterable.forEach().
    // </editor-fold>

    
    /**
     * For some reason the provided map has been filled with null values. 
     * This is a problem, beacuse everytime we get a value from this map, we
     * need to protect our application against NullPointerException. 
     * Write a code to replace these null values with empty Strings. The 
     * set of the keys is provided, note that the "g" key is not in the map,
     * and it should be. 
     */
    @Test
    public void c7_mapWithNullValues() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map = new HashMap<>();
        map.put("a", "alfa");
        map.put("b", "bravo");
        map.put("c", "charlie");
        map.put("d", "delta");
        map.put("e", null);
        map.put("f", null);
        
        //UNCOMMENT//// TODO write code to fix the map
        //BEGINREMOVE
        keys.forEach(key -> map.putIfAbsent(key, ""));
        //ENDREMOVE
        
        assertEquals(7, map.size());
        assertEquals("alfa", map.get("a"));
        assertEquals("bravo", map.get("b"));
        assertEquals("charlie", map.get("c"));
        assertEquals("delta", map.get("d"));
        assertEquals("", map.get("e"));
        assertEquals("", map.get("f"));
        assertEquals("", map.get("g"));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the Map.putIfAbsent() default method.
    // </editor-fold>
    
    
    /**
     * Another way of dealing with the problem of the previous example could
     * be to remove the keys if the values are null. 
     * Write a code that removes the keys of the map if the associated value
     * is null. 
     */
    @Test
    public void c8_mapWithNullValues() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map = new HashMap<>();
        map.put("a", "alfa");
        map.put("b", "bravo");
        map.put("c", "charlie");
        map.put("d", "delta");
        map.put("e", null);
        map.put("f", null);
        
        //UNCOMMENT//// TODO write code to fix the map
        //BEGINREMOVE
        keys.forEach(key -> map.remove(key, null));
        //ENDREMOVE
        
        assertEquals(4, map.size());
        assertEquals("alfa", map.get("a"));
        assertEquals("bravo", map.get("b"));
        assertEquals("charlie", map.get("c"));
        assertEquals("delta", map.get("d"));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the Map.remove() default method.
    // </editor-fold>
    
    
    /**
     * Another way of dealing with the problem of the previous example could
     * be to set the value to the empty string for the keys associated
     * with null, without adding the missing "g" key. 
     * Write a code that sets the value to the empty String for the key that
     * are in the maps, and associated with null. 
     */
    @Test
    public void c9_mapWithNullValues() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map = new HashMap<>();
        map.put("a", "alfa");
        map.put("b", "bravo");
        map.put("c", "charlie");
        map.put("d", "delta");
        map.put("e", null);
        map.put("f", null);
        
        //UNCOMMENT//// TODO write code to fix the map
        //BEGINREMOVE
        keys.forEach(key -> map.replace(key, null, ""));
        //ENDREMOVE
        
        assertEquals(6, map.size());
        assertEquals("alfa", map.get("a"));
        assertEquals("bravo", map.get("b"));
        assertEquals("charlie", map.get("c"));
        assertEquals("delta", map.get("d"));
        assertEquals("", map.get("e"));
        assertEquals("", map.get("f"));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the Map.replace() default method that takes 3 arguments.
    // </editor-fold>
    
    
    /**
     * We are still dealing with a map containing null value. This time, 
     * we want to put the values in upper case, and replace the null
     * values with empty Strings. 
     */
    @Test
    public void c10_computeWithNullValues() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map = new HashMap<>();
        map.put("a", "alfa");
        map.put("b", "bravo");
        map.put("c", "charlie");
        map.put("d", "delta");
        map.put("e", null);
        map.put("f", null);
        
        //UNCOMMENT//// TODO write code transform the map
        //BEGINREMOVE
        keys.forEach(key -> map.compute(key, (k, v) -> v == null ? "" : v.toUpperCase()));
        //ENDREMOVE
        
        assertEquals(7, map.size());
        assertEquals("ALFA", map.get("a"));
        assertEquals("BRAVO", map.get("b"));
        assertEquals("CHARLIE", map.get("c"));
        assertEquals("DELTA", map.get("d"));
        assertEquals("", map.get("e"));
        assertEquals("", map.get("f"));
        assertEquals("", map.get("g"));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the Map.compute() default method, read the Javadoc carefully
    // for the handling of null values. 
    // </editor-fold>
    
    
    /**
     * We are still dealing with a map containing null value. This time, 
     * we want to put the values in upper case, and leave the null values andd
     * absent keys as they are. 
     */
    @Test
    public void c11_computeWithNullValues() {
        List<String> keys = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        Map<String, String> map = new HashMap<>();
        map.put("a", "alfa");
        map.put("b", "bravo");
        map.put("c", "charlie");
        map.put("d", "delta");
        map.put("e", null);
        map.put("f", null);
        
        //UNCOMMENT//// TODO write code transform the map
        //BEGINREMOVE
        keys.forEach(key -> map.computeIfPresent(key, (k, v) -> v.toUpperCase()));
        //ENDREMOVE
        
        assertEquals(6, map.size());
        assertEquals("ALFA", map.get("a"));
        assertEquals("BRAVO", map.get("b"));
        assertEquals("CHARLIE", map.get("c"));
        assertEquals("DELTA", map.get("d"));
        assertEquals(null, map.get("e"));
        assertEquals(null, map.get("f"));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the Map.computeIfPresent() default method, read the Javadoc carefully
    // for the handling of null values. 
    // </editor-fold>
}