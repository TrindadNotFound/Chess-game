package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		List<ChessPiece> capturedList = new ArrayList<>();
		
		//While checkMate != true run program
		while(!chessMatch.getCheckMate())
		{
			try
			{
				//First of all, time time to clear screen
				UI.clearScreen();
				
				UI.printMatch(chessMatch, capturedList);
				System.out.println();
				System.out.print("#Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				
				//Matrix to show how many possible moves exists
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.println("#Source: " + source);
				System.out.print("#Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			
				if(capturedPiece != null)
				{
					capturedList.add(capturedPiece);
				}
			
				//Ask for piece to promotion
				if(chessMatch.getPromoted() != null)
				{
					System.out.print("Enter piece for promotion (B / N / R / Q): ");
					String type = sc.nextLine().toUpperCase();
					
					while(!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q"))
					{
						System.out.print("Invalid piece. Try again: ");
						type = sc.nextLine().toUpperCase();
					}
					
					
					chessMatch.replacePromotedPiece(type);
				}
			}
			catch(ChessException e)
			{
				System.out.println();
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e)
			{
				System.out.println();
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
		UI.clearScreen();
		UI.printMatch(chessMatch, capturedList);
	}
}
