
/**
 * Write a description of class Main here.
 *
 * @author (Bridget)
 * @version (March 30, 2020)
 */
import java.awt.Color;

import javax.swing.JFrame;

public class Main
{

  public static void main(String[] args)
  {
    JFrame jframe = new JFrame();
    Gameplay gameplay = new Gameplay();
    
    
    jframe.setBounds(10, 10, 905, 700);
    jframe.setBackground(Color.black);
    jframe.setResizable(false);
    
    jframe.add(gameplay);
    jframe.setVisible(true);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    
    

  }

}
