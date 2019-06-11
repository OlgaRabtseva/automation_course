package automationcourse.HW_Lesson_1.Task_1;

import java.util.Arrays;
import java.util.Scanner;

public class Task_1_4 {
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
                countOfZeros++;
            }
        }
        if(countOfZeros == 0) {
            System.out.println("Zeros are not found");
        } else {
            System.out.println("Count of zeros: " + countOfZeros);
        }
    }
}
