package day6

fun main ()
{
    val fishes = loadFishes()

    val numOfDays = 256
    for (i in 0 until numOfDays)
    {
        updateState(fishes)
    }
    var result : Long = 0
    fishes.forEach {
        result += it
    }
    println(result)
}

fun loadFishes () : Array<Long>
{
    val fishes = Array<Long>(9) { 0 }
    val stream = readLine()!!.split(',').map { it.toInt() }

    for (fish in stream)
    {
        fishes[fish]++
    }

    return fishes
}

fun updateState (fishes : Array<Long>)
{
    val newFishes = fishes[0]
    for (i in 1 until fishes.size)
    {
        fishes[i - 1] = fishes[i]
    }
    fishes[6] += newFishes
    fishes[8] = newFishes
}