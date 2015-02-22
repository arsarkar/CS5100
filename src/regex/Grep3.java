package regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep3 {

	
	
	public static void main(String[] args) {
		
		//read text from URL and print the content
		String inputString = "";
		try {
			//get the URL
			URL url = new URL("http://en.wikipedia.org/wiki/ohio");
			InputStream stream = url.openStream();
			// read text returned by server
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
			String line;
			//fill inputString
			while((line = br.readLine())!= null) {
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
//		Scanner sc = new Scanner(System.in);
		while(!exit){
			
			//get the regular expression and instantiate the pattern class
        	System.out.println("Regular expression: <img.*src=(\".*?\") \n");
            Pattern pattern = 
            Pattern.compile("<img.*src=(\".*?\")");
            
            //get the input string and instantiate the matcher class
            Matcher matcher = 
            pattern.matcher(inputString);
			
            //print the match and matching location once the match is found
            boolean found = false;
            int i = 0;
            String match;
            while (matcher.find()) {
                match = matcher.group().replaceAll("<img.*=\"","");
                match = match.substring(0, match.length()-1);
            	System.out.println( i + ": " + match+"\n");
                found = true;
                i++;
            }
            if(!found){
                System.out.format("No match found.%n");
            }
            System.out.println("\n--------------------------------------------------------\n");
            exit = true;
		}
		
	}

}
