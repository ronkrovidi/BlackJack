import java.awt.*;
import java.util.*;

public class DeckOfCards
{
	
	private Cards[] DeckArray = new Cards[52];
	private int index = 0;
	
	public DeckOfCards() 
	{
		int index2 = 0;
		for(int i =0; i<4; i++)
		{
			for(int j=0; j<13; j++)
			{
				Cards a = new Cards(i,j);
				DeckArray[index2] = a;
				index2++;
			}
		}
	}
	
	public void shuffle()
	{
		index = 0;
		int counter=0;
		while (counter<1000)
		{
			Random rand = new Random();
			int x= rand.nextInt(52);
			int y= rand.nextInt(52);
			Cards stored = DeckArray[x];
			DeckArray[x] = DeckArray[y];
			DeckArray[y] = stored;
			counter++;
	    } 
	}
	
	public boolean hasNextCard()
	{
		return index<52;
	}
	
	public Cards nextCard()
	{
		if(index<52)
		{
			index++;
			return DeckArray[index-1];
		}
		else
		{
			return null;
		}
	}
	
	public static void main(String[] args)
	{
		DeckOfCards a = new DeckOfCards();
		System.out.println(a.toString());
				while(a.hasNextCard())
				{
					System.out.println(a.nextCard());
				}
		a.shuffle();
		System.out.println(a.toString());
		while(a.hasNextCard())
		{
			System.out.println(a.nextCard());
		}
	}

}