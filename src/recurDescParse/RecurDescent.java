package recurDescParse;

import java.util.*;
/**
 * @version 1.0
 * @author Arkopaul Sarkar
 *
 *Recursive Descent Parser for the following CFG
 *1.	E -> T|T+E|V=E
 *2.	T -> F|T*F
 *3.	F -> C|V|(E)
 *4.	V -> x|y|z
 *5.	C -> D|DC
 *6.	D -> 0|1|2|3|4|5|6|7|8|9
 *Only rule 2 is left recursive with a form T -> T*F|F
 *To remove left recursion, this rule can be substituted by the following two rules.
 *T -> FT’
 *T’ -> *FT’|$
 *Modified CFG without left recursion
 *1.	E -> T|T+E|V=E
 *2.	T -> FT’
 *3.	T’ -> *FT’|$
 *4.	F -> C|V|(E)
 *5.	V -> x|y|z
 *6.	C -> D|DC
 *7.	D -> 0|1|2|3|4|5|6|7|8|9
 */
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
            System.out.println("AST----->");
            if(!rd.expr()){
            	System.out.println("\nExpression is not valid");
            }
            else{
            	System.out.println("\nExpression is valid");
            }
            System.out.println("");	
            
        }
        stdin.close();
		
	}
	
	
	private boolean match(char c) {
		if (expression.size()>0){
			if(expression.get(0).equals(c)){
				System.out.print("\t"+expression.get(0));
				expression.remove(0);
				return true;
			}
		}
		return false;
		
	}
	
	//E ::= T|T+E|V=E 
	public boolean expr(){
		boolean isTerm = true;
		
		//peek into the next to next slot in the stack to select proper rule
		//from E -> T+E or E -> V=E
		if(expression.size()>1){
			if (expression.get(1).equals(equal))
				isTerm = false;
		}
		
		if (isTerm){
			if(term()){
				if (match(plus)){
					if(expr()){
						return true;
					}
				}
				return true;
			}
		}
		else{
			if(variable()){
				if(match(equal)){
					if(expr()){
						return true;
					}
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
			if(factor()){
				if(termPrime()){
					return true;
				}
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
			if(constant()){
				return true;
			}
			return true;
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

/** Console output---------------->

x+y
AST----->
	x	+	y
Expression is valid

x*y
AST----->
	x	*	y
Expression is valid

x+y*z
AST----->
	x	+	y	*	z
Expression is valid

z=x*y
AST----->
	z	=	x	*	y
Expression is valid

(x+y)
AST----->
	(	x	+	y	)
Expression is valid

z=3*x+4*y
AST----->
	z	=	3	*	x	+	4	*	y
Expression is valid

z=123*x+234*y
AST----->
	z	=	1	2	3	*	x	+	2	3	4	*	y
Expression is valid

z = x + 3 * (x+y)
AST----->
	z	=	x	+	3	*	(	x	+	y	)
Expression is valid

+xy
AST----->

Expression is not valid

z==x*y
AST----->
	z	=
Expression is not valid
**/