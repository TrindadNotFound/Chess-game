package boardgame;

public class BoardException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	//Personal exception messages
	public BoardException(String msg)
	{
		super(msg);
	}
}
