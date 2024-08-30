package main;

import entity.Entity;
import object.OBJ_Heart;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    Gamepanel gp;
    Graphics2D g2;
    Font foem,inkType ;
    BufferedImage heart_full,heart_half,heart_blank;
    public boolean messageOn=false;
    public String message="";
    public String currentDialogue;
    public int commandNum=0;
    public int titleNum=0;

    int messageCounter=0;
    public boolean gameFinish= false;

    public UI(Gamepanel gamepanel) {
        this.gp = gamepanel; // Initialize the gp field

        try {
            InputStream is=getClass().getResourceAsStream("/font/BAUHS93.TTF");
            foem= Font.createFont(Font.TRUETYPE_FONT,is);
            is=getClass().getResourceAsStream("/font/Inktype-MAp2J.ttf");
            inkType= Font.createFont(Font.TRUETYPE_FONT,is);
        }catch (FontFormatException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
          //Create hearts
        Entity heart= new OBJ_Heart(gp);
        heart_full=heart.image;
        heart_half=heart.image2;
        heart_blank=heart.image3;



    }

    public void showMessage(String text){
   message =text;
    messageOn=true;
    }
    public void draw(Graphics2D g2) {
       this.g2=g2;

       g2.setFont(inkType);
       g2.setColor(Color.cyan);
       if (gp.gameState==gp.playsState){
        drawPlayerLife();
       }
       if (gp.gameState== gp.titleState){

          drawTitleScreen();
       }
        if (gp.gameState==gp.pauseState){
            //Do pause state thing
            drawPlayerLife();
            drawPauseScreen();
        }
        if (gp.gameState==gp.dialougeState){
            //Do Dialouge state thing
            drawPlayerLife();
            drawDialogueScreen();
        }
    }
    public void drawDialogueScreen(){
int x=gp.tileSize*2;
int y=gp.tileSize/2;
int width=gp.screenWidth-(gp.tileSize*4);
int height=gp.tileSize*4;
drawSubWindow(x,y,width,height);
        g2.setFont(foem);
g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32));
x+=gp.tileSize;
        y+=gp.tileSize;
        for(String line:currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y+=40;
        }
    }
    public void drawSubWindow(int x,int y,int width,int height){
        Color c=new Color(0,0,1,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);
          c=new Color(255,255,255,250);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);

    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,80F));
        String text="PAUSE";
        int x=getXFontCentre(text);
        int y=gp.screenHeight/2;
        g2.drawString(text,x,y);
    }
    public void drawTitleScreen(){
        //Game Name
        //set backgroundcolor
        if (titleNum==0){
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80));
       String Title="Hinder-Land" ;
        int x=getXFontCentre(Title);
        int y=gp.tileSize*3;
        //text Shadow
        g2.setColor(Color.GRAY);
        g2.drawString(Title,x+5,y+5);
        //Main text
        g2.setColor(Color.WHITE);
        g2.drawString(Title,x,y);
        //player Image
        x=gp.screenWidth/2-(gp.tileSize*2);
        y+=gp.tileSize*2;
        g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);
        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48));
        String text;
        text="Game";
        x=getXFontCentre(Title);
        y+=gp.tileSize*3.5;
        g2.drawString(text,x,y);
        if (commandNum==0){
            g2.drawString(">",x-gp.tileSize,y);
        }

        text="Settings";
        x=getXFontCentre(Title);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum==1){
            g2.drawString(">",x-gp.tileSize,y);
        }

        text="Quit";
        x=getXFontCentre(Title);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum==2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        }
        else if (titleNum==1) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // Game Title
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
            String title = "Settings";
            int x = getXFontCentre(title);
            int y = gp.tileSize * 3;

            // Text Shadow
            g2.setColor(Color.GRAY);
            g2.drawString(title, x + 5, y + 5);

            // Main Text
            g2.setColor(Color.WHITE);
            g2.drawString(title, x, y);

            // Player Image
            x = gp.screenWidth / 2 - (gp.tileSize * 2);
            y += gp.tileSize *1;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            // Menu Options
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48));
            String text;

            // Option 1: Game
            text = "Sound";
            x = getXFontCentre(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            // Option 2: Settings
            text = "Character";
            x = getXFontCentre(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            // Option 3: Quit Game
            text = "Other";
            x = getXFontCentre(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Back";
            x = getXFontCentre(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }

        }

        else if (titleNum == 5) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            int x = gp.tileSize * 3;
            int y = gp.tileSize * 4;

            // Draw the first option (blue player)
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,48));
            g2.drawImage(gp.player.blue, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            if (commandNum == 0) {
                g2.drawString("^", x + gp.tileSize, y + gp.tileSize * 3); // Adjusted position
            }

            // Draw the second option (green player)
            x += gp.tileSize * 6;
            g2.drawImage(gp.player.green, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            if (commandNum == 1) {
                g2.drawString("^", x + gp.tileSize, y + gp.tileSize *3); // Adjusted position
            }

            // Draw the "Back" option
            x = getXFontCentre("Back");
            y += gp.tileSize * 4;
            g2.drawString("Back", x, y);
            if (commandNum == 3) { // commandNum == 2 for the Back option
                g2.drawString(">", x - gp.tileSize*5, y);
            }
        }


    }
    public void drawPlayerLife(){
        int x=gp.tileSize/4;
        int y=gp.tileSize/2;
        int i=0;
        //Draw blank health
        while (i<gp.player.maxlife/2){
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x+=gp.tileSize;
        }
        //Draw current life
         x=gp.tileSize/4;
         y=gp.tileSize/2;
         i=0;
         while (i<gp.player.life){
             g2.drawImage(heart_half,x,y,null);
             i++;
             if(i<gp.player.life){
                 g2.drawImage(heart_full,x,y,null);
             }
             i++;
             x+=gp.tileSize;
         }
    }

    public int getXFontCentre(String text){
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.screenWidth/2-length/2;
        return x;
    }
}
