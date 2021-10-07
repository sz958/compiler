package compiler;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class Lexer {
	public String readFile(String file) {
		String line;
		StringBuffer buffer = new StringBuffer();
		String fileString;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null) {
				buffer.append(line);
				//buffer.append("\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileString = buffer.toString();
		return fileString;
	}
	
	public int firstChar(String fileString, int head) {
		while(true) {
			if(fileString.charAt(head) == ' ' || fileString.charAt(head) == '\n')
				head++;
			else
				return head;
		}
	}
	
	public Hashtable<String, String> keyword_list() {
		Hashtable<String, String> keyword = new Hashtable<String, String>();
		keyword.put("void", TokenList.Void.name());
		keyword.put("main", TokenList.Main.name());
		keyword.put("int", TokenList.Int.name());
		keyword.put("if", TokenList.If.name());
		keyword.put("else", TokenList.Else.name());
		keyword.put("switch", TokenList.Switch.name());
		keyword.put("case", TokenList.Case.name());
		keyword.put("for", TokenList.For.name());
		keyword.put("while", TokenList.While.name());
		keyword.put("do", TokenList.Do.name());
		keyword.put("return", TokenList.Return.name());
		return keyword;
	}
	
	public ArrayList<Token> analyse(String fileString){
		int len = fileString.length();
		int head = 0;
		int tail = 0;
		String word;
		char ch;
		ArrayList<Token> token = new ArrayList<Token>();
		Hashtable keyword = keyword_list();
		while(head < len && tail < len) {
			head = firstChar(fileString, head);
			ch = fileString.charAt(head);
			tail = head + 1;
			if(Character.isLetter(ch) || ch == '_') {
				 while(Character.isLetter(fileString.charAt(tail)) || Character.isDigit(fileString.charAt(tail)) || fileString.charAt(tail) == '_') {
					 tail++;
				 }
				 word = fileString.substring(head, tail);
				 if(keyword.containsKey(word)) {
					 token.add(new Token(keyword.get(word).toString(),word));
				 }
				 else {
					 token.add(new Token(TokenList.Identifier.name(),word));
				 }
				 head = tail;
			}else if(Character.isDigit(ch)) {
				while(Character.isDigit(fileString.charAt(tail))) {
					 tail++;
				 }
				 word = Integer.valueOf(fileString.substring(head, tail)).toString();
				 token.add(new Token(TokenList.Number.name(),word));
				 head = tail;
			}else {
				switch(ch) {
				case '+':
					word = Character.toString(ch);
					token.add(new Token(TokenList.Plus.name(),word));
					head = tail;
					break;
				case '-':
					if(Character.isDigit(fileString.charAt(tail))) {
						while(Character.isDigit(fileString.charAt(tail))) {
							 tail++;
						 }
						word = Integer.valueOf(fileString.substring(head, tail)).toString();
						token.add(new Token(TokenList.Number.name(),word));
					}
					else {
						word = Character.toString(ch);
						token.add(new Token(TokenList.Minus.name(),word));
					}
					head = tail;
					break;
				case '*':
					word = Character.toString(ch);
					token.add(new Token(TokenList.Multiply.name(),word));
					head = tail;
					break;
				case '/':
					word = Character.toString(ch);
					token.add(new Token(TokenList.Divide.name(),word));
					head = tail;
					break;
				case '>':
					if(fileString.charAt(tail) == '=') {
						++tail;
						word = fileString.substring(head, tail);
						token.add(new Token(TokenList.GreaterEqual.name(),word));
					}
					else {
						word = Character.toString(ch);
						token.add(new Token(TokenList.Greater.name(),word));
					}
					head = tail;
					break;
				case '<':
					if(fileString.charAt(tail) == '=') {
						++tail;
						word = fileString.substring(head, tail);
						token.add(new Token(TokenList.LessEqual.name(),word));
					}
					else {
						word = Character.toString(ch);
						token.add(new Token(TokenList.Less.name(),word));
					}
					head = tail;
					break;
				case '=':
					if(fileString.charAt(tail) == '=') {
						++tail;
						word = fileString.substring(head, tail);
						token.add(new Token(TokenList.Equal.name(),word));
					}
					else {
						word = Character.toString(ch);
						token.add(new Token(TokenList.Assign.name(),word));
					}
					head = tail;
					break;
				case '(':
					word = Character.toString(ch);
					token.add(new Token(TokenList.LeftRoundBracket.name(),word));
					head = tail;
					break;
				case ')':
					word = Character.toString(ch);
					token.add(new Token(TokenList.RightRoundBracket.name(),word));
					head = tail;
					break;
				case '[':
					word = Character.toString(ch);
					token.add(new Token(TokenList.LeftSquareBracket.name(),word));
					head = tail;
					break;
				case ']':
					word = Character.toString(ch);
					token.add(new Token(TokenList.RightSquareBracket.name(),word));
					head = tail;
					break;
				case '{':
					word = Character.toString(ch);
					token.add(new Token(TokenList.LeftCurlyBracket.name(),word));
					head = tail;
					break;
				case '}':
					word = Character.toString(ch);
					token.add(new Token(TokenList.RightCurlyBracket.name(),word));
					head = tail;
					break;
				case ',':
					word = Character.toString(ch);
					token.add(new Token(TokenList.Comma.name(),word));
					head = tail;
					break;
				case ';':
					word = Character.toString(ch);
					token.add(new Token(TokenList.Semicolon.name(),word));
					head = tail;
					break;
				}
			}

		}
		return token;
	}
}
