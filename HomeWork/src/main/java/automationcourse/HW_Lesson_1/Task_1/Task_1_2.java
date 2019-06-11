package automationcourse.HW_Lesson_1.Task_1;

import java.util.Arrays;
import java.util.Scanner;

public class Task_1_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        System.out.print( "Enter massive size: " );
        int masSize = input.nextInt();

        long[] mas = new long[masSize];
        for (int i = 0; i < masSize ; i++) {
            mas[i] = Math.round(10 * Math.random());
        }
        System.out.println(Arrays.toString(mas));

        long result = 1L;
        int i = 0;
        while (i < masSize) {
            result *= mas[i];
            i++;
        }
        System.out.println(result);
    }
}
