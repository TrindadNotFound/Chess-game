package boardgame;

public class Board 
{
	private Integer rows;
	private Integer columns;
	
	private Piece[][] pieces;
	
	//Constructor
	public Board(Integer rows, Integer columns)
	{
		if(rows < 1 || columns < 1)
		{
			throw new BoardException("Error: To creating a chess board must be at least 1 row and 1 column");
		}
		
		this.rows = rows;
		this.columns = columns;
		
		pieces = new Piece[rows][columns];
	}

	
	//Getters
	public Integer getRows() 
	{
		return rows;
	}

	public Integer getColumns() 
	{
		return columns;
	}
	
	
	public Piece piece(int row, int column)
	{
		if(!positionExists(row, column))
		{
			throw new BoardException("Warning: Position doesn't exist");
		}
		
		return pieces[row][column];
	}
	
	
	public Piece piece(Position position)
	{
		if(!positionExists(position))
		{
			throw new BoardException("Warning: Position doesn't exist");
		}
		
		return pieces[position.getRow()][position.getColumn()];
	}
	
	
	//Place one piece in chess board
	public void placePiece(Piece piece, Position position)
	{
		if(thereIsAPiece(position))
		{
			throw new BoardException("Warning: Already a piece on position " + position);
		}
		
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	
	//Remove piece from board
	public Piece removePiece(Position position)
	{
		if(!positionExists(position))
		{
			throw new BoardException("Warning: Position doesn't exist");
		}
		
		if(piece(position) == null)
		{
			return null;
		}
		
		Piece aux = piece(position);
		aux.position = null;
		
		pieces[position.getRow()][position.getColumn()] = null;
		
		return aux;
	}
	
	
	
	
	//Testing whether position exists across the line and the column
	private boolean positionExists(int row, int column)
	{
		//When exists one position in one row and one column?
		//Answer: Exists when the position is into chess board dimensions
		return row >= 0 && row < rows && column >= 0 && column < columns;
				
	}
	
	//Testing if position exists across "position" 
	public boolean positionExists(Position position)
	{
		return positionExists(position.getRow(), position.getColumn());
	}
	
	
	public boolean thereIsAPiece(Position position)
	{
		//First of all testing if position exists and after check if exists one piece in the position
		if(!positionExists(position))
		{
			throw new BoardException("Warning: Position doesn't exist");
		}
		
		//If position different from null that means in that position exists one piece 
		return piece(position) != null;
	}
}
