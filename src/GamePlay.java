import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GamePlay 
{
	
	public BlackJackHand You;
	public BlackJackHand dealer;
	public DeckOfCards DeckOfCards;
	private int play = 0;
	private int hit = 0;
	public BlackJackTable GUI;
	private int stand =0;
	private int pot = 0;
	private String potText = "";
	
	public GamePlay()
	{
		GUI =  new BlackJackTable();
		GUI.setPlayAction(new PlayAction());
		GUI.setHitAction(new HitAction());
		GUI.setStandAction(new StandAction());
		GUI.setRestartAction(new RestartAction());
		GUI.setCallAction(new CallAction());
		GUI.setRaiseAction(new RaiseAction());
		GUI.setFoldAction(new FoldAction());
		GUI.enablePlayButton();
		GUI.displayPot("Pot: "+pot);
		You = new BlackJackHand();
		dealer = new BlackJackHand();
	}
	
	class RestartAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			You = new BlackJackHand();
			dealer = new BlackJackHand();
			startGame();
		}
	}
	
	class PlayAction implements ActionListener 
	{
		public void actionPerformed (ActionEvent e) 
		{
			You.clearHand();
			dealer.clearHand();
			startGame();
		}
	}
	
	private void startGame()
	{
		GUI.setRestartEnabled(false);
		DeckOfCards = new DeckOfCards();
		DeckOfCards.shuffle();
		You.add(DeckOfCards.nextCard());
		dealer.add(DeckOfCards.nextCard());
		You.add(DeckOfCards.nextCard());
		dealer.add(DeckOfCards.nextCard());
		GUI.displayYou(You);
		GUI.displayDealerCard(dealer.getTopCard());
		if(!You.hasBlackJack() && !dealer.hasBlackJack() && !You.isBusted())
		{
			GUI.displayPot("Pot: "+pot);
			GUI.enableBetButtons();
		}
		if(You.hasBlackJack() || dealer.hasBlackJack() || You.isBusted())
		{
			finishGame();
		}
	}

	class HitAction implements ActionListener
	{ 
		public void actionPerformed (ActionEvent e) 
		{
			if(!You.isBusted() && You.valueOf() !=21)
			{
				You.add(DeckOfCards.nextCard());
				GUI.displayYou(You);
				GUI.enableBetButtons();
				potText = "Pot: " + pot;
			}
			if(You.isBusted() || You.valueOf()==21)
			{
				finishGame();
			}
		}
	}
	
	class StandAction implements ActionListener
	{
		public void actionPerformed (ActionEvent e) 
		{
			finishGame();
		}
	}
	
	class CallAction implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			You.bet(10);
			pot += 10;
			potText = " (you bet $10,";
			dealerBet();
		}
	}
	
	class RaiseAction implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			You.bet(15);
			pot += 15;
			potText = " (you bet $15,";
			dealerBet();
		}
	}
	
	class FoldAction implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			GUI.displayDealer(dealer);
			GUI.displayYou(You);
			GUI.dispLayoutcome("You fold");
			lose();
			GUI.enablePlayButton();
		}
	}
	
	private void dealerBet()
	{
		potText += " dealer ";
		double rand = Math.random();
		if (rand <= 0.3)
		{
			dealer.bet(10);
			pot += 10;
			potText += "bet $10)";
		}
		else if (rand <= 0.9)
		{
			dealer.bet(15);
			pot += 15;
			potText += "bet $15)";
		}
		else
		{
			GUI.displayDealer(dealer);
			GUI.displayYou(You);
			GUI.dispLayoutcome("Dealer fold");
			win();
			GUI.enablePlayButton();
			return;
		}
		GUI.displayYou(You);
		GUI.displayDealer(dealer);
		GUI.displayPot("Pot: "+pot+potText);
		GUI.enableHitAndStandButtons();
	}
	
	private void finishGame()
	{
		if(You.hasBlackJack())
		{
			win();
			GUI.displayDealer(dealer);
			GUI.displayYou(You);
			GUI.dispLayoutcome("Win");
		}
		else if (dealer.hasBlackJack() && You.hasBlackJack())
		{
			GUI.displayDealer(dealer);
			GUI.displayYou(You);
			GUI.dispLayoutcome("Push");
		}
		else if(dealer.hasBlackJack())
		{
			lose();
			GUI.displayDealer(dealer);
			GUI.displayYou(You);
			GUI.dispLayoutcome("Lose");
		}
		else if (You.isBusted())
		{
			lose();
			GUI.displayDealer(dealer);
			GUI.displayYou(You);
			GUI.dispLayoutcome("Lose");
		}
		else 
		{
			while( dealer.valueOf()<17 && !dealer.isBusted() )
			{
				dealer.add(DeckOfCards.nextCard());
			}
			if(dealer.isBusted())
			{
				win();
				GUI.displayDealer(dealer);
				GUI.displayYou(You);
				GUI.dispLayoutcome("Win");
			}
             else if (dealer.hasBlackJack())
             {
            	 lose();
            	 	GUI.displayDealer(dealer);
            	 	GUI.displayYou(You);
            	 	GUI.dispLayoutcome("Lose");
			 } 
             else if (dealer.valueOf() == You.valueOf())
             {
            	 	GUI.displayDealer(dealer);
            	 	GUI.displayYou(You);
            	 	GUI.dispLayoutcome("Push");
			 }
             else if (dealer.valueOf() > You.valueOf())
             {
            	 lose();
            	 	GUI.displayDealer(dealer);
            	 	GUI.displayYou(You);
            	 	GUI.dispLayoutcome("Lose");
		   	 }
             else if (You.valueOf() > dealer.valueOf())
             {
            	 win();
            	 	GUI.displayDealer(dealer);
		        GUI.displayYou(You);
		        GUI.dispLayoutcome("Win");
		     }
			 
		}
		GUI.enablePlayButton();
		GUI.setRestartEnabled(true);
	}
	
	private void win()
	{
		GUI.displayPot("You receive $"+pot);
		You.receive(pot);
		pot = 0;
	}
	
	private void lose()
	{
		GUI.displayPot("Dealer receives $"+pot);
		dealer.receive(pot);
		pot = 0;
	}
	
	public static void main(String[] args)
	{
		new GamePlay();
	}

}