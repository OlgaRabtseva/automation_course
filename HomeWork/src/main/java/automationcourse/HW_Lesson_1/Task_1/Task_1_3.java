package automationcourse.HW_Lesson_1.Task_1;

import java.util.Arrays;
import java.util.Scanner;

public class Task_1_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter massive size: ");
        int masSize = input.nextInt();

        long[] mas = new long[masSize];
        for (int i = 0; i < masSize; i++) {
            mas[i] = Math.round(10 * Math.random());
        }
        System.out.println(Arrays.toString(mas));

        for (int i = 1; i <= masSize; i++) {
            if (i % 3 == 0) {
                mas[i - 1] *= 2;
            }
        }
        System.out.println(Arrays.toString(mas));

        for (int i = 2; i < masSize; i+=3) {
            mas[i] *= 2;
        }
        System.out.println(Arrays.toString(mas));
    }
}
