package boggle;

import static java.lang.System.out;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MS2Main 
{
	public static void main(String[] args) 
	{
		FileInputStream file = null;
		try
		{
			Scanner scan = new Scanner(System.in);

			int seed,dimensions;
			Board board;
			out.print("Enter a board size: ");
			dimensions = scan.nextInt();
			
			out.print("Enter a seed: ");
			seed = scan.nextInt();
		
			board = new Board(dimensions,new BogglePieceGenerator(dimensions*dimensions, seed));
			out.println(board.toString());
			
			String word,again;
			do
			{
				out.print("Enter a word: ");
				word = scan.next().trim();
				
				if(board.isWordOnBoard(word))out.println("Word is on Board!!!!");
				
				else out.println("Word not on Board");
					
				out.print("go again(Y/N)? ");
				again = scan.next().trim();
				
			}while(true/*again.equalsIgnoreCase("Y")*/);
			
	
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			out.print("The command line arguement was not found");
		}
	}
}
