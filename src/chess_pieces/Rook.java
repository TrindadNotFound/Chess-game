package chess_pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece
{

	public Rook(Board board, Color color) 
	{
		super(board, color);
	}

	
	@Override
	public String toString()
	{
		//(R)ook is a piece of chess. In portuguese means "Torre"
		return "R";
	}
}