package chess_pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece
{

	private ChessMatch chessMatch;
	
	public King(Board board, Color color, ChessMatch chessMatch) 
	{
		super(board, color);
		this.chessMatch = chessMatch;
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
	
	//Special move - Rook Castling
	private boolean testRookCastling(Position position)
	{
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		
		if(p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0)
		{
			return true;
		}
		else return false;
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
		
		
		//Special move castling
		if(getMoveCount() == 0 && !chessMatch.getCheck())
		{
			//Kingside rook
			Position pos1 = new Position(position.getRow(), position.getColumn() + 3);
			
			if(testRookCastling(pos1))
			{
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null)
				{
					matriz[position.getRow()][position.getColumn() + 2] = true;
				}
				
			}
			
			
			
			//Queenside rook
			Position pos2 = new Position(position.getRow(), position.getColumn() - 4);
			
			if(testRookCastling(pos1))
			{
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null)
				{
					matriz[position.getRow()][position.getColumn() - 2] = true;
				}
				
			}
		}
		
		return matriz;
	}
}
