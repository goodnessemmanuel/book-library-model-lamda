import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Book;
import utils.Borrow;
import utils.Class;
import utils.Student;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {

    @DisplayName("When a book is borrowed, total copy should drop by 1")
    @Test
    void give()
    {
        Librarian librarian = new Librarian("tony", "Gibson", "Male");
        Book bookToGive = new Book(1234, "Camelonia", "James", 4);
        Student student = new Student(1, "Jude", "Ken", "M", new Class("SS1"));

        int expected = bookToGive.getTotalCopy.get() - 1;
        int actual = librarian.give.apply(new Borrow(student, bookToGive)).getTotalCopy.get();

        assertEquals(expected, actual, "Should reduce book total copy by 1");
    }
}