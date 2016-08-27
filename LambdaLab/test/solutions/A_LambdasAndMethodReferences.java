package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This set of exercises is about lambdas and method references.
 * You will write a lambda or method reference corresponding to
 * each of several different functional interfaces. Each exercise
 * is named after the functional interface intended to be used
 * as the solution.
 */
public class A_LambdasAndMethodReferences {
    @Test
    public void a01predicate() {
        // TODO: write a lambda expression that is a predicate
        // that tests whether a string is longer than four characters.
        //UNCOMMENT//Predicate<String> pred = null;
        //BEGINREMOVE
        Predicate<String> pred = s -> s.length() > 4;
        //ENDREMOVE

        assertTrue(pred.test("abcde"));
        assertFalse(pred.test("abcd"));
    }

    @Test
    public void a02predicate() {
        // TODO: write a lambda expression that is a predicate
        // that tests whether a string is empty
        //UNCOMMENT//Predicate<String> pred = null;
        //BEGINREMOVE
        Predicate<String> pred = s -> s.isEmpty();
        //ENDREMOVE

        assertTrue(pred.test(""));
        assertFalse(pred.test("a"));
    }

    @Test
    public void a03predicate() {
        // TODO: write a method reference that is a predicate
        // that tests whether a string is empty
        //UNCOMMENT//Predicate<String> pred = null;
        //BEGINREMOVE
        Predicate<String> pred = String::isEmpty;
        //ENDREMOVE

        assertTrue(pred.test(""));
        assertFalse(pred.test("a"));
    }

    /*
     * Create a Predicate that returns true if both predicates
     * startsWithJ and lengthIs7 hold.
     */
    @Test
    public void a04predicate() {
        Predicate<String> startsWithJ = s -> s.startsWith("J");
        Predicate<String> lengthIs7 = s -> s.length() == 7;

        //UNCOMMENT//Predicate<String> startsWithJAndLengthIs7 = null; // TODO
        //BEGINREMOVE
        Predicate<String> startsWithJAndLengthIs7 = startsWithJ.and(lengthIs7);
        //ENDREMOVE

        assertFalse(startsWithJAndLengthIs7.test("Hello"));
        assertFalse(startsWithJAndLengthIs7.test("HelloJ1"));
        assertFalse(startsWithJAndLengthIs7.test("Java1"));
        assertTrue(startsWithJAndLengthIs7.test("JavaOne"));
    }

    /*
     * Create a Predicate is true if the length of the provided string
     * is 9 or the provided String equals ERROR.
     */
    @Test
    public void a05predicate() {
        Predicate<String> lengthIs9 = s -> s.length() == 9;
        Predicate<String> equalsError = "ERROR"::equals;

        //UNCOMMENT//Predicate<String> lengthIs9OrERROR = null; // TODO
        //BEGINREMOVE
        Predicate<String> lengthIs9OrERROR = lengthIs9.or(equalsError);
        //ENDREMOVE

        assertFalse(lengthIs9OrERROR.test("Hello"));
        assertTrue(lengthIs9OrERROR.test("Hello J1!"));
        assertTrue(lengthIs9OrERROR.test("ERROR"));
        assertFalse(lengthIs9OrERROR.test("Error"));
    }


    @Test
    public void a04function() {
        // TODO: write a lambda expression that wraps the given
        // string in parentheses.
        //UNCOMMENT//Function<String, String> func = null;
        //BEGINREMOVE
        Function<String, String> func = s -> "(" + s + ")";
        //ENDREMOVE

        assertEquals("(abc)", func.apply("abc"));
    }

    @Test
    public void a05function() {
        // TODO: write a lambda expression that converts the
        // given string to upper case.
        //UNCOMMENT//Function<String, String> func = null;
        //BEGINREMOVE
        Function<String, String> func = s -> s.toUpperCase();
        //ENDREMOVE

        assertEquals("ABC", func.apply("abc"));
    }

    @Test
    public void a06function() {
        // TODO: write a method reference that converts the
        // given string to upper case.
        //UNCOMMENT//Function<String, String> func = null;
        //BEGINREMOVE
        Function<String, String> func = String::toUpperCase;
        //ENDREMOVE

        assertEquals("ABC", func.apply("abc"));
    }

    @Test
    public void a07consumer() {
        // TODO: write a lambda expression that appends the
        // string "abc" to the given StringBuilder.
        //UNCOMMENT//Consumer<StringBuilder> cons = null;
        //BEGINREMOVE
        Consumer<StringBuilder> cons = sb -> sb.append("abc");
        //ENDREMOVE

        StringBuilder sb = new StringBuilder("xyz");
        cons.accept(sb);
        assertEquals("xyzabc", sb.toString());
    }

    @Test
    public void a08consumer() {
        // TODO: write a lambda expression that clears the given list.
        //UNCOMMENT//Consumer<List<String>> cons = null;
        //BEGINREMOVE
        Consumer<List<String>> cons = list -> list.clear();
        //ENDREMOVE

        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        cons.accept(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void a09consumer() {
        // TODO: write a method reference that clears the given list.
        //UNCOMMENT//Consumer<List<String>> cons = null;
        //BEGINREMOVE
        Consumer<List<String>> cons = List::clear;
        //ENDREMOVE

        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        cons.accept(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void a10consumer() {
        // TODO: Given two consumers, create a consumer that passes the String to the
        // first consumer, then to the second

        Consumer<List<String>> c1 = list -> list.add("first");
        Consumer<List<String>> c2 = list -> list.add("second");

        //UNCOMMENT//Consumer<List<String>> consumer = null; // TODO
        //BEGINREMOVE
        Consumer<List<String>> consumer = c1.andThen(c2);
        //ENDREMOVE

        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        consumer.accept(list);
        assertEquals(list, Arrays.asList("a", "b", "c", "first", "second"));
    }

    @Test
    public void a10supplier() {
        // TODO: write a lambda expression that returns a new StringBuilder
        // containing the string "abc".
        Supplier<StringBuilder> sup = () -> new StringBuilder("abc");

        assertEquals("abc", sup.get().toString());
    }

    @Test
    public void a11supplier() {
        // TODO: write a lambda expression that returns a new, empty StringBuilder
        //UNCOMMENT//Supplier<StringBuilder> sup = null;
        //BEGINREMOVE
        Supplier<StringBuilder> sup = () -> new StringBuilder();
        //ENDREMOVE

        assertEquals("", sup.get().toString());
    }

    @Test
    public void a12supplier() {
        // TODO: write a method reference that returns a new, empty StringBuilder
        //UNCOMMENT//Supplier<StringBuilder> sup = null;
        //BEGINREMOVE
        Supplier<StringBuilder> sup = StringBuilder::new;
        //ENDREMOVE

        assertEquals("", sup.get().toString());
    }

    @Test
    public void a13bifunction() {
        // TODO: write a lambda expression that, given two strings, returns the result
        // of concatenating the first, followed by the second, followed by the
        // first again.
        //UNCOMMENT//BiFunction<String, String, String> bifunc = null;
        //BEGINREMOVE
        BiFunction<String, String, String> bifunc = (s1, s2) -> s1 + s2 + s1;
        //ENDREMOVE

        assertEquals("abcdefabc", bifunc.apply("abc", "def"));
    }

    @Test
    public void a14bifunction() {
        // TODO: write a lambda expression that returns the index of
        // the first occurrence of the second string within the first string,
        // or -1 if the second string doesn't occur within the first string.
        //UNCOMMENT//BiFunction<String, String, Integer> bifunc = null;
        //BEGINREMOVE
        BiFunction<String, String, Integer> bifunc = (s1, s2) -> s1.indexOf(s2);
        //ENDREMOVE

        assertTrue(bifunc.apply("abcdefghi", "def") == 3);
        assertTrue(bifunc.apply("abcdefghi", "xyz") == -1);
    }

    @Test
    public void a15bifunction() {
        // TODO: write a method reference that returns the index of
        // the first occurrence of the second string within the first string,
        // or -1 if the second string doesn't occur within the first string.
        //UNCOMMENT//BiFunction<String, String, Integer> bifunc = null;
        //BEGINREMOVE
        BiFunction<String, String, Integer> bifunc = String::indexOf;
        //ENDREMOVE

        assertTrue(bifunc.apply("abcdefghi", "def") == 3);
        assertTrue(bifunc.apply("abcdefghi", "xyz") == -1);
    }

    @Test
    public void a16runnable() {
        StringBuilder sb = new StringBuilder("abc");
        String suffix = "xyz";

        // TODO: write a lambda expression that appends the 'suffix'
        // variable (a String) to the sb variable (a StringBuilder).
        //UNCOMMENT//Runnable r = null;
        //BEGINREMOVE
        Runnable r = () -> sb.append(suffix);
        //ENDREMOVE

        r.run();
        r.run();
        r.run();
        assertEquals("abcxyzxyzxyz", sb.toString());
    }
}
