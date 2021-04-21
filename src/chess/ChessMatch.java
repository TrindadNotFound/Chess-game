package chess;

import boardgame.Board;
import chess_pieces.King;
import chess_pieces.Rook;

public class ChessMatch
{
	private Board board;
	
	public ChessMatch()
	{
		board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces()
	{
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for(int i = 0; i<board.getRows(); i++)
		{
			for(int ii= 0; ii<board.getColumns(); ii++)
			{
				//Downcast to ChessPiece
				mat[i][ii] = (ChessPiece)board.piece(i, ii);
			}
		}
		
		return mat;
	}
	
	
	private void initialSetup()
	{
		placeNewPieces('a', 1, new Rook(board, Color.WHITE));
		placeNewPieces('b', 1, new King(board, Color.BLACK));
		placeNewPieces('a', 8, new King(board, Color.WHITE));
	}
	
	private void placeNewPieces(char column, int row, ChessPiece piece)
	{
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		
	}
}
