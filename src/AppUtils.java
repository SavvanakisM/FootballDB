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
    
}
