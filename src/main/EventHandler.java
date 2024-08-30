package main;

import java.awt.*;

public class EventHandler {
    Gamepanel gp;  // Assuming the class name is GamePanel, ensure it matches your actual class name
   EventRect eventRect[][];
   int previousEventX,previousEventY;
   boolean cantouchEvent=true;

    public EventHandler(Gamepanel gp) {
        this.gp = gp;
        eventRect =new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col=0;
        int row=0;
        while(col<gp.maxWorldCol&&row<gp.maxWorldRow){
        eventRect[col][row] = new EventRect();
            eventRect[col][row].x=23;
            eventRect[col][row].y=23;
            eventRect[col][row].height=2;
            eventRect[col][row].width=2;
            eventRect[col][row].eventRectDefaultX =    eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY =    eventRect[col][row].y;
        col++;
        if (col==gp.maxWorldCol){
            col=0;
            row++;
        }
        }
    }

    public void checkEvent() {
        int xDistance=Math.abs(gp.player.wordlx-previousEventX);
        int yDistance=Math.abs(gp.player.worldy-previousEventY);
        int distance =Math.max(yDistance,xDistance*10);
        if (distance>gp.tileSize){
            cantouchEvent=true;
        }
        if (cantouchEvent==true) {
            if (hit(66, 57, "right")) {damagePit(66, 57, gp.dialougeState);}
            if (hit(69, 88, "right")) {damagePit(69, 88, gp.dialougeState);}
            if (hit(17, 16, "up")) {healingpool(17, 16, gp.dialougeState);}
        }
    }

    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        // Adjust the solidArea position relative to the world coordinates
        gp.player.solidArea.x = gp.player.wordlx + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldy + gp.player.solidArea.y;

        // Set eventRect's position based on eventCol and eventRow
        eventRect[col][row].x = col * gp.tileSize +  eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize +  eventRect[col][row].y;

        // Check if the player's solidArea intersects with the eventRect
        if (gp.player.solidArea.intersects(eventRect[col][row])&& eventRect[col][row].eventdone==false) {
            if (gp.player.direction.equals(reqDirection) || reqDirection.equals("any")) {
                hit = true;
                previousEventX=gp.player.wordlx;
                previousEventY=gp.player.worldy;

            }
        }

        // Reset the solidArea and eventRect positions
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int col, int row,int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall in a pit";  // Assuming "ui" and "currentDialogue" are defined correctly
        gp.player.life -= 1;
        cantouchEvent=false;
//        eventRect[col][row].eventdone=true;
    }
    public void healingpool(int col, int row,int gamestate){
        if (gp.keyH.enterPressed==true){
            if ( gp.player.life<=10) {
                gp.gameState = gamestate;
                gp.ui.currentDialogue = "Angel will heal your wounds !";
                gp.player.life += 2;
            }
        }
        gp.keyH.enterPressed=false;
        cantouchEvent=false;

    }
}
