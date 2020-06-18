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
- Calling the superclass constructor is mandatory: if the superclass has a primary constructor, then you must call it in the subclass header or your code won’t compile.
- If you define a property in the superclass using val, you must override it in the subclass if you want to assign a different value to it.
- If a superclass property has been defined using var, you don’t need to override it in order to assign a new value to it, as var variables can be reused for other values. You can instead assign it a new value in the subclass’s initializer block.
- Overriding properties lets you do more than assign default values
    - You can override a property’s getter and setter.
    - You can override a val property in the superclass with a var property in the subclass.
    - You can override a property’s type with one of the superclass version’s subtypes. (When you override a property, its type must match the type of the superclass version of the property, or be one of its subtypes.)

- ch5 How to override functions