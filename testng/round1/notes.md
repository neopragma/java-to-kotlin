# Round 1 - Generate a receipt

Our task is to add functionality to the point of sale application to produce a receipt for each Transaction. As our team has decided to migrate the code to Kotlin, our task list looks something like this:

1. Add Kotlin support to the Gradle build.
2. Create a directory structure to house the Kotlin sources.
3. Create code to represent a new domain entity, Sale, whose responsibility is to manage the creation of a Transaction and the resulting Receipt. Imagine the process of a cashier ringing up a sale for a customer. That's essentially the function of Sale. This new code will be test-driven with microtests written in Java using TestNG, and the implementation will be in Kotlin. We'll want unit tests with Sale isolated, and integration tests with real instances of collaborators.
4. Convert the Transaction class to Kotlin. Leave the other collaborating classes alone. The unit test cases before and after the conversion must yield the same results.

