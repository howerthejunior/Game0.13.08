package main;

import Monter.Mon_Green;
import entity.NPC_Old_Man;
import object.*;


public class AssetSetter {
    Gamepanel gp;

    public AssetSetter(Gamepanel gp) {
        this.gp = gp;
    }

    public void setObject() {
    }

    public void setNPC() {
        String[] dialogue1 = {
                "Hello! Lad.",
                "Welcome to our village Terdovosky.",
                "I am Head of this village.",
                "I am a Blacksmith by proffesion.",
                "What you want."
        };

        String[] dialogue2 = {
                "This is the Florecsent lake .",
                "There are many myth about it. \n Do you know who lives here ?",
                "I do fishing in this lake.",
                "Stay vigilant.",
                "I am now very old."
        };

        String[] dialogue3 = {
                "Ah, a new face!",
                "Who are you ?",
                "DO you know about Rhebose.",
                "Stay safe.",
        };

        gp.NPC[0] = new NPC_Old_Man(gp, dialogue1);
        gp.NPC[0].wordlx = gp.tileSize * 85;
        gp.NPC[0].worldy = gp.tileSize * 17;

        gp.NPC[1] = new NPC_Old_Man(gp, dialogue3);
        gp.NPC[1].wordlx = gp.tileSize * 76;
        gp.NPC[1].worldy = gp.tileSize * 18;

        gp.NPC[2] = new NPC_Old_Man(gp, dialogue3);
        gp.NPC[2].wordlx = gp.tileSize * 80;
        gp.NPC[2].worldy = gp.tileSize * 8;

        gp.NPC[3] = new NPC_Old_Man(gp, dialogue2);
        gp.NPC[3].wordlx = gp.tileSize * 60;
        gp.NPC[3].worldy = gp.tileSize * 87;


    }
    public void setMonster(){
        gp.monster[0] = new Mon_Green(gp);
        gp.monster[0].wordlx = gp.tileSize * 65;
        gp.monster[0].worldy = gp.tileSize * 58;

        gp.monster[1] = new Mon_Green(gp);
        gp.monster[1].wordlx = gp.tileSize * 68;
        gp.monster[1].worldy = gp.tileSize * 61;

        gp.monster[2] = new Mon_Green(gp);
        gp.monster[2].wordlx = gp.tileSize * 71;
        gp.monster[2].worldy = gp.tileSize * 56;

        gp.monster[3] = new Mon_Green(gp);
        gp.monster[3].wordlx = gp.tileSize * 65;
        gp.monster[3].worldy = gp.tileSize * 55;
    }

}