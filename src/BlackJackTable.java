import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class BlackJackTable extends JFrame 
{
	
	  private Container contentPane;
	  private JPanel buttonPanel = new JPanel();
	  private JPanel playPanel = new JPanel();
	  private JButton playButton = new JButton("Play");
	  private JButton hitButton = new JButton("Hit");
	  private JButton standButton = new JButton("Stand");
	  private JButton restartButton = new JButton("Restart");
	  private JPanel betPanel = new JPanel();
	  private JButton callButton = new JButton("Call");
	  private JButton raiseButton = new JButton("Raise");
	  private JButton foldButton = new JButton("Fold");
	  private JTextArea potText = new JTextArea();
	  private JPanel outputPanel = new JPanel();
	  private JTextArea YouArea = new JTextArea();
	  private JTextArea dealerArea = new JTextArea();

	  public BlackJackTable()
	  {
	    contentPane = getContentPane();
	    contentPane.setLayout(new BorderLayout());
	    outputPanel.setLayout(new BorderLayout());
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
	    setSize(new Dimension(600, 400));
	    setTitle("BlackJack");
	    
	    contentPane.add(buttonPanel,  BorderLayout.SOUTH);
	    buttonPanel.add(playPanel, null);
	    playPanel.add(playButton, null);
	    playPanel.add(hitButton, null);
	    playPanel.add(standButton, null);
	    playPanel.add(restartButton, null);
	    restartButton.setEnabled(false);
	    buttonPanel.add(betPanel, null);
	    betPanel.add(callButton, null);
	    betPanel.add(raiseButton, null);
	    betPanel.add(foldButton, null);
	    buttonPanel.add(potText, null);
	    contentPane.add(outputPanel, BorderLayout.CENTER);
	    outputPanel.setLayout(new FlowLayout());
	    outputPanel.add(YouArea, null);
	    outputPanel.add(dealerArea, null);
	    hitButton.setEnabled(false);
	    standButton.setEnabled(false);
	    YouArea.setText("  ");
	    dealerArea.setText("  ");
	    int width = getWidth();
	    int height = getHeight();
	    YouArea.setPreferredSize(new Dimension(width/2-20,height));
	    dealerArea.setPreferredSize(new Dimension(width/2-20,height));
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setVisible(true);
	  }

	  public void setPlayAction(ActionListener listener)
	  {
	    playButton.addActionListener(listener);
	  }

	  public void setHitAction(ActionListener listener)
	  {
	    hitButton.addActionListener(listener);
	  }

	  public void setStandAction(ActionListener listener)
	  {
	    standButton.addActionListener(listener);
	  }
	  
	  public void setRestartAction(ActionListener listener)
	  {
		  restartButton.addActionListener(listener);
	  }

	  public void setCallAction(ActionListener listener)
	  {
	    callButton.addActionListener(listener);
	  }

	  public void setRaiseAction(ActionListener listener)
	  {
	    raiseButton.addActionListener(listener);
	  }

	  public void setFoldAction(ActionListener listener)
	  {
	    foldButton.addActionListener(listener);
	  }

	  public void displayYou(BlackJackHand BlackJackHand)
	  {
	    YouArea.setText("You ($"+BlackJackHand.getMoney()+"):\n"+BlackJackHand.valueOf()+"\n"+BlackJackHand);
	  }

	  public void displayDealer(BlackJackHand BlackJackHand)
	  {
	    dealerArea.setText("Dealer ($"+BlackJackHand.getMoney()+"):\n"+BlackJackHand.valueOf()+"\n"+BlackJackHand);
	  }

	  public void displayDealerCard(Cards card)
	  {
	    dealerArea.setText("Dealer Shows:\n"+card);
	  }

	  public void dispLayoutcome(String outcome)
	  {
	    YouArea.setText(YouArea.getText()+"\n\n"+outcome);
	  }
	  
	  public void displayPot(String pot)
	  {
		  potText.setText(pot);
	  }

	  public void enableHitAndStandButtons()
	  {
	    playButton.setEnabled(false);
	    hitButton.setEnabled(true);
	    standButton.setEnabled(true);
	    setRestartEnabled(false);
	    setBetButtonsEnabled(false);
	  }

	  public void enablePlayButton()
	  {
	    playButton.setEnabled(true);
	    hitButton.setEnabled(false);
	    standButton.setEnabled(false);
	    setBetButtonsEnabled(false);
	  }
	  
	  public void setRestartEnabled(boolean enabled)
	  {
		  restartButton.setEnabled(enabled);
	  }
	  
	  public void enableBetButtons()
	  {
		  playButton.setEnabled(false);
		  hitButton.setEnabled(false);
		  standButton.setEnabled(false);
		  setBetButtonsEnabled(true);
	  }
	  
	  public void setBetButtonsEnabled(boolean enabled)
	  {
		  callButton.setEnabled(enabled);
		  raiseButton.setEnabled(enabled);
		  foldButton.setEnabled(enabled);
	  }

	  static public void main(String[] args)
	  {
	    new BlackJackTable();
	  }

}