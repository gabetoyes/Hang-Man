import java.util.*;
public class HangOn
{
	String WordToFind = Reader.A;

	int wrongLetterNum = 0, 
			arrayNum = WordToFind.length();
	char theEqual, 
			arrayFullWord[] = WordToFind.toCharArray();
	char[] Letters = new char[arrayNum];
	static LinkedList<Character>WasEqualStorager = new LinkedList<Character>();
	//Make a container for all equals letters (Not LinkedList), have to sort 'em
	//Maybe sort is not an option :(
	 LinkedList<Character>SummonedLetters = new LinkedList<Character>();
	public HangOn()
	{
		
		System.out.println("All Letters: " + arrayNum);
		for(int i = 0; i < arrayNum; i++)
		{
			Letters[i] = '_';
		}
		Letters[0] = arrayFullWord[0];
		Letters[arrayNum - 1] = arrayFullWord[arrayNum - 1];
		SummonedLetters.addFirst(Letters[0]);
		SummonedLetters.offer(Letters[arrayNum - 1]);
		CheckLetters();
	}
	boolean EqualLetters(char isEqual)
	{
		LinkedList<Integer>TheIndexer = new LinkedList<Integer>();
		int letterNum = 0;
		for(int i = 0; i < arrayNum; i++)
		{
			if(isEqual == arrayFullWord[i])
			{
				TheIndexer.addLast(i);
				letterNum++;
			}
		}
		WasEqualStorager.add(isEqual);
		if(letterNum > 1){
			ShowLetters(TheIndexer, isEqual);
			return true;
		}
		else return false;
	}
	void ShowLetters(LinkedList<Integer>IndexesToAdd, char letterToAdd)
	{
		IndexesToAdd.sort(null);
		//System.out.println("In ShowLetters(LinkedList, char)");
		//System.out.println("Indexes To Add: " + IndexesToAdd);
		for(int i = 0; i < IndexesToAdd.size(); i++)
			Letters[IndexesToAdd.get(i)] = letterToAdd;
	}
	void ShowLetters()//Update or stay -_- Maybe not...
	{
		//System.out.println("In ShowLetters()");
	}
	void CheckLetters()
	{
		for(int i = 0; i <= arrayNum - 1; i++)
		{
			if(SummonedLetters.contains(Letters[i]))
			{
				if(!WasEqualStorager.contains(Letters[i]))
					if(EqualLetters(Letters[i]) == true);
			}
		}
	}
	void OutputTheWord()
	{
		SummonedLetters.sort(null);//maybe remove soon?
		System.out.println("\nThe word is:");
		System.out.println(String.valueOf(Letters).replaceAll(".", "$0  "));
		System.out.println("Used letters: " + SummonedLetters);
		//System.out.println("WasEqualStorager: " + WasEqualStorager);//one day may be used... maybe
		System.out.println("Number of wrong letters: " + wrongLetterNum + "\n(Max: 8)\n");
	}
	boolean isEnded()
	{
		for(int i = 0; i < arrayNum; i++)
			if(Letters[i] == '_' && wrongLetterNum < 8){
				return false;
			}
		return true;
	}
	void TryLetter(char isContains[])
	{
		if(!SummonedLetters.contains(isContains[0]))
		{
			SummonedLetters.add(isContains[0]);
		}
		else return;
			
		for(int i = 0; i < arrayNum; i ++)
		{
			if(arrayFullWord[i] == isContains[0])
			{
				Letters[i] = isContains[0];
				return;
			}
			if(!WasEqualStorager.contains(isContains[0]))
				if(EqualLetters(isContains[0]) == true);
		}
			wrongLetterNum++;
	}
	public static void main(String[] args)throws Exception
	{
		/*
		System.out.println("Working Directory = " +
	            System.getProperty("user.dir").replaceAll( "\\'" , "/"));
		String k = System.getProperty("user.dir");
		String dir =k.replace( "\\" , "/");
		System.out.println(dir);
		*/
		
		Reader.Readers();
		//uppressWarnings("resource")
		Scanner tryContains = new Scanner(System.in);//Better tryContains than uselessShit2000
		HangOn word = new HangOn();
		do
		{
			word.OutputTheWord();
			System.out.print("Try letter:" );
			word.TryLetter(tryContains.next().toCharArray());
			//System.out.print("KYS");//Best code ever
			if(word.wrongLetterNum >= 8)
			{
				//word.OutputTheWord();
				for (int i = 0; i < 50; ++i) 
					System.out.println();//BEST CLEAR CONSOLE EVERRRR
				System.out.println("\n\n\nYou used more than 8 different letters\n"
						+ "You Lost :(\nBetter KYS\nThe word was:\n" + 
						String.valueOf(word.WordToFind).replaceAll(".", "$0  "));//Cool, huh?
				//return;
			}
			if(word.isEnded() && word.wrongLetterNum < 8)
			{
				for (int i = 0; i < 50; ++i) 
					System.out.println();//BEST CLEAR CONSOLE EVERRRR
				word.OutputTheWord();
				System.out.println("You Win! || GG\nYo still need to KYS");
			}
			if(word.isEnded());//word.OutputTheWord();
			else 
				for (int i = 0; i < 50; ++i) 
					System.out.println();//BEST CLEAR CONSOLE EVERRRR
		}
		while(!word.isEnded());
		
		//word.OutputTheWord();
		System.out.println("Press Enter to exit");
		tryContains.nextLine();
		tryContains.nextLine();
		
		System.out.println("END");
		tryContains.close();
		//Thread.sleep(9000L);
		System.exit(0);
	}
}