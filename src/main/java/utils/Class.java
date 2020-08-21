package utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Class
{
    private String name;

    public Class (String name)
    {
        this.name = name;
    }

    public Supplier<String> getName = () -> name;

    public Consumer<String> setName = (name) -> this.name = name;

    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                '}';
    }
}
