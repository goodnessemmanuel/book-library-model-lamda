import utils.Book;
import utils.Borrow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Set up a library instance with this class.
 * use the add book method to add book/s to
 * the created instance.
 */

public class Library implements Serializable
{
    private String name;
    private static List<Book> books;
    private static Queue<Borrow> borrowQueue;

    public Library()
    {
        this("Library Name"); //default name
    }

    public Library(String name)
    {
        this.name = name;
        books = new ArrayList<>(100); //initial capacity
        borrowQueue = new LinkedList<>();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Supplier<String> getName = () -> name;

    //add book to library
    public Consumer<Book> addBook = (Book book) ->
    {
        if (book != null)
        {
            books.add(book);
        }
    };

    public void setBorrowQueue(Queue<Borrow> queue)
    {
        borrowQueue = queue;
    }

    public static Supplier<Queue<Borrow>> supplierBorrowQueue = () -> borrowQueue;

    /**
     * @param books add multiple book
     */
    public Consumer<List<Book>> addBooks = (List<Book> books) -> books.forEach(book -> addBook.accept(book));


    public static Supplier<List<Book>> bookListSupplier = () -> books;

    public Supplier<Integer> totalBookCopies = () -> books.size();

    /**
     * @return a formatted string of
     * all the book in the Library
     */
    @Override
    public String toString()
    {
        return books.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }
}
