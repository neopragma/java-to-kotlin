# To Do List

This is a series of modifications our team has been asked to make to the point of sale application. As we work these items, we'll incrementally convert some of the Java code to Kotlin. The format of the tasks is not important for our purpose, but they _could_ represent User Stories, Work Packages, or any other representation of "requirements" used with any development process or method. The development process or method our team uses has no bearing on the feasibility of incrementally migrating code from Java to Kotlin. The use of executable test cases will help us avoid errors, but otherwise no particular practices are assumed. 

## Round 1 - Generate a receipt

When a customer makes a purchase, we want to generate a receipt for the purchase. The application doesn't currently produce receipts. There's a Transaction class, but there's no logic to process a sale. 

Our task is to add functionality to process a sale, and to generate a receipt (or at least the data that would appear on a receipt) based on the completed Transaction. 

## Round 2 - Record transactions

The point of sale system currently forgets every transaction that occurs. Instead of that, we'd like it to keep a record of all the transactions that occur. 

## Round 3 - Close the business day

The point of sale system currently has no concept of "closing" a business day. We'd like to add functionality to close the day, including (a) reconciling the calculated amount of money that should be in the cash register (assume just one register) against the amount physically counted by store personnel and manually entered into the system.

## Round 4 - Transmit records to home office

At close of business each day, we want to transmit our sales records to the home office. We'll need a fake "adapter" that pretends to send the data, similar to the SkuAdapter provided in the starter code base. 


