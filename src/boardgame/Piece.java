package boardgame;


//This class is abstract because regardless of the piece, all of them will inherit properties of this class
public abstract class Piece 
{
	protected Position position;
	
	private Board board;
	
	public Piece(Board board)
	{
		this.board = board;
	}

	protected Board getBoard() 
	{
		return board;
	}
	
	
	
	public abstract boolean[][] possibleMoves();

	public boolean possibleMove(Position position)
	{
		//Hook with subclass
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	
	public boolean isThereAnyPossibleMove()
	{
		boolean[][] matriz = possibleMoves();
		
		
		//Search in matrix for possible moves. 
		for(int i = 0; i < matriz.length; i++)
		{
			for(int ii = 0; ii < matriz.length; ii++)
			{
				if(matriz[i][ii])
				{
					return true;
				}
			}
		}
		return false;
	}
}
