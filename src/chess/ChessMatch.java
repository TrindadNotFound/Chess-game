package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess_pieces.King;
import chess_pieces.Rook;

public class ChessMatch
{
	private Board board;
	private int turn;
	private Color currentPlayer;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	
	private void initialSetup()
	{
		//White pieces
		placeNewPieces('a', 2, new Rook(board, Color.WHITE));
		placeNewPieces('b', 2, new Rook(board, Color.WHITE));
		placeNewPieces('c', 2, new Rook(board, Color.WHITE));
		placeNewPieces('d', 2, new Rook(board, Color.WHITE));
		placeNewPieces('e', 2, new Rook(board, Color.WHITE));
		placeNewPieces('c', 1, new King(board, Color.WHITE));
		
		//Black pieces
		placeNewPieces('a', 7, new Rook(board, Color.BLACK));
		placeNewPieces('b', 7, new Rook(board, Color.BLACK));
		placeNewPieces('c', 7, new Rook(board, Color.BLACK));
		placeNewPieces('d', 7, new Rook(board, Color.BLACK));
		placeNewPieces('e', 7, new Rook(board, Color.BLACK));
		placeNewPieces('c', 8, new King(board, Color.BLACK));
	}
	
	private void placeNewPieces(char column, int row, ChessPiece piece)
	{
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	public ChessMatch()
	{
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	
	//Getters
	public int getTurn() 
	{
		return turn;
	}

	public Color getCurrentPlayer() 
	{
		return currentPlayer;
	}

	
	
	
	public ChessPiece[][] getPieces()
	{
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for(int i = 0; i<board.getRows(); i++)
		{
			for(int ii= 0; ii<board.getColumns(); ii++)
			{
				//Downcast to ChessPiece
				mat[i][ii] = (ChessPiece)board.piece(i, ii);
			}
		}
		
		return mat;
	}
	
	
	private void validateSourcePosition(Position position)
	{
		//Testing if exist any piece in source position
		if(!board.thereIsAPiece(position))
		{
			throw new ChessException("Warning: There is no piece on source position");
		}
		
		
		//Testing if exist any possible move
		if(!board.piece(position).isThereAnyPossibleMove())
		{
			throw new ChessException("Warning: There is no possible moves for piece");
		}
		
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor())
		{
			throw new ChessException("Warning: The chosed piece is not yours");
		}
	}
	
	
	
	private void validateTargetPosition(Position source, Position target)
	{
		//If for source piece the destination is not a possible movement the we can not move to target position
		if(!board.piece(source).possibleMove(target))
		{
			throw new ChessException("Warning: The chose piece can't move to target position");
		}
	}
	
	
	
	
	//Move a piece from actual position to another position --------
	private Piece makeMove(Position source, Position target)
	{
		Piece p = board.removePiece(source);
		Piece capturePiece = board.removePiece(target);
		
		board.placePiece(p, target);
		
		
		if(capturePiece != null)
		{
			piecesOnTheBoard.remove(capturePiece);
			capturedPieces.add(capturePiece);
		}
		
		return capturePiece;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition)
	{
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		
		return board.piece(position).possibleMoves();
	}
	
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition)
	{
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		
		Piece capturePiece = makeMove(source, target);
	
		nextTurn();
		return (ChessPiece)capturePiece;
	}
	//---------------------------------------------------------------
	
	
	private void nextTurn()
	{
		turn ++;
		if(currentPlayer == Color.WHITE)
		{
			currentPlayer = Color.BLACK;
		}
		else
		{
			currentPlayer = Color.WHITE;
		}
	}
}
