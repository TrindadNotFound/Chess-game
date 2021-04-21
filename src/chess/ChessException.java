package chess;

import boardgame.BoardException;

public class ChessException extends BoardException
{

	private static final long serialVersionUID = 1L;

	//Personal exception message
	public ChessException(String msg)
	{
		super(msg);
	}
}
