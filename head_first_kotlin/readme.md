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
    - super\<A\>.myFunction()
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
- safe cast： val wolf = r as? Wolf, This casts r as a Wolf if r holds an object of that type, and returns null if it doesn’t. as? lets you perform a safe explicit cast. If the cast fails, it returns null.
- try and throw are both expressions
    - How to use try as an expression, The return value of a try is either the last expression in the try, or the last expression in the catch (the finally block, if there, doesn’t affect the return value).
    - How to use throw as an expression, throw is also an expression, so you can, for example, use it with the Elvis operator using code like this:
        ```
        val h = w?.hunger ?: throw AnimalException()
        ```
        - If w and hunger are not null, the above code assigns the value of w’s hunger property to a new variable named h. If, however, w or hunger are null, it throws an AnimalException.
        - throw has a return type of Nothing. This is a special type that has no values, so a variable of type Nothing? can only hold a null value.
- You can also use Nothing to denote code locations that can never be reached. You can, say, use it as the return type of a function that never returns: The compiler knows that the code stops execution after fail() is called.
    ```
    fun fail(): Nothing {
        throw BadException()
    }
    ```
- be careful of the differences between mutableList.sort() and muableList.sorted()
    - .reverse() and .reversed()
    - .shuffle() and .shuffled()
- How set determine a object is duplicate or not
    - compare hashCode with the hashCodes of the objects in the set. if the set does not have an object with the same hashCode, the new object is not dumplicate. if the set has an object with the same hashCode, then next step.
    - uses === compare the new object with objects in the set. if the set has an object === the new object, the new object is dumplicate, else next step
    - uses == compare the new object with objects in the set. if then set has an object == the new object, the new object is dumplicate, else it is not duplicate.
- Rules for overriding hashCode and equals
    - If two objects are equal, they must have matching hash codes.
    - If two objects are equal, calling equals on either object must return true. In other words, if (a.equals(b)) then (b.equals(a)).
    - If two objects have the same hash code value, they are not required to be equal. But if they’re equal, they must have the same hash code value.
    - So, if you override equals, you must override hashCode.
    - The default behavior of the hashCode function is to generate a unique integer for each object. So if you don’t override hashCode in a non-data class, no two objects of that type can ever be considered equal.
    - The default behavior of the equals function is to do a === comparison, which tests whether the two references refer to a single object. So if you don’t override equals in a non-data class, no two objects can ever be considered equal since references to two different objects will always contain a different bit pattern.
- Map.get() returns a null value if the specified key doesn't exist, whereas Map.getValue() throws an exception.
- restrict T to a specific supertype
    - class Contest\<T: Pet\> {}
- Use out to make a generic type covariant
    - If a generic type is covariant, it means that you can use a subtype in place of a supertype. When we prefix a generic type with out, we say that the generic type is covariant. In other words, it means that a subtype can be used in place of a supertype.
    - If you want to be able to use a generic subtype object in a place of a generic supertype, you can do so by prefixing the generic type with out. In our example, we want to be able to assign a Retailer\<Cat\> (a subtype) to a Retailer\<Pet\> (a supertype) so we’ll prefix the generic type T in the Retailer interface with out like so:
        ```
        interface Retailer<out T>{}
        ```
    - In general, a class or interface generic type may be prefixed with out if the class has functions that use it as a return type, or if the class has val properties of that type. You can’t, however, use out if the class has function parameters or var properties of that generic type. 
    - Another way of thinking about this is that a generic type that’s prefixed with out can only be used in an “out” position, such as a function return type. It can’t, however, be used in an “in” position, so a function can’t receive a covariant type as a parameter value.
- Use in to make a generic type contravariant
    - If a generic type is contravariant, it means that you can use a supertype in place of a subtype. This is the opposite of covariance.
    - While out allows you to use a generic subtype in place of a supertype (like assigning a Retailer\<Cat\> to a Retailer\<Pet\>), in lets you use a generic supertype in place of a subtype.
    - The following code show how to use a Vet\<Pet\> in place of a Vet\<Cat\>
        ```
        class Vet<in T : Pet>{}
        class Contest<T : Pet>(var vet: Vet<T>) {}
        val catContest = Contest<Cat>(Vet<Pet>())
        ```
    - In general, a class or interface generic type may be prefixed with in if the class has functions that use it as a parameter type. You can’t, however, use in if any of the class functions use it as a return type, or if that type is used by any properties, irrespective of whether they’re defined using val or var.
    - In other words, a generic type that’s prefixed with “in” can only be used in an “in” position, such as a function parameter value. It can’t be used in “out” positions.
- A generic type can be locally contravariant
    - Should a Vet\<Cat\> always accept a Vet\<Pet\>?
        ```
        val catVet : Vet<Cat> = Vet<Pet>() // ??
        ```
    - Remove the in prefix from the Vet class and add it to the COntest constructor instead. This means that T is conTravariant, but only in the Contest constructor.
    ```
        class Vet<T : Pet>{}
        class Contest<T : Pet>(var vet: Vet<in T>) {}
        val catContest = Contest<Cat>(Vet<Pet>())
    ```
- When a generic type has no in or out prefix, we say that the type is invariant. An invariant type can only accept references of that specific type.
- Kotlin’s approach to generics seems different to Java’s. Is that right?
    - Yes, it is. With Java, generic types are always invariant, but you can use wildcards to get around some of the problems this creates. Kotlin, however, gives you far greater control as you can make generic types covariant, contravariant, or leave them as invariant.
- lambda: You can replace a single parameter with it
    - If you have a lambda which has a single parameter, and the compiler can infer its type, you can omit the parameter, and refer to it in the lambda body using the keyword it.
- Use Unite to say a lambda has no return value, ypu can also use Unit to explicitly spedify that you don't want to access ther result of a lambda's calculation.
    - val myLambda: () -> Unit = {println("Hi!")}
    - val myLambda: (Int, Int) -> Unit = {x, y -> x +y }
- higher-order function: A function that uses a lambda as a parameter or return value is known as a higher-order function.
- You can move the lambda OUTSIDE the ()’s or remove the ()’s entirely
    - If the final parameter of a function you want to call is a lambda, you can move the lambda argument outside the function call’s parentheses. 
    - If you have a function that has just one parameter, and that parameter is a lambda, you can omit the parentheses entirely when you call the function.
- When you have a lambda whose body has multiple lines, the last evaluated expression is used as the lambda’s return value.
- Use typealias to provide a different name for an existing type
    - typealias DoubleConversion = (Double) -> Double
    - typealias DuckArray = Array\<Duck\>
- Closure, Captured :The variables defined outside the lambda which the lambda can access are sometimes referred to as the lambda’s closure. In clever words, we say that the lambda can access its closure. And we say that the lambda’s closure has captured the variable.
- Closure means that a lambda can access any local variables that it captures.
- List and Set are both types of Iterable, while Map isn’t. This is why some of higher-order functions can't be used directly with map.
- The reduce function. This function works in a similar way to fold, except that you don’t have to specify the initial value. It automatically uses the first item in the collection as the initial value.
- The delay function pauses the current COROUTINE
    - A better approach in this situation is to use the coroutines delay function instead. This has a similar effect to Thread.sleep, except that instead of pausing the current thread, it pauses the current coroutine.  It suspends the coroutine for a specified length of time and this allows other code on the same thread to run instead. 
- The delay function may be used in these two situations:
    - From inside a coroutine.
    - From inside a function that the compiler knows may pause, or suspend. When you call a suspendable function (such as delay) from another function, that function must be marked with suspend.
- Visibility modifiers
    - Kotlin has four visibility modifiers: public, private, protected and internal.
    - Visibility modifiers and top level code
    
        |  Modifier: |  What it does: |
        |:-----------|:---|
        | public     | 	Makes the declaration visible everywhere. This is applied by default, so it can be omitted.  |
        | private    | Makes the declaration visible to code inside its source file, but invisible elsewhere.  |
        | protected  | Note that protected isn’t available for declarations at the top level of a source file or package.  |
        | internal   | Makes the declaration visible inside the same module, but invisible elsewhere. A module is a set of Kotlin files that are compiled together, such as an IntelliJ IDEA module.  |
    - Remember: if you don’t specify a package, the code is automatically added to a nameless package by default.
    - Visibility modifiers and classes/interfaces

        |  Modifier: |  What it does: |
        |:-----------|:---|
        | public     | Makes the member visible everywhere that the class is visible. This is applied by default, so it can be omitted.  |
        | private    | Makes the member visible inside the class, and invisible elsewhere.  |
        | protected  | Makes the member visible inside the class, and any of its subclasses.  |
        | internal   | Makes the member visible to anything in the module that can see the class.  |
- Enum classes
- Sealed Classes
    - Sealed classes are used for representing restricted class hierarchies, when a value can have one of the types from a limited set, but cannot have any other type.
    - To declare a sealed class, you put the sealed modifier before the name of the class. A sealed class can have subclasses, but all of them must be declared in the same file as the sealed class itself.
- Nested and inner classes
    - A nested class is a class that’s defined inside another class. 
        - A nested class in Kotlin is like a static nested class in Java.
        - If you want a nested class to be able to access the properties and functions defined by its outer class, you can do so by making it an inner class.
        - The key thing is that an inner class instance is always tied to a specific instance of the outer class, so you can’t create an Inner object without first creating an Outer object.
- Object declarations
    - If you’re familiar with design patterns, an object declaration is the Kotlin equivalent of a Singleton.
    - An object declaration defines a class declaration and creates an instance of it in a single statement. 
        ```
        object DuckManager {
            fun herdDucks(){}
        }

        // using code like this
        DuckManager.herdDucks()

        ```
- Class objects and companion objects
    - Class objects: Add an object declaration to a class to create a single instance of that type which belongs to the class. When you add an object declaration to a class, it creates an object that belongs to that class. One instance of the object is created per class, and it’s shared by all instances of that class.
        ```
        class Duck {
            object DuckFactory {
                fun create() : Duck = Duck()
            }
        }

        // using code like this
        val newDuck = Duck.DuckFactory.create()
        ```
    - Companion objects: A companion object can be used as the Kotlin equivalent to static methods in Java. One object per class can be marked as a companion object using the companion prefix. A companion object is like a class object, except that you can omit the object’s name. If you prefix an object declartion with companion, you no longer need to provide an object name. You can, however, include the name if you want to.
    - Any functions you add to a companion object are shared by all class instances.
    - When you create a companion object, you access it by simply referring to the class name.
        ```
        class Duck {
            companion object DuckFactory {  // you can omit the object name： DuckFactory
                fun create() : Duck = Duck()
            }
        }

        // using code like this
        val newDuck = Duck.create()
        ```
- Object expressions
    - An object expression is an expression that creates an anonymous object on the fly with no predefined type.
    - Object expressions are mainly used as the equivalent of anonymous inner classes in Java.
        ```
        val startingPoint = object {
            val x = 0
            val y = 0
        }

        val myMouseAdapter = object: MouseAdapter() {
            override fun mouseClicked(e : MouseEvent) {
                // do something when the mouse is clicked
            }
            
            override fun mouseReleased(e : MouseEvent) {
                // do something when the mouse is released
            }
        }
        ```
- Extensions
    - Extensions let you add new functions and properties to an existing type without you having to create a whole new subtype.
    - There are also Kotlin extension libraries you can use to make your coding life easier, such as Anko and Android KTX for Android app development.
        ```
        // Defines a function named toDollar(), which extends Double
        fun Double.toDollar() : String {
            return "$$this" // return the current value, prefixed with $
        }
        // create extension properties
        val String.halfLength
            get() = length / 2.0
        ```
- Design patterns: Object declarations, Singleton, Extensions, Decorator, Delegation.
    - Object declarations provide a way of implementing the Singleton pattern, as each declaration creates a single instance of that object. Extensions may be used in place of the Decorator pattern as they allow you to extend the behavior of classes and objects. And if you’re interested in using the Delegation pattern as an alternative to inheritance, you can find out more [here](https://kotlinlang.org/docs/reference/delegation.html)
- Delegation
    - The by-clause in the supertype list for Derived indicates that b will be stored internally in objects of Derived and the compiler will generate all the methods of Base that forward to b.
        ```
        interface Base {
            fun print()
        }

        class BaseImpl(val x: Int) : Base {
            override fun print() { print(x) }
        }

        class Derived(b: Base) : Base by b

        fun main() {
            val b = BaseImpl(10)
            Derived(b).print()
        }
        ```
- Delegated Properties [docs](https://kotlinlang.org/docs/reference/delegated-properties.html)
    - lazy properties: the value gets computed only upon first access;
    - observable properties: listeners get notified about changes to this property;
    - storing properties in a map, instead of a separate field for each property.
        ```
        import kotlin.reflect.KProperty

        class Delegate {
            operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
                return "$thisRef, thank you for delegating '${property.name}' to me!"
            }
        
            operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
                println("$value has been assigned to '${property.name}' in $thisRef.")
            }
        }

        class Example {
            var p: String by Delegate()
        }

        val e = Example()
        // This prints: NEW has been assigned to ‘p’ in Example@33a17727.
        println(e.p)
        // This prints: NEW has been assigned to ‘p’ in Example@33a17727.
        e.p = "NEW"
        ```
    - Note that since Kotlin 1.1 you can declare a delegated property inside a function or code block, it shouldn't necessarily be a member of a class.
    - Standard Delegates
        - Lazy: the first call to get() executes the lambda passed to lazy() and remembers the result, subsequent calls to get() simply return the remembered result.
        - Observable: The handler is called every time we assign to the property (after the assignment has been performed). 
            - If you want to intercept assignments and "veto" them, use vetoable() instead of observable(). The handler passed to the vetoable is called before the assignment of a new property value has been performed.
- return, break, continue with labels


- Appendix A : coroutines
