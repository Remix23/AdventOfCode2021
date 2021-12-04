import java.io.File

fun main() {
    println(solveTask3())

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
}

fun getMostCommonBitInPositions (data : List<String?>, pos : Int, mode : Int) : Int
{
    var counter = 0
    for (str in data)
    {
        if (str!![pos] == '1')
        {
            counter++
        }
    }
    if (counter >= data.size - counter)
    {
        return mode
    } else {
        return 1 - mode
    }
}

fun calc (data : Array<String?>, toSave : Int) : Int
{
    val numbers = data.toMutableList()
    for (i in 0 until numbers.first()!!.length)
    {
        val mostCommonBit = getMostCommonBitInPositions(numbers, i, toSave)
        // println("Most common bit: $mostCommonBit")

        var j = 0
        while (j < numbers.size)
        {
            if (numbers[j]!![i].toString() != mostCommonBit.toString())
            {
                numbers.removeAt(j)
            } else {
                j++
            }
            if (numbers.size == 1) {
                return numbers.first()!!.toInt(2)
            }
        }
    }
    return numbers.first()!!.toInt(2)
}

fun solveTask3 () : Int
{
//    print("Number of elems: ")
//    val n : Int = readLine()!!.toInt()

    val fileName = "src/main/in.txt"
    val file = File(fileName)

    val numbers = Array<String?>(1000, { e -> ""})

//    for (i in 0 until n)
//    {
//        numbers[i] = readLine()
//    }
    var linecounter = 0
    file.forEachLine {
        numbers[linecounter] = it
        linecounter++
    }

    val oxygen = calc(numbers, 1)
    val co2 = calc(numbers, 0)

    return oxygen * co2
}