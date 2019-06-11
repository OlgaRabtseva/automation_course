package automationcourse.HW_Lesson_1.Task_0;

public class Task_0_0 {
    public static void main(String[] args) {
        //i++  сначала происходит присваивание переменной, а потом инктементация
        int i = 5;
        int a = i++;
        System.out.println("a = " + a + "\n" + "i = " + i);

        //++j сначала инкрементация j, только потом это значение присваивается переменной
        int j = 5;
        int b = ++j;
        System.out.println("b = " + b + "\n" + "j = " + j);
    }
}
