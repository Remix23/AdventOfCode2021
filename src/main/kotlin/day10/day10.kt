package day10

val assertions = mapOf('(' to ')', '{' to '}', '[' to ']', '<' to '>')
val costs = mapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)

fun main ()
{
    var results = mutableListOf<Long>()
    for (i in 0..98)
    {
        val tofill = checkLine()
        tofill.reverse()
        if (tofill.size > 0) results.add(calcAutoCompleteErrorScore(tofill))
    }
    results.sort()
    println(results[results.size / 2])

}

fun checkLine () : MutableList<Char>
{
    val line = readLine()
    val stack = mutableListOf<Char>()

    line!!.forEach {
        if (it == '(' || it == '[' || it == '{' || it == '<')
        {
            stack.add(it)
        } else {
            if (assertions[stack.last()] == it){
                stack.removeAt(stack.size - 1)
            } else {
                stack.clear()
                return stack
            }
        }
    }
    return stack
}

fun calcAutoCompleteErrorScore (mistakes : MutableList<Char>) : Long
{
    var result = 0L

    mistakes.forEach {
        result *= 5
        result += costs[it]!!
    }

    return result
}