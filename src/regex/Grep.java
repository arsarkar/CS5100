package regex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A test class to test the regex utility in java
 * @author sarkara1
 * @version 1.0
 */
public class Grep {

	
	Matcher matcher;
	Pattern regex;
	
	/*
	 * Constructor to instantiate Patter and Matcher
	 */
	public Grep(String input, String exp){
		this.regex = Pattern.compile(exp);
		if (regex != null)
			this.matcher = regex.matcher(input);
		System.out.println("Input: "+ input);
		System.out.println("regex: "+exp);
	}
	
	/*
	 * If the match is found it is printed 
	 */
	public void printMatch(){
		boolean success = false;
		while (matcher.find()) {
            System.out.format("I found the text" +
                " \"%s\" starting at " +
                "index %d and ending at index %d.%n",
                matcher.group(),
                matcher.start(),
                matcher.end());
            success = true;
		}
		if(!success)
			System.out.println("No match found!");
	}
	
	public static String getInput(){
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	
	public static void main(String[] args) {
		
		boolean complete = false;
		String inputString = "";
		String exp = "";
		Scanner sc = new Scanner(System.in);
    	if (sc != null) {
    		while (!complete){
    			//take the regular expression from scanner
    			System.out.println("Enter the regular expression");
    			exp = getInput();
    			
                //provision to take either a string or a file
    			System.out.println("Enter 1 for a string or 2 for a filename");
                switch (sc.nextInt()) {
    				case 1:
    					// take an input string from user
    					System.out.println("Enter string");
    					inputString = getInput();
    		            break;
    				case 2:
    					//read a txt file to the inputString
    					System.out.println("Enter fileName");
    					FileReader inputFile = null;
    					BufferedReader bufferReader = null;
    					try {
    						inputFile = new FileReader(getInput());
    						bufferReader = new BufferedReader(inputFile);
    				        String line = "";
    						while ((line = bufferReader.readLine())!= null)   {
    				        	if (inputString.isEmpty())
    				        		inputString = line;
    				        	else
    				        		inputString = inputString + "\n" + line;
    				        }
    				        bufferReader.close();
    				        inputFile.close();
    					} catch (FileNotFoundException e) {
    						e.printStackTrace();
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				default:
    					break;
    				}
                //call the main class to use matcher 
                Grep grep = new Grep(inputString, exp);
	            //print all matches found
                grep.printMatch();
	            
                // get ready for next iteration
                System.out.println("Want to continue(Y/N)?");
                if (sc.nextLine().equalsIgnoreCase("Y"))
                	complete = false;
                else
                	complete = true;
            }
        }
		
	}

}
