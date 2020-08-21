package utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * The super class of teacher and student within the library.
 * borrow a book by using the borrow request method.
 */

public abstract class Person
{
    private String firstName;
    private String lastName;
    private String gender;
    private Book bookBorrowed;

    public Person(String firstName, String lastName, String gender)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Supplier<String> getFirstName = () -> firstName;

    public Consumer<String> setFirstName = (String firstName) -> this.firstName = firstName;

    public Supplier<String> getLastName = () -> lastName;

    public Consumer<String> setLastName = (lastName) -> this.lastName = lastName;

    public Supplier<String> getGender = () -> gender;

    public Consumer<String> setGender = (String gender) -> this.gender = gender;


    public Supplier<String> fullName = () -> String.format("%s %s", firstName, lastName);


    /** bookBorrowed field to be set when
     * request to borrow is granted by the librarian
     * @param bookBorrowed to this student
     */
    public Consumer<Book> setBookBorrowed = (bookBorrowed) -> this.bookBorrowed = bookBorrowed;

    public Supplier<Book> getBookBorrowed = () -> bookBorrowed;


    /**
     * @return grant privileges to
     * people base on their role
     */
    public abstract String role();

}
