import utils.Book;
import utils.Borrow;
import utils.Person;
import utils.Student;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Librarian class
 */

public class Librarian extends Person
{

    public Librarian(String firstName, String lastName, String gender)
    {
        super(firstName, lastName, gender);
    }

    public static Function<Borrow, Book> give = borrow ->
    {
        Book book = borrow.getBook();
        int currentCopy = book.getTotalCopy.get();
        book.setTotalCopy.accept(currentCopy - 1);
        return book;
    };

    @Override
    public String role() { return "librarian";}

    public static BorrowComparator<? extends Person> firstComeFirstServe()
    {
        return new BorrowComparator<>();
    }

    /**
     * Librarian private comparator
     * class to maintain queue order
     * @param <T> Comparator generic
     */
    private static class BorrowComparator<T> implements Comparator<Borrow>
    {
        @Override
        public int compare(Borrow left, Borrow right)
        {
            if(left.equals(right))
            {
                Person p1 = left.getMember.get();
                Person p2 = right.getMember.get();
                if (p2.role().equals("student") && p1.role().equals("teacher")
                    && left.getBook().getTotalCopy.get() <= 1)
                {
                    return left.equals(right) ? -1 : 0; //when a student borrows the same book as teacher swap them in the queue
                }
            }
            return 0;
        }
    }
}
