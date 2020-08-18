- page 125
----
- class visibility modifiers
    - public: The instantiation can be done from anywhere inside and outside your program. This is the default.
    - private: The instantiation can be done only from inside the very same class or object. This makes sense if you use secondary constructors.
    - protected: The setting is the same as private, but the instantiation can be done from subclasses as well. Subclasses belong to inheritance, which is discussed in Chapter 3.
    - internal: The instantiation can be done from anywhere inside the module. In Kotlin, a module is a set of files compiled together. You use this modifier if you don’t want other programs (from other projects) to  ccess a constructor, but you otherwise want the constructor to be freely accessible from other classes or objects inside your program.

- object is something similar to singleton in C++

- companion object is similar to static member in C++

- A Structured Project
    - The root for the activity class.
    - A package random for the random numbers. We put the interface right into the package, and the two implementations into a subpackage impl.
    - A gui package for the Console view element.
    - A model package for the user data class. Developers often use the term model to refer to data structures and data relations.
    - A common package for the Constants singleton object.

- check whether a lateinit var has been initialized or not by using ::name.isInitialized
    ```
    lateinit var name: Type
    ::name.isInitialized
    ```

- In Kotlin, function arguments cannot be reassigned inside the function body. This is not a disadvantage, as reassigning function parameters inside the function is considered bad practice anyway.

- Functions modifiers : The optional [modifiers] you can prepend to the function declaration for finetuning a function's behavior are as follows：
    ```
    // Fuctions not returning values
    [modifiers]
    fun functionName([parameters]){
        [Function Body]
    }
    // Functions returning values
    [modifiers]
    fun functionName([parameters]): ReturnType {
        [Function Body]
        return [expression]
    }
    // replace the body by a single expressiong if this is possible
    // The ReturnType can be omitted here if the type the expression yields to is the anticipated function return type. Kotlin can therefore infer from.
    [modifiers]
    fun functionName([parameters]): ReturnType = [expression]
    ```
    - private, protected, internal, and public: These are visibility modifiers.
    - open: Use this to mark a function in a class as overridable by subclasses.
    - override: Use this to mark a function in a class as overrideing a function from an interface or from a superclass.
    - final override: This is the same as override, but indicates that futher overwirting by subclasses is prohibited.
    - abstract: Abstract functions cannot have a body, and classes with abstract functions cannot be instantiated. You must override such functions in a subclass to make them concrete (which means "unabstract" them).

- Kotlin assumes a special void type that it calls Unit. If you omit: ReturnType and the function does not return a value, Unit is assumed. If, for whatever reason, you can write fun name(...) : Unit {...} to improve the readability.

- Abstract classes are something between interfaces and normal classes: They provide implementations for some functions and leave other functions abstract (unimplemented) to allow for some variety.