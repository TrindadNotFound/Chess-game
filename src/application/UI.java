package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI
{
	
	//Colors
	//Thanks to: https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	
	//This is to clean screen (cmd)
	public static void clearScreen()
	{
		//Copy from https://www.javatpoint.com/how-to-clear-screen-in-java
		System.out.print("\033[H\033[2J");  
		System.out.flush();   
	}
	
	public static void printBoard(ChessPiece[][] pieces)
	{
		System.out.println();
		System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "  --- CHESS GAME --- " + ANSI_RESET);
		//Top Border
		System.out.println(ANSI_RED + "_____________________" + ANSI_RESET);
		
		
		//Print game board
		for(int i = 0; i<pieces.length; i++)
		{
			System.out.print(ANSI_RED + "|" + ANSI_RESET + (8-i) + "  ");
			for(int ii = 0; ii<pieces.length; ii++)
			{
				printPiece(pieces[i][ii], false);
			}
			System.out.println(ANSI_RED + "|" + ANSI_RESET);
		}
		System.out.println(ANSI_RED + "|" + ANSI_RESET + "   a b c d e f g h " + ANSI_RED + "|" + ANSI_RESET);
		
		//Bottom Boarder
		System.out.println(ANSI_RED + "|___________________|" + ANSI_RESET);
		
		System.out.println();
		
		System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "Green" + ANSI_RESET +  " = White pieces");
		System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "Yellow" + ANSI_RESET + " = Black pieces");
	}
	
	
	
	//Show how many possible movements have and paint them
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves)
	{
		System.out.println();
		System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "  --- CHESS GAME --- " + ANSI_RESET);
		
		//Top Border
		System.out.println(ANSI_RED + "_____________________" + ANSI_RESET);
		
		
		//Print game board
		for(int i = 0; i<pieces.length; i++)
		{
			System.out.print(ANSI_RED + "|" + ANSI_RESET + (8-i) + "  ");
			for(int ii = 0; ii<pieces.length; ii++)
			{
				printPiece(pieces[i][ii], possibleMoves[i][ii]);
			}
			System.out.println(ANSI_RED + "|" + ANSI_RESET);
		}
		System.out.println(ANSI_RED + "|" + ANSI_RESET + "   a b c d e f g h " + ANSI_RED + "|" + ANSI_RESET);
		
		//Bottom Boarder
		System.out.println(ANSI_RED + "|___________________|" + ANSI_RESET);
		
		System.out.println();
		
		System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "Green" + ANSI_RESET +  " = White pieces");
		System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "Yellow" + ANSI_RESET + " = Black pieces");
	}
	
	
	
	private static void printPiece(ChessPiece piece, boolean background)
	{
		//Paint background in blue
		if(background)
		{
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		
		//Print pieces and empty positions
		if(piece == null)
		{
			System.out.print("-" + ANSI_RESET);
		}
		else
		{
			if(piece.getColor() == Color.WHITE)
			{
				System.out.print(ANSI_GREEN + piece + ANSI_RESET);
			}
			else
			{
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	
	
	//Read chess movements from players
	public static ChessPosition readChessPosition(Scanner sc)
	{
		try 
		{
			String s = sc.nextLine();
			
			char column = s.charAt(0);
			
			int row = Integer.parseInt(s.substring(1));
			
			return new ChessPosition(column, row);
		}
		catch (RuntimeException e) 
		{
			throw new InputMismatchException("Error: Chess position unvalable: Valid positions between a1 and h8");
		}

	}
}
