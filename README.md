# The Algorithm Design Manual by Steven S. Skiena Notes

* This is a compilation of my notes while reading Algorithm Design Manual by Steven S. Skiena.

## Chapter 1 - Introduction to Algorithm Design

* There are three desirable properties for a good algorithm. We seek algorithms
that are correct and efficient, while being easy to implement.

* Correct algorithms usually come with a proof of correctness, which is an explanation of why we know that the algorithm must take every instance of the problem to the desired result. However, before we go further we demonstrate why “it’s obvious” never suffices as a proof of correctness, and is usually flat-out wrong.

* Recap on Permutations, Combinations & Factorial Numbers

    * The 3 things mentioned in the title have a lot to do with probability. Factorial numbers simply tell you how many possible ways that a specific number of objects can be arranged. An arrangement can also be called a **permutation**. . And then there are **combinations**.

    * Example: A set of numbers (1, 2, 3) could be arranged as:
        * `1, 2, 3 - 1, 3, 2 - 2, 1, 3 - 2, 3, 1 - 3, 1, 2 - 3, 2, 1`
        * `3 * 2 * 1 = 6` permutations, also known as `3!`
    
    * The number of possible arrangements (or permutations) for a specific number of objects is called the factorial of that number. For example, `1! = 1; 2! = 2; 3! = 6; 4! = 24; 5! = 120; 6! = 720`. (The exclamation point is also the symbol for factorial in mathematics.) By the way `0! = 1` because there's only one possible way to arrange zero objects! It's a bit of a paradox since there's really nothing to arrange!

    * **In permutations, order does matter.**

    * **In combinations, order doesn't matter.**

        * In both of these, the factorial function is used but with a slight difference between them.

        * `P(n,r) = n!/{(n-r)!}`
        * `C(n,r) = n!/{r!(n-r)!}`

        * You can divide the Permutation Function by `r!` to get the Combination Function.
    
    * A **permutation** is a set of `n` objects taken `r` at a time with no repetition in a specific order, while a **combination** is a set of `n` objects taken `r` at a time with no repetition but **rearranging the collected objects does NOT produce something different**.

* The `ExhaustiveScheduling` function proposed on Chapter 1 is an example of combination. Order does not matter for the `N` possible subsets of schedules. On another hand, `OptimalTSP` also proposed on Chapter 1 is an example of permutation, the order of the points does matter do the overall cost of the function.

* Come back page `27`

