import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Random;

public class RockPaperScissorsLizardSpock extends Frame { 
  private Label lblYourChoice;
  private Label lblPlayerScore;
  private Label lblComputerScore;
  private CheckboxGroup grp;
  private TextArea txtResults;
  private TextArea txtResultsPlayer;
  private TextArea txtResultsComp;
  private Button btnRPSLS;
  private Button btnExit;
  private Button btnRestart;
  private Random rand;
  private int userChoice;
  private int compScore = 0;
  private int userScore = 0;
  private boolean user;
  private boolean comp;
  private String strUser;
  private String strComp;
  private Dialog dialog;

  public RockPaperScissorsLizardSpock(){
    setLayout(new FlowLayout());
    setSize(550, 350);
    setTitle("Rock Paper Scissors Lizard Spock");
    
    // check boxes
    lblYourChoice = new Label("Your choice: ");
    CheckboxGroup grp = new CheckboxGroup();
    Checkbox rock = new Checkbox("Rock", grp, true);
    Checkbox paper = new Checkbox("Paper", grp, false);
    Checkbox scissors = new Checkbox("Scissors", grp, false);
    Checkbox lizard = new Checkbox("Lizard", grp, false);
    Checkbox spock = new Checkbox("Spock", grp, false);
    add(rock);
    add(paper);
    add(scissors);
    add(lizard);
    add(spock);
    
    // game button
    btnRPSLS = new Button("RockPaperScissorsLizardSpock!");
    add(btnRPSLS);
    
    // results label
    txtResults = new TextArea("Results: ", 5, 40);
    add(txtResults);
    txtResults.setEditable(false);
    
    // score labels
    lblPlayerScore = new Label("Player's Score: ");
    lblComputerScore = new Label("Computer's Score: ");
    add(lblPlayerScore);
    add(lblComputerScore);
    
    // results text area - player
    txtResultsPlayer = new TextArea(1, 5);
    add(txtResultsPlayer);
    txtResultsPlayer.setText(userScore + "");
    txtResultsPlayer.setEditable(false);
    
    // results text area - computer
    txtResultsComp = new TextArea(1, 5);
    add(txtResultsComp);
    txtResultsComp.setText(compScore + "");
    txtResultsComp.setEditable(false);
    
    // dialog
    dialog = new Dialog(this);
    btnExit = new Button("EXIT");
    btnRestart = new Button("OK");
     
    // to show everything without resizing
    setVisible(true);
    
    // button functions - exit & restart
    btnExit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent s){
          System.exit(0);
        }
      });
    btnRestart.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
          restart();
          dialog.setVisible(false);
        }
      });
    
    
    // button functions - game
    btnRPSLS.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e){
		  Random rand = new Random();
		  int compChoice = rand.nextInt(5) + 1;

		  // comp choices to string
		  if(compChoice == 1){
			  strComp = new String("ROCK");
		  }
		  else if(compChoice == 2){
			  strComp = new String("PAPER");
		  }
		  else if(compChoice == 3){
			  strComp = new String("SCISSORS");
		  }
		  else if(compChoice == 4){
			  strComp = new String("LIZARD");
		  }
		  else if(compChoice == 5){
			  strComp = new String("SPOCK");
		  }
		  
		  // to get user values -> getState
		  if(rock.getState()){
			  userChoice = 1;
			  strUser = new String("ROCK");
	        }
		  else if(paper.getState()){
	          userChoice = 2;
	          strUser = new String("PAPER");
	        }
		  else if(scissors.getState()){
			  userChoice = 3;
			  strUser = new String("SCISSORS");
	        }
		  else if(lizard.getState()){
			  userChoice = 4;
			  strUser = new String("LIZARD");
	        }
		  else if(spock.getState()){
			  userChoice = 5;
			  strUser = new String("SPOCK");
	        }
		  
		  // battle proper
		  
		  /* 1 rock -> scissor, lizard
		   * 2 paper -> rock, spock
		   * 3 scissors -> paper, lizard
		   * 4 lizard -> paper, spock
		   * 5 spock -> rock, scissor
		   */
		  
		  // user = rock
		  if(userChoice == 1){
	          if(compChoice == 3 || compChoice == 4){
	            user = true;
	            comp = false;
	          }
	          else if (compChoice == 2 || compChoice == 5){
	            user = false;
	            comp = true;
	          }
	          else if (compChoice == 1){
	            user = false;
	            comp = false;
	          }
	        }
		  // user = paper
		  else if (userChoice == 2){
	          if (compChoice == 1 || compChoice == 5){
	        	user = true;
		        comp = false;
	          }
	          else if (compChoice == 3 || compChoice == 4){
	            user = false;
	            comp = true;
	          }
	          else if (compChoice == 2){
	            user = false;
	            comp = false;
	          }
	        }
		  // user = scissors
		  else if (userChoice == 3){
	          if (compChoice == 2 || compChoice == 4){
	            user = true;
	            comp = false;
	          }
	          else if(compChoice == 1 || compChoice == 5){
	            user = false;
	            comp = true;
	          }
	          else if(compChoice == 3){
	            user = false;
	            comp = false;
	          }
	        }
		  // user = lizard
		  else if(userChoice == 4){
	          if(compChoice == 2 || compChoice == 5){
	            user = true;
	            comp = false;
	          }
	          else if(compChoice == 1 || compChoice == 3){
	            user = false;
	            comp = true;
	          }
	          else if(compChoice == 4){
	            user = false;
	            comp = false;
	          }
	        }
		  // user = spock
		  else if(userChoice == 5){
	          if (compChoice == 1 || compChoice == 3){
	            user = true;
	            comp = false;
	          }
	          else if(compChoice == 2 || compChoice == 4){
	            user = false;
	            comp = true;
	          }
	          else if(compChoice == 5){
	            user = false;
	            comp = false;
	          }
	        }
	  
		  // results - output
		  if(user == true && comp == false){
			  txtResults.setText("Result: \n" + 
		  "Player chose: " + strUser + 
		  "\nComputer chose: " + strComp + 
		  "\nYOU WIN THIS ROUND");
			  userScore++;
			  txtResultsPlayer.setText(userScore + "");
		  }
		  else if(user == false && comp == true){
			  txtResults.setText("Result: \n" + 
		  "Player chose: " + strUser + 
		  "\nComputer chose: " + strComp + 
		  "\nYOU LOSE THIS ROUND");
			  compScore++;
			  txtResultsComp.setText(compScore + "");
		  }
		  else if(user == false && comp == false){
			  txtResults.setText("Result: \n" + 
		  "Player chose: " + strUser + 
		  "\nComputer chose: " + strComp + 
		  "\nDRAW");
		  }
		  
		  // results - dialog
		  if(userScore == 5){
			  dialog.setLayout(new FlowLayout());
			  dialog.setVisible(true);
			  dialog.setSize(350, 100); 
			  dialog.add(btnRestart);
			  dialog.add(btnExit);
			  dialog.setTitle("GAME OVER! PLAYER WON!!");
		  }
		  else if(compScore == 5){
			  dialog.setLayout(new FlowLayout());
			  dialog.setVisible(true);
			  dialog.setSize(350, 100);
			  dialog.add(btnRestart);
			  dialog.add(btnExit);
			  dialog.setTitle("GAME OVER! COMPUTER WON!!");
		  }
	  }
  });
  }
  
  //restart
  public void restart(){
      user = false;
      comp = false;
      userScore = 0;
      compScore = 0;
      txtResultsPlayer.setText(userScore + "");
      txtResultsComp.setText(compScore + "");
    }

public static void main(String[] args){
  new RockPaperScissorsLizardSpock();
}
}