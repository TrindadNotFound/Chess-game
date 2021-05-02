package chess_pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece
{

	public Queen(Board board, Color color) 
	{
		super(board, color);
	}

	
	@Override
	public String toString()
	{
		//(Q)ueen is a piece of chess. In portuguese means "Rainha"
		return "Q";
	}
	
	
	
	@Override 
	public boolean[][] possibleMoves() 
	{
		//Possible moves for Queen piece
	
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);

		//Above the piece --------------------
		p.setValues(position.getRow()-1, position.getColumn());
		
		
		//WHILE position p exists AND there is no piece there THEN position is marked with TRUE value
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		
		//If exist a opponent piece then that position is marked with TRUE
		if(getBoard().positionExists(p) && isThereOpponentPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		//---------------------------------
		
		
		
		
		//Left of the piece --------------------
		p.setValues(position.getRow(), position.getColumn() - 1);
		
		
		//WHILE position p exists AND there is no piece there THEN position is marked with TRUE value
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		
		//If exist a opponent piece then that position is marked with TRUE
		if(getBoard().positionExists(p) && isThereOpponentPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		//-----------------------------------

		
		
		
		//Right of the piece --------------------
		p.setValues(position.getRow(), position.getColumn() + 1);
		
		
		//WHILE position p exists AND there is no piece there THEN position is marked with TRUE value
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		
		//If exist a opponent piece then that position is marked with TRUE
		if(getBoard().positionExists(p) && isThereOpponentPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		//-----------------------------------

		
		
		
		//Below the piece --------------------
		p.setValues(position.getRow() + 1, position.getColumn());
		
		
		//WHILE position p exists AND there is no piece there THEN position is marked with TRUE value
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		
		//If exist a opponent piece then that position is marked with TRUE
		if(getBoard().positionExists(p) && isThereOpponentPiece(p))
		{
			matriz[p.getRow()][p.getColumn()] = true;
		}
		//-----------------------------------
		
		
		
		
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
