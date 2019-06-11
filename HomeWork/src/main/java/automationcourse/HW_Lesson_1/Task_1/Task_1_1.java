package automationcourse.HW_Lesson_1.Task_1;

import java.util.Scanner;

public class Task_1_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        System.out.print( "Enter massive size: " );
        int masSize = input.nextInt();

        int[] mas = new int[masSize];
        for (int i = 0; i < masSize ; i++) {
            mas[i] = (int) Math.round(100 * Math.random());
        }

        int i = 0;
        while (i < masSize) {
            System.out.print(mas[i] + " ");
            i++;
        }
        System.out.println();
        i = 0;
        while (i < masSize) {
            System.out.print(mas[masSize - 1 - i] + " ");
            i++;
        }
    }
}
