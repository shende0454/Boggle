package boggle;
import static java.lang.System.out;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MS1Main 
{

	public static void main(String[] args) 
	{
		//read input file and check exceptions
				FileInputStream file = null;
				try
				{
					file = new FileInputStream(args[0]);
					//reads the file and makes a list of all the words
					Scanner reader = new Scanner(file);

					String line = "";
					Board board = new Board(4,new BogglePieceGenerator(16, 3));
					boolean result = true;
					while (reader.hasNext() && result) 
					{
						line = reader.nextLine().trim();
						if(line.equals("UNBENT"))
						{
							out.print("");
						}
						result = board.isWordOnBoard(line);
					}
					reader.close();
					if(result)
					{
						out.print("test passed");
					}
					else 
					{
						out.print("test failed at word " + line);
					}
				}
				catch(FileNotFoundException e)
				{
					out.print("The file was not found");
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					out.print("The command line arguement was not found");
				}
	
	}

}
