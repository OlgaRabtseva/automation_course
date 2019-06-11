package automationcourse.HW_Lesson_2;

import java.util.Objects;

public class Student extends Person {

    private int age;
    private int course;
    private String department;
    private double averageMark;

    public Student(final String name, final int age, final int course,
                   final String department, final double averageMark) {
        super(name);
        this.age = age;
        this.course = course;
        this.department = department;
        this.averageMark = averageMark;
    }

    @Override
    public String getName() {
        return super.getName() + ", age: " + getAge();
    }

    private int getAge() {
        return age;
    }

    protected int getCourse() {
        return course;
    }

    String getDepartment() {
        return department;
    }

    public double getAverageMark() {
        return averageMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return age == student.age &&
                course == student.course &&
                Double.compare(student.averageMark, averageMark) == 0 &&
                department.equals(student.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), age, course, department, averageMark);
    }

    @Override
    public String toString() {
        return "\nStudent {" +
                "\n\tname: " + super.getName() +
                "\n\tage: " + age +
                "\n\tcourse: " + course +
                "\n\tdepartment: '" + department + '\'' +
                "\n\taverageMark: " + averageMark +
                "\n\ttype: " + getType() +
                "\n}";
    }
}
