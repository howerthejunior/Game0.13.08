package Monter;

import entity.Entity;
import main.Gamepanel;

import java.util.Random;

public class Mon_Green extends Entity {

    public Mon_Green(Gamepanel gp) {
        super(gp);
        type=2;
        name="Green_devil";
        speed=2;
        life=maxlife;
        solidArea.x=2;
        solidArea.y=18;
        solidArea.width=40;
        solidArea.height=30;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        getImage();
    }
    public void getImage(){
        up1=setup("/monster/greenslime_down_1");
        up2=setup("/monster/greenslime_down_2");
        down1=setup("/monster/greenslime_down_1");
        down2=setup("/monster/greenslime_down_2");
        left1=setup("/monster/greenslime_down_1");
        left2=setup("/monster/greenslime_down_2");
        right1=setup("/monster/greenslime_down_1");
        right2=setup("/monster/greenslime_down_2");

    }


    public void setAction() {
        actionLock++;

        // Check if it's time to update the monster's action
        if (actionLock >= 100) {
            actionLock = 0;

            // Check if the player is within range
            int playerX = gp.player.wordlx;
            int playerY = gp.player.worldy;
            int distanceX = Math.abs(wordlx - playerX);
            int distanceY = Math.abs(worldy - playerY);

            // Convert detection range from tiles to pixels
            int detectionRange = 12 * gp.tileSize;

            // Determine the direction based on the player's position
            if (distanceX <= detectionRange && distanceY <= detectionRange) {
                // Player is within range; follow the player
                if (distanceX > distanceY) {
                    direction = (wordlx < playerX) ? "right" : "left";
                } else {
                    direction = (worldy < playerY) ? "down" : "up";
                }
            } else {
                // Randomly choose a new direction if the player is not in range
                Random random = new Random();
                int i = random.nextInt(100);
                if (i <= 25) {
                    direction = "up";
                } else if (i <= 50) {
                    direction = "down";
                } else if (i <= 75) {
                    direction = "left";
                } else {
                    direction = "right";
                }
            }
        }

        // Check for collision with tiles and change direction if needed
        collisionOn = false;
        gp.cChecker.checkTile(this);
        if (collisionOn) {
            // Change direction if a collision is detected
            Random random = new Random();
            int i = random.nextInt(100);
            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
        }

    }


}
