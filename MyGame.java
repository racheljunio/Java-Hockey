import shapes.*;

import java.awt.Color;
import java.util.Random;

public class MyGame extends Game {

  //Hockey Ball
	Circle puck = new Circle(new Point(Game.WIDTH * 3. / 4., Game.HEIGHT /2), 15);
	//Hockey Pamalo XD
	Circle[] mallet = new Circle[2];
	//Variables for start, Score for Player1 and 2
	int start = 0, p1Score = 0, p2Score = 0, speed = 20;
	//Variables for Mallet1a and 2 if hit and their speed
	int mallet1_hit = 0, mallet2_hit = 0, mallet_speed = 20;
	//Goals for Player1 and 2
	Rectangle goal1 = new Rectangle();
	Rectangle goal2 = new Rectangle();
	//Score
	String score = new String();
	//Player 1 and 2 
	String[] playerNames = new String[2];
	int GameState =0;
	Random generator = new Random();
	
  @Override
  public void setup() {
	  puck.setColor(Color.GREEN);
	  Game.setBackgroundColor(Color.WHITE);
	  
    // prevent pot leaving the border
	  Game.setBorderBehavior(Game.BorderBehavior.BOUNCE);
	  
	// create middle partition
	  Rectangle line = new Rectangle(new Point(Game.WIDTH / 2., Game.HEIGHT * .65), 5, Game.HEIGHT * 50);
	  //Rectangle line = new Rectangle
	  line.setColor(Color.LIGHT_GRAY);
	  
	// player names
	  playerNames[0] = "Player-2";
	  playerNames[1] = "Player-1";
	  
	// Creating mallets
	  
	  Point[] mallet_center = new Point[2];
	  mallet_center[0] = new Point(Game.WIDTH * 7. / 8., Game.HEIGHT /2);
	  mallet_center[1] = new Point(Game.WIDTH * 1. / 8., Game.HEIGHT /2);
	  
	  for (int i = 0; i < mallet.length; ++i) {
		  mallet[i] = new Circle();
		  mallet[i].setCenter(mallet_center[i]);
		  mallet[i].setRadius(30);
		  mallet[i].setSolid(true);
		  mallet[i].say(playerNames[i]);
	  }
	  mallet[0].setColor(Color.RED);
	  mallet[1].setColor(Color.BLUE);
	  
	// Goals
	  goal1.setColor(Color.BLACK);
	  goal1.setWidth(20);
	  goal1.setHeight(100);
	  goal1.setUpperLeft(new Point(0.0,Game.HEIGHT/2+(goal1.getHeight()/2)));

	  goal2.setColor(Color.BLACK);
	  goal2.setWidth(20);
	  goal2.setHeight(100);
	  goal2.setUpperLeft(new Point(Game.WIDTH-(goal2.getWidth()/2),Game.HEIGHT/2+(goal2.getHeight()/2)));
	  
	// 
	
	// Score board
	  score = "Player-1: 0 \nPlayer-2: 0";
	  
   
  }

  @Override
  public void update() {
	  
	  // player names
	  
	  
	  // score board
	  Game.setSubtitle(score);
	  Game.getSubtitleStyle().setColor(Color.BLACK);
	  
	  if (start == 0) {
		  if (mallet[0].isTouching(puck)) {
			  puck.setSpeed(speed);
			  mallet1_hit = 1;
			  mallet2_hit = 0;
			  start++;
		  }
		  else if (mallet[1].isTouching(puck)) {
			  puck.setSpeed(speed);
			  mallet1_hit = 0;
			  mallet2_hit = 1;
			  start++;
		  }
	  }
	  
	  	  
	  Direction movementDirection = Keyboard.direction();
	  
	  if (Keyboard.keyIsPressed(Keyboard.W))
		  mallet[1].moveUp(mallet_speed);
	  else if (Keyboard.keyIsPressed(Keyboard.A))
		  mallet[1].moveLeft(mallet_speed);
	  else if (Keyboard.keyIsPressed(Keyboard.D))
		  mallet[1].moveRight(mallet_speed);
	  else if (Keyboard.keyIsPressed(Keyboard.S))
		  mallet[1].moveDown(mallet_speed);
	  else if(Keyboard.keyIsPressed(Keyboard.Q))
          mallet[1].move(Direction.inDegrees(135), mallet_speed);
      else if(Keyboard.keyIsPressed(Keyboard.C))
    	  mallet[1].move(Direction.inDegrees(315), mallet_speed);
      else if(Keyboard.keyIsPressed(Keyboard.Z))
          mallet[1].move(Direction.inDegrees(225), mallet_speed);
      else if (Keyboard.keyIsPressed(Keyboard.E))
    	  mallet[1].move(Direction.inDegrees(45), mallet_speed);
	  else if (Keyboard.keyIsPressed(Keyboard.I))
		  mallet[0].moveUp(mallet_speed);
	  else if (Keyboard.keyIsPressed(Keyboard.J))
		  mallet[0].moveLeft(mallet_speed);
	  else if (Keyboard.keyIsPressed(Keyboard.L))
		  mallet[0].moveRight(mallet_speed);
	  else if (Keyboard.keyIsPressed(Keyboard.K))
		  mallet[0].moveDown(mallet_speed);
	  else if(Keyboard.keyIsPressed(Keyboard.U))
          mallet[0].move(Direction.inDegrees(135), mallet_speed);
      else if(Keyboard.keyIsPressed(Keyboard.GT))
    	  mallet[0].move(Direction.inDegrees(315), mallet_speed);
      else if(Keyboard.keyIsPressed(Keyboard.N))
          mallet[0].move(Direction.inDegrees(225), mallet_speed);
      else if (Keyboard.keyIsPressed(Keyboard.O))
    	  mallet[0].move(Direction.inDegrees(45), mallet_speed);
	  
	//
	  if (mallet[0].isTouching(puck)) {
		  reboundPuck(0);
		  mallet1_hit = 1;
		  mallet2_hit = 0;
	  }
	  else if (mallet[1].isTouching(puck)) {
		  reboundPuck(1);
		  mallet1_hit = 0;
		  mallet2_hit = 1;
	  }
	  
	// Update goal
	  if(puck.isTouching(goal1)){
		  if (mallet1_hit == 1) {
			  p2Score++;
			  speed += 2;
			  score = "Player-1: ".concat(Integer.toString(p1Score));
			  score = score.concat("\nPlayer-2: ".concat(Integer.toString(p2Score)));
			  restart_puck(1);
		  }
      }
      if(puck.isTouching(goal2)){
    	  if (mallet2_hit == 1) {
    		  p1Score++;
    		  speed += 2;
    		  score = "Player-1: ".concat(Integer.toString(p1Score));
			  score = score.concat("\nPlayer-2: ".concat(Integer.toString(p2Score)));
    		  restart_puck(2);
    	  }
      }

  } 
 
 
  
  public void restart_puck(int side) {
	  mallet1_hit = 0;
	  mallet2_hit = 0;
	  if (side == 1) { // keep puck right
		  puck.setCenter(new Point(Game.WIDTH * 3. / 4., Game.HEIGHT / 2.));
		  puck.setSpeed(0);
		  start = 0;
	  }
	  else {
		  puck.setCenter(new Point(Game.WIDTH * 1. / 4., Game.HEIGHT / 2.));
		  puck.setSpeed(0);
		  start = 0;
	  }
  }
  
  public void reboundPuck(int whichMallet) {
	  int rand = generator.nextInt(10);
	  if (whichMallet == 0) {
		  //Point mallet_coor = mallet[0].getCenter();
		  //Point puck_coor = puck.getCenter();
		  puck.rotate(180 + rand);
	  }
	  else {
		  //Point mallet_coor = mallet[0].getCenter();
		  //Point puck_coor = puck.getCenter();
		  puck.rotate(180 + rand);
	  }
  }

  public static void main(String[] args) {
    new MyGame();
  }

  public MyGame() {
    super(false);
    setup();
    ready();
  }
}
