package com.example.shelf

class Book (
    val title: String,
    val page: Int
)

fun main()
{
    val books = init()
    books.forEach {
        println(it.title+" has "+it.page+" pages.")
    }
    longestThree(books)
    totalPage(books)
    createGroups(books)
}

fun init(): List<Book> {
    val books = listOf(
        Book("The Thirty-Nine Steps", 138),
        Book("The Prime of Miss Jean Brodie", 150),
        Book("A Single Man", 152),
        Book("Murphy", 158) ,
        Book("A Clockwork Orange", 160),
        Book("Strange Case of Dr Jekyll and Mr Hyde",161),
        Book("The Alchemist", 167),
        Book("Mrs Dalloway", 172),
        Book("The Hitchhiker’s Guide To The Galaxy",  180),
        Book("The Great Gatsby", 184),
        Book("Amongst Women", 184),
        Book("The Wind In The Willows", 190),
        Book("The End Of The Affair", 191),
        Book("Party Going", 192),
        Book("The Maltese Falcon", 201)
    )
    return books
}

fun longestThree(books: List<Book>) {
    var longest: Int = 0
    books.forEach {
        if(it.page>longest) longest = it.page
    }
    books.forEach {
        if(it.page==longest)
            println(it.title+" is the longest book with "+longest.toString()+" pages.")
    }
    var longest2: Int = 0
    books.forEach {
        if(it.page in longest2+1..<longest) longest2 = it.page
    }
    books.forEach {
        if(it.page==longest2)
            println(it.title+" is the second longest book with "+longest2.toString()+" pages.")
    }
    var longest3: Int = 0
    books.forEach {
        if(it.page in longest3+1..<longest2) longest3 = it.page
    }
    books.forEach {
        if(it.page==longest3)
            println(it.title+" is the third longest book with "+longest3.toString()+" pages.")
    }
}

fun totalPage(books: List<Book>) {
    var total: Int = 0
    books.forEach {
        total+=it.page
    }
    println("There are total "+total.toString()+" pages")
}

fun createGroups(books: List<Book>) {
    val groupedBooks = books.groupBy {
        it.title.first()
    }
    groupedBooks.forEach { (ch, boooks) ->
        println(ch+"->"+boooks.map { it.title })
    }
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