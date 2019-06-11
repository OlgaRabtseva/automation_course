package automationcourse.HW_Lesson_1.Task_1;

import java.util.Arrays;
import java.util.Scanner;

public class Task_1_5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter massive size: ");
        int masSize = input.nextInt();

        long[] mas = new long[masSize];
        for (int i = 0; i < masSize; i++) {
            mas[i] = Math.round(10 * Math.random());
        }
        System.out.println(Arrays.toString(mas));

        int countOfZeros = 0;
        for (int i = 0; i < masSize; i++) {
            if (mas[i] == 0) {
                System.out.print(i + " ");
                countOfZeros++;
            }
        }
        if(countOfZeros == 0) {
            System.out.println("Zeros are not found");
        }
    }
}
