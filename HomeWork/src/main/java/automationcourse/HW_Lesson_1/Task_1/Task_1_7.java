package automationcourse.HW_Lesson_1.Task_1;

import java.util.Arrays;
import java.util.Scanner;

public class Task_1_7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter massive size: ");
        int masSize = input.nextInt();

        long[] mas = new long[masSize];
        for (int i = 0; i < masSize; i++) {
            mas[i] = Math.round(10 * Math.random());
        }
        System.out.println(Arrays.toString(mas));

        for (int i = 0; i < masSize/2; i++) {
            long opposite = mas[masSize - 1 - i];
            mas[masSize - 1 - i] = mas[i];
            mas[i] = opposite;
        }
        System.out.println(Arrays.toString(mas));
    }

}
