import java.awt.*;
import java.util.*;



public class Cards 
{
   
	private int suit;
	private int rank;
	private static final String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        private static final String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	
	public Cards(int suit, int rank)
	{
		this.suit = suit;
		this.rank = rank;
	}
	
	public int valueOf()
	{
		if (this.rank ==0)
		{
		return 11;
		}
		if (this.rank < 10)
		{
		return rank + 1;
		}
		if(this.rank >=10)
		{
		return 10;
		}
		else
		{
		return 0;
		}
	}
	
	public String toString()
	{
	        return ranks[this.rank] + " of " + suits[this.suit];
	}
	
	public static void main(String[] args)
	{
		Cards a = new Cards(0,0);
		System.out.println("Card a has a value of " + a.valueOf() + " and is a " + a.toString());
		Cards b = new Cards(3,4);
		System.out.println("Card a has a value of " + b.valueOf() + " and is a " + b.toString());
		Cards c = new Cards(3,12);
		System.out.println("Card a has a value of " + c.valueOf() + " and is a " + c.toString());
	}

}