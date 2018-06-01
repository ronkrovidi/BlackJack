public class BlackJackHand 
{
	
	public int count = 0;
	private Cards[] BlackJackHand;
	private int money = 100;
	
	public BlackJackHand() 
	{
		BlackJackHand = new Cards[20];
	}
	
	public void add(Cards card)
	{
		BlackJackHand[count++] = card;
	}
	
	public void clearHand()
	{
		BlackJackHand = new Cards[20];
		count = 0;
	}
	
	public Cards getTopCard()
	{
		return BlackJackHand[0];
	}
	
	public int getMoney()
	{
		return money;
	}
	
	public void bet(int amount)
	{
		money -= amount;
	}
	
	public void receive(int amount)
	{
		money += amount;
	}

	public int valueOf()
	{
		int sum=0;
		int rank=0;
		int count2= count;
		int aces = 0;
		for(int i=0; i< count2; i++)
		{
			rank = BlackJackHand[i].valueOf();
			sum += rank;
			if( rank == 11)
			{
				aces++;
			}
	    }
		while (aces > 0 && sum >21)
		{
			sum -= 10;
			aces--;
		}		
		return sum;
	}
	
	public boolean hasBlackJack()
	{
		int total = BlackJackHand[0].valueOf() + BlackJackHand[1].valueOf();
		return total ==21;
	}

	public boolean isBusted()
	{
		return valueOf() > 20;
	}
	
	public String toString()
	{
		String string ="";
		int count3 = count;
		for(int i=0; i< count3; i++)
		{
			string += BlackJackHand[i].toString();
			string += "\n";
		}
		if(isBusted())
		{
			string += "\n\n";
			string += "Bust";
		}
		if(hasBlackJack())
		{
			string += "\n\n";
			string += "Blackjack";
		}
		return string;
	}
	
	public static void main(String[] args)
	{
		DeckOfCards DeckOfCards = new DeckOfCards();
		DeckOfCards.shuffle();
		BlackJackHand a = new BlackJackHand();
		a.add(DeckOfCards.nextCard());
		a.add(DeckOfCards.nextCard());
		a.add(DeckOfCards.nextCard());
		System.out.println(a.toString());
		System.out.println(a.valueOf());
	}

}