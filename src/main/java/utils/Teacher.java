package utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * All Teacher type object are referenced
 * using the blueprint of this Teacher class
 */

public class Teacher extends Person
{
    private int id;

    public Teacher(int id, String firstName, String lastName, String gender)
    {
        super(firstName, lastName, gender);
        this.id = id;
    }

    public Supplier<Integer> getId = () -> id;

    @Override
    public String role() { return "teacher";}

    @Override
    public String toString() {
        return this.getBookBorrowed.get() == null ?
                "Teacher{" +
                "id=" + id +
                ", name=" + fullName.get() +
                ", gender=" + getGender.get() +
                '}':

                "Teacher{" +
                "id=" + id +
                ", name=" + fullName.get() +
                ", gender=" + getGender.get() +
                ", bookBorrowed=" + this.getBookBorrowed.get() +
                '}';
    }
}
