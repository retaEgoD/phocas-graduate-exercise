# Phocas Technical exercise

Please create a private GitHub repository containing your solution.

When you have completed the exercise, please invite "ashley-taylor" and "NickiGraf" to your repository.

Please include your name in the readme of the submission.

## Supplied data

Within this repository there is a file called `Generator.jar` execute this by running
`java -jar Generator.jar` this will create a file called `dataset.ndjson`

Requires Java 17 to run.

`dataset.ndjson` is a ndjson file containing fake transactions for a retail store.

This file is close to `3GB` big

It includes some information about the "customer".

Name and address with the address consisting of road, suburb and city.

the addresses are real and sourced from https://data.linz.govt.nz 

It also includes the salesperson's name, category, brand, value (NZD), and the date in ISO 8601 format (UTC)

From this file, we want the following questions answered


## Questions

For all questions, we only care about transactions that happened in Christchurch, January 2022 

Who is the 4th best salesperson by value?

For this salesperson, what is their best selling brand by value?

For this salesperson, what is their best selling category by value?

For this salesperson, how many transactions were in BOTH the top-selling brand and category?

For this salesperson, what road has the most transactions?


## Requirements

Written in Java 17 or newer.

Run from the command line.

The answers are outputted on a separate line in the order of the questions.

No other logging outputted.

Can use any libraries.

No external database is permitted.

Must only read the `dataset.ndjson` files once.

Use less than 200MB of memory. Running with `java -Xmx200m` will enforce this.

Have appropriate unit testing.



## Optional

Use less than 150MB

## Stretch

Use less than 30MB

This is challenging. Please only implement this requirement if it does not greatly reduce the simplicity of your solution.


## Supplied

We have stubbed out a Maven project. Please use the `main` method supplied in `Solution.java` as the entry point to your application.

We have configured Maven and GitHub actions to run this when you submit your solution automatically.

---

We are looking for a simple, clean implementation to this problem. Even with a 30MB memory limit, there are simple solutions.
