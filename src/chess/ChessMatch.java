package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess_pieces.Bishop;
import chess_pieces.King;
import chess_pieces.Knight;
import chess_pieces.Pawn;
import chess_pieces.Queen;
import chess_pieces.Rook;

public class ChessMatch
{
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	
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
	
	public boolean getCheck()
	{
		return check;
	}
	
	public boolean getCheckMate()
	{
		return checkMate;
	}
	
	public ChessPiece getEnPassantVulnerable()
	{
		return enPassantVulnerable;
	}
	
	public ChessPiece getPromoted()
	{
		return promoted;
	}
	
	
	
	//Pieces in board
	private void initialSetup()
	{
		//White pieces
		placeNewPieces('a', 1, new Rook(board, Color.WHITE));
		placeNewPieces('b', 1, new Knight(board, Color.WHITE));
		placeNewPieces('c', 1, new Bishop(board, Color.WHITE));
		placeNewPieces('d', 1, new Queen(board, Color.WHITE));
		placeNewPieces('e', 1, new King(board, Color.WHITE, this));
		placeNewPieces('f', 1, new Bishop(board, Color.WHITE));
		placeNewPieces('g', 1, new Knight(board, Color.WHITE));
		placeNewPieces('h', 1, new Rook(board, Color.WHITE));
		
		placeNewPieces('a', 2, new Pawn(board, Color.WHITE, this));
		placeNewPieces('b', 2, new Pawn(board, Color.WHITE, this));
		placeNewPieces('c', 2, new Pawn(board, Color.WHITE, this));
		placeNewPieces('d', 2, new Pawn(board, Color.WHITE, this));
		placeNewPieces('e', 2, new Pawn(board, Color.WHITE, this));
		placeNewPieces('f', 2, new Pawn(board, Color.WHITE, this));
		placeNewPieces('g', 2, new Pawn(board, Color.WHITE, this));
		placeNewPieces('h', 2, new Pawn(board, Color.WHITE, this));
		
		
		
		
		//Black pieces
		placeNewPieces('a', 8, new Rook(board, Color.BLACK));
		placeNewPieces('b', 8, new Knight(board, Color.BLACK));
		placeNewPieces('c', 8, new Bishop(board, Color.BLACK));
		placeNewPieces('d', 8, new Queen(board, Color.BLACK));
		placeNewPieces('e', 8, new King(board, Color.BLACK, this));
		placeNewPieces('f', 8, new Bishop(board, Color.BLACK));
		placeNewPieces('g', 8, new Knight(board, Color.BLACK));
		placeNewPieces('h', 8, new Rook(board, Color.BLACK));
		
		placeNewPieces('a', 7, new Pawn(board, Color.BLACK, this));
		placeNewPieces('b', 7, new Pawn(board, Color.BLACK, this));
		placeNewPieces('c', 7, new Pawn(board, Color.BLACK, this));
		placeNewPieces('d', 7, new Pawn(board, Color.BLACK, this));
		placeNewPieces('e', 7, new Pawn(board, Color.BLACK, this));
		placeNewPieces('f', 7, new Pawn(board, Color.BLACK, this));
		placeNewPieces('g', 7, new Pawn(board, Color.BLACK, this));
		placeNewPieces('h', 7, new Pawn(board, Color.BLACK, this));
		
	}
	
	
	//With this method we know who is the opponent
	private Color opponent(Color color)
	{
		if(color == Color.WHITE)
		{
			return Color.BLACK;
		}
		else
		{
			return Color.WHITE;
		}
	}
	
	//See if exist a king on the board
	private ChessPiece king(Color color)
	{
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
	
		for(Piece p : list)
		{
			if(p instanceof King)
			{
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("Error: There is not " + color + " king on the board");
	}
	
	
	//Test if king is in CHECK
	private boolean testCheck(Color color)
	{
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		
		for(Piece p : opponentPieces)
		{
			boolean[][] matrix = p.possibleMoves();
			
			if(matrix[kingPosition.getRow()][kingPosition.getColumn()])
			{
				return true;
			}
		}
		return false;
	
	}
	
	
	//Test if king is in CHECK MATE
	private boolean testCheckMate(Color color)
	{
		//If king is not in check then is not in check mate
		if(!testCheck(color))
		{
			return false;
		}
		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		
		for(Piece p : list)
		{
			boolean[][] matrix = p.possibleMoves();
			for(int i=0; i<board.getRows(); i++)
			{
				for(int ii=0; ii<board.getRows(); ii++)
				{
					//Move piece "P" to matrix[i][ii] position and test if still in check
					if(matrix[i][ii])
					{
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, ii);
						
						Piece capturedPiece = makeMove(source, target);
						
						
						//Test if King still in check
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck)
						{
							return false;
						}
					}
				}
			}
			
		}
		return true;
	
	}
	
	private void placeNewPieces(char column, int row, ChessPiece piece)
	{
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
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
	
	
	//Move a piece from actual position to another position
	private Piece makeMove(Position source, Position target)
	{
		ChessPiece p = (ChessPiece)board.removePiece(source);
		p.increaseMoveCount();
		
		Piece capturedPiece = board.removePiece(target);
		
		board.placePiece(p, target);
		
		
		if(capturedPiece != null)
		{
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		//Special move  - Kingside rook
		if(p instanceof King && target.getColumn() == source.getColumn() + 2)
		{
			Position sourceRook = new Position(source.getRow(), source.getColumn() + 3);
			Position targetRook = new Position(source.getRow(), source.getColumn() + 1);
			
			ChessPiece rook = (ChessPiece)board.removePiece(sourceRook);
			board.placePiece(rook, targetRook);
			
			rook.increaseMoveCount();
		}
		
		
		//Special move  - Queenside rook
		if(p instanceof King && target.getColumn() == source.getColumn() - 2)
		{
			Position sourceRook = new Position(source.getRow(), source.getColumn() - 4);
			Position targetRook = new Position(source.getRow(), source.getColumn() - 1);
			
			ChessPiece rook = (ChessPiece)board.removePiece(sourceRook);
			board.placePiece(rook, targetRook);
			
			rook.increaseMoveCount();
		}
		
		
		//Special move - En Passant
		if(p instanceof Pawn)
		{
			if(source.getColumn() != target.getColumn() && capturedPiece == null)
			{
				Position pawnPosition;
				
				if(p.getColor() == Color.WHITE)
				{
					pawnPosition = new Position(target.getRow() + 1, target.getColumn());
				}
				else //Color == BLACK
				{
					pawnPosition = new Position(target.getRow() - 1, target.getColumn());
				}
				
				capturedPiece = board.removePiece(pawnPosition);
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);
			}
		}
		
		return capturedPiece;
	}
	
	
	//Undo moves
	private void undoMove(Position source, Position target, Piece capturedPiece)
	{
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		
		board.placePiece(p, source);
		
		if(capturedPiece != null)
		{
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
		
		//Special move - Undo Kingside rook
		if(p instanceof King && target.getColumn() == source.getColumn() + 2)
		{
			Position sourceRook = new Position(source.getRow(), source.getColumn() + 3);
			Position targetRook = new Position(source.getRow(), source.getColumn() + 1);
			
			ChessPiece rook = (ChessPiece)board.removePiece(targetRook);
			board.placePiece(rook, sourceRook);
			
			rook.decreaseMoveCount();
		}
		
		//Special move - Undo Queenside rook
		if(p instanceof King && target.getColumn() == source.getColumn() - 2)
		{
			Position sourceRook = new Position(source.getRow(), source.getColumn() - 4);
			Position targetRook = new Position(source.getRow(), source.getColumn() - 1);
			
			ChessPiece rook = (ChessPiece)board.removePiece(targetRook);
			board.placePiece(rook, sourceRook);
			
			rook.decreaseMoveCount();
		}
		
		//Special move - Undo En Passant
		if(p instanceof Pawn)
		{
			if(source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable)
			{
				ChessPiece pawn = (ChessPiece)board.removePiece(target);
				
				
				Position pawnPosition;
				if(p.getColor() == Color.WHITE)
				{
					pawnPosition = new Position(3, target.getColumn());
				}
				else //Color == BLACK
				{
					pawnPosition = new Position(4, target.getColumn());
				}
				board.placePiece(pawn, pawnPosition);
				
				capturedPiece = board.removePiece(pawnPosition);
			}
		}
		
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
	
				
		if(testCheck(currentPlayer))
		{
			undoMove(source, target, capturePiece);
			throw new ChessException("Warning: You can not put yourself in Check");
		}
		
		
		ChessPiece movedPiece = (ChessPiece)board.piece(target);
		
		//Special move - Piece Promotion
		promoted = null;
		if(movedPiece instanceof Pawn)
		{
			if((movedPiece.getColor() == Color.WHITE && target.getRow() == 0) || (movedPiece.getColor() == Color.BLACK && target.getRow() == 7))
			{
				promoted = (ChessPiece)board.piece(target);
				promoted = replacePromotedPiece("Q");
			}
		}
				
		
		if(testCheck(opponent(currentPlayer)) == true)
		{
			check = true;
		}
		else
		{
			check = false;
		}
		
						
		//Tested if the opponent from moved piece stayed in check mate.
		if(testCheckMate(opponent(currentPlayer)))
		{
			checkMate = true;
		}
		else
		{
			nextTurn();
		}
		
		//Special move  - En Passant
		if(movedPiece instanceof Pawn && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2))
		{
			enPassantVulnerable = movedPiece;
		}
		else
		{
			enPassantVulnerable = null;
		}
		
		return (ChessPiece)capturePiece;
	}

	
	//Switch piece - Special move (Promotion)
	public ChessPiece replacePromotedPiece(String type)
	{
		if(promoted == null)
		{
			throw new IllegalStateException("Error: No piece to be promoted");
		}
		
		Position pos = promoted.getChessPosition().toPosition();
		Piece p = board.removePiece(pos);
		
		piecesOnTheBoard.remove(p);
		
		ChessPiece newPiece = newPiece(type, promoted.getColor());
		board.placePiece(newPiece, pos);
		piecesOnTheBoard.add(newPiece);
		
		return newPiece;
	}
	
	
	private ChessPiece newPiece(String type, Color color)
	{
		if(type.equals("B")) return new Bishop(board, color); 
		if(type.equals("N")) return new Knight(board, color);
		if(type.equals("Q")) return new Queen(board, color);
		return new Rook(board, color);
	}
	
	
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
