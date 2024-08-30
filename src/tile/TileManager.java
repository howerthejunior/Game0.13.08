package tile;

import main.Gamepanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {
    Gamepanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;
    ArrayList<String> FileNames = new ArrayList<>();
    ArrayList<String> collisionstatus = new ArrayList<>();


    public TileManager(Gamepanel gp) {
        this.gp = gp;
       // InputStream is=getClass().getResourceAsStream("/maps/tiledata.txt");
       // BufferedReader br = new BufferedReader(new InputStreamReader(is));
        tile = new Tile[50]; // Adjust this size if you have more or fewer tiles
        getTileImage();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {

           setup(1,"grass00",false);
           setup(2,"grass00",false);
           setup(3,"grass00",false);
           setup(4,"grass00",false);
           setup(5,"grass00",false);
           setup(6,"grass00",false);
           setup(7,"grass00",false);
           setup(8,"grass00",false);
           setup(9,"grass00",false);

           //tiles
        setup(10,"earth",false);
        setup(11,"grass00",false);
           setup(12,"grass01",false);
        setup(13,"road00",false);
        setup(14,"road01",false);
        setup(15,"road02",false);
        setup(16,"road03",false);
        setup(17,"road04",false);
        setup(18,"road05",false);
        setup(19,"road06",false);
        setup(20,"road07",false);
        setup(21,"road08",false);
        setup(22,"road09",false);
        setup(23,"road10",false);
        setup(24,"road11",false);
        setup(25,"road12",false);
        setup(26,"tree",true);
        setup(27,"wall",true);
        setup(28,"water00",true);
        setup(29,"water01",true);
        setup(30,"water02",true);
        setup(31,"water03",true);
        setup(32,"water04",true);
        setup(33,"water05",true);
        setup(34,"water06",true);
        setup(35,"water07",true);
        setup(36,"water08",true);
        setup(37,"water09",true);
        setup(38,"water10",true);
        setup(39,"water11",true);
        setup(40,"water12",true);
        setup(41,"water13",true);
        setup(43,"whut",true);

            // Initialize additional tiles as needed, following the pattern above

    }

    public void setup(int index, String imageName, boolean collision){

        UtilityTool Utool=new UtilityTool();
        try {

            tile[index]=new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
            tile[index].image=Utool.scaleImage(tile[index].image,gp.tileSize, gp.tileSize);
            tile[index].collision=collision;


        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void loadMap(String filepath) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            if (is == null) {
                throw new IOException("Map file not found: " + filepath);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (row < gp.maxWorldRow) {
                String line = br.readLine();
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                String[] numbers = line.trim().split(" ");
                for (col = 0; col < numbers.length && col < gp.maxWorldCol; col++) {
                    try {
                        int num = Integer.parseInt(numbers[col]);
                        if (num < tile.length) {
                            mapTileNum[col][row] = num;
                        } else {
                            System.err.println("Tile index out of bounds at row " + row + " col " + col);
                            mapTileNum[col][row] = 0; // Default to 0 if index is out of bounds
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing number: " + numbers[col] + " at row " + row + " col " + col);
                        mapTileNum[col][row] = 0; // Default to 0 if parsing fails
                    }
                }
                row++;
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error loading map: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            if (tileNum >= 0 && tileNum < tile.length && tile[tileNum] != null) {
                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.wordlx + gp.player.screenX;
                int screenY = worldY - gp.player.worldy + gp.player.screenY;

                if (worldX + gp.tileSize > gp.player.wordlx - gp.player.screenX &&
                        worldX - gp.tileSize < gp.player.wordlx + gp.player.screenX &&
                        worldY + gp.tileSize > gp.player.worldy - gp.player.screenY &&
                        worldY - gp.tileSize < gp.player.worldy + gp.player.screenY) {

                    g2.drawImage(tile[tileNum].image, screenX, screenY,  null);
                }
            }

            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
