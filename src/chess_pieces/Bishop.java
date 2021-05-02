package chess_pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece
{

	public Bishop(Board board, Color color) 
	{
		super(board, color);
	}

	
	@Override
	public String toString()
	{
		//(B)ishop is a piece of chess. In portuguese means "Bispo"
		return "B";
	}
	
	
	
	@Override 
	public boolean[][] possibleMoves() 
	{
		//Possible moves for Bishop piece
	
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);

		//NW the piece --------------------
		p.setValues(position.getRow()-1, position.getColumn() - 1);
		
		
		//WHILE position p exists AND there is no piece there THEN position is marked with TRUE value
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		
		//If exist a opponent piece then that position is marked with TRUE
		if(getBoard().positionExists(p) && isThereOpponentPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		//---------------------------------
		
		
		
		
		//NE of the piece --------------------
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		
		
		//WHILE position p exists AND there is no piece there THEN position is marked with TRUE value
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);;
		}
		
		//If exist a opponent piece then that position is marked with TRUE
		if(getBoard().positionExists(p) && isThereOpponentPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		//-----------------------------------

		
		
		
		//SE of the piece --------------------
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		
		
		//WHILE position p exists AND there is no piece there THEN position is marked with TRUE value
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		
		//If exist a opponent piece then that position is marked with TRUE
		if(getBoard().positionExists(p) && isThereOpponentPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		//-----------------------------------

		
		
		
		//SW the piece --------------------
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		
		
		//WHILE position p exists AND there is no piece there THEN position is marked with TRUE value
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		
		//If exist a opponent piece then that position is marked with TRUE
		if(getBoard().positionExists(p) && isThereOpponentPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		//-----------------------------------
		
		return matriz;
	}
}
