package boardgame;

public class Position 
{
	private Integer row;
	private Integer column;
	
	
	//Constructor
	public Position(Integer row, Integer column)
	{
		this.row = row;
		this.column = column;
	}

	
	//Getters AND Setters
	public Integer getRow() 
	{
		return row;
	}

	public void setRow(int row) 
	{
		this.row = row;
	}

	public Integer getColumn()
	{
		return column;
	}

	public void setColumn(int column) 
	{
		this.column = column;
	}
	
	
	public void setValues(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	@Override
	public String toString()
	{
		return row + ", " + column;
	}
	
}
