# JavaOne 2017 Lambda/Streams Programming Laboratory [HOL5959]

## Introduction

Welcome to the Lambda/Streams Programming Laboratory! The goal of this
lab is for you to learn about the lambda expressions, default methods,
and APIs (particularly the Streams library) introduced in Java 8, plus
a few API additions in Java 9.

The lab is structured as a set of exercises in the form of JUnit
tests. To complete each exercise, write some code that uses a
lambda or new API that enables the test to pass.

## Lab Instructions

1. Open the NetBeans dev build by clicking on the desktop icon.

2. The LambdaLab project should already be open. If it isn't,
it can be located in $HOME/HOL5959/LambdaLab.

3. Inside the LambdaLab project, open the Test Packages folder.

4. Open up one of the following exercise files:

        A_Lambdas.java
        B_Comparators.java
        C_DefaultMethods.java
        D_SimpleStreams.java
        E_IntermediateStreams.java
        F_AdvancedStreams.java
        G_MatcherScanner.java
        H_Challenges.java

5. Each exercise is in the form of a single JUnit test method. Each
test is marked with an @Ignore annotation so that JUnit will skip that
test initially.

6. To work on a test, delete the @Ignore annotation, fill in code at
the // TODO marker, trying to avoid modifying any setup code above the
TODO marker and assertion code below the TODO marker.

7. Press Control-F6 to run the tests in this file.

8. Make all the tests pass and get a 100% green bar!

## Detailed Test Description

At the top of each exercise is a comment that describes the goal of
the exercise. Within the test method, there is a // TODO comment that
marks the location where you need fill in some implementation
code. There may be some setup code above the // TODO comment, and
there will be some assertion-checking code below.  You shouldn't have
to modify any of the setup code at the top of the test method or the
assertions at the bottom of the test method.

There is sometimes a hint or two after a test method. If you're having
trouble with an exercise, look for hints. The hint text is inside
a editor fold that is closed by default, so click on the plus-sign
in the left margin to read it.

The intent of the exercises is to solve them using the Java 8 lambda
expressions feature, the Java 8 default methods feature, the Java 8
Streams API, and other APIs added in Java 8 or Java 9. Of course, it
is possible to use conventional Java code, such as for-loops, but all
of the exercises are amenable to being solved using new features in
just a handful of lines. Most exercises will take less than half a
dozen lines. Some of the more difficult exercises may take up to eight
lines, depending upon how aggressive you are about breaking
lines. None of the exercises involve writing large amounts of
code. Most of the streams-based exercises involve writing a single
stream pipeline.

Several of the exercises read data from a text file. The field named
"reader" is a BufferedReader which will be opened for you on the text
file. In any exercise that refers to reading from the text file, you
can simply use the "reader" variable without worry about opening or
closing it. This is set up by JUnit using the @Before and @After
methods at the bottom of the file. The text file is "SonnetI.txt"
(Shakespeare's first sonnet) which is located at the root of this
NetBeans project.

If you're really stuck, the solutions to the exercises are in the package

        Test Packages > solutions

There is one solutions file corresponding to each exercise file.  Many
exercises can be solved in several different ways. In some cases, the
solutions file will have several alternatives. Even if you've solved
an exercise, it can be useful to look at the solutions to compare your
solutions with those of the lab authors.
