package recurDescParse;

import java.util.*;

public class RecurDescent {
	
	static char plus = '+';
	static char equal = '=';
	static char star = '*';
	static char empty = '$';
	static char leftBracket = '(';
	static char rightBracket = ')';
	static char varX = 'x';
	static char varY = 'y';
	static char varZ = 'z';
	static char[] digits = {'0','1','2','3','4','5','6','7','8','9'};
	List<Character> expression = new LinkedList<Character>();
	
	public static void main(String[] args){
		
		RecurDescent rd = new RecurDescent();
		Scanner stdin = new Scanner(System.in);
        while (stdin.hasNext()) {
        	
        	//get the next line from console
        	String exp = stdin.nextLine();
        	//Remove whitespace
        	exp = exp.replaceAll("\\s", "");
        	//exit the console when end is reached
        	if (exp.equals("$")){
        		break;
        	}
        	//initialize queue
        	rd.expression.clear();
        	//split into tokens
            for(int i = 0; i<exp.length();i++){
            	rd.expression.add(exp.charAt(i));
            }
            //call non-terminal function to start parsing
            if(!rd.expr()){
            	System.out.println("Expression is not valid");
            }
            
        }
        stdin.close();
		
	}
	
	
	private boolean match(char c) {
		
		if(expression.get(0).equals(c)){
			expression.remove(0);
			return true;
		}
		return false;
		
	}
	
	//E ::= T|T+E|V=E 
	public boolean expr(){
		
		if (term()){
			if (match(plus)){
				if(expr()){
					return true;
				}
			}
			return true;
		}
		else if (variable()){
			if (match(equal)){
				if (expr()){
					return true;
				}
			}
		}		
		return false;
	}

	//T ::= FT’
	private boolean term() {
		if (factor()){
			if (termPrime()){
				return true;
			}
		}
		return false;
	}

	//T’ ::= *FT’|$
	private boolean termPrime() {
		if(match(star)){
			if(termPrime()){
				return true;
			}
		}
		return true;
	}

	//F ::= C|V|(E)
	private boolean factor() {
		if (constant()){
			return true;
		}
		else if(variable()){
			return true;
		}
		else if(match(leftBracket)){
			if(expr()){
				if (match(rightBracket)){
					return true;
				}
			}
		}
		return false;
	}
	
	//V ::= x|y|z
	private boolean variable() {
		if(match(varX)){
			return true;
		}
		else if (match(varY)){
			return true;
		}
		else if (match(varZ)){
			return true;
		}
		return false;
	}

	//C ::= D|DC
	private boolean constant() {
		if (digit()){
			return true;
		}
		else if (digit()){
			if(constant()){
				return true;
			}
		}
		return false;
	}

	//D ::= 0|1|2|3|4|5|6|7|8|9
	private boolean digit() {
		for(char c:digits){
			if(match(c)){
				return true;
			}
		}
		return false;
	}
	
	
}
