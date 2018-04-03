package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This set of exercises is about lambdas and method references.
 * You will write a lambda or method reference corresponding to
 * each of several different functional interfaces. Each exercise
 * is named after the functional interface intended to be used
 * as the solution.
 */
public class A_Lambdas {

    /**
     * Write a lambda expression that is a predicate
     * that tests whether a string is longer than four characters.
     */
    @Test
    public void a_predicate1() {
        //TODO//Predicate<String> pred = null;
        //BEGINREMOVE
        Predicate<String> pred = s -> s.length() > 4;
        //ENDREMOVE

        assertTrue(pred.test("abcde"));
        assertFalse(pred.test("abcd"));
    }

    /**
     * Write a lambda expression that is a predicate
     * that tests whether a string is empty.
     */
    @Test
    public void a_predicate2() {
        //TODO//Predicate<String> pred = null;
        //BEGINREMOVE
        Predicate<String> pred = s -> s.isEmpty();
        //ENDREMOVE

        assertTrue(pred.test(""));
        assertFalse(pred.test("a"));
    }

    /**
     * Write an unbound method reference that is a predicate
     * that tests whether a string is empty. An unbound method
     * reference has a class name on the left-hand side of the ::
     * operator:
     *
     *     classname::methodname
     */
    @Test
    public void a_predicate3() {
        //TODO//Predicate<String> pred = null;
        //BEGINREMOVE
        Predicate<String> pred = String::isEmpty;
        //ENDREMOVE

        assertTrue(pred.test(""));
        assertFalse(pred.test("a"));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Copy the lambda expression from the previous exercise and then pop
    // up the menu over the "light bulb" icon in the left margin. This menu
    // has an option to convert the lambda to a method reference. (The exact
    // gesture will vary among IDEs.)
    // </editor-fold>

    /**
     * Create a predicate that returns true if both predicates
     * startsWithJ and lengthIs7 hold.
     */
    @Test
    public void a_predicate4() {
        Predicate<String> startsWithJ = s -> s.startsWith("J");
        Predicate<String> lengthIs7 = s -> s.length() == 7;

        //TODO//Predicate<String> startsWithJAndLengthIs7 = null;
        //BEGINREMOVE
        Predicate<String> startsWithJAndLengthIs7 = startsWithJ.and(lengthIs7);
        //ENDREMOVE

        assertFalse(startsWithJAndLengthIs7.test("Hello"));
        assertFalse(startsWithJAndLengthIs7.test("HelloJ1"));
        assertFalse(startsWithJAndLengthIs7.test("Java1"));
        assertTrue(startsWithJAndLengthIs7.test("JavaOne"));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // see java.util.function.Predicate.and()
    // </editor-fold>

    /**
     * Create a predicate that is true if the length of the provided string
     * is 9 or the provided string equals ERROR.
     */
    @Test
    public void a_predicate5() {
        Predicate<String> lengthIs9 = s -> s.length() == 9;
        Predicate<String> equalsError = "ERROR"::equals;
        // Note: this could also be: Predicate.isEqual("ERROR")

        //TODO//Predicate<String> lengthIs9orError = null;
        //BEGINREMOVE
        Predicate<String> lengthIs9orError = lengthIs9.or(equalsError);
        //ENDREMOVE

        assertFalse(lengthIs9orError.test("Hello"));
        assertTrue(lengthIs9orError.test("Hello J1!"));
        assertTrue(lengthIs9orError.test("ERROR"));
        assertFalse(lengthIs9orError.test("Error"));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // see java.util.function.Predicate.or()
    // </editor-fold>

    /**
     * Write a lambda expression that wraps the given
     * string in parentheses.
     */
    @Test
    public void b_function1() {
        //TODO//Function<String, String> func = null;
        //BEGINREMOVE
        Function<String, String> func = s -> "(" + s + ")";
        //ENDREMOVE

        assertEquals("(abc)", func.apply("abc"));
    }

    /**
     * Write a lambda expression that converts the
     * given string to upper case.
     */
    @Test
    public void b_function2() {
        //TODO//Function<String, String> func = null;
        //BEGINREMOVE
        Function<String, String> func = s -> s.toUpperCase();
        //ENDREMOVE

        assertEquals("ABC", func.apply("abc"));
    }

    /**
     * Write an unbound method reference that converts the
     * given string to upper case.
     */
    @Test
    public void b_function3() {
        //TODO//Function<String, String> func = null;
        //BEGINREMOVE
        Function<String, String> func = String::toUpperCase;
        //ENDREMOVE

        assertEquals("ABC", func.apply("abc"));
    }

    /**
     * Given two Functions, one that converts a null reference to an
     * empty string, and another that gets the length of a string,
     * create a single function converts nulls and then gets the
     * string's length.
     */
    @Test
    public void b_function4() {
        Function<String, String> unNullify = s -> s == null ? "" : s;
        Function<String, Integer> length = String::length;

        //TODO//Function<String, Integer> lengthBis = null;
        //BEGINREMOVE
        Function<String, Integer> lengthBis = unNullify.andThen(length);
        // Alternatively:
        // Function<String, Integer> lengthBis = length.compose(unNullify);
        //ENDREMOVE

        assertEquals((Integer)14, lengthBis.apply("Hello JavaOne!"));
        assertEquals((Integer)0, lengthBis.apply(""));
        assertEquals((Integer)0, lengthBis.apply(null));
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // See java.util.Function.andThen() or java.util.Function.compose()
    // </editor-fold>

    /**
     * Write a lambda expression that appends the
     * string "abc" to the given StringBuilder.
     */
    @Test
    public void c_consumer1() {
        //TODO//Consumer<StringBuilder> cons = null;
        //BEGINREMOVE
        Consumer<StringBuilder> cons = sb -> sb.append("abc");
        //ENDREMOVE

        StringBuilder sb = new StringBuilder("xyz");
        cons.accept(sb);
        assertEquals("xyzabc", sb.toString());
    }

    /**
     * Write a lambda expression that clears the given list.
     */
    @Test
    public void c_consumer2() {
        //TODO//Consumer<List<String>> cons = null;
        //BEGINREMOVE
        Consumer<List<String>> cons = list -> list.clear();
        //ENDREMOVE

        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        cons.accept(list);
        assertTrue(list.isEmpty());
    }

    /**
     * Write an unbound method reference that clears the given list.
     */
    @Test
    public void c_consumer3() {
        //TODO//Consumer<List<String>> cons = null;
        //BEGINREMOVE
        Consumer<List<String>> cons = List::clear;
        //ENDREMOVE

        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        cons.accept(list);
        assertTrue(list.isEmpty());
    }

    /**
     * Given two consumers, create a consumer that passes the String to the
     * first consumer, then to the second.
     */
    @Test
    public void c_consumer4() {
        Consumer<List<String>> c1 = list -> list.add("first");
        Consumer<List<String>> c2 = list -> list.add("second");

        //TODO//Consumer<List<String>> consumer = null;
        //BEGINREMOVE
        Consumer<List<String>> consumer = c1.andThen(c2);
        //ENDREMOVE

        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        consumer.accept(list);
        assertEquals(List.of("a", "b", "c", "first", "second"), list);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // see java.util.function.Consumer.andThen()
    // </editor-fold>

    /**
     * Write a lambda expression that returns a new StringBuilder
     * containing the string "abc".
     */
    @Test
    public void d_supplier1() {
        //TODO//Supplier<StringBuilder> sup = null;
        //BEGINREMOVE
        Supplier<StringBuilder> sup = () -> new StringBuilder("abc");
        //ENDREMOVE

        assertEquals("abc", sup.get().toString());
    }

    /**
     * Write a lambda expression that returns a new, empty StringBuilder.
     */
    @Test
    public void d_supplier2() {
        //TODO//Supplier<StringBuilder> sup = null;
        //BEGINREMOVE
        Supplier<StringBuilder> sup = () -> new StringBuilder();
        //ENDREMOVE

        assertEquals("", sup.get().toString());
    }

    /**
     * Write a constructor reference that returns a new, empty StringBuilder.
     */
    @Test
    public void d_supplier3() {
        //TODO//Supplier<StringBuilder> sup = null;
        //BEGINREMOVE
        Supplier<StringBuilder> sup = StringBuilder::new;
        //ENDREMOVE

        assertEquals("", sup.get().toString());
    }

    /**
     * Write a lambda expression that, given two strings, returns the result
     * of concatenating the first with the second, followed by the
     * first again.
     */
    @Test
    public void e_bifunction1() {
        //TODO//BiFunction<String, String, String> bifunc = null;
        //BEGINREMOVE
        BiFunction<String, String, String> bifunc = (s1, s2) -> s1 + s2 + s1;
        //ENDREMOVE

        assertEquals("FirstSecondFirst", bifunc.apply("First", "Second"));
    }

    /**
     * Write a lambda expression that returns the index of
     * the first occurrence of the second string within the first string,
     * or -1 if the second string doesn't occur within the first string.
     */
    @Test
    public void e_bifunction2() {
        //TODO//BiFunction<String, String, Integer> bifunc = null;
        //BEGINREMOVE
        BiFunction<String, String, Integer> bifunc = (s1, s2) -> s1.indexOf(s2);
        //ENDREMOVE

        assertEquals(3, bifunc.apply("abcdefghi", "def").intValue());
        assertEquals(-1, bifunc.apply("abcdefghi", "xyz").intValue());
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // The String method
    //     public int indexOf(String)
    // works as a BiFunction, because the receiver (a String instance)
    // counts as the first argument. The argument to indexOf() becomes
    // the second argument to the BiFunction.
    // </editor-fold>

    /**
     * Write an unbound method reference that returns the index of
     * the first occurrence of the second string within the first string,
     * or -1 if the second string doesn't occur within the first string.
     */
    @Test
    public void e_bifunction3() {
        //TODO//BiFunction<String, String, Integer> bifunc = null;
        //BEGINREMOVE
        BiFunction<String, String, Integer> bifunc = String::indexOf;
        //ENDREMOVE

        assertEquals(3, bifunc.apply("abcdefghij", "def").intValue());
        assertEquals(-1, bifunc.apply("abcdefghij", "xyz").intValue());
    }
    // Hint 1:
    // <editor-fold defaultstate="collapsed">
    // Try using the IDE command to convert the lambda from the previous
    // exercise into a method reference.
    // </editor-fold>
    // Hint 2:
    // <editor-fold defaultstate="collapsed">
    // This is just like the example above with the argument shifting.
    // The only difference is that arguments aren't specified in a
    // method reference, so overload resolution has to do more work
    // to find the overloaded method that matches.
    // </editor-fold>


    /**
     * Write a lambda expression that appends the 'suffix'
     * variable (a String) to the sb variable (a StringBuilder).
     */
    @Test
    public void f_runnable1() {
        StringBuilder sb = new StringBuilder("abc");
        String suffix = "xyz";

        //TODO//Runnable r = null;
        //BEGINREMOVE
        Runnable r = () -> sb.append(suffix);
        //ENDREMOVE

        r.run();
        r.run();
        r.run();
        assertEquals("abcxyzxyzxyz", sb.toString());
    }

    /**
     * Write a lambda expression that takes a string argument
     * and returns the index of that argument into the string
     * "abcdefghij", or that returns -1 if the string argument
     * doesn't occur.
     */
    @Test
    public void g_boundMethodRef1() {
        //TODO//Function<String, Integer> func = null;
        //BEGINREMOVE
        Function<String, Integer> func = s -> "abcdefghij".indexOf(s);
        //ENDREMOVE

        assertEquals(2, func.apply("cde").intValue());
        assertEquals(4, func.apply("efg").intValue());
        assertEquals(-1, func.apply("xyz").intValue());
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Call the indexOf() method on a string literal.
    // </editor-fold>

    /**
     * Write a bound method reference that takes a string argument
     * and returns the index of that argument into the string
     * "abcdefghij", or that returns -1 if the string argument
     * doesn't occur. A bound method reference has an instance,
     * or an expression that evaluates to an instance, on the left-hand
     * side of the :: operator:
     *
     *     myObject::methodname
     *
     * This is in contrast to an unbound method reference, which has
     * a classname on the left-hand side of the :: operator.
     */
    @Test
    public void g_boundMethodRef2() {
        //TODO//Function<String, Integer> func = null;
        //BEGINREMOVE
        Function<String, Integer> func = "abcdefghij"::indexOf;
        //ENDREMOVE

        assertEquals(2, func.apply("cde").intValue());
        assertEquals(4, func.apply("efg").intValue());
        assertEquals(-1, func.apply("xyz").intValue());
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Place a string literal on the left-hand side of the :: operator.
    // </editor-fold>
}
