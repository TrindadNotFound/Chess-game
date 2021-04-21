package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;

public class UI
{
		
	public static void printBoard(ChessPiece[][] pieces)
	{
		
		System.out.println(" --- CHESS GAME ---");
		//Top Border
		System.out.println("_____________________");
		
		
		//Print game board
		for(int i = 0; i<pieces.length; i++)
		{
			System.out.print("| " + (8-i) + " ");
			for(int ii = 0; ii<pieces.length; ii++)
			{
				printPiece(pieces[i][ii]);
			}
			System.out.println("|");
		}
		System.out.println("|   a b c d e f g h |");
		
		//Bottom Boarder
		System.out.println("|___________________|");
	}
	
	
	private static void printPiece(ChessPiece piece)
	{
		//Print one piece
		if(piece == null)
		{
			System.out.print("-");
		}
		else
		{
			System.out.print(piece);
		}
		System.out.print(" ");
	}
	
	
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
