- A function uses parameters. A caller passes it arguments.
- Functions with no return value
    - Declaring a return type of Unit means that the function returns no value. or uou can omit the return type from the function declaration.
- You can’t assign a new value to any of a function’s parameter variables. Behind the scenes, the parameter variables are created as local val variables that can’t be reused for other values.
- for
    - for (x in 1..10) // [1,10]
    - for (x in 1 until 10) // [1,10)
    - for (x in 10 downTo 1) // [10, 1]
    - for (x in 1..10 step 2)
- Initializer blocks are executed when the object is initialized, immediately after the constructor is called, and they’re prefixed with the init keyword. Your class can have multiple initializer blocks. Each one runs in the order in which it appears in the class body, interleaved with any property initializers.
    ``` Kotlin
    class Dog(val name: String, var weight: Int, breed_param: String) {
    // The above properties defined in the constructor are created first

    init {
        println("This initializer block runs next.")
    }

    // These following properties are created after the first initializer block has finished.
    var activites = arrayOf("Walks")
    var breed = breed_param.toUpperCase()

    init {
        println("The second initializer block runs after the properties above have been created")
    }
}
    ```
- Using field in your getters and setters in place of the property name is important, as it stops you getting stuck in an endless loop. 
- 

- ch5