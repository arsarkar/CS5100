package regex;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep2 {

	
	
	public static void main(String[] args) {
		
		//read a text file and print the content
		String inputString = "";
		try {
			for (String line : Files.readAllLines(Paths.get("pos.txt"), Charset.defaultCharset())) {
				if (inputString.isEmpty())
					inputString = line;
				else
					inputString += "\n"+line;
			}
			System.out.println(inputString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n--------------------------------------------------------\n");
		
		//now read the regular expressions and use the matcher to find the matches
		boolean exit = false;
		Scanner sc = new Scanner(System.in);
		while(!exit){
			
			//get the regular expression and instantiate the pattern class
        	System.out.println("Enter the regular expression");
            Pattern pattern = 
            Pattern.compile(sc.nextLine());
            
            //get the input string and instantiate the matcher class
            Matcher matcher = 
            pattern.matcher(inputString);
			
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
            System.out.println("\n--------------------------------------------------------\n");
		}
		
	}

}
