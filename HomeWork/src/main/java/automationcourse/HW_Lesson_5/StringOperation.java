package automationcourse.HW_Lesson_5;

public class StringOperation {

    public int calculatePerimeter(int radius) {
        return (int) (2 * Math.PI * radius);
    }

    public String getSubString(String str) {
        return str.substring(5,9);
    }

    public String replaceString(String str) {
        return str.replaceAll(" ", " :) ");
    }

    public int indexOfChar(String str) {
        return str.indexOf("d");
    }

    public boolean matchTwoStrings(String str1, String str2) {
        return str1.equalsIgnoreCase(str2);
    }







}
