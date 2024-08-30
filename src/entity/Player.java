package entity;

import main.Gamepanel;
import main.Keyhandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    Keyhandler keyH;

    public  final int screenX;
    String name;
    public final int screenY;
    public int bootsTimer=0;
   public int playersource=1;

   int standcount =0;


    public Player(Gamepanel gp, Keyhandler keyH) {
        super(gp);
        this.keyH = keyH;
        setDefaultValue();
        getPlayerImage();
        screenX=gp.screenWidth/2-(gp.tileSize/2);
        screenY=gp.screenHeight/2-(gp.tileSize/2);
        // collision area
        solidArea = new Rectangle();
        solidArea.x=6;
        solidArea.y=14;
        solidAreaDefaultX=solidArea.x;
         solidAreaDefaultY=solidArea.y;
        solidArea.width=30;
        solidArea.height=30;

    }

    public void setDefaultValue() {
        wordlx = gp.tileSize*50;
        worldy = gp.tileSize*50;
        speed = 4;
       direction="down";
       maxlife=16;
       life=maxlife;

    }
    public void getPlayerImage(){
          playerID(playersource);


    }


    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            //ckeck object collision
            int objINDEX = gp.cChecker.checkObject(this, true);
            pickUpObject(objINDEX);
//check NPC collision
            int npcindex = gp.cChecker.checkEntity(this, gp.NPC);
            InteractNpc(npcindex);
            // check monster
            int monsterIndex=gp.cChecker.checkEntity(this,gp.monster);
            contactMonseter(monsterIndex);

            //event
            gp.eHandler.checkEvent();
            gp.keyH.enterPressed=false;

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
        // Outside if statement
        if(invisible==true){
            invinsibleConter++;
            if (invinsibleConter>30){
                invisible=false;
                invinsibleConter=0;
            }
        }

    }
            public void InteractNpc(int i) {
                if (i != 999) {
                    if (keyH.enterPressed==true) {
                        gp.gameState = gp.dialougeState;
                        gp.NPC[i].speak();
                    }
                }
            }


    public void pickUpObject(int i){
   if (i!=999){

}
        }

    public void draw(Graphics2D g2){

//        g2.setColor(Color.white);
//        g2.fillRect(x,y,gp.tileSize,gp.tileSize);
        BufferedImage image =null;
        switch(direction){

            case "up":
                if(spriteNum==1){
                image=up1;
                }
                if (spriteNum==2){
                    image=up2;
                }

                break;
            case "down":
                if (spriteNum==1){
                    image=down1;
                }
                if (spriteNum==2){
                    image=down2;
                }

                break;
            case "left":
                if (spriteNum==1){
                    image=left1;
                }
                if (spriteNum==2){
                    image=left2;
                }
                break;
            case "right":
                if (spriteNum==1){
                    image=right1;
                }
                if (spriteNum==2){
                    image=right2;
                }
                break;
        }
        if(invisible==true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
        }
        g2.drawImage(image,screenX,screenY,null );
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
//DEBUG
//        g2.setFont(new Font("Arial",Font.PLAIN,26));
//        g2.setColor(Color.white);
//        g2.drawString("invisible :"+invinsibleConter,10,400);
    }
    public void playerID(int playersource) {

        if (playersource==1){
            up1 = setup("/player/boy_up_1");
            up2 = setup("/player/boy_up_2");
            down1 = setup("/player/boy_down_1");
            down2 = setup("/player/boy_down_2");
            left1 = setup("/player/boy_left_1");
            left2 = setup("/player/boy_left_2");
            right1 = setup("/player/boy_right_1");
            right2 = setup("/player/boy_right_2");
            name="BLue drown" ;
             }
        if (playersource==2){
            up1 = setup("/player/green_up_1");
            up2 = setup("/player/green_up_2");
            down1 = setup("/player/green_down_1");
            down2 = setup("/player/green_down_2");
            left1 = setup("/player/green_left_1");
            left2 = setup("/player/green_left_2");
            right1 = setup("/player/green_right_1");
            right2 = setup("/player/green_right_2");
            name="Green  Gonblin" ;
        }
        blue = setup("/player/boy_down_1");
        green = setup("/player/green_down_1");
    }
    public void contactMonseter(int i){
        if (i!=999){
            if (invisible==false){
            life-=1;
            invisible=true;
            }
        }
    }

}

