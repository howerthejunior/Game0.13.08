package entity;

import main.Gamepanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
       public Gamepanel gp;
        public int wordlx, worldy;
        public BufferedImage image,image2,image3;
        public String name;
        public boolean collision=false;
        UtilityTool Utool=new UtilityTool();
        public int speed;
        public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2,green,blue;
        public String direction="down";
        public int type; // 0= player 1=npc 2=monster
        public int spriteCounter = 0;
        public int spriteNum = 1;
        public Rectangle solidArea = new Rectangle(0, 0, 30, 40);
        public int solidAreaDefaultX, solidAreaDefaultY;
        public boolean collisionOn = false;
        public int actionLock=0;
        public boolean invisible=false;
        public int invinsibleConter=0;
        String dialogue[]=new String[20];
        public int dialogueNum=0;
        //Character status
        public int maxlife;
        public int life;

        public Entity(Gamepanel gp) {
                this.gp = gp;
        }
        public void setAction() {
        }
        public void speak() {
                if (dialogue[dialogueNum] == null) {
                        dialogueNum = 0;
                }

                gp.ui.currentDialogue = dialogue[dialogueNum];
                dialogueNum++;

                // Reset dialogueNum if it exceeds the array length
                if (dialogueNum >= dialogue.length) {
                        dialogueNum = 0;
                }

                switch (gp.player.direction) {
                        case "up":
                                direction = "down";
                                break;
                        case "down":
                                direction = "up";
                                break;
                        case "left":
                                direction = "right";
                                break;
                        case "right":
                                direction = "left";
                                break;
                }
        }

public void update(){
                setAction();
                collisionOn=false;
                gp.cChecker.checkTile(this);
                gp.cChecker.checkEntity(this,gp.NPC);
                gp.cChecker.checkEntity(this,gp.monster);
                gp.cChecker.checkObject(this,false);
               boolean contactPlayer= gp.cChecker.checkplayer(this);
               if (this.type==2&& contactPlayer==true){
                       if (gp.player.invisible==false){
                               gp.player.life-=1;
                               gp.player.invisible=true;
                       }
               }
        if (collisionOn == false) {

                switch (direction) {
                        case "up":
                                worldy -= speed;
                                break;
                        case "down":
                                worldy += speed;
                                break;
                        case "left":
                                wordlx -= speed;
                                break;
                        case "right":
                                wordlx += speed;

                                break;
                }
        }
        //change sprite image in how much frame
        spriteCounter++;
        if (spriteCounter > 12) {
                if (spriteNum == 1) {
                        spriteNum = 2;
                } else if (spriteNum == 2) {
                        spriteNum = 1;
                }
                spriteCounter = 0;

        }
}
        public void draw(Graphics2D g2) {
                BufferedImage image = null;
                int screenX = wordlx - gp.player.wordlx + gp.player.screenX;
                int screenY = worldy - gp.player.worldy + gp.player.screenY;

                if (wordlx + gp.tileSize > gp.player.wordlx - gp.player.screenX &&
                        wordlx - gp.tileSize < gp.player.wordlx + gp.player.screenX &&
                        worldy + gp.tileSize > gp.player.worldy - gp.player.screenY &&
                        worldy - gp.tileSize < gp.player.worldy + gp.player.screenY) {

                        // Assign the correct image based on the direction and sprite number
                        switch (direction) {
                                case "up":
                                        if (spriteNum == 1) {
                                                image = up1;
                                        } else if (spriteNum == 2) {
                                                image = up2;
                                        }
                                        break;
                                case "down":
                                        if (spriteNum == 1) {
                                                image = down1;
                                        } else if (spriteNum == 2) {
                                                image = down2;
                                        }
                                        break;
                                case "left":
                                        if (spriteNum == 1) {
                                                image = left1;
                                        } else if (spriteNum == 2) {
                                                image = left2;
                                        }
                                        break;
                                case "right":
                                        if (spriteNum == 1) {
                                                image = right1;
                                        } else if (spriteNum == 2) {
                                                image = right2;
                                        }
                                        break;
                        }

                        // Now draw the image if it has been set
                        if (image != null) {
                                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                        }
                }
        }

        public BufferedImage setup(String imageName) {
                UtilityTool Utool = new UtilityTool();
                BufferedImage scaledImage = null;

                try {
                        scaledImage = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
                        scaledImage = Utool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return scaledImage;
        }
        public Gamepanel getGamepanel() {
                return gp;
        }
}

