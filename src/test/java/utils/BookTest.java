package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @DisplayName("Add correct copy of book")
    @Test
    void shouldAddCorrectBookCopy()
    {
        Book book = new Book(1223, "New School Chemistry", "Jamie Jiks");
        int expected1 = 1; //default copy
        int actual1 = book.getTotalCopy.get();

        book.addCopies.accept(3);
        int expected2 = 4; //3 copies added
        int actual2 = book.getTotalCopy.get();

        book.addCopies.accept(- 4); //negative parameter
        int expected3 = expected2;
        int actual3 = book.getTotalCopy.get();

        assertAll(
                () -> assertEquals(expected1, actual1, "utils.Book should be one copy on when first created"),
                () -> assertEquals(expected2, actual2, "book number of copy should add correctly"),
                () -> assertEquals(expected3, actual3, "Should not allow negative parameter value")
        );

    }

    @DisplayName("When two book are equal")
    @Test
    void shouldDetermineIfTwoBookAreEqual()
    {
        Book book1 = new Book(1122, "EqualBook", "Equal");
        Book book2 = new Book(1122, "EqualBook", "Equal");
        boolean condition = book1.equals(book2);

        assertTrue(condition,"Should determine if two book are equal");
    }
}