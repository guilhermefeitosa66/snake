import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class Game extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener
{ 
  BufferedImage imageBuffer;
  int FPS = 30;
  int WINDOW_WIDTH = 800;
  int WINDOW_HEIGHT = 600;

  Sprite s1;
  Sprite s2;
  Song bgSong;
 
  public void init()
  {
    setTitle("Titulo do Jogo!");
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setResizable(false);
    setLayout(null);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    imageBuffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);

    /*Listenners*/
    addKeyListener(this);
    addMouseListener(this);
    addMouseMotionListener(this);

    /*new Sprite(imageURL, w, h, x, y, rows, cols, bufferedImage)*/
    s1 = new Sprite("sprites/sprite-80x105-4x4.png", 80, 105, 200, 200, 4, 4, imageBuffer);
    s2 = new Sprite("sprites/sprite-80x105-4x4.png", 80, 105, 400, 200, 4, 4, imageBuffer);
    s1.setScene(2);
    bgSong = new Song("songs/super-mario.mid", 2);
    bgSong.play();
  }
 
  public void update()
  {
    s1.slowAnimate();

    if(s1.collision(s2))
      bgSong.pause();
      //s2 = null;
    s1.offset(s1.getX()+1, s1.getY());
  }
 
  public void display()
  {
    Graphics2D g = (Graphics2D) getGraphics();
    Graphics2D ib = (Graphics2D) imageBuffer.getGraphics();
    ib.setColor(new Color(255, 255, 255));
    ib.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

    s1.draw();
    s2.draw();

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
  public void keyPressed(KeyEvent e){}
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
    Thread game = new Thread(new Game());
    game.start();
  }
}