package automationcourse.HW_Lesson_1.Task_1;

import java.util.Arrays;
import java.util.Scanner;

public class Task_1_12 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter massive size: ");
        int masSize = input.nextInt();

        long[] mas = new long[masSize];
        for (int i = 0; i < masSize; i++) {
            mas[i] = Math.round(10 * Math.random());
        }
        System.out.println(Arrays.toString(mas));

        shiftToOnePosition(mas);
        shiftToOnePosition(mas);
        System.out.println(Arrays.toString(mas));
    }

    private static void shiftToOnePosition(long[] mas) {
        int masSize = mas.length;

        long needToShift = mas[0];
        for (int i = 1; i < masSize; i++) {
            long oldValue = mas[i];
            mas[i] = needToShift;
            needToShift = oldValue;
            if(i + 1 == masSize) {
                mas[0] = needToShift;
            }
        }
    }

}
