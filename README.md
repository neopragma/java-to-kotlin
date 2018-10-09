# Java to Kotlin through incremental refactoring

Assume you have an existing Java product and you'd like to convert it to Kotlin. Rather than halting value-add work to undertake a big-bang conversion, you can convert the code little by little in the normal course of work on the product. 

This repository contains two trivial Java applications, one using TestNG for testing and one using JUnit4. The examples show how the Java source code can be converted to Kotlin piecemeal, using the existing test suites as a safety net.

The projects use Gradle for dependency management and builds. There's no dependency on any particular IDE or text editor, but you might find the Java-to-Kotlin conversion feature of JetBrains IntelliJ IDEA helpful. 

This code illustrates a plain vanilla application, not an Android project, a JavaScript project, or a native binary project. The ```build.gradle``` files are conventional (not using the DSL) and contain little more than is necessary to build the code and run tests. They are set up for separate unit test and integration test tasks, as that is pretty normal in real-world projects. 

The use of conventional Gradle specifications and older testing frameworks is meant to mimic what we are likely to find in existing applications in the wild. 

## Directories and projects

The repository is structured as follows:

```shell
java-to-kotlin/
  |
  +-- junit4
  |     |
  |     +-- starter 
  |     |     |
  |     |     + (initial Java code base)
  |     |
  |     +-- round1
  |           |
  |           + (first round of changes)
  |
  +-- testng
  |     |
  |     +-- starter
  |     |     |
  |     |     + (initial Java code base)
  |     |
  |     +-- round1
  |           |
  |           + (first round of changes)
  |
  +-- to-do-list 
        |
        +-- round1
              |
              +-- (normal work items)
```

There's more than one round of changes, of course. The approach is to show that we can accomplish this goal without making it into a special conversion project. The source and destination languages are both JVM languages, the compiled code is interoperable, and both languages can be tested with TestNG and/or JUnit4 cases written in either Java or Kotlin. These characteristics enable us to take an incremental approach protected by the application's existing test coverage, adding very little time, cost, or risk to the work.

The ```to-do-list``` directory contains "normal work items" that our team is asked to perform to make enhancements to the application. The examples demonstrate how to fold the conversion work into the normal work incrementally.

