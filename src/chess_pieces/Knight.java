package chess_pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece
{

	public Knight(Board board, Color color) 
	{
		super(board, color);
	}

	
	@Override
	public String toString()
	{
		//K(N)ight is a piece of chess. In portuguese means "Cavalo"
		return "N";
	}


	private boolean canMove(Position position)
	{
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	@Override 
	public boolean[][] possibleMoves() 
	{
		//Possible moves for Knight piece
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		
		
		p.setValues(position.getRow() - 1, position.getColumn() - 2);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		
		
		p.setValues(position.getRow() - 2, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		
	
		p.setValues(position.getRow() - 2, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		
		
		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		
		
		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		
		
		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		
		
		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
			
		
		
		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		return matriz;
	}
}
