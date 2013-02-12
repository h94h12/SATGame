//Huiming Han
//Game.java
//In this game, the user must pass two levels that test their knowledge on SAT words to win. 

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.event.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Game extends JApplet 
{
	private CardLayout cards;
	private Container c;
	
	private StartPanel start;
       private ChoicePanel choose; //user chooses which door/level to go to.Uses ActionListener, JTextArea, JButton,
       private Door1 doorpanel1; //door 1: user must click words with positive/negative connotations Uses MouseListener & ActionListener,JButton, JLabel, Timer
       private Door2 doorpanel2;// door 2: user must answer multiple choice questions with RadioPanel
       private Cave monsterlair; //panel before entering door 2
       private InstructPanel howtoplay; //game instructions
       private GameOver gameoverpanel; //displayed when no more lives left
       private Win winpanel; //displayed when both levels passed

       private int lives; //start with 3 lives
       private int numberofquestions = 11; //number of muliple choice questions asked by monster
	
	private boolean door1passed = false; //if user complete game in door1 or not
	private JButton door1, door2;
	
	public void init() {
		
		lives = 3;
		
		c = this.getContentPane();
		cards = new CardLayout();
		c.setLayout(cards);
		
		start = new StartPanel(); 
		start.setBackground(new Color(65, 165, 255));
		c.add(start, "Start");
		
		howtoplay = new InstructPanel();
		howtoplay.setBackground(new Color(65, 165, 235));
		c.add(howtoplay, "Instructions");
		
		choose = new ChoicePanel();
		choose.setBackground(Color.black);
		c.add(choose, "Choose door");
		
		doorpanel1 = new Door1();
		doorpanel1.setBackground(Color.black);
		c.add(doorpanel1, "Door 1");
		
		doorpanel2 = new Door2();
		doorpanel2.setBackground(Color.yellow);
		c.add(doorpanel2, "Door 2");
		
		monsterlair = new Cave();
		monsterlair.setBackground(Color.black);
		c.add(monsterlair, "Cave");
		
		gameoverpanel = new GameOver();
		gameoverpanel.setBackground(Color.black);
		c.add(gameoverpanel, "Game Over");
		
		winpanel = new Win();
		winpanel.setBackground(Color.white);
		c.add(winpanel, "Win");
		
		
	
	}

	class StartPanel extends JPanel implements ActionListener{
		
		private Image intropic;
		private JButton startbutton, instructions;
		private JLabel title;
		
		public StartPanel ( )   {
			
			this.setLayout(null);
			
			title = new JLabel("      SAT WORD SURVIVOR");
			this.add(title);
			title.setBounds(200, 50, 200, 100);
			title.setBorder( BorderFactory.createEtchedBorder() );
			
			startbutton = new JButton("START");
			startbutton.addActionListener(this);
			this.add(startbutton);
			startbutton.setBounds(430, 300, 130, 50);
				
			instructions = new JButton("INSTRUCTIONS");
			instructions.addActionListener(this);
			this.add(instructions);
			instructions.setBounds(430, 450, 130, 50);
			
			intropic = getImage ( getDocumentBase ( ), "SATrunningfinal.gif" );
			WaitForImage ( this, intropic );
		
			}
			
			public void WaitForImage ( JPanel component, Image image )   {
				MediaTracker tracker = new MediaTracker ( component );
				try  {
					tracker.addImage ( image, 0 );
					tracker.waitForID ( 0 );
				}
				catch ( InterruptedException e )   {
					e.printStackTrace ( );   
				}
			}
				
		   public void paintComponent(Graphics g){
			   
			   super.paintComponent(g);
			   g.drawImage(intropic, 10, 150, 400, 480,this);
		   }	
	
		
		public void actionPerformed(ActionEvent evt) {
			String command = evt.getActionCommand();
			if( command == "START" )
			cards.show(c, "Choose door");
			else if (command == "INSTRUCTIONS")
			cards.show(c, "Instructions");
			
		}
	
}
	
	class InstructPanel extends JPanel implements ActionListener{
		private JButton okay; //press this button to go back to start screen
		private JTextArea instructions; //displays instructions
				
		public InstructPanel()
		{
			this.setLayout(null);
			okay = new JButton("Back");
			this.add(okay);
			okay.addActionListener(this);
			okay.setBounds(490, 540, 90, 40);
			
			instructions = new JTextArea("You play a student whose tutor trapped him in a strange place. In order to escape"+
                                                    "\n\n you must pass all two levels in any order. "+
                                                    "\n\n\n\nIn the first level (door 1), you are randomly asked to collect gems that have words that have"+
                                                    "\n\n either negative or positive connotations. If you press the wrong word, you get a strike and with"+
                                                    "\n\n three strikes, you lose a life. There is a 30 second time limit for you to collect all the gems"+
                                                    "\n\nso think fast. Otherwise you lose a life if time runs out."+
                                                    "\n\n\n\nIn the second level (door 2), a monster confronts you and has a conversation with you involving"+
                                                    "\n\n SAT words. You must respond in the most logical manner that uses the vocabulary correctly. The"+
                                                    "\n\n monster mauls you if you respond illogically and you lose a life so be careful!"+
                                                    "\n\n\n\n Lose 3 lives and you'll be forever stuck in this simulated reality! If you survive 2 levels"+
                                                    "\n\nyou get to return to reality");
            this.add(instructions);
            instructions.setBounds(20, 20, 560, 560);
            instructions.setBorder(BorderFactory.createRaisedBevelBorder());


			
				
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if( command == "Back" )
			cards.show(c, "Start"); //go back to start screen
			
			
		}	
	}
	
	class ChoicePanel extends JPanel implements ActionListener{
		private JLabel caption; //empty label that makes the border
		private JTextArea about; 
		private Image cave; 
		
		public ChoicePanel()
		{
			this.setLayout(null);
			
			caption = new JLabel(" ");
			this.add(caption);
			caption.setBounds(10, 10, 580, 180);
			caption.setBorder(BorderFactory.createRaisedBevelBorder());	
			
			about = new JTextArea("It seems your SAT tutor trapped you in a simulated reality to force you to learn vocab! \n You see two cave entrances in front of you and you must complete the task awaiting you in each one in order to return to reality.");
			this.add(about);
			about.setLineWrap(true);
			about.setOpaque(false);
			about.setBounds(15, 15, 570, 180);
			about.setForeground(Color.white);
			
			door1 = new JButton("1");
			door2 = new JButton("2");
			
			this.add(door1);
			this.add(door2);
			
			door1.addActionListener(this);
			door2.addActionListener(this);
			
			door1.setBounds(130, 380, 50, 50);
			door2.setBounds(420, 370, 50, 50);
			
			door1.setBackground(Color.black);
			door1.setForeground(Color.white);
			door2.setBackground(Color.black);
			door2.setForeground(Color.white);
			
			cave = getImage ( getDocumentBase ( ), "cave.jpg" );
			WaitForImage ( this, cave );
		}
		
		public void WaitForImage ( JPanel component, Image image )   {
				MediaTracker tracker = new MediaTracker ( component );
				try  {
					tracker.addImage ( image, 0 );
					tracker.waitForID ( 0 );
				}
				catch ( InterruptedException e )   {
					e.printStackTrace ( );   
				}
			}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.white);
			g.drawString("You have "+lives+" lives left", 15, 160);
			g.drawImage(cave,0, 200, 600, 400,this);
		}
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("1"))
			cards.show(c, "Door 1"); //level 1
			else if(command.equals("2"))
			{
				int choice = 0; //start from the first question
				doorpanel2.loadQuestion(choice); //sets up multiple choice questions
				cards.show(c, "Cave"); //level 2
				
			
			}
			
		
		
	}
}

	class Door1 extends JPanel implements MouseListener, ActionListener{
		
		private int connotation; // 1 is positive connotation, 0 is negative 
		private String negpos; //indicates if ask for negative or positive connotation 
		
		private boolean displaywords = false;
		private boolean door1passed = false;
		private boolean timeisup = false;
	
		private int points; //number of points player earned by pressing right word
		private int strikes; //number of times player clicks wrong word 
		
		private String [][] satwords = {{"belicose", "inane", "magnanimous", "halcyon", "pultritude"}, 
								    {"diligent", "gaffe", "impetuous", "peerless", "acrimonious"},
								    {"avarice", "venerable", "altruistic", "amiable", "sophomoric"},
								    {"odious", "lackadaisical", "marred", "ethereal", "undaunted"},
								    {"jocund", "felicity", "raucous", "esoteric", "vivacity"}};
		private boolean [][] satgoodconn = {{false, false, true, true, true},
									  {true, false, false, true, false},
									  {false, true, true, true, false},
									  {false, false, false, true, true},
									  {true, true, false, false, true}}; 
									  
		private boolean [][] displaygrid = {{true, true, true, true, true},
									  {true, true, true, true, true},
									  {true, true, true, true, true},
									  {true, true, true, true, true},
									  {true, true, true, true, true}};	
		private Image star, stone;			     
		
		private JButton goback;
		private JLabel timeisupmessage;
		
		private TimerPanel gametimer;
		
		private Gridpanel canvas;
		
		public Door1()
		{
			this.setLayout(null);
			
			points = strikes = 0;
			
			connotation = (int)(Math.random() * 2);
			if (connotation == 1) //asks for positive connotation
			negpos = new String("positive");
			else //asks for negative connotation, value is 0
			negpos = new String("negative");
			
			gametimer = new TimerPanel();
			this.add(gametimer);
			gametimer.setBackground(Color.white);
			gametimer.setBounds(400, 540, 200, 40);
			
			goback = new JButton("Back");
			this.add(goback);
			goback.addActionListener(this);
			goback.setBounds(15, 580, 70, 20);
			goback.setEnabled(false); //button will be enabled when one can go back
			
			timeisupmessage = new JLabel("TIME IS UP! You lost a life, better go back");
			timeisupmessage.setForeground(Color.red);
			this.add(timeisupmessage);
			timeisupmessage.setBounds(90, 580, 300, 20);
			timeisupmessage.setVisible(false);//label wil be visible when time is up
			
			
			canvas = new Gridpanel();
			this.add(canvas);
			canvas.setBounds(20, 20, 560, 560);
			canvas.addMouseListener(this);
			
			star = getImage ( getDocumentBase ( ), "star.jpg" );
			choose.WaitForImage( this, star );
			
			stone = getImage ( getDocumentBase ( ), "stone.jpg" );
			choose.WaitForImage( this, stone );
			
			
		}
		
		
		
	class TimerPanel extends JLabel implements MouseListener, ActionListener {

      	 private long startTime;   // Start time of stopwatch.
                                 //   (Time is measured in milliseconds.)

       	 private boolean running;  // True when the stopwatch is running.

         private Timer timer;  // A timer that will generate events
                             // while the stopwatch is running
                             
       
         public TimerPanel() {
             // Constructor.
          super("  Click to start timer.  ", JLabel.CENTER);
         this.setForeground(Color.white);

          addMouseListener(this);
          timer = null;
        }

       public void resetTimer()
       {
		this.setText("Click to start timer.");
		this.setForeground(Color.white);
       	timer = null;
		running = false;
       	timeisup = false;
      
       	
		}
       
       public void actionPerformed(ActionEvent evt) {
        long time = (System.currentTimeMillis() - startTime) / 1000;
           
         if(!timeisup && !door1passed) //show how much time is left
          {this.setForeground(Color.white);
          setText("Time left:  " + (30 - time) + " seconds");
			}
            
           if(time>=30) //time is up when 30 seconds
           timeisup = true;
           
       }

       public void mousePressed(MouseEvent evt) {
              
			if (running == false) {
                // Record the time and start the stopwatch.
             
				if (!timeisup && !door1passed)
				running = true;
            
				displaywords = true; // displays words and starts game once user clicks timer
				canvas.repaint();
				startTime = evt.getWhen();  // Time when mouse was clicked.
				setText("Running:  30 seconds");
            
				if (timer == null) {
                timer = new Timer(100,this);
                timer.start();
				}
            
				else
                timer.restart();
                
				repaint();
          }
          else {
                // Stop the stopwatch.  Compute the elapsed time since the
                // stopwatch was started and display it.
             timer.stop();
             running = false;
             long endTime = evt.getWhen();
             double seconds = (endTime - startTime) / 1000.0;
             setText("Time: " + seconds + " sec.");
          }
       }

       public void mouseReleased(MouseEvent evt) { }
       public void mouseClicked(MouseEvent evt) { }
       public void mouseEntered(MouseEvent evt) { }
       public void mouseExited(MouseEvent evt) { }

}
		
		public void resetDoor1()
		{
			timeisup = false;
			timeisupmessage.setVisible(false);
			goback.setEnabled(false);
			gametimer.resetTimer();
			for(int i = 0; i< 5; i++)
			for(int j = 0; j<5; j++)
			displaygrid[i][j] = true;
			
			
			strikes = points = 0;
			canvas.repaint();
			
		}
		
		
		class Gridpanel extends JPanel
		{
			
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(stone, 0, 0, 580, 580, this);
				
				g.setColor(Color.white);
				g.drawString("Collect the gems that have words with "+negpos+" connotations", 10, 10);
			
				for (int i = 0; i < 5; i++)
				{
					for (int j = 0; j < 5; j++)
					{
						if (displaywords && displaygrid[i][j] == true)
						{
							g.drawImage(star,i*90+50, j*90+50, 90, 90,this);
							g.setColor(Color.red);
							g.drawString(satwords[i][j], i*90 + 60, j*90 + 100);
							
						}
					}
				}
				g.setColor(Color.black);
				
				
				 if ((points == 12 && connotation == 0) || (points == 13 && connotation == 1)) 
				 {
					 g.setColor(Color.green);
					 door1passed = true;
					 goback.setEnabled(true);
					 gametimer.timer.stop();
					 timeisupmessage.setText("YOU PASSED!!! You may now exit this cave");
					 timeisupmessage.setVisible(true);
					 canvas.repaint();
					 
				 }
				 
				 else if (displaywords == true && door1passed == false)
				 {
					 g.setColor(Color.red);
					 g.drawString("Lives: "+lives+"", 15, 520);
					 g.drawString("Points:"+points+"", 15, 550);
				 	 g.drawString("Strikes:"+strikes+"", 15, 535);
				 }
				 
				 if(strikes >= 3)
				 {
					 displaywords = false;
					 door1passed = false;
					timeisupmessage.setText("You got 3 strikes! You lost a life, better go back");
				 	timeisupmessage.setVisible(true);
					 goback.setEnabled(true);
					 gametimer.timer.stop();
					 canvas.repaint();
				 }
				 else if(timeisup)
				 {
				 	displaywords = false;
					door1passed = false;
					timeisupmessage.setText("TIME IS UP! You lost a life, better go back");
				 	timeisupmessage.setVisible(true);
				 	gametimer.timer.stop();
				 	goback.setEnabled(true);
				 	canvas.repaint();
				 	
				}
				
			}
			
		}
		
		public void actionPerformed(ActionEvent evt){
		
			String command = evt.getActionCommand();
			if( command == "Back" )
			{
					resetDoor1();
				
					if (door1passed == false)
					{
						lives--;
					
						door1.setEnabled(true);
						resetDoor1();
						timeisupmessage.setVisible(false);
						
						if(timeisup)
						timeisup = false;
						
						cards.show(c, "Choose door");	
					}
				
					else if (door1passed == true)
					{
						door1.setEnabled(false);
						cards.show(c, "Choose door");
						
						if(door2.isEnabled() == true)
						cards.show(c,"Choose door");
				
						else if(door1.isEnabled() == false && door2.isEnabled() == false)
						cards.show(c, "Win"); //user passed both levels 
					}
					
					if(lives == 0)
					cards.show(c, "Game Over");
						
				
					
			}
				
			
			
		
	}
	
	public void mousePressed (MouseEvent e) {
			
			int xpos = 0;
			int ypos = 0;
			
			if(displaywords)
			{
				if(e.getY() > 50 && e.getX() > 50 && e.getY() < 500 && e.getX() < 500)
			{
				xpos = (int)((e.getX()-50)/90);
				ypos = (int)((e.getY()-50)/90);
				
				
			}
			
			if((connotation == 1 && satgoodconn[xpos][ypos] == true)||(connotation == 0 && satgoodconn[xpos][ypos] == false)) //user clicks right word
			{
				points++;
				displaygrid[xpos][ypos] = false;
				canvas.repaint();
			}
			
			else                    //user clicks wrong word
			strikes++;
			
			canvas.repaint();
		}
			
			
		}
		public void mouseClicked (MouseEvent e) {}
		public void mouseReleased ( MouseEvent e )   {}
		public void mouseEntered ( MouseEvent e )   {}
		public void mouseExited ( MouseEvent e )     {}
		
}
	
	class Cave extends JPanel implements MouseListener
	{
		private InfoPanel canvas;
		private Image eyes;
		public Cave()
		{
			this.setLayout(null);
			canvas = new InfoPanel();
			canvas.setBackground(Color.black);
			this.add(canvas);
			canvas.setBounds(20, 20, 560, 560);
			canvas.addMouseListener(this);
			
			eyes = getImage ( getDocumentBase ( ), "monstereyes.gif" );
			WaitForImage ( this, eyes );
		}
		
		public void WaitForImage ( JPanel component, Image image )   {
				MediaTracker tracker = new MediaTracker ( component );
				try  {
					tracker.addImage ( image, 0 );
					tracker.waitForID ( 0 );
				}
				catch ( InterruptedException e )   {
					e.printStackTrace ( );   
				}
			}
		
		class InfoPanel extends JPanel{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				
				g.drawImage(eyes, 50, 200, 150, 100,this);
				g.setColor(Color.white);
				g.drawString("You have entered the monster's lair.", 300, 300);
				g.drawString("It will try to have a conversation with you", 300, 320);
				g.drawString("Be very careful with what you say!", 300, 340);
				g.drawString("Choose the most logical response.", 300, 360);
				g.drawString("Click to continue...", 300, 380);
			}
		}
		public void mousePressed (MouseEvent e) {
			cards.show(c,"Door 2");
			
			}
		public void mouseClicked (MouseEvent e) {}
		public void mouseReleased ( MouseEvent e )   {}
		public void mouseEntered ( MouseEvent e )   {}
		public void mouseExited ( MouseEvent e )     {}
		
	}

	class Door2 extends JPanel implements ActionListener, KeyListener{
	
		private JRadioButton A, B, C, D, placeHolder;
		private JLabel prompt, result;
		private File afile;
		private String [] question;
		private String [][] answer;
		private String [] correctanswer;
		private int currentquestion;
		private boolean moveon;
		private int questionnum;
		
		private JButton goback;
		
		

		public Door2 ( )
		{
			addKeyListener(this);
			
			questionnum = 0;
			
			this.setLayout(new GridLayout(6,1));
			loadDataFromFile();
			currentquestion = 0;
			moveon = false;
			
			prompt = new JLabel("Answer the monster's questions appropriately...", JLabel.CENTER);
			prompt.setForeground(Color.white);
			prompt.setBackground(Color.black);
			prompt.setOpaque(true);
			this.add(prompt);
			
			
     	 
			ButtonGroup answerGroup = new ButtonGroup();
      
			A = new JRadioButton("First Choice");
			answerGroup.add(A);
			A.addActionListener(this);
			this.add(A);

			B = new JRadioButton("Second Choice");
			answerGroup.add(B);
			B.addActionListener(this);
			this.add(B);

			C = new JRadioButton("Third Choice");
			answerGroup.add(C);
			C.addActionListener(this);
			this.add(C);

			D = new JRadioButton("Fourth Choice");
			answerGroup.add(D);
			D.addActionListener(this);
			this.add(D);

			placeHolder = new JRadioButton("You cannot see me . . .");
			answerGroup.add(placeHolder);
			placeHolder.setSelected(true);
			
			result = new JLabel("Choose one of the four answers in response to the prompt above.", JLabel.CENTER);
			result.setForeground(Color.white);
			result.setBackground(Color.black);
			result.setOpaque(true);
			this.add(result);
			
		
			
		}

		public void loadDataFromFile ( )
		{
			afile = new File("questions.txt");
			Scanner fromfile = null;
			question = new String [numberofquestions];
			answer = new String [numberofquestions][4];
			correctanswer = new String [numberofquestions];
			String aline;
			try
			{
				fromfile = new Scanner(afile);
			}
			catch (FileNotFoundException e)
			{
				System.out.println("Cannot find file");
				System.exit(1);
			}
			int i = 0;
			while (fromfile.hasNext())
			{
				aline = fromfile.nextLine();
				question[i] = aline;
				System.out.println(aline);
				for (int j = 0; j < 4; j++)
				{
					aline = fromfile.nextLine();
					answer[i][j] = aline;
					System.out.println(aline);
				}
				aline = fromfile.nextLine();
				correctanswer[i] = aline;
				System.out.println(aline);
				i++;
			}
		}

		public void loadQuestion (int choice)
		{
			System.out.println(choice);
			currentquestion = choice;
			prompt.setText(question[choice]);
			A.setText(answer[choice][0]);
			B.setText(answer[choice][1]);
			C.setText(answer[choice][2]);
			D.setText(answer[choice][3]);
			result.setBackground(Color.black);
			result.setText("Choose the most logical reply or the one that appeases the monster the most.");
			placeHolder.setSelected(true);
			A.setEnabled(true);
			B.setEnabled(true);
			C.setEnabled(true);
			D.setEnabled(true);
			moveon = false;
		}

		public void paintComponent (Graphics g)
		{
			this.requestFocus();
		}
   
		public void actionPerformed (ActionEvent evt)
		{
			int value = 0;
			if (A.isSelected())
			{
				value = 0;
				B.setEnabled(false);
				C.setEnabled(false);
				D.setEnabled(false);
			}
			else if (B.isSelected())
			{
				value = 1;
				A.setEnabled(false);
				C.setEnabled(false);
				D.setEnabled(false);
			}
			else if (C.isSelected())
			{
				value = 2;
				A.setEnabled(false);
				B.setEnabled(false);
				D.setEnabled(false);
			}
			else if (D.isSelected())
			{
				value = 3;
				A.setEnabled(false);
				B.setEnabled(false);
				C.setEnabled(false);
			}
			if(answer[currentquestion][value].substring(0,1).equals(correctanswer[currentquestion]))
			{
				if(questionnum < 10) //didn't reach last question yet 
				{
					result.setBackground(Color.green);
					result.setText("The monster is satisfied by your answer!  Press the space bar to go to the next question");
				} 
				
				if(questionnum == 10) //reached last question
				{
					result.setBackground(Color.blue);
					result.setText("The monster is impressed by your proficiency in vocabulary and lets you exit the cave. Press shift to exit.");
				}
				
			}
			else
			{
				lives--;
				result.setBackground(Color.red);
				result.setText("Your inept conversation skills have outraged the monster and it mauls you! "+lives+" lives left!");
			}
			
			moveon = true;
			this.requestFocus();
		}
		public void keyPressed (KeyEvent e)
		{
			int value = e.getKeyCode();
			if (value == KeyEvent.VK_SPACE && moveon == true)
			{
				if(questionnum < 10)
				{
					questionnum++;
					doorpanel2.loadQuestion(questionnum);
					
					if(lives == 0)
					cards.show(c,"Game Over");
					
				}
				
				doorpanel2.repaint();
				
			}
			else if (value == KeyEvent.VK_SHIFT && questionnum == 10)
			{
				door2.setEnabled(false);
				
				if(door1.isEnabled() == true)
				cards.show(c,"Choose door");
				
				else if(door1.isEnabled() == false && door2.isEnabled() == false)
				cards.show(c, "Win"); //user passed both levels 
			}
			
		}

		public void keyTyped (KeyEvent e)   {}

		public void keyReleased (KeyEvent e)   {}

	}

class Win extends JPanel //displayed when user passes the 2 levels
       {

               private Image backtoreality;
               public Win()
               {
                       backtoreality = getImage ( getDocumentBase ( ), "backtoreality.gif" );
                       choose.WaitForImage( this, backtoreality );

               }

               public void WaitForImage ( JPanel component, Image image )   {
                               MediaTracker tracker = new MediaTracker ( component );
                               try  {
                                       tracker.addImage ( image, 0 );
                                       tracker.waitForID ( 0 );
                               }
                               catch ( InterruptedException e )   {
                                       e.printStackTrace ( );
                               }
                       }

               public void paintComponent(Graphics g)
               {
                       super.paintComponent(g);
                       g.drawImage(backtoreality,0, 0, 600, 600,this);

                       g.drawString("You are suddenly overcome by a blindingly bright light.Your surroundings become", 100, 500);
                       g.drawString("a nebulous haze and then solidify into something more familiar: your bedroom.", 100, 515);
                       g.drawString("It turns out that all of this was a dream. You awake to your alarm clock and", 100, 530);
                       g.drawString("remember that you are taking the SAT in 2 hours. Good luck.", 100, 545);
               }

               

       }
       
       class GameOver extends JPanel //displayed when 0 lives left
       {

               private Image gameover;
               public GameOver()
               {
                       gameover = getImage ( getDocumentBase ( ), "gameover.gif" );
                       choose.WaitForImage( this, gameover );

               }

               public void WaitForImage ( JPanel component, Image image )   {
                               MediaTracker tracker = new MediaTracker ( component );
                               try  {
                                       tracker.addImage ( image, 0 );
                                       tracker.waitForID ( 0 );
                               }
                               catch ( InterruptedException e )   {
                                       e.printStackTrace ( );
                               }
                       }

               public void paintComponent(Graphics g)
               {
                       super.paintComponent(g);
                       g.drawImage(gameover,0, 0, 600, 600,this);

               }

               

       }



}

	
