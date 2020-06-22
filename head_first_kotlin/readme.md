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
- The rules for overriding functions
    - The function parameters in the subclass must match those in the superclass.
    - The function return types must be compatible. The overriding function must return either the same type, or a subclass type.
- An overridden function or property stays open until it’s declared final. You don’t have to declare it as open further down the tree. If you want to stop a function or property from being overridden further down the class hierarchy, you can prefix it with final.
- If you mark a property or function as abstract, you must mark the class as abstract too.
- Abstract properties and functions don’t need to be marked as open.
- You MUST implement all abstract properties and functions
    - The first concrete class in the inheritance tree below the abstract superclass must implement all abstract properties and functions.
    - With abstract subclasses, you have a choice: you can either implement the abstract properties and functions, or pass the buck to its subclasses.
- An interface lets you define common behavior OUTSIDE a superclass hierarchy
- Interface functions can be abstract(without a body) or concrete(has a body)
- Interface properties
    - Unlike abstract classes, interfaces can’t have constructors.
    - Unlike properties in abstract classes, properties that are defined in an interface can’t store state, and therefore can’t be initialized. You can, however, return a value for a property by defining a custom getter.
    - Interface properties don’t have backing fields. So you can’t, say, define a custom setter that updates a property’s value. However, you can define a setter so long as it doesn't try and reference the property's back field.
- If a class inherits multiple implementations of the same function or property, the class must provide its own implementation, or specify which version of the function or property it should use.
    - super<A>.myFunction()
- If you override the equals function, you should override the hashCode function as well. If two objects are considered equal, they must have the same hash code value.
- Data classes can’t be declared abstract or open, so you can’t use a data class as a superclass. Data classes can implement interfaces, however, and from Kotlin 1.1, they can also inherit from other classes.
- When the compiler generates implementations for data class functions, such as overriding the equals function and creating a copy function, it only includes the properties defined in the primary constructor. So if you add properties to a data class by defining them in the class body, they won’t be included in any of the generated functions (equals hashCode toString).
- Every data class must have a primary constructor, which must define at least one parameter. Each parameter must be prefixed with val or var.
- Each secondary constructor starts with the constructor keyword, and is followed by the set of parameters used to call it. If the class has a primary constructor, each secondary constructor must delegate to it.
- Function overloading is when you have two or more functions with the same name but with different argument lists. An overloaded function is NOT the same as an overridden function.
- Dos and don’ts for function overloading:
    - The return types can be different.
    - You can’t change ONLY the return type.
- data class best practice: u should use val for all fields, and use copy to make another instance whihc has some different field values.
    - I’ve noticed that you’ve only defined data class properties in the constructor using val. Can I define them using var as well?
        - You can, but we’d strongly encourage you to make your data classes immutable by only creating val properties. Doing so means that once a data object has been created, it can’t be updated, so you don’t have to worry about some other code changing any of its properties. Only having val properties is also a requirement of certain data structures.
-  @JvmOverloads. This tells the compiler to automatically create overloaded versions that can more easily be called from Java.
- Note that in order to annotate the primary constructor with @JvmOverloads, you must also prefix the constructor with the constructor keyword. Most of the time, this keyword is optional.
- w?.eat(), This will only call the Wolf’s eat function when w is not null.
- w?.hunger, If w is not null, the expression returns a reference to the hunger property’s value. If, however, w is null, the value of the entire expression evaluates to null.
- w?.hunger = 6, The code checks the value of w, and if it’s not null, the code assigns a value of 6 to the hunger property. If w is null, however, the code does nothing.
- w?.let {println(it.hunger)}, if w is not null, let’s print its hunger. Once you’ve established that the value is not null, you can refer to it in the body of the let using it.
- w?.hunger ?: -1, if w is not null and its hunger property is not null, return the value of the hunger property, otherwise return -1
- The Elvis operator ?: is a safe version of an if expression. It returns the value on its left if that is not null. Otherwise, it returns the value on its right.
- The !! operator deliberately throws a NullPointerException
    - The not-null assertion operator, or !!, is different to the other methods for dealing with nulls that we’ve looked at over the past few pages. Instead of making sure that your code is safe by handling any null values, the not-null assertion operator deliberately throws a NullPointerException if something turns out to be null.
-

- ch8 The !! operator deliberately throws a NullPointerException
