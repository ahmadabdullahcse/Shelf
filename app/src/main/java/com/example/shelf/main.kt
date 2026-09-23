package com.example.shelf

fun main()
{
    var minutes: Int
    minutes = string2Int(readln())
    if(minutes!=-1) println(formatDuration(minutes))
}

fun formatDuration(minutes: Int): String {
    return "${minutes / 60}h ${minutes % 60}m"
}

fun string2Int(str: String): Int {
    if(str.isNullOrEmpty())
    {
        println("String is null or empty")
        return -1
    };
    var res: Int = 0
    try {
        res = str.toInt()
        if(res<0) {
            println("Please input a number greater or equal 0")
            return -1;
        }
        return res
    }
    catch(e: Exception)
    {
        println("Please input a number")
        return -1
    }
}