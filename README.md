Java 8 Lambdas Hands-on Lab Materials

(version 2 for late 2014 conferences)

Open LambdaLab in your favorite IDE. (Currently NetBeans is
supported.) Then open Test Packages / exercises / Exercises.java.

The exercises are in the form of JUnit tests. Each exercise is a
single test method. Each test method is initially marked with @Ignore
so that it will be not be run by JUnit. Each test method has some
setup code and some assertions at the bottom.

To work on an exercise, uncomment @Ignore and add code at or near the
TODO comment that will make the assertions pass.

The intent of the exercises is to solve them using the new Java 8
lambda feature, the new Java 8 Streams API, and other APIs added in
Java 8. Of course, it is possible to use conventional Java code, e.g.,
for-loops, but all of the exercises are amenable to being solved using
new Java 8 features in just a handful of lines. Most exercises will
take less than half a dozen lines. Some of the more difficult
exercises may take up to eight lines, depending upon how aggressive
you are about breaking lines. None of the exercises involve writing
large amounts of code. Most of the streams-based exercises involve
writing a single stream pipeline.

Each exercise has one or two hints hidden in a code fold below the
exercise. Open these up if you're stuck.

If you're really stuck, the solutions to the exercises are in the file
Test Packages / solutions / Exercises.java. Many exercises can be
solved in several different ways. In some cases, the solutions file
will have several alternatives. Even if you've solved an exercise, it
can be useful to look at the solutions to compare your solutions with
those of the lab authors.
