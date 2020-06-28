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
- So what's the difference between const val and val?
    - The value for const val is determined at compile time, where as the value for val is determined during program execution, which means, val can be assigned by a function at run time.
        ```
        val value1 = complexFunctionCall() // OK
        const val CONSTANT1 = complexFunctionCall() // NOT ok
        ```
- The basic difference between companion objects and regular objects is:
    - Companion objects are initialized from the static constructor of the containing class, that is, they are created when the object is created.
    - Regular objects are initialized lazily on the first access to that object; that is, when they are first used.
- Extension functions are resolved statically, at compile time, based on the type of the variable.
- To do an is check, you need to tell Kotlin that the type is reified, or real, and can be used in the function. To do that, put inline in front of the fun keyword, and reified in in front of the generic type R.
    ```
    inline fun <reified R: WaterSupply> hasWaterSupplyOfType() = waterSupply is R
    ```
- You can use reified types for regular functions and extension functions, too.
    ```
    inline fun <reified T: WaterSupply> WaterSupply.isOfType() = this is T
    ```
- To use a [star-projection](https://kotlinlang.org/docs/reference/generics.html#star-projections), put <*> after Aquarium. Move hasWaterSupplyOfType() to be an extension function, because it isn't really part of the core API of Aquarium.
    ```
    inline fun <reified R: WaterSupply> Aquarium<*>.hasWaterSupplyOfType() = waterSupply is R
    ```
- Concept: Reified types and type erasure
    - In the earlier example, you had to mark the generic type as reified and make the function inline, because Kotlin needs to know about them at runtime, not just compile time.
    - All generic types are only used at compile time by Kotlin. This lets the compiler make sure that you're doing everything safely. By runtime all the generic types are erased, hence the earlier error message about checking an erased type.
    - It turns out the compiler can create correct code without keeping the generic types until runtime. But it does mean that sometimes you do something, like is checks on generic types, that the compiler can't support. That's why Kotlin added reified, or real, types.
    - You can read more about [reified types and type erasure](https://kotlinlang.org/docs/reference/typecasts.html#type-erasure-and-generic-type-checks) in the Kotlin documentation.
- annotations
    - Annotations are a way of attaching metadata to code, and are not something specific to Kotlin. The annotations are read by the compiler and used to generate code or logic.
    - Annotations go right before the thing that is annotated, and most things can be annotated—classes, functions, methods, and even control structures. Some annotations can take arguments.
    - You can also create your own annotations, but this is mostly useful if you are writing a library that needs particular information about classes at runtime, that is [reflection](https://kotlinlang.org/docs/reference/reflection.html#reflection).
    - Annotations are really powerful for creating libraries that inspect things both at runtime and sometimes at compile time. However, typical application code just uses annotations provided by frameworks.
- run(), apply(), let()
    - The run() function is an extension that works with all types. It takes one lambda as its argument and returns the result of executing the lambda.
    - The apply() function is similar to run(), but it returns the changed object it was applied to instead of the result of the lambda.
    - The let() function is similar to apply(), but it returns a copy of the object with the changes.
        ```
        data class Fish (var name: String)

        fish.run {
            name
        }

        val fish2 = Fish(name = "splashy").apply {
            name = "sharky"
        }
        println(fish2.name) // sharky

        println(fish.let { it.name.capitalize()}
        .let{it + "fish"}
        .let{it.length}
        .let{it + 31})  // 42
        pirntln(fish)   // Fish(name=splashy)
        ```
- Single Abstract Methods (SAM)
    - Single Abstract Method just means an interface with one method on it. They are very common when using APIs written in the Java programming language, so there is an acronym for it, SAM. Some examples are Runnable, which has a single abstract method, run(), and Callable, which has a single abstract method, call().



- Lesson 5.1: Extensions