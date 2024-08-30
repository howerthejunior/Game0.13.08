package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Gamepanel extends JPanel implements Runnable {


    //screen size
    public int originalTileSize =16;
    public int scale = 3;
   public int tileSize = originalTileSize*scale;
public  int maxScreenCol=16;

    public  int maxScreenRow=12;
  public int screenWidth = tileSize*maxScreenCol;//768 pixel
   public int screenHeight = tileSize*maxScreenRow;//576 pixel
    //world settings
    public final int maxWorldCol=100;
    public final int maxWorldRow=100;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;

    //GameState
    public int gameState;
    public final int titleState=0;
    public final int playsState=1 ;
    public final int pauseState=2 ;
    public final int dialougeState=3;


    int FPS =30;
    TileManager tileM= new TileManager(this);
    public Keyhandler keyH= new Keyhandler(this);
    public EventHandler eHandler=new EventHandler(this);

    Sound music=new Sound();
    Sound se=new Sound();
    public UI ui=new UI(this);
    public CollisionChecker cChecker= new CollisionChecker(this);
    public AssetSetter aSetter=new AssetSetter(this);
    Thread gameThread;

    //entity and object
   public  Player player= new Player(this,keyH);
   public Entity[] obj =new Entity[20];
   public Entity monster[]=new Entity[20];
   ArrayList<Entity> entityList = new ArrayList<>();
   public Entity NPC[]= new Entity[10];

    public Gamepanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
         this.addKeyListener(keyH);
         this.setFocusable(true);
        for (int i = 0; i < obj.length; i++) {
            obj[i] = null;  // Ensure all elements are null initially
            }

    }

    public void setUpGame()
    {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
      //playMusic(0);
      gameState= titleState;
    }
    public void startGameThread()
    {
        gameThread= new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double interval=1000000000/FPS;
        double nextdrawinterval=System.nanoTime()+interval;
      while(gameThread!=null){
            // long currentTime=System.nanoTime();
           //  System.out.println(currentTime);
       // System.out.println("Game loop is working");
          update();
          repaint();
          try {
          double remaintime= nextdrawinterval -System.nanoTime();
              remaintime=remaintime/1000000;
              if(remaintime<0 )  {
                  remaintime=0;
              }
              Thread.sleep((long)remaintime) ;
              nextdrawinterval+=interval;
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
      }


    }
    public void  update() {
        if (gameState==playsState){
            player.update();
            for (int i=0;i<NPC.length;i++)
                if (NPC[i]!=null){
            NPC[i].update();}
            for (int i=0;i<monster.length;i++){
                if (monster[i]!=null){
                    monster[i].update();}
            }
        }

       if (gameState==pauseState){

       }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // DEBUG
        long drawstart = 0;
        if (keyH.checkdrawtime) {
            drawstart = System.nanoTime();
            System.out.println("Player Position: (" + player.wordlx + ", " + player.worldy + ")");
            System.out.println("Player X: " + player.wordlx);
            System.out.println("Player Y: " + player.worldy);
        }

        // Title Screen
        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            // Draw tiles
            tileM.draw(g2);

            // Add entities to the entityList
            entityList.add(player);
            for (Entity npc : NPC) {
                if (npc != null) {
                    entityList.add(npc);
                }
            }
            for (Entity object : obj) {
                if (object != null) {
                    entityList.add(object);
                }
            }
            for (Entity mon : monster) {
                if (mon != null) {
                    entityList.add(mon);
                }
            }

            // Sort entities by their worldy position
            Collections.sort(entityList, Comparator.comparingInt(e -> e.worldy));

            // Draw entities
            for (Entity entity : entityList) {
                entity.draw(g2);
            }

            // Clear the entity list after drawing
            entityList.clear();

            // Draw UI
            ui.draw(g2);

            // DEBUG: Display draw time
            if (keyH.checkdrawtime) {
                long drawend = System.nanoTime();
                long passes = drawend - drawstart;
                g2.setColor(Color.WHITE);
                g2.drawString("Draw time: " + passes + " ns", 10, 400);
                System.out.println("Draw time: " + passes + " ns");
            }
        }

        g2.dispose();
    }
    public void playMusic(int i){
       music.setFile(i);
      music.play();
       music.loop();
    }
    public  void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}

