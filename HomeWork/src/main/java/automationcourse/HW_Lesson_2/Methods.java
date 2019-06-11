package automationcourse.HW_Lesson_2;

public class Methods {

    public int getMinInt(final int val1, final int val2) {
        return val1 <= val2 ? val1 : val2;
    }

    public boolean isEven(final int val) {
        return val % 2 == 0;
    }

    public int square(final int val) {
        return (int) Math.pow(val, 2);
    }

    public int cube(final int val) {
        return (int) Math.pow(val, 3);
    }
}
