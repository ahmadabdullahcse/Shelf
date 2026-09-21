package com.example.shelf

fun main()
{
    var minutes: Int
    minutes = string2Int(readln())
    println(formatDuration(minutes))
}

fun formatDuration(minutes: Int): String {
    return "${minutes / 60}h ${minutes % 60}m"
}

fun string2Int(str: String): Int {
    if(str.isNullOrEmpty())
    {
        println("String is nill or empty")
        return 0
    };
    val res = str.toInt()
    if(res<0) return 0;
    return res
}