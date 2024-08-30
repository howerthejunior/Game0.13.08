package object;

import entity.Entity;
import main.Gamepanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_door extends Entity {
    public OBJ_door(Gamepanel gp) {
        super(gp);
        name = "door";  // Set the name of the object to "door"
        down1=setup("/objects/door");
        solidArea.x=0;
        solidArea.y=0;
        solidArea.width=48;
        solidArea.height=32;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
    }
}
