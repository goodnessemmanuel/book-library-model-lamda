package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BorrowTest
{
    @Test
    @DisplayName("Should process correct date")
    void shouldDisplayCorrectDate()
    {
        String expected = "11/8/2020"; //date as at when I wrote this test
        Student student = new Student(1, "Jude", "Ken", "M", new Class("SS1"));
        Borrow borrow = new Borrow(student, new Book(1232, "James Smith", "Paul T."));
        String actual = borrow.getDateBorrowed.get();

        assertEquals(expected, actual, "Should display date book was borrowed");
    }
}
