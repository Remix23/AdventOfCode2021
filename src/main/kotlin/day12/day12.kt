package day12

import java.io.File
import kotlin.contracts.contract

fun main ()
{
    val graph = loadMap("C:\\Users\\keruj\\IdeaProjects\\KotlinAdventOfCode\\src\\main\\inputs\\in12.txt")

    println(getPaths(graph))
}

fun loadMap (path : String) : Map<String, MutableList<String>>
{
    val graph = mutableMapOf<String, MutableList<String>>()

    val file = File(path)

    file.forEachLine {
        val vertices = it.split("-")
        if (graph[vertices.first()].isNullOrEmpty()) {
            graph[vertices.first()] = mutableListOf(vertices.last())
        } else {
            graph[vertices.first()]!!.add(vertices.last())
        }
        if (vertices.first() != "start") {
            if (graph[vertices.last()].isNullOrEmpty()) {
                graph[vertices.last()] = mutableListOf(vertices.first())
            } else {
                graph[vertices.last()]!!.add(vertices.first())
            }
        }
    }
    return graph
}

fun getPaths (graph : Map<String, MutableList<String>>) : Int
{
    val paths = mutableListOf<MutableList<String>>()
    graph["start"]!!.forEach {
        paths.add(mutableListOf(it))
    }

    var finishedPaths = 0

    while (finishedPaths < paths.size)
    {
        val pathsNum = paths.size
        for (i in 0 until pathsNum)
        {
            val path = paths[i]
            val pathLast = path.last()

            if (pathLast == "end") { finishedPaths++; continue}

            path.add(graph[pathLast]!!.first())
            if (graph[pathLast]!!.size > 1)
            {
                for (j in 1 until graph[pathLast]!!.size)
                {
                    val copy = path.slice(0 until path.size - 1).toMutableList()
                    copy.add(graph[pathLast]!![j])
                    paths.add(copy)
                }
            }
        }
    }
    return finishedPaths
}