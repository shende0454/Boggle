package boggle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * This class is used to generate letters for a Boggle board.  
 * It is intended to simulate the dice rolling aspect of an IRL boggle game.
 * @author Joe Meehean
 *
 */
public class BogglePieceGenerator {
	
	/**
	 * Set of Boggle "dice" for a small board game.
	 */
	private static final String[] SMALL_BOARD_DICE = {  
			"aaeegn", "abbjoo", "achops", "affkps",
			"aoottw", "cimotu", "deilrx", "delrvy",
			"distty", "eeghnw", "eeinsu", "ehrtvw", 
			"eiosst", "elrtty", "himnqu", "hlnnrz"};
	
	/**
	 * Set of Boggle "dice" for a large board game.
	 */
	private static final String[] LARGE_BOARD_DICE = {
			"aafirs", "adennn", "aaafrs", "aeegmu", "aaeeee",
			"aeeeem", "afirsy", "aegmnn", "bjkqxz",	"ceipst", 
			"ceiilt", "ccnstw", "ceilpt", "ddlonr", "dhlnor",
			"dhhlor", "dhhnot", "ensssu", "emottt", "eiiitt", 
			"fiprsy", "gorrvw", "hiprry", "nootuw", "ooottu"};
	
	/**
	 * All boards this size or smaller are small.
	 */
	private static final int SMALL_BOARD_SIZE = 16;
	
	private ArrayList<String> dice;
	private Random rand;
	private int index;
	
	/**
	 * Constructor for the BogglePieceGenerator.  A larger board will have more and different dice.
	 * Seeding the random number generator lets us get the same board in different implementations of Boggle.
	 * @param boardSize size of the Boggle board
	 * @param seed seed for the random number generator
	 */
	public BogglePieceGenerator(int boardSize, int seed) {
		// create a list of dice based on the board size
		if( boardSize <= SMALL_BOARD_SIZE ) {
			dice = new ArrayList<String>(Arrays.asList(SMALL_BOARD_DICE));
		}
		else {
			dice = new ArrayList<String>(Arrays.asList(LARGE_BOARD_DICE));
		}
		
		// seed the random number generator
		rand = new Random(seed);
		
		// start with the first dice in the list
		index = 0;
	}

	/**
	 * "Rolls" the next die and returns the letter that comes up. 
	 * @return the Boggle letter for the next die
	 */
	public char generateNextLetter() {
		// if this is the first character generated OR
		// we have used all the dice already
		// (re)shuffle
		if( index == 0 ) {
			Collections.shuffle(dice, rand);
		}
		
		// select a side from the current die
		int dieSide = rand.nextInt(dice.get(index).length());
		
		// get the letter from that side
		char letter = dice.get(index).charAt(dieSide);
		
		// move to the next die (handling wrap around)
		index++;
		if( index >= dice.size() ) {
			index = 0;
		}
		
		return letter;
	} 
}
