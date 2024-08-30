package object;

import entity.Entity;
import main.Gamepanel;
import main.UI;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_key extends Entity {
    public OBJ_key(Gamepanel gp){
        super(gp);
     name="key";
     down1=setup("/objects/key");
    }
}
