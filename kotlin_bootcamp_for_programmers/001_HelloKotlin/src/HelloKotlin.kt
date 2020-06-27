// 1. Select Tools > Kotlin > Kotlin REPL to open the REPL.

// 2. Type or paste the code below into the REPL.
// fun printHello() {
//     println("Hello World")
// }
//
// printHello()

// 3. Press Control+Enter (Command+Enter on a Mac). You should see Hello World

fun main(args: Array<String>) {
    println("Hello, world!")

    val decorations = listOf ("rock", "pagoda", "plastic plant", "alligator", "flowerpot")

    // eager, creates a new list
    val eager = decorations.filter { it [0] == 'p' }
    println("eager: $eager")

    // lazy, will wait until asked to evaluate
    val filtered = decorations.asSequence().filter { it[0] == 'p' }
    println("filtered: $filtered")
    // force evaluation of the lazy list
    val newList = filtered.toList()
    println("new list: $newList")
}