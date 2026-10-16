package com.example.shelf

enum class ReadingStatus {
    WANT_TO_READ,
    IN_PROGRESS,
    FINISHED,
    NOT_SET
}

enum class Error {
    INVALID_INPUT,
    INVALID_INDEX,
    EMPTY_LIST
}
data class Book (
    val title: String,
    val page: Int,
    val status: ReadingStatus
)

sealed class SaveStatus {
    data object Success: SaveStatus()
    data class Failed(val error: Error): SaveStatus()
}
var books = mutableListOf<Book>()

fun main() {
    while(true) {
        println("Choose an option:")
        println("1. Add a book")
        println("2. List all books")
        println("3. Update a book")
        println("4. Update status of a book")
        println("5. Delete a book")
        println("6. Exit")
        val x = string2Int(readln())
        when(x) {
            1 -> addBook()
            2 -> listBooks()
            3 -> updateBook()
            4 -> updateStatus()
            5 -> deleteBook()
            6 -> break
            else -> println("Choose a correct option")
        }
    }

}

fun showSaveResult(status: SaveStatus) {
    when(status) {
        is SaveStatus.Success -> println("Saved Successfully")
        is SaveStatus.Failed -> println("Failed to save for ${status.error}")
    }
}

fun addBook() {
    println("Enter book name:")
    var bookName = readln()
    while(bookName.isBlank() || bookName.length>100) {
        println("Book name can't be blank or more than 100 char. Please enter valid book name:")
        bookName = readln()
    }
    println("Enter page number:")
    val pageNumber = string2Int(readln())
    println("""Enter progress:
        1. Want to read
        2. In Progress
        3. Finished
    """.trimMargin())
    val status = string2Int(readln())
    val readingStatus = when(status) {
        1 -> ReadingStatus.WANT_TO_READ
        2 -> ReadingStatus.IN_PROGRESS
        3 -> ReadingStatus.FINISHED
        else -> ReadingStatus.NOT_SET
    }
    books.add(Book(bookName, pageNumber, readingStatus))
    showSaveResult(SaveStatus.Success)
    if(status !in 1..3){
        println("Reading status set \"Want to read\" as default.")
    }
}
fun listBooks() {
    if(books.isEmpty()) {
        println("There is no book to show :-(")
    }
    var sl:Int = 0
    books.forEach {
        sl++
        println(sl.toString()+". "+it.title+" has "+it.page+" pages and reading status is "+it.status+".")
    }
}

fun updateBook() {
    if(books.isEmpty()) {
        showSaveResult(SaveStatus.Failed(Error.EMPTY_LIST))
        return
    }
    println("Enter the number of book you want to update")
    listBooks()
    when(val index = string2Int(readln())) {
        in 1..books.size -> {
            println("Enter updated name:")
            var newBookName = readln()
            while(newBookName.isBlank() || newBookName.length>100)
            {
                println("Book name can't be blank or more than 100 char. Please enter valid book name:")
                newBookName = readln()
            }
            println("Enter updated page number:")
            var newPageNumber = string2Int(readln())
            if(newPageNumber==-1) newPageNumber = books[index-1].page
            books[index-1] = books[index-1].copy(title = newBookName, page = newPageNumber)
            showSaveResult(SaveStatus.Success)
        }
        else -> showSaveResult(SaveStatus.Failed(Error.INVALID_INDEX))
    }
}

fun updateStatus() {
    if(books.isEmpty()) {
        showSaveResult(SaveStatus.Failed(Error.EMPTY_LIST))
        return
    }
    println("Enter the number of book you want to update status")
    listBooks()
    when(val index = string2Int(readln())) {
        in 1..books.size -> {
            println("""Enter updated progress:
                1. Want to read
                2. In Progress
                3. Finished
            """.trimMargin())
            val status = string2Int(readln())
            val readingStatus = when(status) {
                1 -> ReadingStatus.WANT_TO_READ
                2 -> ReadingStatus.IN_PROGRESS
                3 -> ReadingStatus.FINISHED
                else -> ReadingStatus.NOT_SET
            }
            if(readingStatus== ReadingStatus.NOT_SET) {
                showSaveResult(SaveStatus.Failed(Error.INVALID_INPUT))
                return
            }
            books[index-1] = books[index-1].copy(status=readingStatus)
            showSaveResult(SaveStatus.Success)
        }
        else -> showSaveResult(SaveStatus.Failed(Error.INVALID_INDEX))
    }
}

fun deleteBook() {
    if(books.isEmpty()) {
        showSaveResult(SaveStatus.Failed(Error.EMPTY_LIST))
        return
    }
    println("Enter the number of book you want to delete")
    listBooks()
    when(val index = string2Int(readln()))
    {
        in 1..books.size -> {
            books.removeAt(index-1)
            showSaveResult(SaveStatus.Success)
        }
        else -> showSaveResult(SaveStatus.Failed(Error.INVALID_INPUT))
    }
}

fun init(): List<Book> {
    val books = listOf(
        Book("The Thirty-Nine Steps", 138, ReadingStatus.WANT_TO_READ),
        Book("The Prime of Miss Jean Brodie", 150, ReadingStatus.FINISHED),
        Book("A Single Man", 152, ReadingStatus.WANT_TO_READ),
        Book("Murphy", 158, ReadingStatus.IN_PROGRESS),
        Book("A Clockwork Orange", 160, ReadingStatus.WANT_TO_READ),
        Book("Strange Case of Dr Jekyll and Mr Hyde",161, ReadingStatus.WANT_TO_READ),
        Book("The Alchemist", 167, ReadingStatus.IN_PROGRESS),
        Book("Mrs Dalloway", 172, ReadingStatus.WANT_TO_READ),
        Book("The Hitchhiker’s Guide To The Galaxy",  180, ReadingStatus.WANT_TO_READ),
        Book("The Great Gatsby", 184, ReadingStatus.IN_PROGRESS),
        Book("Amongst Women", 184, ReadingStatus.WANT_TO_READ),
        Book("The Wind In The Willows", 190, ReadingStatus.WANT_TO_READ),
        Book("The End Of The Affair", 191, ReadingStatus.FINISHED),
        Book("Party Going", 192, ReadingStatus.FINISHED),
        Book("The Maltese Falcon", 201, ReadingStatus.WANT_TO_READ)
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
        //println("String is null or empty")
        return -1
    };
    var res: Int = 0
    try {
        res = str.toInt()
        if(res<0) {
            //println("Please input a number greater or equal 0")
            return -1;
        }
        return res
    }
    catch(e: Exception)
    {
       // println("Please input a number")
        return -1
    }
}