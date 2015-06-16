import java.util.*;
import java.awt.image.*;

public class Food
{
  Sprite food;

  public Food(BufferedImage imageBuffer)
  {

    int x = random((int) imageBuffer.getWidth() / 15) * 15;
    int y = random((int) imageBuffer.getHeight() / 15) * 15;

    this.food = new Sprite("sprites/food.png", 15, 15, x, y, 1, 1, imageBuffer);
  }

  public void draw()
  {
    food.draw();
  }

  public int random(int limit)
  {
    int number = (int) (Math.random() * limit);
    return number;
  }
}