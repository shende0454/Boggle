package boggle;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Lexicon 
{
	private LinkedList<String> lex;
	
	public static enum Status{WORD,WORD_PREFIX,NOT_WORD};
	
	public Lexicon(Collection<String> wordCollection)
	{
		lex = new LinkedList<String>();
		for(String str:wordCollection)
		{
			lex.add(str.toUpperCase());
		}
	}
	
	public Lexicon.Status wordStatus(String word)
	{
		Status result = Status.NOT_WORD;
		word = word.toUpperCase();
		Iterator<String> iter = lex.iterator();
		String str;
		while(iter.hasNext() && result != Status.WORD)
		{
			str = iter.next();
			if(word.length() == str.length())
			{
				if(word.equals(str)) result = Status.WORD;
			}
			else if(word.length() < str.length() && result != Status.WORD_PREFIX)
			{
				if(str.startsWith(word)) result = Status.WORD_PREFIX;
			}
		}
		return result;
	}
}
