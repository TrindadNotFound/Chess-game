package chess_pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece
{

	public Pawn(Board board, Color color) 
	{
		super(board, color);
	}

	
	
	
	
	
	
	@Override
	public boolean[][] possibleMoves() 
	{
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		//Test possible moves for white pawn 
		if(getColor() == Color.WHITE)
		{
			//Test whether the pawn can move forward one position
			p.setValues(position.getRow() - 1, position.getColumn());
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
			{
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			
			//Test whether the pawn can move forward two position
			p.setValues(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) &&getMoveCount() == 0)
			{
				
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			
			//Test whether the pawn can attack an enemy piece
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			
			if(getBoard().positionExists(p) && isThereOpponentPiece(p))
			{
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			//Test whether the pawn can attack an enemy piece
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			
			if(getBoard().positionExists(p) && isThereOpponentPiece(p))
			{
				matrix[p.getRow()][p.getColumn()] = true;
			}
		}
		else
		{
			//Test whether the pawn can move forward one position
			p.setValues(position.getRow() + 1, position.getColumn());
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
			{
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			
			//Test whether the pawn can move forward two position
			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) &&getMoveCount() == 0)
			{
				
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			
			//Test whether the pawn can attack an enemy piece
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			
			if(getBoard().positionExists(p) && isThereOpponentPiece(p))
			{
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			//Test whether the pawn can attack an enemy piece
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			
			if(getBoard().positionExists(p) && isThereOpponentPiece(p))
			{
				matrix[p.getRow()][p.getColumn()] = true;
			}	
		}
		
		return matrix;
	}


	@Override
	public String toString()
	{
		return "P";
	}




	

}
