package compiler;

import java.util.*;

public class Lexer_Test {

	public static void main(String[] args) {
		String str;
		Lexer lexer = new Lexer();
		str = lexer.readFile("Test3.txt");
		System.out.println(str);
		ArrayList<Token> token = new ArrayList<Token>();
		token = lexer.analyse(str);
		for(Token t : token) {
			System.out.println("< " + t.key + " , " + t.value + " >");
		}
	}

}
