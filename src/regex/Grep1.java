package regex;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Grep1 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (true) {
        	
        	//get the regular expression and instantiate the pattern class
        	System.out.println("Enter the regular expression");
            Pattern pattern = 
            Pattern.compile(sc.nextLine());
            
            //get the input string and instantiate the matcher class
            System.out.println("Enter string");
            Matcher matcher = 
            pattern.matcher(sc.nextLine());

            //print the match and matching location once the match is found
            boolean found = false;
            while (matcher.find()) {
                System.out.format("I found the text" +
                    " \"%s\" starting at " +
                    "index %d and ending at index %d.%n",
                    matcher.group(),
                    matcher.start(),
                    matcher.end());
                found = true;
            }
            if(!found){
                System.out.format("No match found.%n");
            }
        }
    }
}
