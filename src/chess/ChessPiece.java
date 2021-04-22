package chess;

import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece
{
	private Color color;
	
	
	//Constructor
	public ChessPiece(Board board, Color color)
	{
		super(board);
		this.color = color;
	}


	
	//Getter
	public Color getColor() 
	{
		return color;
	}

	
	
}
