package functional.manipulation

import kotlin.reflect.full.*

//- Annotations are a way of attaching metadata to code, and are not
// something specific to Kotlin. The annotations are read by the compiler
// and used to generate code or logic.
//- Annotations go right before the thing that is annotated, and most
// things can be annotated—classes, functions, methods, and even control
// structures. Some annotations can take arguments.
//- You can also create your own annotations, but this is mostly useful
// if you are writing a library that needs particular information about
// classes at runtime, that is [reflection](https://kotlinlang.org/docs/reference/reflection.html#reflection).
//- Annotations are really powerful for creating libraries that inspect
// things both at runtime and sometimes at compile time. However, typical
// application code just uses annotations provided by frameworks.

annotation class ImAPlant

@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class OnGet

@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class OnSet

@ImAPlant
class Plant {
    fun trim() {}
    fun fertilize() {}

    @get:OnGet
    val isGrowing: Boolean = true

    @set:OnSet
    var needsFood: Boolean = false
}

fun testAnnotations() {
    val classObj = Plant::class
    for (m in classObj.declaredMemberFunctions) {
        println(m.name)
    }

    val plantObject = Plant::class
    for (a in plantObject.annotations) {
        println(a.annotationClass.simpleName)
    }
    val myAnnotationObject = plantObject.findAnnotation<ImAPlant>()
    println(myAnnotationObject)
}

//////

fun labels() {
    outerLoop@ for (i in 1..100) {
        print("$i ")
        for (j in 1..100) {
            if (i > 10) break@outerLoop  // breaks to outer loop
        }
    }
}

fun main() {
    testAnnotations()
    labels()
}

