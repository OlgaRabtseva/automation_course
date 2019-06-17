package automationcourse.HW_Lesson_4;

public class Calculator {
//    public static void main(String[] args) {
//        Calculator calculator = new Calculator();
//        calculator.divideOperation(2147483647, 0);
//        calculator.plusOperation(2147483647, 2);
//        calculator.plusOperation(-505, -450);
//        calculator.multipleOperation(-505, 454654654);
//        calculator.minusOperation(-505, -450);
//    }

    public int plusOperation(long a, long b) {
        long result = a + b;
        //warningMessage(result);
        return (int) result;
    }

    public int multipleOperation(long a, long b) {
        long result = a * b;
        //warningMessage(result);
        return (int) result;
    }

    public int divideOperation(int a, int b) {
//        int result;
//        if (!isZero(b)) {
//            result = a / b;
//        } else {
//            result = 0;
//            System.out.println("You can't divide by 0");
//        }
        return a / b;
    }

    public int minusOperation(int a, int b) {
        //System.out.println(a - b);
        return a - b;
    }

    public String getMessage(String sign) {
        return "Please enter " + sign + " to get the result";
    }

//    private boolean isZero(int a) {
//        boolean isZero = false;
//        if (a == 0) {
//            isZero = true;
//        }
//        return isZero;
//    }
//
//    private boolean isTooBigOrTooSmall(long a) {
//        boolean isTooBigOrTooSmall = false;
//        if (a > Integer.MAX_VALUE || a < Integer.MIN_VALUE) {
//            isTooBigOrTooSmall = true;
//        }
//        return isTooBigOrTooSmall;
//    }
//
//    private void warningMessage(long a) {
//        if (isTooBigOrTooSmall(a)) {
//            System.out.println("Result is bigger than int can contain :(");
//        }
//    }
}
