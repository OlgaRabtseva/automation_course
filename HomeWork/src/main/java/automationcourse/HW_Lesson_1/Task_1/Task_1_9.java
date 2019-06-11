package automationcourse.HW_Lesson_1.Task_1;

import java.util.Arrays;
import java.util.Scanner;

public class Task_1_9 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter massive size: ");
        int masSize = input.nextInt();

        long[] mas = new long[masSize];
        for (int i = 0; i < masSize; i++) {
            mas[i] = Math.round(100 * Math.random());
        }
        System.out.println(Arrays.toString(mas));

        long min = mas[0];
        int minIndex = 0;
        long max = mas[0];
        int maxIndex = 0;
        for (int i = 1; i < masSize; i++) {
            if (mas[i] >= max) {
                maxIndex = i;
                max = mas[i];
            } else if (mas[i] <= min) {
                minIndex = i;
                min = mas[i];
            }
        }
        System.out.println("Max index = " + maxIndex);
        System.out.println("Min index = " + minIndex);
    }

}
