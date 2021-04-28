package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece
{
	private Color color;
	
	
	//Constructor
	public ChessPiece(Board board, Color color)
	{
		super(board);
		this.color = color;
	}


	
	//Getters
	public Color getColor() 
	{
		return color;
	}

	
	public ChessPosition getChessPosition()
	{
		return ChessPosition .fromPosition(position);
	}
	
	
	
	//Allows you to move a piece to a position where an enemy piece exist
	protected boolean isThereOpponentPiece(Position position)
	{
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		//Check if piece color is different from the piece that's going moved
		return p != null && p.getColor() != color;
	}
	
}
