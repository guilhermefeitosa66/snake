import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.io.File;

public class Song
{
  private Sequencer player;
  private Sequence song;
  private String songFile;
  private int repeat;

  public Song(String songFile, int repeat)
  {
    this.songFile = songFile;
    this.repeat = repeat;
  }

  public Song(String songFile)
  {
    this.songFile = songFile;
    this.repeat = 1;
  }

  public void play()
  {
    try
    {
      this.player = MidiSystem.getSequencer();
      this.song = MidiSystem.getSequence(new File(this.songFile));
      this.player.open();
      this.player.setSequence(this.song);
      this.player.setLoopCount(repeat);
      this.player.start();
    }catch(Exception e){
      System.out.println("Error in song: "+ this.songFile);
    }
  }

  public void pause()
  {
    this.player.stop();
  }
}