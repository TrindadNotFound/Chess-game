package chess_pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece
{

	private ChessMatch chessMatch;
	
	public Pawn(Board board, Color color, ChessMatch chessMatch) 
	{
		super(board, color);
		this.chessMatch = chessMatch;
	}

	
	@Override
	public String toString()
	{
		//(P)awn is a piece of chess. In portuguese means "Pẽao"
		return "P";
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
			
			//Special move - En Passant (WHITE pieces)
			if(position.getRow() == 3)
			{
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable())
				{
					matrix[left.getRow() - 1][left.getColumn()] = true;
				}
				
				
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				
				if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable())
				{
					matrix[right.getRow() - 1][right.getColumn()] = true;
				}
			}
			
		}
		else //Color == BLACK
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
			
			
			
			
			//Special move - En Passant (BLACK pieces)
			if(position.getRow() == 4)
			{
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable())
				{
					matrix[left.getRow() + 1][left.getColumn()] = true;
				}
				
				
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				
				if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable())
				{
					matrix[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}
		
		return matrix;
	}

}
