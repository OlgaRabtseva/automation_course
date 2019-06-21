package automationcourse.HW_Lesson_6;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        //System.out.println( "Hello World!" );
        //System.out.println(app.calculatePerimeter(5));
        System.out.println(app.stringListCreator("sd","adsd",1).toString());

    }
    public int calculatePerimeter(int radius) {
        return (int) (2 * Math.PI * radius);
    }

    public String getSubString(String str) {
        return str.substring(5,9);
    }

    public String replaceString(String str) {
        return str.replaceAll(" ", " ! ");
    }

    public int indexOfChar(String str) {
        return str.indexOf("d");
    }

    public boolean matchTwoStrings(String str1, String str2) {
        return str1.equalsIgnoreCase(str2);
    }

    public Object[][] stringListCreator(String str1, String str2, int number) {
        return new Object[][] {
                {str1,str2,number},
                {str1,str1,number},
                {number,str2,str1}
        };
    }

}
