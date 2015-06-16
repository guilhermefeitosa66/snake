import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class GameSnake extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener
{ 
  BufferedImage imageBuffer;
  Graphics2D g;
  Graphics2D g2d;
  int FPS = 30;
  int WINDOW_WIDTH = 800;
  int WINDOW_HEIGHT = 600;

  Snake snake;
  Food food;
  Scenery scenery;
  
 
  public void init()
  {
    super.setTitle("Titulo do Jogo!");
    super.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    super.setResizable(false);
    super.setLayout(null);
    super.setVisible(true);
    super.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.imageBuffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    this.g = (Graphics2D) super.getGraphics();
    g2d = (Graphics2D) this.imageBuffer.getGraphics();


    /*Listenners*/
    addKeyListener(this);
    addMouseListener(this);
    addMouseMotionListener(this);
    
    /*Game objects*/
    snake = new Snake(imageBuffer);
    food = new Food(imageBuffer);
    scenery = new Scenery(imageBuffer);
  }
 
  public void update()
  {
    this.snake.eat(this.food);
    this.snake.move();
    this.snake.die(this.scenery);
  }
 
  public void display()
  {
    if(this.scenery.getScenery() == 1)
    {
      scenery.draw();
      snake.draw();
      food.draw();
      g2d.setFont(new Font("Ubuntu", Font.BOLD, 14));
      g2d.setColor(new Color(0, 0, 0));
      g2d.drawString("POINTS: " + snake.getPoints(), 700, 50);
    }else{
      scenery.draw();
    }

    g.drawImage(imageBuffer, 0, 0, null);
  }

  public void run()
  {
    init();
    while(true)
    {
      update();
      display();
      try
      {
        Thread.sleep(1000/FPS);
      } catch (Exception e) {
        System.out.println("Erro.");
      }
    }
  }

  /*Keyboard events*/
  public void keyPressed(KeyEvent e)
  {
    if(this.scenery.getScenery() == 1)
    {
      this.snake.control(e);
    }else{
      this.scenery.control(e);
      this.snake = new Snake(this.imageBuffer);
      this.food = new Food(this.imageBuffer);
    }
  }

  public void keyReleased(KeyEvent e){}
  public void keyTyped(KeyEvent e){}
  
  /*Mouse events*/
  public void mouseClicked(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mousePressed(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
  public void mouseDragged(MouseEvent e){}
  public void mouseMoved(MouseEvent e){}

  public static void main(String[] args)
  {
    Thread game = new Thread(new GameSnake());
    game.start();
  }
}