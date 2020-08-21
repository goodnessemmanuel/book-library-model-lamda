import utils.*;
import utils.Class;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main
{
    static Supplier<Library> supplyLibrary;
    static Supplier<Librarian> supplyLibrarian;
    static Library library;
    static Librarian librarian;
    static String fileName;
    static Scanner sc;
    static boolean running = true;

    public static void main(String[] args)
    {
        supplyLibrary = () -> new Library("Nelson Mandela"); //instantiate library supplier
        supplyLibrarian = () -> new Librarian("James", "Aristole", "male");

        //create book object
        Book book1 = new Book(3421, "Fundamentals of Physics", "P.N Okeke");
        book1.addCopies.accept(3);
        Book book2 =  new Book(1234, "Chemistry", "Thomas J.", 1);
        Book book3 =  new Book(3461, "Dominion Attitude", "Paul Joan", 5);

        //invoke library from supplier
        library = supplyLibrary.get();
        librarian = supplyLibrarian.get();

        //add book to library
        library.addBook.accept(book1);
        library.addBook.accept(book2);
        library.addBook.accept(book3);

        System.out.println(library.toString()); //view all library book

        //create teachers and student to use library
        Student student1 = new Student(1, "John", "Tennison", "male", new Class("Jss1"));
        Student student2 = new Student(2, "Kosh", "Stephanie", "female", new Class("SS2"));
        Teacher teacher1 = new Teacher(3, "Linda", "Gates", "female");
        Teacher teacher2 = new Teacher(2, "Andrew", "Gibson", "Male");

        //initiate all borrow request by teacher and/or student
        List<Borrow> libraryBorrowList = new LinkedList<>();
        libraryBorrowList.add(new Borrow(student1, book1));
        libraryBorrowList.add(new Borrow(student2, book2));
        libraryBorrowList.add(new Borrow(teacher1, book1));
        libraryBorrowList.add(new Borrow(teacher2, book3));

        //borrowers queue according to librarian order
        Queue<Borrow> borrowRequestsQueue = borrowQueueLine.apply(libraryBorrowList);

        //process all valid request queue
        processBookBorrow.accept(availableBookCopy, borrowRequestsQueue);

    }

    private static final Function<List<Borrow>, Queue<Borrow>> borrowQueueLine = (list) ->
    {
       Queue<Borrow> borrowRequestsQueue = new PriorityQueue<>(Librarian.firstComeFirstServe());
       try
       {
           list.forEach(borrowRequestsQueue::offer); //add each borrow request to the priority queue
       }
       catch (NoSuchElementException e)
       {
           e.printStackTrace();
       }
       return borrowRequestsQueue;
    };

    //Function process all valid borrow request in queue
    private static final BiConsumer<Function<Queue<Borrow>, List<Borrow>>, Queue<Borrow>> processBookBorrow = (process, queue) ->
    {
        List<Borrow> borrowList = process.apply(queue);
        borrowList.forEach(borrow -> {
            Person member = borrow.getMember.get(); //library member
             if (member.role().equals("student") || member.role().equals("teacher"))
             {
                 try
                 {
                     assert queue.peek() != null; //check the next person in queue
                     if (queue.peek().getMember.get().role().equals("teacher")
                             && borrow.getBook().getTotalCopy.get() <= 2)
                     {
                         System.out.println("Book copy is limited and can only be issued to a teacher");
                         queue.remove();
                     }
                     else if (borrow.getBook().getTotalCopy.get() == 1)
                     {
                         System.out.println("Book taken"); //last copy for library use
                     }
                     else
                     {
                         Borrow borrowRequest = Objects.requireNonNull(queue.poll()); //get request from queue
                         Book bookBorrowed = Librarian.give.apply(borrowRequest); //librarian approve
                         member.setBookBorrowed.accept(bookBorrowed); //record the book borrowed
                         System.out.println("Book successfully borrowed");
                     }

                 }
                 catch (NoSuchElementException e)
                 {
                     e.printStackTrace();
                 }
             }
        });
    };

    //function ensures more than  one copy of book
    private static final Function<Queue<Borrow>, List<Borrow>> availableBookCopy =
            (borrowQueue) -> borrowQueue.stream().filter(borrow -> borrow.getBook().getTotalCopy.get() > 1)
                    .collect(Collectors.toList());

    /**
     * call this method to run library from I/O
     * add files and resume library operation
     * when application come back on
     */
    public static void runLibraryFromIO()
    {
        library = supplyLibrary.get();

        sc = new Scanner(System.in);

        while (running)
        {

            System.out.println(instruction().get(0));
            System.out.println(instruction().get(1));
            System.out.println(instruction().get(2));
            System.out.println(instruction().get(3));

            try
            {
                int response = sc.nextInt();
                switch (response)
                {
                    case 0 -> loadLibraryBooks();

                    case 1 -> saveAndQuit();

                    case 2 -> System.out.println(library.toString());

                    case 3 -> addBookToLib();
                }
            }
            catch (NumberFormatException e)
            {
                System.err.println(e.getMessage());
            }

        }
        System.exit(0);
    }

    private static void loadLibraryBooks() {
        System.out.println("Choose file to load ");
        fileName = sc.next();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try
        {
            //save with dot ser
            fis = new FileInputStream(new File(fileName + ".ser"));
            ois = new ObjectInputStream(fis);
            library = (Library) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
           try
           {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            }
           catch (IOException e)
           {
               System.err.println("an error occurred " + e.getMessage());
           }
        }
    }

    //add books to library using scanner class
    private static void addBookToLib()
    {
        String  title, author;
        int isbn;

        System.out.println("\nEnter book title ");
        title = sc.next();
        System.out.println("\nEnter book author ");
        author = sc.next();
        System.out.println("\nEnter book isbn ");
        isbn = sc.nextInt();
        Book book = new Book(isbn, title, author);
        library.addBook.accept(book);
    }

    private static void saveAndQuit ()
    {
        library = supplyLibrary.get();
        System.out.println("Enter a file name: ");
        running = false;
        fileName = sc.next();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try
        {
            File file = new File(fileName + ".ser");
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(library); //write library object to output stream
            fos.flush();
            oos.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                assert fos != null;
                fos.close();

                assert oos != null;
                oos.close();
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }

    }

    static Map<Integer, String> instruction()
    {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "\nEnter 0 to load library");
        map.put(1, "\nEnter 1 to save and exit");
        map.put(2, "\nEnter 2 to list book in library");
        map.put(3, "\nEnter 3 to add book to library");

        return map;
    }
}
