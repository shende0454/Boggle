package boggle;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;


public class Board 
{
	private Piece[][] board;
	
	public Board(int dimension, BogglePieceGenerator generator)
	{
		board = new Piece[dimension][dimension];
		char letter;
		for(int y = 0;y < dimension;y++)
		{
			for(int x = 0;x < dimension;x++)
			{
				letter = Character.toUpperCase(generator.generateNextLetter());
				addPiece(y,x,letter);
				
			}
		}
	}
	
	private void addPiece(int row, int column, char letter)
	{
		board[row][column] = new Piece(row,column,letter);
		
		//fill adjacent list
		int startX = column - 1, endX = column + 1;
		int startY = row - 1, endY = row + 1;
		if(row<column)
		{
			row = column;
		}
		for(int y = startY;y <= endY;y++)
		{
			for(int x = startX;x <= endX;x++)
			{
				if(inRange(x,y) && !(y == row && x == column) && board[y][x] != null)
				{
					board[y][x].adjacents.add(board[row][column]);
					board[row][column].adjacents.add(board[y][x]);
				}
			}
		}
	}

	public static class Piece
	{
		private char value;
		private int row;
		private int col;
		private LinkedList<Piece> adjacents;
		private boolean visited;
		
		private Piece(int row, int column, char letter)
		{
			this.row = row;
			col = column;
			adjacents = new LinkedList<Piece>();
			value = letter;
			visited = false;
		}
		
		public int getColumn()
		{
			return col;
		}
		
		public int getRow()
		{
			return row;
		}
		
		public char getLetter()
		{
			return value;
		}
		
		public String toString()
		{
			String result = "[" + col + "," + row + "]-" + value;
			return result;
		}
	}

	public List<Board.Piece> getAdjacents(Board.Piece piece)
	{
		return piece.adjacents;
	}
	
	public Board.Piece getPiece(int row, int column)
	{
		return board[row][column];
	}
	
	public int getDimension()
	{
		return board.length;
	}
	
	public String toString()
	{
		//Point location = new Point();
		StringBuilder result = new StringBuilder();
		char letter;
		for(int y = 0;y < board.length;y++)
		{
			for(int x = 0;x < board.length;x++)
			{
				//Point location = new Point(x,y);
				letter = board[y][x].value;
				result.append(letter);
				result.append(" ");
			}
			result.append("\n");
		}
		return result.substring(0);
	}
	
	public boolean isWordOnBoard(String word)
	{
		boolean result = false;
		word = word.toUpperCase();
		char firstCharacter = Character.toUpperCase(word.charAt(0));
		Iterator<Board.Piece> iter = pieceFinder(firstCharacter).iterator();
		while(iter.hasNext() && !result)
		{
			result = hasNextLetter(iter.next(), word,1);
		}
		return result;
	}
	
	private boolean hasNextLetter(Piece piece,String word, int depth)
	{
		piece.visited = true;
		boolean result = false;
		if(depth >= word.length())
		{
			result = true;
		}
		else
		{
			Iterator<Board.Piece> iter = piece.adjacents.iterator();
			Piece p;
			while(iter.hasNext() && !result)
			{
				p = iter.next();
				if(!p.visited && word.charAt(depth) == p.value)
				{
					result = hasNextLetter(p, word,depth + 1);
				}
			}
		}
		piece.visited = false;
		return result;
	}

	// private method that returns all the pieces of the same letter
	private LinkedList<Board.Piece> pieceFinder(char letter)
	{
		LinkedList<Board.Piece> result = new LinkedList<Board.Piece>();
		
		for(int y = 0;y < board.length;y++)
		{
			for(int x = 0;x < board.length;x++)
			{
				if(board[y][x].value == letter)
				{
					result.add(board[y][x]);
				}
			}
		}
		
		return result;
	}
	
	private boolean inRange(int x,int y)
	{
		return x >= 0 && x < board.length && y >= 0 && y < board.length;
	}


}
