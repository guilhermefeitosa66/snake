import java.util.*;
import java.awt.image.*;
import java.awt.event.*;

public class Snake
{
  private ArrayList<Sprite> snake;
  private Sprite head;
  private String direction;
  private BufferedImage imageBuffer;
  private int points;

  public Snake(BufferedImage imageBuffer)
  {
    this.imageBuffer = imageBuffer;
    this.direction = "none";
    this.points = 0;

    int x = random((int) imageBuffer.getWidth() / 15) * 15;
    int y = random((int) imageBuffer.getHeight() / 15) * 15;
    
    this.snake = new ArrayList<Sprite>();
    this.snake.add(new Sprite("sprites/head.png", 15, 15, x, y, 1, 1, imageBuffer));

    for (int i = 0; i < 5 ; i++)
      this.snake.add(new Sprite("sprites/body.png", 15, 15, x, y, 1, 1, imageBuffer));
    
    head = snake.get(0);
  }

  public int getPoints()
  {
    return this.points;
  }

  public void draw()
  {
    for (int i = snake.size() - 1; i >= 0; i--)
      snake.get(i).draw();
  }

  public void move()
  {
    moveBody();
    if(this.direction == "up")
      this.head.offset(this.head.getX(), this.head.getY() - 15);
    if(this.direction == "down")
      this.head.offset(this.head.getX(), this.head.getY() + 15);
    if(this.direction == "left")
      this.head.offset(this.head.getX() - 15, this.head.getY());
    if(this.direction == "right")
      this.head.offset(this.head.getX() + 15, this.head.getY());

    if(this.head.getX() > this.imageBuffer.getWidth())
      this.head.offset(0, this.head.getY());
    if(this.head.getX() < 0)
      this.head.offset(this.imageBuffer.getWidth(), this.head.getY());
    if(this.head.getY() > this.imageBuffer.getHeight())
      this.head.offset(this.head.getX(), 0);
    if(this.head.getY() < 0)
      this.head.offset(this.head.getX(), this.imageBuffer.getHeight());
  }

  private void moveBody()
  {
    for(int i = (this.snake.size() - 1); i > 0; i--)
    {
      this.snake.get(i).offset(
        this.snake.get(i - 1).getX(),
        this.snake.get(i - 1).getY()
      );
    }
  }

  public void control(KeyEvent e)
  {
    if(e.getKeyCode() == e.VK_UP && direction != "down")
      this.direction = "up";
    if(e.getKeyCode() == e.VK_DOWN && direction != "up")
      this.direction = "down";
    if(e.getKeyCode() == e.VK_LEFT && direction != "right")
      this.direction = "left";
    if(e.getKeyCode() == e.VK_RIGHT && direction != "left")
      this.direction = "right";
  }

  public void eat(Food food)
  {
    if(this.head.collision(food.food))
    {
      this.snake.add(
        new Sprite("sprites/body.png", 15, 15, this.head.getX(), this.head.getY(), 1, 1, this.imageBuffer)
      );
      int x = random((int) imageBuffer.getWidth() / 15) * 15;
      int y = random((int) imageBuffer.getHeight() / 15) * 15;
      food.food.offset(x, y);
      this.points += 5;
    }
  }

  public void die(Scenery scenery)
  {
    if(direction != "none")
    {
      for(int i = (this.snake.size() - 1); i > 0; i--)
      {
        if(this.head.collision(this.snake.get(i)))
        {
          direction = "none";
          scenery.setScenery(2);
        }
      }
    }
  }

  public int random(int limit)
  {
    int number = (int) (Math.random() * limit);
    return number;
  }
}