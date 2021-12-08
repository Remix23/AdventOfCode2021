package day8

fun main () {
    var result = 0

    for (i in 0 until 200) {
        val (renderingNums, outputNums) = loadNotes()

        val vires = Array<Set<Char>>(7) { emptySet() }

        vires[0] = renderingNums[1].first subtract renderingNums[0].first // return the highest section

        val fourAndSeven = renderingNums[1].first union renderingNums[2].first
        val nine = renderingNums.filter { (it.first subtract fourAndSeven).size == 1 && it.first.size == 6}.first()

        vires[6] = nine.first subtract (renderingNums[1].first union fourAndSeven) // return the lowest section
        vires[4] = renderingNums.last().first subtract nine.first // 8 - 9 returns left lower section

        val three = renderingNums.filter { (it.first subtract  (renderingNums[1].first union vires[6])).size == 1 && it.first.size == 5}.first()

        vires[3] =  three.first subtract (renderingNums[1].first union vires[6]) // returns the middle section
        vires[1] = renderingNums[2].first subtract three.first // 3 - 4 returns the left higher section

        val five = renderingNums.filter { !it.first.containsAll(three.first) && (it.first intersect renderingNums[2].first).size == 3 && it.first.size == 5 && (it.first intersect renderingNums[0].first).size == 1}.first()
        vires[2] = (renderingNums.last().first subtract vires[4]) subtract five.first

        vires[5] = renderingNums[0].first subtract vires[2]

        result += processNums(outputNums, vires)
    }
    println(result)
}

fun loadNotes () : Pair<Array<Pair<Set<Char>, Int>>, Array<String>>
{
    val stream = readLine()!!.split("|")

    val uniqueNums = stream[0].trim().split(" ").map { Pair(it.toSet(), it.length) }.sortedBy { it.second }.toTypedArray()
    val outputNums = stream[1].trim().split(" ").toTypedArray()

    return Pair(uniqueNums, outputNums)
}

fun processNums (numbers : Array<String>, wires : Array<Set<Char>>) : Int
{
    var final = ""

    for (num in numbers)
    {
        val section = num.map { wires.indexOf(setOf(it)) }.toIntArray().sortedArray()
        var result = "0"
        if (section.contentEquals(intArrayOf(2, 5)))
        {
            result = "1"
        }
        if (section.contentEquals(intArrayOf(0, 2, 3, 4, 6)))
        {
            result = "2"
        }
        if (section.contentEquals(intArrayOf(0, 2, 3, 5, 6)))
        {
            result = "3"
        }
        if (section.contentEquals((intArrayOf(1, 2, 3, 5))))
        {
            result = "4"
        }
        if (section.contentEquals(intArrayOf(0, 1, 3, 5, 6)))
        {
            result = "5"
        }
        if (section.contentEquals(intArrayOf(0, 1, 3, 4, 5, 6)))
        {
            result = "6"
        }
        if (section.contentEquals(intArrayOf(0, 2, 5)))
        {
            result = "7"
        }
        if (section.contentEquals(intArrayOf(0, 1, 2, 3, 4, 5, 6)))
        {
            result = "8"
        }
        if (section.contentEquals(intArrayOf(0, 1, 2, 3, 5, 6)))
        {
            result = "9"
        }
        final += result
    }
    return final.toInt()
}