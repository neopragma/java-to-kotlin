# Round 1 - Generate a receipt

Our task is to add functionality to the point of sale application to produce a receipt for each Transaction. If we're working with User Stories, we might have a story like this:

As a customer, I want to get a receipt when I buy things so I'll have a record of my purchase.

Acceptance Criteria:

When a sale is rung up, the system produces a receipt showing each item purchased, the quantity purchased, the sale price, the extended price, and the sales tax. 

Scenarios:

```
Given a customer purchases the following items:
| description                    | quantity | price  | taxable |
| "ACME Dynamite (Case)"         | 1        | $32.50 | yes     |
| "ACME Portable Holes (3-pack)" | 2        | $11.50 | yes     |
| "ACME Bird Seed"               | 1        |  $8.50 | no      |
When the sale is completed
Then the system produces a receipt showing:
| description                    | quantity | price  | extended_price | tax_amount | final_price |
| "ACME Dynamite (Case)"         | 1        | $32.50 | $32.50         | $2.76      | $35.26      |
| "ACME Portable Holes (3-pack)" | 2        | $11.50 | $23.00         | $1.96      | $24.96      |
| "ACME Bird Seed"               | 1        |  $8.50 | $8.50          | N/A        | $8.50       |
And the receipt shows a total sale amount of $68.72
```

As our team has decided to migrate the code to Kotlin, our task list looks something like this:

1. Add Kotlin support to the Gradle build.
2. Create a directory structure to house the Kotlin sources.
3. Create code to represent a new domain entity, Sale, whose responsibility is to manage the creation of a Transaction and the resulting Receipt. Imagine the process of a cashier ringing up a sale for a customer. That's essentially the function of Sale. This new code will be test-driven with microtests written in Java using TestNG, and the implementation will be in Kotlin. We'll want unit tests with Sale isolated, and integration tests with real instances of collaborators.
4. Convert the Transaction class to Kotlin. Leave the other collaborating classes alone. The unit test cases before and after the conversion must yield the same results.

