import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener
{
  
  private int[] snakeXlength = new int[750];
  private int[] snakeYlength = new int[750];
  
  private boolean left = false;
  private boolean right = false;
  private boolean up = false;
  private boolean down = false;
  
  private ImageIcon titleImage;
  
  private ImageIcon leftmouth;
  private ImageIcon rightmouth;
  private ImageIcon upmouth;
  private ImageIcon downmouth;
  private ImageIcon snakeimage;
  private ImageIcon enemyImage;
  
  private int[] enemyXpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300,
                             325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600,
                             625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
  private int[] enemyYpos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350,
                             375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
  
  
  
  private Random random = new Random();
  private int xpos = random.nextInt(34);
  private int ypos = random.nextInt(23);
  
  private int score = 0;
  private int finalScore, highScore,finalSnakeLength, maxSnakeLength;
  private int lengthOfSnake = 3;
  private int moves = 0;
  private boolean gameOver, started;
  
  private Timer timer;
  private int delay = 100;
  
  public Gameplay()
  {
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    timer = new Timer(delay, this);
    timer.start();
    
  }
  
  public void paint(Graphics g)
  {
    if(moves == 0)
    {
      snakeXlength[2] = 50;
      snakeXlength[1] = 75;
      snakeXlength[0] = 100;
      
      snakeYlength[2] = 100;
      snakeYlength[1] = 100;
      snakeYlength[0] = 100;
      
      }
      
     // Draw the title image border
    g.setColor(Color.black);
    g.drawRect(24, 10, 851, 55);
    
    titleImage = new ImageIcon(getClass().getClassLoader().getResource("snaketitle.jpg"));
    titleImage.paintIcon(this, g, 25, 11);
    
    // Draw the title image
    titleImage = new ImageIcon(getClass().getClassLoader().getResource("earthenemy.png"));
    titleImage.paintIcon(this, g, 25, 11);
    
    // Draw border for gameplay
    g.setColor(Color.black);
    g.drawRect(24, 74, 851, 577);
    
    // Draw background for gameplay
    g.setColor(Color.DARK_GRAY);
    g.fillRect(25, 75, 850, 576);
    
    // Draw score
    g.setColor(Color.white);
    g.setFont(new Font("Monospaced", Font.PLAIN, 14));
    g.drawString("Score: " +score, 780, 30);
    
    // Draw length of snake
    g.setColor(Color.white);
    g.setFont(new Font("Monospaced", Font.PLAIN, 14));
    g.drawString("Length: " +lengthOfSnake, 780, 50);
    
    rightmouth = new ImageIcon(getClass().getClassLoader().getResource("alien.png"));
    rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
    
    for(int a = 0; a < lengthOfSnake; a++)
    {
      if(a == 0 && right)
      {
        rightmouth = new ImageIcon(getClass().getClassLoader().getResource("alienright.png"));
        rightmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
      }
      if(a == 0 && left)
      {
        leftmouth = new ImageIcon(getClass().getClassLoader().getResource("alienleft.png"));
        leftmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
      }
      if(a == 0 && up)
      {
        upmouth = new ImageIcon(getClass().getClassLoader().getResource("alien.png"));
        upmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
      }
      if(a == 0 && down)
      {
        downmouth = new ImageIcon(getClass().getClassLoader().getResource("alien.png"));
        downmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
      }
      if(a != 0)
      {
        snakeimage = new ImageIcon(getClass().getClassLoader().getResource("star.png"));
        snakeimage.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
      }
    }
    
    enemyImage = new ImageIcon(getClass().getClassLoader().getResource("earthenemy.png"));
    
    if((enemyXpos[xpos]== snakeXlength[0] && enemyYpos[ypos] == snakeYlength[0]))
    {
      score +=5;
      finalScore = score;
      lengthOfSnake++;
      finalSnakeLength = lengthOfSnake;
      if(finalScore > highScore)
      {
        highScore = finalScore;
      }
      if(finalSnakeLength > maxSnakeLength)
      {
        maxSnakeLength = finalSnakeLength;
      }
      xpos = random.nextInt(34);
      ypos = random.nextInt(23);
      
    }
    enemyImage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);
    
        // Game Over if snake contacts itself
    for(int b = 1; b < lengthOfSnake; b++)
    {
      if(snakeXlength[b] == snakeXlength[0] && snakeYlength[b] == snakeYlength[0])
      {
        right = false;
        left = false;
        up = false;
        down = false;
        
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.ITALIC, 50));
        g.drawString("Game Over!", 300, 300);
        
        g.setFont(new Font("Serif", Font.ITALIC, 20));
        g.drawString("Space Bar to Restart", 350, 340);
      }
    }
      if(gameOver)
    {
      right = false;
      left = false;
      up = false;
      down = false;
      
      g.setColor(Color.white);
      g.setFont(new Font("Serif", Font.ITALIC, 50));
      g.drawString("Game Over!", 310, 300);
      
      g.setFont(new Font("Serif", Font.ITALIC, 20));
      g.drawString("Space Bar to Restart", 350, 340);
      
      g.setFont(new Font("Serif", Font.ITALIC, 20));
      g.drawString("Final Score is : " + String.valueOf(finalScore), 375, 400);
      g.drawString("Snake Length is : " + String.valueOf(lengthOfSnake), 375, 450);
      g.drawString("High Score is : " + String.valueOf(highScore), 375, 500);
      g.drawString("Greatest Snake Length is : " + String.valueOf(maxSnakeLength), 325, 550);
      
      gameOver = false;
      
    }
    if(!started)
    {
      g.setColor(Color.white);
      g.setFont(new Font("SansSerif", Font.ITALIC, 40));
      g.drawString("Right Arrow Key to Start", 200, 350);
      started = true;
    }
      
    
    
    
    g.dispose();
    
  }
    
    
   public void actionPerformed(ActionEvent e)
  {
      timer.start();
    if(right)
    {
      for(int r = lengthOfSnake -1; r >= 0; r--)
      {
        snakeYlength[r+1] = snakeYlength[r];
      }
      for(int r = lengthOfSnake; r >= 0; r--)
      {
        if(r == 0)
        {
          snakeXlength[r] = snakeXlength[r] + 25;
        }else {
          snakeXlength[r] = snakeXlength[r-1];
        }
        if(snakeXlength[r] > 849)
        {
          //snakeXlength[r] = 25;
          gameOver = true;
        }
      }
      repaint();
    }
    if(left)
    {
      for(int r = lengthOfSnake -1; r >= 0; r--)
      {
        snakeYlength[r+1] = snakeYlength[r];
      }
      for(int r = lengthOfSnake; r >= 0; r--)
      {
        if(r == 0)
        {
          snakeXlength[r] = snakeXlength[r] - 25;
        }else {
          snakeXlength[r] = snakeXlength[r-1];
        }
        if(snakeXlength[r] < 26)
        {
          //snakeXlength[r] = 850;
          gameOver = true;
        }
      }
      repaint();
      
    }
    
    if(up)
    {
      for(int r = lengthOfSnake -1; r >= 0; r--)
      {
        snakeXlength[r+1] = snakeXlength[r];
      }
      for(int r = lengthOfSnake; r >= 0; r--)
      {
        if(r == 0)
        {
          snakeYlength[r] = snakeYlength[r] - 25;
        }else {
          snakeYlength[r] = snakeYlength[r-1];
        }
        if(snakeYlength[r] < 76)
        {
          //snakeYlength[r] = 625;
          gameOver = true;
        }
      }
      repaint();
      
    }
    if(down)
    {
      for(int r = lengthOfSnake -1; r >= 0; r--)
      {
        snakeXlength[r+1] = snakeXlength[r];
      }
      for(int r = lengthOfSnake; r >= 0; r--)
      {
        if(r == 0)
        {
          snakeYlength[r] = snakeYlength[r] + 25;
        }else {
          snakeYlength[r] = snakeYlength[r-1];
        }
        if(snakeYlength[r] > 624)
        {
          //snakeYlength[r] = 75;
          gameOver = true;
        }
      }
      repaint();
      
    }
  
    
  
}

      
    
    
    public void keyPressed(KeyEvent e)
  {
      if(e.getKeyCode() == KeyEvent.VK_SPACE)
   {
     moves = 0;
     score = 0;
     lengthOfSnake = 3;
     repaint();
   }
   if(e.getKeyCode() == KeyEvent.VK_RIGHT)
   {
     moves++;
     right = true;
     if(!left)
     {
       right = true;
     }else {
       right = false;
       left = true;
     }
     up = false;
     down = false;
   }
   if(e.getKeyCode() == KeyEvent.VK_LEFT)
   {
     moves++;
     left = true;
     if(!right)
     {
       left = true;
     }else {
       left = false;
       right = true;
     }
     up = false;
     down = false;
   }
   if(e.getKeyCode() == KeyEvent.VK_UP)
   {
     moves++;
     up = true;
     if(!down)
     {
       up = true;
     }else {
       up = false;
       down = true;
     }
     right = false;
     left = false;
   }
   if(e.getKeyCode() == KeyEvent.VK_DOWN)
   {
     moves++;
     down = true;
     if(!up)
     {
       down = true;
     }else {
       down = false;
       up = true;
     }
     right = false;
     left = false;
   }
    
    
    }
    public void keyReleased(KeyEvent e)
    {
    }
    public void keyTyped(KeyEvent e)
  {
    }
    
}