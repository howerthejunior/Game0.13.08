package object;

import entity.Entity;
import main.Gamepanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_boots extends Entity {
    public OBJ_boots(Gamepanel gp){
        super(gp);

        name="boots";
        down1=setup("/objects/boots");
    }
}
