package object;

import entity.Entity;
import main.Gamepanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_chest extends Entity {
    public OBJ_chest(Gamepanel gp){
        super(gp);
        name="chest";
        down1=setup("/objects/chest");
    }
}
