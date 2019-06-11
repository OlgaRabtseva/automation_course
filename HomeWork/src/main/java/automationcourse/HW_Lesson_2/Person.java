package automationcourse.HW_Lesson_2;

import java.util.Objects;

public class Person extends Organism implements Human {

    private String name;

    public Person(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    String getType() {
        return "mammal :)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "\nPerson {" +
                "\n\tname: '" + name + '\'' +
                "\n}";
    }
}
