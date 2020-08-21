package utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * All Student type object are referenced
 * using the blueprint of this Student class
 */

public class Student extends Person
{
    private int id;
    private Class sClass;

    public Student(int id, String firstName, String lastName, String gender, Class sClass)
    {
        super(firstName, lastName, gender);
        this.id = id;
        this.sClass = sClass;
    }

    @Override
    public String role() { return "Student";}

    public Supplier<Class> getsClass = () -> sClass;

    @Override
    public String toString() {
        return this.getBookBorrowed.get() == null ?

                "Student{" +
                "id=" + id +
                ", FullName=" + this.fullName.get() +
                ", FullName=" + this.getGender.get() +
                ", sClass=" + sClass.toString() +
                '}' :

                "Student{" +
                "id=" + id +
                ", FullName=" + this.fullName.get() +
                ", gender=" + this.getGender.get() +
                ", Class=" + sClass.toString() +
                ", bookBorrowed=" + this.getBookBorrowed.get() +
                '}';
    }
}
