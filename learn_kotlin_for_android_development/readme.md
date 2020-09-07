- page 425
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
    ```Kotlin
    lateinit var name: Type
    ::name.isInitialized
    ```

- In Kotlin, function arguments cannot be reassigned inside the function body. This is not a disadvantage, as reassigning function parameters inside the function is considered bad practice anyway.

- Functions modifiers : The optional [modifiers] you can prepend to the function declaration for finetuning a function's behavior are as follows：
    ```Kotlin
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

- Visibility
    |Visibility|Asset|Description|
    |:--|:--|:--|
    | public | Function or property | (Defalut) The function or property is visible from everywhere inside and outside the structure unit.|
    |private| Function or property | The function or property is visible only form inside the same structure unit. |
    | protected | Function or property | The function or property is visible from inside the same structure unit, and from inside any direct subclass. Subclass get declared via class TheSubClass-Name : TheClassName { ... } and they inherit all the public and protected properties and functions of the class from which they inherit. |
    | internal | Function or property | FUunctions and properties are public only for structure units from the same program. For programs form other compilations, especially for programs from others you include in your software, internal gets treated like private. |
    | public | Class, singleton object, or companion object | (Default) The structure unit is visible from everywhere inside and outside the program. |
    | private | Class, singleton object, or companion object | The structure unit is visible only form inside the same file. For inner classes the structure unit is only visible form an enclosing structure. For example <pre lang=kotlin>`    class A {`<br>`        private class B {`<br>`            ...`<br>`        }`<br>`        fun function() {`<br>`            val b = B()`<br>`        }`<br>`    }`</pre> |
    | protected | Class, singleton object, or companion object | The structure unit is visible only form an enclosing structure unit or a subclass of it. For example <pre lang=kotlin>`    class A {`<br>`        protected class B {`<br>`            ...`<br>`        }`<br>`        fun function() {`<br>`            val b = B()`<br>`        }`<br>`    }`<br>`    class AA : A {`<br>`    // subclass of A`<br>`        fun function() {`<br>`            val b = B()`<br>`        }`<br>`     } `</pre> |
    ||||

- In Kotlin, any class automatically and implicitly inherits from the built-in class Any.

- this and this@ClassName. an extension of this that allows us to get the instance of the enclosing class.
    ```Kotlin
    interface X {
        fun doSomething()
    }

    class A {
        fun meth() {
            val x = object : x {
                override doSomething() {
                    println(this)       // this refers to the anonymous class.
                    println(this@A)     // this@A refers to the instance of class A.
                }
            }
        }
    }
    ```

- we cannot really extension properties, but extension property accessors, because extension properties are not allowed to actually create real data fields. For example:
    ```Kotlin
    val String.l get() = this.length
    ```

- Using Tail Recursion to prevent callstack overflow. convert a normal function to a tail recursion function
    ```Kotlin
    // normal function
    fun factorial(n: Int) {
        return if(n--1) n else n * factorial(n-1)
    }
    // tail recursion function
    tailrec fun factorial(n: Int) {
        return if(n--1) n else n * factorial(n-1)
    }
    ```

- Infix Operators : operand1 OPERATOR operand2
    ```Kotlin
    infix operator
    fun SomeClass1.oper(param:SomeClass2): ResultType = {
        ...
        return [result_expression]
    }
    [expression1] oper [expression2]

    ```

- operator overloading
    - find the right textual representation of the operator. eg. minus for -
    - overide it
    ```Kotlin
    data class Point(val x:Double, val y:Double) {
        operator fun minus(p2:Point) = Vector(p2.x-thixs.x, p2.y-this.y)
    }
    ```

- Delegation is similar to inheritance; it starts the same way: class TheClass : SomeInterface... . The difference is where the implementing code resides: For delegation it is presumed that an object is at hand that already implements the interface and The Class primarily delegates the work to this object.
    ```Kotlin
    interface TheInterface {
        fun someMehtod(i:Int): Int
    }
    class Implementor0 : SomeInterface {
        override fun someMethod(i:Int) : Int = i*2
    }

    class Implementor: TheInterface {
        val delegate = Implementor0()
        override fun someMethod(i:Int): Int = delegate(i)   // delegate the work
        // it counld also add some extra work, as in
        // override fun someMethod(i:Int):Int = delegate(i-1) + 1
    }

    // Kotlin has a concise notation for the delegation basic pattern
    class Implementor : TheInterface by Implementor0()
    // or
    val impl0 = Implementor0()
    class Implementor : TheInterface by impl0

    // you still can change any function by overriding it.
    class Implementor : TheInterface by Implentor0() {
        override fun domeMethod(i:Int):Int = i * 42
    }

    // or If you explicitly need the delegate object, you must add it to the constructor as in
    class Implementor(val delegate:TheInterface) : TheInterface by impl0 {
        override fun someMethod(i:Int):Int = delegate.someMethod(i-1) + 1
    }
    val b = Implementor0()
    val instance = Implementor(b)
    ```

- Functions with other functions as parameters are called higher order functioins.

- Scoping Functions: appply, let, alse, run, and with (LET us ALSO RUN WITH APPLY.)
    - apply
        ```Kotlin
        // apply automatically returns the object instance.
        // object.apply {
        //     ...
        // }
        class A {
            fun goA() {...}
            ...
            val x = SomeClass().apply {
                this.x = ...    // -> SomeClass.x
                x = ...         // -> SomeClass.x
                this@A.goA()    // -> A.goA()
            }
        }
        ```
    - let, the let scoping funtion frequently gets used to transform an object into a different object.
        ```Kotlin
        // the last line must contain the expression that let{} is supposed to return.
        object.let { o ->
            [statements]    // do s.th. with 'o'
            [value]
        }
        // // You can also omit the o ->, do s.th with 'it'
        object.let {
            [statements]    // do s.th. with 'it'
            [value]
        }

        val s = "Hello World"
        val s2 = s.let{it +"\n"}    // s.let {string -> string + "\n}
        ```
    - with， The with scoping function is the brother of apply{...}. The difference is it just gets the object or value to convert to a receiver as a parameter:
        ```Kotlin
        // The with function is frequently used to avoid repeatedly writing the object ot act on.
        val o = ... // some value
        with(o) {
            // o is now "this"
            ...
        }

        with(object) {
            f1(37)
            f1(12)
            fx("hello")
        }
        // instead of
        object.f1(37)
        object.f1(12)
        object.fx("hello")
        ```
    - also, The also scoping function is realted to the apply{...}, but does not redefine this. Instead it provides the object or value in front of also as a parameter to the lambda function parameter:
        ```Kotlin
        object.also { obj ->
            // 'obj' is object
            ...
        }
        object.also {
            // 'it' is object
            ...
        }
        ```
    - run, The run scoping function is similar to the apply{...} function. However, it does not return the receiver object, but instead return the value of the last statement:
        ```Kotlin
        val s = "hello"
        val x = s.run {
            // 'this' is s
            ...
            [value]
        }
        // You can see run{...} as a general-purpose "do something with an object" brackete. One prominent use case, though, consists of acting on an object only if it is not null.
        var v: String? = ...
        v?.run {
            ...
        }
        ```

- The takeIf() function returns the receiver(someInstance) if the boolean expression evaluates to ture; optherwise it returns null. Here inside the boolean expression you can use it to refer to someInstance.
    ```Kotlin
    someInstance.takeIf{ [boolean_expression] }?.run {
        ...
    }
    ```

- Exceptions in Expressions. (Be careful not to abuse try-catch blocks, only use it for unanticipated problems)
    - one interesting feature of Kotlin is that you can use try-catch blocks and throw satements in expressions. The outcome of try-catch block is the value of the last line inside the try{} or the catch{} block.
        ```Kotlin
        val x = try{ arr[ind] }
            catch(e: ArrayIndexOutofBoundsException) { -1 }
        ```
    - A throw someException has a value, too. It is of type Nothing and in the Kotlin type hierarchy is a subclass of everything.
        ```Kotlin
        val v = map[someKey] ?: throw Exception("no such key in the map")
        ```

- in place and in a functional style.
    - Sorting can happen in place, which means the array or collection you want to have sorted gets altered, or in a functional style, which means the result of the operation is ther sorted array or collection and the original data container stays untouched. Sorting in place will be the faster choice, but bears the risk that other program parts get corrupted if they hold a reference to the original array or collection. Functional sorting can improve program stability, but you can expect some performance penalty, so choose wisely.

- Grouping, Folding, Reducing, and Zipping. These are advanced operations on arryas and collections like list and sets.
    - Grouping, which is about reorganizing your data in such a way that groups of data are gathered according to some key deduced from the data or imposed on the data.
        ```Kotlin
        // groupBy(keysSelector : (T) -> K)： Map<K, List<T>>
        data class Car(val id:Int, val make:Int, val name:String, val vin:String)
        val cars = listOf(
            Car(1, 1994, "Sirus",       "WXX 130 007-1J-582943"),
            Car(2, 1997, "Sirus",       "WXX 130 008-1J-582957"),
            Car(3, 2010, "Casto 4.0",   "WXQ 456 088-4K-005614"),
            Car(4, 2010, "Babo MX",     "WYY 518 004-55-171598"),
            Car(5, 1994, "Casto 4.0",   "WXQ 456 005-4K-005658"),
            Car(6, 2011, "Quasto",      "WA0 100 036-00-012378")
        )
        // val groupedByMake = cars.groupBy { car -> car.make }
        val groupedByMake = cars.groupBy(Car::make)
        val group2010:List<Car>? = groupedByMake[2010]
        println(group2010) // [Car(id=3, make=2010, name=Casto 4.0, vin=WXQ 456 088-4K-005614), Car(id=4, make=2010, name=Babo MX, vin=WYY 518 004-55-171598)]
        ```
    - Folding, which is about letting an object scan through all elements of an array or collection(set or list) and update itself each iteration. Think, for example, of a list of invoices and summing up all money amounts. This is nothing spectacular; on could write
        ```Kotlin
        val someObject = ...
        list.forEach { e ->
            // update someObj using elem
            ...
        }
        ```
        However, there is an intrinsic danger that code could initialize the object before the loop starts doing lots of weird things, so there is a function that performs the task using one statement. Actually, it is a set of functions.
        - fold(initial: R, operation: (acc: R, T) -> R): R . The function takes as parameters the object that is going to be updated each loop iteration and a function that performs the updating. This updater takers as parameters the actual version of the gathering object and the current loop element. This returns the gathering object with all data container elements applied. In most practical cases the first parameter is probably a newly constructed objects. as in list.fold(Gatherer(), ...).
        - foldRight(initial: R, operation: (T, acc: R) -> R): R. This is similar to fold(), but it iterates through the array or collection in reverse order. To express this backward scanning, the parameter order of the inner function gets reversed, too.
        - foldIndexed(initial: R, operation: (index: Int, acc:R, T) -> R): R.
        - foldRightIndexed(initial: R, operation: (index: Int, T, acc: R) -> R): R
        ```Kotlin
        val con = listOf(1, 2, 3, 4)
        val s = con.fold(0, {acc, e -> acc + e})
        println(s)  // 10
        ```
    - Reducing, which is the little brother of folding. The gatherer is not specified explicitly and instead the first element of the array or collection(a set or a list) is used. The folding operation or more precisely reduction operation then understandably starts with the second element of the data. Reduction functions are listed here.
        - reduce(operation: (acc: R, T) -> R): R
        - reduceRight(operation: (T, acc: R) -> R): R
        - reduceIndexed(operation: (index: Int, acc: R, T) -> R): R
        - reduceRightIndexed(operation: (index: Int, T, acc: R) -> R): R
        ```Kotlin
        val l : List<Long> = LongRange(1, 20).toList()
        val s = l.reduce{acc, i ->
            acc*i
        }
        println(s)  // 2432902008176640000
        ```
    - Zipping, which is a set of functions to combine two arrays or collections element-wise.
        - zip(other: Array<out R>): List<Pair<T, R>>
        - zip(other: Iterable<R>): List<Pair<T, R>>
        ```Kotlin
        // Both of them are defined as infix functions, so you can write
        array.zip(otherArray)
        // or
        array zip otherArray

        array.zip(list)
        array zip list

        collection.zip(array)
        collection zip array

        collection.zip(otherCollection)
        collection zip otherCollection

        // If you try to zip arrays or collections of unequal lengths, the bigger one gets clipped at then end and the resulting list will have the size of the smaller one.
        ```
        - unzip(): Piar<List<T>, List<R>>
        - For zipping there is an alternative function to having a second parameter adder. This is a transform function doing something with the paired elements before outputting them to the zip result.

- The Spread Operator, For any function with a vararg parameter, you can use an array to pass values to the function
    ```Kotlin
    function xyz(a: Int, vararg x: String) {
        ...
    }
    val arr = arrayOf("1", "2", "3")
    xyz(42, *arr)
    // The * in front of the parameter is called a spread operator.
    // Note that this works only for arrays. but you can conversion a list or set via .toArray(), toIntArray() ...
    ```

- ArrayDeque

- Lambda functions can have a result. The result of a lambda function is whatever the last line evaluated to.

- Declaration-Side Variance: in and out variance annotation.
    ```Kotlin
    // out variance annotation
    class A<out T> {
        fun extract() : T = ...
    }
    var a = A<String>()
    var b = A<Any>()
    b = a // variance? YES!
    val extracted: String = b.extract() // OK, because we are reading!

    // in variance annotation
    class A<in T> {
        fun add(p: T) {...}
    }
    var a = A<String>()
    var b = A<Any>()
    a = b // variance? YES
    a.add("World") // OK, because we are writing!
    ```
    - So with the in or out variance annotation added to the type parameters, and confining class operations to allow for either only an input of the generic type or only an output of the generic type, variance is possible in Kotlin! If you need both, you can use a different construct, as covered in the section "Type Projections" later.

    - Note: The out variance for classes also gets called covariance, and the in variance is called contravariance. The name declaration-side variance stems from declaring the in or out variance in the declaration of the class. Other languages, such as Java, use a different type of variance that takes effect while using the clas and hence gets called use-side variance.

    - Because immutable collections cannot be written to, Kotlin automatically makes them covariant. If you prefer, you can think of Kotlin implicitly adding the out variance annotation to immutable collections.

- Type Projections: in projection, out projection, and star projections
    - In the previous section we saw that for the out style variance the corresponding class is not allowed to have functions with the generic type as a function parameter, and that for the in style variance we accordingly cannot have a function returning the generic type. This is, of course, unsatisfactory if we need both kinds of functions in a class. Kotlin also has an answer for this type of requirement. It is called type projection and because it aims at variance while using different functions of a class, it is the Kotling equivalent of use-side variance.
    - The idea goes as follows: We still use the in and out variance annotations, but instead of declaring them for the whole class we add them ot function parameters. We slightly rewrite the example from the previous section and add in and out variance annotations:
        ```Kotlin
        class Producer<T> {
            fun getData(): Iterable<T>? = null
        }

        class Consumer<T> {
            fun setData(p: Iterable<T>) {}
        }

        class A<T> {
            // The out in the add() function says that we need an object that produces T objects.
            fun add(p: Producer<out T>) {}
            // The in in the extractTo function designates an object that consumes T objects.
            fun extractTo(p: Consumer<in T>) {}
        }

        var a = A<String>()
        var b = A<Any>()

        var inputStrings = Producer<String>()
        var inputAny = Producer<Any>()
        a.add(inputStrings)
        a.add(inputAny)         // FAILS!
        b.add(inputStrings)     // only because of "out"
        b.add(inputAny)

        var outputStrings = Consumer<String>()
        var outputAny = Consumer<Any>()
        a.extractTo(outputStrings)
        a.extractTo(outputAny)  // only because of "in"
        b.extractTo(outputStrings)  // FAILS!
        b.extractTo(outputAny)
        ```
    - Star Projections
        - If you have a class or an interface with in or out variance annotations, you can use the special wildcard*, which means the following:
            - For the out variance annotation, * means out Any?.
            - For the in variance annotation, * menas in Nothing.
        - Remember that Any is the superclass of any class, and Nothing is the subclass of any class.
            ```Kotlin
            interface Interf<in A, out B> {
                ...
            }

            val x: Interf<*, Int> = ...     // same as Interf<in Nothing, Int>
            val y: Interf<Int, *> - ...     // same as Interf<INt, out Any?>
            ```
        - You use the star wildcard in cases where you know nothing about the type, but still want to satisfy variance semantics prescribed by class or interface declarations.

- Generic Constraints: restrict the type to a certain class or interface or one of its subtypes.
    ```Kotlin
    // confines T to a SpecificType or any of its subclasses
    <T : SpecificType>
    // SpecificType might have generic parameters themselves
    <T : SpcificType<T>>

    // Type bounds can be multiply declared.
    class TheClass<T> where T: UpperBound1, T : upperBound2, ... {

    }

    fun <T> functionName(...) where T : UpperBound1, T : UpperBound2, ... {

    }
    ```

- reduce: A reduction takes the first element of a collection or an iterable, stores it in a variable, and then repeatedly applies an operation with all the other elements from the colleciton or iterable. If, for example, the operation is addition, in the end you get the sum of the collection:
    ```Kotlin
    // reduce, reduceINdexed, reduceRight, reduceRightIndexed

    val l = listOf(1, 2, 3, 4)
    val r = l.reduce{acc, e -> acc+e}
    println(r)  // 10
    ```
    - <S, E : S> iter<E>.reduce( operation: (acc : S, E) -> S). Note that although the iteration goes over elements of type E, the operation function is allowed to also evaluate to a supertype of E. This is what the E : S in the type specification stands for. In this case the accumulator and the overall result will have the same type as this supertype.

- fold: A folding is the big borther of reduction. whereas the reduction starts with the first element of the collection or iterable, and then uses the rest of the elements to update it, the folding works with a dedicated folding accumulator object that receives step by step all the iterated-over elements and therefore can update its state. Because the accumulator object can have any suitable type, folding is more powerful than reduction.
    ```Kotlin
    // fold, foldIndexed, foldRight, foldRightIndexed

    val l = listOf(1, 2, 3, 4)
    val r = l.fold(0){acc, e -> acc +e}
    println(r)  // 10

    data class Parcel(val receiverId:Int, val weight:Double)
    val ll = listOf( Parcel(1267395, 1.45),
        Parcel(1515670, 0.46),
        Parcel(8345674, 2.50),
        Parcel(3418566, 1.47),
        Parcel(3491245, 3.04)
    )
    val ss = ll.fold(0.0){acc, e ->
        acc + e.weight
    }
    println(ss)  // 8.92
    ```

- Joining: a way to create a string representation of a collection or an iterable, joining the string representations of all elements. (Yes, you can also do it via fold())
    ```Kotlin
    fun <E> Iterable<E>.joinToString(
        separator: CharSequence = ", ",
        prefix: CharSequence = "",
        postfix: CharSequence = "",
        limit: Int = -1,
        truncated: CharSequence = "...",
        transform: (E) -> CharSequence = null
    ): String
    ```

- Grouping is about splitting a list into sublists based on some criterion. groupBY, groupingBy

- Zipping, bring two related lists together.
    ```Kotlin
    <E, R> Iterable<E>.zip(
        other: Iterable<R>
    ): List<Pair<E, R>>

    <E, R> Iterable<Pair<E, R>>.unzip():
        Pair<List<E>, List<R>>
    ```

- Windowing. For user interface programming you frequently need to split a list into chunks of a given size. Say, for example, the user interface shows chunks of size 10 and provides page forward and page backward buttons to show the next or the previous chunk of a longer list. For this aim the standard library provides awindowsing function.
    ```Kotlin
    // creates a windowed view of an iterable or a collection. Each chunk has
    // size and step indicates the index offset for each chunk (usually you set step=size). You must set partialWindows to true if you want to allow smaller chunks at the end.
    <E> iterable.windowed(
        size: Int, step: Int
        = 1, partialWindows:
        Boolean = false ) : List<List<E>>

    // provides a transform function to act on each chunk.
    <E, R> iterable.
        windowed( size:
        Int, step: Int = 1,
        partialWindows: Boolean
        = false, transform:
        (List<E>) -> R) : List<R>
    ```

- Sequences are lazily evaluated collections. By that we mean that other than for collections from the kotlin.collections package, no large amounts of data are held in memory. So, if you create a collection of size 1,000,000 there will be 1,000,000 items in the form of object references or primitives allocated in memory. A sequence of size 1,000,000, however, just indicates we have something that can be iterated over 1,000,000 times, without all the values associated with it. Sequence interfaces, classes, and functions have their own package: kotlin.sequences.
    ```Kotlin
    sequenceOf(1,2,7,9)
    iter.asSequence()

    // create genuine sequences that do not depend on existing collections or arrays.
    fun <T : Any> generateSequence(
        nextFunction: () -> T?
    ): Sequence<T>
    var iterVar = 0
    val seq = generateSequence {
        itervar++
    }

    fun <T : Any> generateSequence(
        seed: T?,
        nextFunction: (T) -> T?
        ): Sequence<T>
    // or
    fun <T : Any> generateSequence(
        seedFunction: () -> T?,
        nextFunction: (T) -> T?
        ): Sequence<T>
    val seq = generateSequence(
        seed = 0,
        nextFunction = { curr -> curr + 1 }
    )
    // example usage:
    seq.take(10).forEach { i ->
        // i will have values 0, 1, 2, ..., 9
        ...
    }

    val seqFib = generateSequence(
        seed = Pair(1,1),
        nextFunction = { curr ->
            Pair(curr.second, curr.first + curr.second)
        }
    )
    // example usage
    seqFib.take(10).map { it.second }.forEach {
        Log.e("LOG", "fib: " + it)
    }

    fun <T> sequence(
        block: suspend SequenceScope<T>.() -> Unit
    ): Sequence<T>
    val sequence = sequence {
        yieldAll(1..10 step 2)
    }
    // Usage example:
    sequence.take(8).forEach {
        Log.e("Log", it.toString())
    }
    // -> 1, 3, 5, 7, 9
    ```

- Basic Multithreading the Java Way
    - The most important multithreading-related clas in Java is java.util.Thread. You can create one using its constructor, but Kotlin has a function that simplifies thread creation: thread(). Its synopsis reads like this:
        ```Kotlin
        fun thread(
            start: Boolean = true,
            isDaemon: Boolean = false,
            contextClassLoader: ClassLoader? = null,
            name: String? = null,
            priority: Int = -1,
            blcok: () -> Unit
        )

        // You use it as follows, for example:
        var thr: Thread = thread(start = true) {
            ... do something ...
        }
        var thr2: Thread = thread(start = false) {
            ...
        }
        thr2.start()

        // The most basic thread example for an Android app might read (remember that a function as a last invocation parameter can go outside parentheses):
        // inside an activity:
        override fun onCreate(savedInstanceState: Bundle?) {
            ...
            thread {
                while(true) {
                    Thread.sleep(1000L)
                    Log.e("LOG", Date().toString())
                }
            }
        }

        // avoid multiple threads working on a shared list at the same time by wrapping the relevant code inside synchronized() {} blocks as follows:
        val l = mutableListOf(1, 2, 3)
        var i = 0
        thread {
            while(true) {
                Thread.sleep(10L)
                i++
                synchronized(l) {
                    if (i % 2 == 0) {
                        l.add(i)
                    } else {
                        l.remove(l.first())
                    }
                }
            }
        }

        thread {
            while(true) {
                Thread.sleep(1000L)
                synchronized(l) {
                    log.e("LOG", l.joinToString())
                }
            }
        }
        // It is also possible to add more parameters to the synchronized instruction.
        synchronized(l1, l2) {
            ...
        }
        ```

- Advanced Multithreading the Java Way
    - Scattering synchronized blocks and join functions throught your code poses a couple of problems:
        1. It makes your code hard to understand; understanding multithreaded state handling is anything but easy for nonrtivial programs.
        2. Having several threads and synchronized blocks might end up in a deadlock: Some thread A watis for thread B while thread B is waiting for thread A.
        3. Writing too many join functions for gathering the threads' calculation results might result in too many threads just waiting, thwarting the advantages of multithreading.
        4. Using synchronized blocks for any collection handling might also end up in too many threads just waiting.
    - Special Concurrency Collections
        - CopyOnWriteArrayList: Copying the complete list is costly, so this implementation usually helps only where reading operations vastly outnumber writing operations.
        - CopyOnWriteArraySet:
        - ConcurrentLinkedDeque: A thread-safe Deque where iteration operations are weakly consistent, meaning read elements reflect the deque's state at some point at or since the creation of the iterator. No ConcurrentModificationException will be thrown.
        - ConcurrentLinkedQueue:
        - ConcurrentSkipListSet: A thread-safe Set where iteration operations are weakly consistent, meaning read elements reflect the deque's state at some point at or since the creation of the iterator. No ConcurrentModificationException will be thrown. Other than the type specification the API documentation suggests, the elements must implement the Comparable interface.
        - ConsurrentSkipListMap:
    - Locks
        - Lock(): similar to synchronized block, but in a more object-oriented way
        - ReentrantLock()
        - ReadWriteLock()
    - Atomic Variable Types
        ```Kotlin
        class Counter {
            var c:AtomicInteger = AtomicInteger(0)
            fun increment() { c.incrementAndGet() }
            fun decrement() { c.decrementAndGet() }
        }
        ```
    - Executors, Futures, and Callables
        - The main interfaces and classes
            - Callable: This is something that can be invoked, possibly by another thread, and returns a result.
            - Runnable: This one is not in package java.util.concurrent, but in package java.lang. It is something that can be invoked, possibly by another thread. No result is returned.
            - Executors: This is an important utility class for, among other things, obtaining ExecutorService and ScheduledExecutorService implementations.
            - ExecutorService: This is an interface for objects that allows invoking Runnables or Callables and gathering their results.
            - ScheduledExecutorService: This is an interface for objects that allows invoking Runnables or Callbales and gathering their results. The invocation happens after some delay, or in a repeated manner.
            - Future: THis is an object you can use to fetch the result from a Callable. (CompletableFuture)
            - ScheduledFuture: This is an object you can use to fetch the result from a Callbale submitted to a ScheduledExecutorService.
        - The primary usage pattern
            1. Using Executors to get an ExecutorService or ScheduledExecutorSerivce. Save it in a property such as srvc or schedSrvc.
            2. Using ExecutorService.invoke*, ExecutorService.submit*, ScheduledExecutorService.shcedule* to start tasks.
            3. Wait for termination, as signaled by suitable functions from ExecutorService or ScheduledExecutorService, or by the Futures or ScheduledFutures you might have received in the previous step.

- Kotlin Coroutines
    -