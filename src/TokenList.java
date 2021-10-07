package compiler;

public enum TokenList {
	// Id
	Identifier,			// Identifier
	// Number		
	Number,				// Number
	// KeyWord
	Void,				// void
	Main,				// main
	Int,				// int
	If,					// if
	Else,				// else
	Switch,				// switch
	Case,				// case
	For,				// for
	While,				// while
	Do,					// do
	Return,				// return
	// Operator
	Plus,				// +
	Minus,				// -	
	Multiply,			// *
	Divide,				// /
	Less,				// <
	LessEqual,			// <=
	Greater,			// >
	GreaterEqual,		// >=
	Equal,				// ==
	Assign,				// =
	// Break
	Semicolon,			// ;
	Comma,				// ,
	LeftRoundBracket,	// (
	RightRoundBracket,	// )
	LeftSquareBracket,	// [
	RightSquareBracket,	// ]	
	LeftCurlyBracket,	// {
	RightCurlyBracket	// }
}
