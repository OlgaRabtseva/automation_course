package automationcourse.HW_Lesson_2;

public class Example {
    public static void main(String[] args) {
        Person person = new Person("Olga");
        final Student studentOlga = new Student("Olga", 35, 1, "PVT", 9.9);
        System.out.println("Name of student: " + studentOlga.getName());
        System.out.println("Department of student: " + studentOlga.getDepartment());
        System.out.println("Course of student: " + studentOlga.getCourse());
        System.out.println("Average mark of student: " + studentOlga.getAverageMark());
        System.out.println("Organism type of student: " + studentOlga.getType());
        System.out.println("Student.toString() is :" + studentOlga.toString());

        System.out.println();

        final Methods methods = new Methods();
        int a = 5;
        int b = 8;
        System.out.println("Min val from [" + a + ", " + b + "] is " + methods.getMinInt(a, b));
        System.out.println("Integer " + a + " is " + (methods.isEven(a) ? "" : "not ") + "even");
        System.out.println("Integer " + b + " is " + (methods.isEven(b) ? "" : "not ") + "even");
        System.out.println("Square of " + a + " is " + methods.square(a));
        System.out.println("Cube of " + b + " is " + methods.cube(b));
    }
}
