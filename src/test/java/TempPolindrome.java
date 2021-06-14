import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TempPolindrome {
    private final static Logger LOGGER = LogManager.getLogger(TempPolindrome.class);

    public static void main(String[] args) {
        String word = "civic";

//        System.out.println(checkPolindrome(word));

        System.out.println(calculateTax(-120000,true));
    }
    public static boolean checkPolindrome(String word){
        boolean isPolindrome = false;
        int len = word.length();
        String reverse = "";

        for (int i = len-1; i>=0;  i --) {
            reverse += word.charAt(i);
        }
        System.out.println(reverse);

        return reverse.equals(word);


    }
    public static int missingNum(int[] arr, int n){
        int actualSum= 0;
        int expectedSum = n*(n+1)/2;

        for (int num:arr ) {
            actualSum = actualSum+num;

        }
        return expectedSum -actualSum;

    }
















    public  static  double calculateTax(double salary, boolean marriageStatus){
        LOGGER.info("Received request to calculate tax");
        LOGGER.info("Person's salary is "+ salary + " , marital status is "+ marriageStatus);

        if(salary >100000 && marriageStatus ){
            LOGGER.info("your tax is only 20%");
            return salary * 0.2;
        }else if(salary > 100000 && !marriageStatus){
            LOGGER.info("your tax is only 30%");
            return salary *0.3;
        }else if(salary<0){
            LOGGER.error("salary can not be negative");
            return 0;
        }
        LOGGER.info("tax only 10%");
        return salary* 0.1;
    }



}
