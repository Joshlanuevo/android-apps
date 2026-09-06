package com.example.review.play

/*
 * Kotlin: Lambda Expressions, Anonymous Functions,
 *        and Higher-Order Functions
 */


/*
 * Kotlin Literals
 *
 * A literal is a constant value written directly in the source code
 * and assigned to a variable or used as an expression.
 */
val x = 10
var double1 = 13.8
val str = "Welcome to Our Course"


/*
 * Function Literals
 *
 * When we assign a function to a variable, it becomes a function literal.
 *
 * Kotlin provides 2 types of function literals:
 * 1. Lambda expression
 * 2. Anonymous function
 *
 * fun name () : return type {
 *      body / method body
 *      return statement
 * }
 */

fun addTwoNum(x: Int, y: Int): Int {
    return x + y   // method body
}


/*
 * Syntax of a Lambda Expression
 *
 * val lambda: (DataType1, DataType2) -> ReturnType = { x: DataType1, y: DataType2 -> methodBody }
 * variable      Function Type                                   Lambda Expression
 *  name
 *
 * The 3 shorter forms (pick one, never repeat the types twice):
 *
 * 1. Full form      : val add: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
 * 2. Infer the type : val add = { a: Int, b: Int -> a + b }
 * 3. Infer the params: val add: (Int, Int) -> Int = { a, b -> a + b }
 */

val addFull: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
val addInferredType = { a: Int, b: Int -> a + b }
val addInferredParams: (Int, Int) -> Int = { a, b -> a + b }


/*
 * The Implicit Parameter: it
 *
 * If the lambda has exactly ONE parameter and the type is known,
 * you can drop the parameter name and the arrow, then use "it".
 */

val square: (Int) -> Int = { it * it }
val shout: (String) -> String = { it.uppercase() + "!" }


/*
 * Return Value of a Lambda
 *
 * A lambda has NO return keyword. The value of the LAST EXPRESSION
 * in the body is automatically the return value.
 */

val describe: (Int) -> String = { n ->
    val parity = if (n % 2 == 0) "even" else "odd"   // statement
    "$n is $parity"                                  // last expression = returned
}

/*
 * A lambda that returns nothing has the type (Params) -> Unit
 */
val logMessage: (String) -> Unit = { message -> println("LOG: $message") }


/*
 * Anonymous Function
 *
 * The second kind of function literal. It looks like a normal function
 * but has NO name. Unlike a lambda, it CAN declare its return type explicitly.
 *
 * fun (params): ReturnType { body }
 */

val subtract = fun(a: Int, b: Int): Int { return a - b }   // block body, needs return
val multiply = fun(a: Int, b: Int) = a * b                 // expression body, type inferred


/*
 * Lambda vs Anonymous Function
 *
 * +-------------------+---------------------------+------------------------------+
 * |                   | Lambda                    | Anonymous function           |
 * +-------------------+---------------------------+------------------------------+
 * | Syntax            | { a, b -> a + b }         | fun(a: Int, b: Int) = a + b  |
 * | Return type       | always inferred           | can be written explicitly    |
 * | return keyword    | not allowed (needs label) | allowed, returns from itself |
 * | Passed outside () | yes (trailing lambda)     | no                           |
 * +-------------------+---------------------------+------------------------------+
 *
 * This is why an anonymous function is handy when the body has several
 * exit points.
 */

val firstNegative = fun(numbers: List<Int>): Int? {
    for (n in numbers) {
        if (n < 0) return n   // returns from the anonymous function
    }
    return null
}

/*
 * In a lambda you must use a LABELED return instead: return@label
 */
val classify: (Int) -> String = lambda@{ n ->
    if (n == 0) return@lambda "zero"
    if (n > 0) "positive" else "negative"
}


/*
 * Higher-Order Function
 *
 * A function that either:
 * 1. takes another function as a parameter, or
 * 2. returns a function,
 * or both.
 */

// 1. Function as a PARAMETER
fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

fun repeatAction(times: Int, action: (Int) -> Unit) {
    for (i in 1..times) {
        action(i)
    }
}

// 2. Function as a RETURN TYPE
fun makeMultiplier(factor: Int): (Int) -> Int {
    return { number -> number * factor }
}

// Nullable function type + default value
fun greet(name: String, formatter: ((String) -> String)? = null): String {
    return formatter?.invoke(name) ?: "Hello, $name"
}


/*
 * Closure
 *
 * A lambda or anonymous function can access and MODIFY variables
 * declared outside of it. Those variables stay alive as long as the
 * function literal does.
 */

fun makeCounter(): () -> Int {
    var count = 0          // captured by the lambda below
    return { ++count }
}


/*
 * Function Reference  ::
 *
 * Instead of wrapping an existing function in a lambda
 * ( { a, b -> addTwoNum(a, b) } ), pass a reference to it: ::addTwoNum
 */

fun isEven(n: Int): Boolean = n % 2 == 0


/*
 * inline
 *
 * Every lambda you pass around normally becomes an object at runtime.
 * Marking a higher-order function "inline" tells the compiler to paste
 * the body at the call site instead, removing that overhead.
 * It also allows a plain (non-local) return inside the lambda.
 */

inline fun measure(block: () -> Unit) {
    val start = System.nanoTime()
    block()
    println("took ${System.nanoTime() - start} ns")
}

fun containsNegative(numbers: List<Int>): Boolean {
    numbers.forEach {
        if (it < 0) return true   // non-local return: forEach is inline
    }
    return false
}


fun main() {

    println("--- 1. Executing a lambda ---")
    val printWelcome = { println("Welcome to Our Course") }
    printWelcome()          // Welcome to Our Course
    printWelcome.invoke()   // Welcome to Our Course


    println("\n--- 2. Lambda syntax forms ---")
    println(addFull(2, 3))            // 5
    println(addInferredType(2, 3))    // 5
    println(addInferredParams(2, 3))  // 5
    println(addTwoNum(2, 3))          // 5  (a normal function, for comparison)


    println("\n--- 3. Implicit parameter: it ---")
    println(square(7))          // 49
    println(shout("kotlin"))    // KOTLIN!


    println("\n--- 4. Last expression is the return value ---")
    println(describe(10))       // 10 is even
    println(describe(7))        // 7 is odd
    logMessage("lambda returning Unit")


    println("\n--- 5. Anonymous functions ---")
    println(subtract(10, 4))                        // 6
    println(multiply(10, 4))                        // 40
    println(firstNegative(listOf(3, 8, -5, -9)))    // -5
    println(firstNegative(listOf(3, 8)))            // null
    println(classify(0))                            // zero
    println(classify(-4))                           // negative


    println("\n--- 6. Function as a parameter ---")
    println(calculate(6, 3) { a, b -> a + b })      // 9
    println(calculate(6, 3) { a, b -> a * b })      // 18
    println(calculate(6, 3, ::addTwoNum))           // 9  (function reference)

    /*
     * Trailing lambda:
     * when the LAST parameter is a function, the lambda may be moved
     * outside the parentheses. If it is the only parameter, drop them entirely.
     */
    repeatAction(3) { i -> println("run number $i") }


    println("\n--- 7. Function as a return type ---")
    val triple = makeMultiplier(3)
    println(triple(5))                              // 15
    println(makeMultiplier(10)(5))                  // 50

    println(greet("Ana"))                           // Hello, Ana
    println(greet("Ana") { it.uppercase() })        // ANA


    println("\n--- 8. Closure ---")
    val next = makeCounter()
    println(next())   // 1
    println(next())   // 2
    println(next())   // 3

    var total = 0
    listOf(1, 2, 3, 4).forEach { total += it }   // modifies an outer variable
    println("total = $total")                    // total = 10


    println("\n--- 9. Standard library higher-order functions ---")
    val numbers = listOf(5, 2, 9, 1, 7, 4)

    println(numbers.filter { it > 4 })              // [5, 9, 7]
    println(numbers.filter(::isEven))               // [2, 4]
    println(numbers.map { it * 2 })                 // [10, 4, 18, 2, 14, 8]
    println(numbers.sortedBy { it })                // [1, 2, 4, 5, 7, 9]
    println(numbers.sortedByDescending { it })      // [9, 7, 5, 4, 2, 1]
    println(numbers.any { it > 8 })                 // true
    println(numbers.all { it > 0 })                 // true
    println(numbers.count { it % 2 == 1 })          // 4
    println(numbers.maxOrNull())                    // 9
    println(numbers.reduce { acc, n -> acc + n })   // 28  (starts from first item)
    println(numbers.fold(100) { acc, n -> acc + n }) // 128 (starts from 100)
    println(numbers.groupBy { if (isEven(it)) "even" else "odd" })

    // Chaining reads left to right
    val result = numbers
        .filter { it % 2 == 1 }
        .map { it * it }
        .sorted()
    println(result)                                 // [1, 25, 49, 81]


    println("\n--- 10. inline and non-local return ---")
    measure { (1..100_000).sum() }
    println(containsNegative(listOf(1, 2, -3)))     // true
    println(containsNegative(listOf(1, 2, 3)))      // false
}