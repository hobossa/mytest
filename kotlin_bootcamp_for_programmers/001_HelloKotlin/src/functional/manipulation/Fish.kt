package functional.manipulation

data class Fish(var name: String)

fun myWith(name: String, block: String.() -> Unit) {
    name.block()
}

fun fishExamples() {
    val fish = Fish("splashy")  // all lowercase
    with(fish.name) {
        // println(this.capitalize())    // the this is implicit and not needed.
        println(capitalize())
    }
    myWith(fish.name) {
        println(capitalize())
    }
}

fun runExample() {
    val runnable = object : Runnable {
        override fun run() {
            println("I'm a Runnable")
        }
    }
    JavaRun.runNow(runnable)

    JavaRun.runNow {
        println("Passing a lambda as a Runnable")
    }
}

fun main() {
    fishExamples()

    runExample()
}