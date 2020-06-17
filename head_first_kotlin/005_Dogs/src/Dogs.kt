class Dog(val name: String, weight_param: Int, breed_param: String) {
    // The above properties defined in the constructor are created first

    init {
        // This initializer block runs next.
        println("Dog $name has been created.")
    }

    // These following properties are created after the first initializer block has finished.
    var activites = arrayOf("Walks")
    var breed = breed_param.toUpperCase()

    init {
        // The second initializer block runs after the properties above have been created"
        println("The breed is $breed")
    }

    var weight = weight_param
        set(value) {
            if (value > 0) field = value
        }

    val weightInKgs: Double
        get() = weight / 2.2

    fun bark() {
        println(if (weight < 20) "Yip!" else "Woof!")
    }
}

fun main(args: Array<String>) {
    val myDog = Dog("Fido", 70, "Mixed")
    myDog.bark()
    myDog.weight = 75
    println("Weight in Kgs is ${myDog.weightInKgs}")
    myDog.weight = -2
    println("Weight is ${myDog.weight}")
    myDog.activites = arrayOf("Walks", "Fetching balls", "Frisbee")
    for (item in myDog.activites) {
        println("My dog enjyos $item")
    }

    val dogs = arrayOf(Dog("Kelpie", 20, "Westie"), Dog("Ripper", 10, "Poodle"))
    dogs[1].bark()
    dogs[1].weight = 15
    println("Weight for ${dogs[1].name} is ${dogs[1].weight}")
}