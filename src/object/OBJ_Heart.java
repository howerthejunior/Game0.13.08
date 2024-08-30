package object;

import entity.Entity;
import main.Gamepanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {
    public OBJ_Heart(Gamepanel gp){
        super(gp);
        image=setup("/objects/heart_full");
        image2=setup("/objects/heart_half");
        image3=setup("/objects/heart_blank");

        name="heart";

    }
}
