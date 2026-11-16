import java.util.HashMap;
import java.util.Scanner;

public class AppUtils {
    // Prompts until a valid entity name is entered and returns its corresponding ID
    public static int idChecker(String s1, String s2, Scanner scanner, HashMap<String, Integer> index) {     
        String s;
      
        while(true){
            System.out.println(s1);
            s=scanner.nextLine();
            if(index.containsKey(s)){
                return index.get(s);
            }
            System.out.println(s2);
            System.out.println("Do you want to retry? 1. Yes 2. No");
            if(scanner.nextLine().equals("2")){
                return -1;
            }
        }
    }

    public static String duplChecker(String n, HashMap<String, Integer> index, Scanner scanner){ 
        while(index.containsKey(n)){
            System.out.println(n + " already exists.\nDo you want to retry? 1. Yes 2. No");
            String c = scanner.nextLine();
            if(c.equals("2")){
                return "";
            }
            else if(c.equals("1")){
                System.out.println("Try again:");
                n = scanner.nextLine();
            }
            else
            {
                System.out.println("\nInvalid option. Please try again.");              
            }
        }
        return n;
    }
}