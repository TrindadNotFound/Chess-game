package chess_pieces;

import boardgame.Board;
import boardgame.Position;
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


	private boolean canMove(Position position)
	{
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	@Override 
	public boolean[][] possibleMoves() 
	{
		//Possible moves for King piece
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		
		//Above
		p.setValues(position.getRow() - 1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		
		//Below
		p.setValues(position.getRow() + 1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		
		//Left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		
		//Right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		
		//NW
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		
		//NE
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		
		//SW
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
			
		
		//SE
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		return matriz;
	}
}
