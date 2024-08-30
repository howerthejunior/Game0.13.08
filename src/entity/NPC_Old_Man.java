package entity;

import main.Gamepanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_Old_Man extends Entity{
    public NPC_Old_Man(Gamepanel gp, String[] dialogues) {
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue(dialogues);
    }
    public void getImage(){

        up1=setup("/NPC/oldman_up_1");
        up2=setup("/NPC/oldman_up_2");
        down1=setup("/NPC/oldman_down_1");
        down2=setup("/NPC/oldman_down_2");
        left1=setup("/NPC/oldman_left_1");
        left2=setup("/NPC/oldman_left_2");
        right1=setup("/NPC/oldman_right_1");
        right2=setup("/NPC/oldman_right_2");
    }
    public void setDialogue(String[] dialogues) {
        dialogue = dialogues;
    }
public void setAction(){
        actionLock++;
        if (actionLock==100){
    Random random= new Random();
    int i=random.nextInt(100)+1;
    if (i<=25){
        direction="up";
    }
    if (i>25&&i<=50){
        direction="down";
    }
    if (i>50&&i<=75){
        direction="left";
    }
    if (i>75&&i<=100) {
        direction = "right";
    }
    actionLock=0;
    }
}
public void speak(){

         super.speak();
        }


}
