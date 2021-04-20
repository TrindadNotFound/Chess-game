package chess_pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece
{

	public King(Board board, Color color) 
	{
		super(board, color);
	}

	
	@Override
	public String toString()
	{
		//(K)ing is a piece of chess. In portuguese means "Rei"
		return "K";
	}
}
