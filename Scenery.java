import java.util.*;
import java.awt.image.*;
import java.awt.event.*;

public class Scenery
{
  private ArrayList<Sprite> scenarios;
  private ArrayList<Song> songs;
  private int scenery;

  public Scenery(BufferedImage imageBuffer)
  {
    this.scenery = 0;

    this.scenarios = new ArrayList<Sprite>();
    this.scenarios.add(new Sprite("sprites/game-start.png", 800, 600, 0, 0, 1, 1, imageBuffer));
    this.scenarios.add(new Sprite("sprites/game.png", 800, 600, 0, 0, 1, 1, imageBuffer));
    this.scenarios.add(new Sprite("sprites/game-over.png", 800, 600, 0, 0, 1, 1, imageBuffer));

    this.songs = new ArrayList<Song>();
    this.songs.add(new Song("songs/mega-man-x.mid", 999));
    this.songs.add(new Song("songs/mega-man-x2.mid", 999));
    this.songs.add(new Song("songs/mortal-kombat.mid", 999));
    
    this.songs.get(this.scenery).play();
  }

  public void draw()
  {
    this.scenarios.get(this.scenery).draw();
  }

  public void setScenery(int scenery)
  {
    if(scenery >= 0 && scenery < scenarios.size())
    {
      this.songs.get(this.scenery).pause();
      this.scenery = scenery;
      this.songs.get(this.scenery).play();
    }
  }

  public int getScenery()
  {
    return this.scenery;
  }

  public void control(KeyEvent e)
  {
    if(e.getKeyCode() == e.VK_ENTER && this.scenery != 1)
      this.setScenery(1);
  }
}