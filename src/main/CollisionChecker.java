package main;

import entity.Entity;

public class CollisionChecker {
    Gamepanel gp;

    public CollisionChecker(Gamepanel gp) {
        this.gp = gp;

    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.wordlx + entity.solidArea.x;
        int entityRightWorldX = entity.wordlx + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldy + entity.solidArea.y;
        int entityBottomWorldY = entity.worldy + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;

                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;

                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;

                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;

                }
                break;


        }

    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        if (gp.obj != null) {
            for (int i = 0; i < gp.obj.length; i++) {
                if (gp.obj[i] != null) { // Check if the object is not null
                    // Get entity's solid area position
                    entity.solidArea.x = entity.wordlx + entity.solidArea.x;
                    entity.solidArea.y = entity.worldy + entity.solidArea.y;

                    // Get object's solid area position
                    gp.obj[i].solidArea.x = gp.obj[i].wordlx + gp.obj[i].solidArea.x;
                    gp.obj[i].solidArea.y = gp.obj[i].worldy + gp.obj[i].solidArea.y;

                    switch (entity.direction) {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                                if (gp.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                            if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                                if (gp.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                                if (gp.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                                if (gp.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                    }

                    // Reset solid area position
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                    gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
                }
            }
        }
            return index;
        }
        public int checkEntity(Entity entity, Entity[] target){
            int index = 999;
            if (target != null) {
                for (int i = 0; i < target.length; i++) {
                    if (target[i] != null) { // Check if the object is not null
                        // Get entity's solid area position
                        entity.solidArea.x = entity.wordlx + entity.solidArea.x;
                        entity.solidArea.y = entity.worldy + entity.solidArea.y;

                        // Get object's solid area position
                        target[i].solidArea.x = target[i].wordlx + target[i].solidArea.x;
                        target[i].solidArea.y = target[i].worldy + target[i].solidArea.y;

                        switch (entity.direction) {
                            case "up": entity.solidArea.y -= entity.speed;
                                break;
                            case "down": entity.solidArea.y += entity.speed;
                                break;
                            case "left": entity.solidArea.x -= entity.speed;
                                break;
                            case "right": entity.solidArea.x += entity.speed;

                                break;
                        }
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            if(target[i]!=entity) {
                                entity.collisionOn = true;
                                index = i;
                            }
                        }

                        // Reset solid area position
                        entity.solidArea.x = entity.solidAreaDefaultX;
                        entity.solidArea.y = entity.solidAreaDefaultY;
                        target[i].solidArea.x = target[i].solidAreaDefaultX;
                        target[i].solidArea.y = target[i].solidAreaDefaultY;
                    }
                }
            }
            return index;
        }
        public boolean checkplayer(Entity entity){

        boolean contactPlayer=false;
            // Get entity's solid area position
            entity.solidArea.x = entity.wordlx + entity.solidArea.x;
            entity.solidArea.y = entity.worldy + entity.solidArea.y;

            // Get object's solid area position
            gp.player.solidArea.x = gp.player.wordlx +  gp.player.solidArea.x;
            gp.player.solidArea.y =  gp.player.worldy + gp.player.solidArea.y;

            switch (entity.direction) {
                case "up": entity.solidArea.y -= entity.speed;
                break;
                case "down": entity.solidArea.y += entity.speed;
                    break;
                case "left": entity.solidArea.x -= entity.speed;
                    break;
                case "right": entity.solidArea.x += entity.speed;
                    break;
            }
            if (entity.solidArea.intersects( gp.player.solidArea)) {
                entity.collisionOn = true;
                contactPlayer=true;
            }
            // Reset solid area position
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.player.solidArea.x =  gp.player.solidAreaDefaultX;
            gp.player.solidArea.y =  gp.player.solidAreaDefaultY;
            return contactPlayer;
        }


     }
