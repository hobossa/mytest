# [Kotlin Bootcamp for Programmers](https://developer.android.com/courses/kotlin-bootcamp/overview)

- Compare eager and lazy filters
    - By default, filter is eager, and each time you use the filter, a list is created.
    - To make the filter lazy, you can use a Sequence, which is a collection that can only look at one item at a time, starting at the beginning, and going to the end. Conveniently, this is exactly the API that a lazy filter needs.
        ```
        val decorations = listOf ("rock", "pagoda", "plastic plant", "alligator", "flowerpot")

        // eager, creates a new list
        val eager = decorations.filter { it [0] == 'p' }
        println("eager: $eager")

        // lazy, will wait until asked to evaluate
        val filtered = decorations.asSequence().filter { it[0] == 'p' }
        println("filtered: $filtered")
        ```
- Compare abstract classes and interfaces
    - Neither an abstract class nor an interface can be instantiated on its own, which means you cannot create objects of those types directly.
    - Abstract classes have constructors.
    - Interfaces can't have any constructor logic or store any state.
- When to use abstract classes versus interfaces
    - Use an interface if you have a lot of methods and one or two default implementations, for example as in AquariumAction below.
    - Use an abstract class any time you can't complete a class
- Use interface delegation
    - Interface delegation is an advanced technique where the methods of an interface are implemented by a helper (or delegate) object, which is then used by a class. This technique can be useful when you use an interface in a series of unrelated classes: you add the needed interface functionality to a separate helper class, and each of the classes uses an instance of the helper class to implement the functionality.


- Lesson 5.1: Extensions