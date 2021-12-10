package day10

val assertions = mapOf('(' to ')', '{' to '}', '[' to ']', '<' to '>')
val costs = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)

fun main ()
{
    var result = 0
    for (i in 0..97)
    {
        val mistakes = checkLine()
        if (mistakes.size > 0) result += costs[mistakes.first()]!!
    }
    println(result)
}

fun checkLine () : MutableList<Char>
{
    val line = readLine()
    val stack = mutableListOf<Char>()
    val mistakes = mutableListOf<Char>()

    line!!.forEach {
        if (it == '(' || it == '[' || it == '{' || it == '<')
        {
            stack.add(it)
        } else {
            if (assertions[stack.last()] == it){
                stack.removeAt(stack.size - 1)
            } else {
                mistakes.add(it)
            }
        }
    }
    return mistakes
}

fun calcSyntaxErrorScore (mistakes : MutableList<Char>) : Int
{
    return 0
}