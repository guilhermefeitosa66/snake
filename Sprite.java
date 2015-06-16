import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;

public class Sprite
{
  private ImageIcon image;
  private int w, h, x, y, rows, cols, scene, frame, fps, time;
  private BufferedImage spriteBufferedImage;
  private Graphics2D g2dGame;
  private Graphics2D g2dSprite;
  BufferedImage[][] frames;

  public Sprite(String imagePath, int w, int h, int x, int y, int rows, int cols, BufferedImage bufferedImage)
  {
    this.image = new ImageIcon(imagePath);
    this.w = w;
    this.h = h;
    this.x = x;
    this.y = y;
    this.rows = rows;
    this.cols = cols;
    this.scene = 0;
    this.frame = 0;
    this.fps = 3;
    this.time = 0;
    this.g2dGame = (Graphics2D) bufferedImage.getGraphics();
    initScenes();
  }

  public void offset(int x, int y)
  {
    this.x = x;
    this.y = y;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public int getW()
  {
    return this.w;
  }

  public int getH()
  {
    return this.h;
  }

  public void rotate(int degree)
  {
    g2dGame.translate(
      (this.x + (this.w / 2)),
      (this.y + (this.h / 2))
    );
    g2dGame.rotate(degree * (Math.PI / 180));
    g2dGame.translate(
      -(this.x + (this.w / 2)),
      -(this.y + (this.h / 2))
    );
  }

  public void setFrame(int frame)
  {
    if(frame >= this.cols || frame < 0)
      this.frame = 0;
    else
      this.frame = frame;
  }

  public void setScene(int scene)
  {
    if(scene >= this.rows || scene < 0)
      this.scene = 0;
    else
      this.scene = scene;
  }

  public void setScene(int scene, int frame)
  {
    setScene(scene);
    setFrame(frame);
  }

  public int getScene()
  {
    return this.scene;
  }

  public int getFrame()
  {
    return this.frame;
  }

  public void animate()
  {
    setFrame(this.frame + 1);
  }

  public void slowAnimate()
  {
    this.time += 1;
    if(this.time > fps)
    {
      setFrame(this.frame + 1);
      this.time = 0;
    }
  }

  private void initScenes()
  {
    this.frames = new BufferedImage[this.rows][this.cols];
    for(int i = 0; i < this.rows; i++)
    { 
      for(int j = 0; j < this.cols; j++)
      {
        this.frames[i][j] = new BufferedImage(this.w, this.h, BufferedImage.TYPE_INT_ARGB);
        this.g2dSprite = (Graphics2D) this.frames[i][j].getGraphics();
        
        setScene(i, j);
        initFrames();
      }
    }
    setScene(0, 0);
  }

  private void initFrames()
  {
    this.g2dSprite.drawImage(
      this.image.getImage(),
      this.frame * this.w * -1,
      this.scene * this.h * -1,
      null
    );
  }

  public void draw()
  {
    this.g2dGame.drawImage(this.frames[this.scene][this.frame], this.x, this.y, null);
  }

  Rectangle2D getArea()
  {
    return new Rectangle2D.Double(this.x, this.y, this.w, this.h);
  }

  public boolean collision(Sprite sprite)
  {
    boolean collide = false;
    Area area = new Area(sprite.getArea());
    area.intersect(new Area(this.getArea()));

    if(!area.isEmpty())
    {
      collide = true;
    }

    return collide;
  }
}