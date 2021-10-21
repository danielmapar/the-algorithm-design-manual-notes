# The Algorithm Design Manual by Steven S. Skiena Notes

* This is a compilation of my notes while reading Algorithm Design Manual by Steven S. Skiena.

## Chapter 1 - Introduction to Algorithm Design

* There are three desirable properties for a good algorithm. We seek algorithms
that are correct and efficient, while being easy to implement.

* Correct algorithms usually come with a proof of correctness, which is an explanation of why we know that the algorithm must take every instance of the problem to the desired result. However, before we go further we demonstrate why “it’s obvious” never suffices as a proof of correctness, and is usually flat-out wrong.

### 1.2 Selecting the Right Jobs

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

### 1.3 Reasoning about Correctness

* We need tools to distinguish correct algorithms from incorrect ones, the primary one of which is called a proof.

* A proper mathematical proof consists of several parts. First, there is a clear, precise statement of what you are trying to prove. Second, there is a set of assumptions of things that are taken to be true, and hence can be used as part of the proof. Third, there is a chain of reasoning that takes you from these assumptions to the statement you are trying to prove. Finally, there is a little square ( ) or QED at the bottom to denote that you have finished, representing the Latin phrase for “thus it is demonstrated.”

* The book is not going to emphasize formal proofs of correctness, because they are very difficult to do right and quite misleading when you do them wrong. A proof is indeed a demonstration. Proofs are useful only when they are honest, crisp arguments that explain why an algorithm satisfies a non-trivial correctness property. Correct algorithms require careful exposition, and efforts to show both correctness and not incorrectness.

### 1.3.1 Problems and Properties

* Problem specifications have two parts: (1) the set of allowed input instances, and (2) the required properties of the algorithm’s output. It is impossible to prove the correctness of an algorithm for a fuzzily- stated problem. Put another way, ask the wrong question and you will get the wrong answer.

* Some problem specifications allow too broad a class of input instances. Sup- pose we had allowed film projects in our movie scheduling problem to have gaps in production (e.g. filming in September and November but a hiatus in October). Then the schedule associated with any particular film would consist of a given set of intervals. Our star would be free to take on two interleaving but not overlapping projects (such as the above-mentioned film nested with one filming in August and October). The earliest completion algorithm would not work for such a generalized scheduling problem. Indeed, no efficient algorithm exists for this generalized problem, as we will see in Section 11.3.2.

* **Take-Home Lesson: An important and honorable technique in algorithm de- sign is to narrow the set of allowable instances until there is a correct and efficient algorithm. For example, we can restrict a graph problem from general graphs down to trees, or a geometric problem from two dimensions down to one.**

* There are two common traps when specifying the output requirements of a problem. The first is asking an ill-defined question. Asking for the best route between two places on a map is a silly question, unless you define what best means. Do you mean the shortest route in total distance, or the fastest route, or the one minimizing the number of turns? All of these are liable to be different things.

* The second trap involves creating compound goals. The three route-planning criteria mentioned above are all well-defined goals that lead to correct, efficient optimization algorithms. But you must pick a single criterion. A goal like Find the shortest route from `a` to `b` that doesn’t use more than twice as many turns as necessary is perfectly well defined, but complicated to reason about and solve.

* Finding the right formulation for your problem is an important part of solving it. And studying the definition of all these classic algorithm problems will help you recognize when someone else has thought about similar problems before you.

### 1.3.2 Expressing Algorithms

* Reasoning about an algorithm is impossible without a careful description of the sequence of steps that are to be performed. The three most common forms of algorithmic notation are (1) English, (2) pseudocode, or (3) a real programming language. Pseudocode is perhaps the most mysterious of the bunch, but it is best defined as a programming language that never complains about syntax errors.


* All three methods are useful because there is a natural tradeoff between greater ease of expression and precision. English is the most natural but least precise programming language, while Java and C/C++ are precise but difficult to write and understand. Pseudocode is generally useful because it represents a happy medium.

### 1.3.3 Demonstrating Incorrectness

# PAGE 30