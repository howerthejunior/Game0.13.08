package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyhandler implements KeyListener {
//DEBUG
    boolean checkdrawtime = false;


    public boolean upPressed, downPressed, leftPressed, rightPressed,enterPressed;
    public Gamepanel gp;

    public Keyhandler(Gamepanel gp) {
        this.gp=gp;
    }



    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //PLAY STATW
        int code = e.getKeyCode();
        if (gp.gameState == gp.playsState) {
            code = e.getKeyCode();
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;

            }
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_ENTER) {
           enterPressed=true;
            }
        }
        //PAUSE STATE
       else if (gp.gameState == gp.pauseState){
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playsState;
            }
       }
        //DOALOGUE STATE
        else if (gp.gameState == gp.dialougeState){
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState=gp.playsState;
            }
        }
        //TITLE STATE
        else if (gp.gameState==gp.titleState) {
            if (gp.ui.titleNum == 1) {
                // Handling key press for titleNum == 1
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    gp.playSE(1);
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3; // Assuming 4 options in the menu
                        gp.playSE(1);
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    gp.playSE(1);
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                        gp.playSE(1);
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    gp.playSE(3);
                    if (gp.ui.commandNum == 0) {
                        // Logic for the first option in titleNum == 1
                    } else if (gp.ui.commandNum == 1) {
                      gp.ui.titleNum=5;
                      gp.ui.commandNum=0;
                    } else if (gp.ui.commandNum == 2) {
                        // Logic for the third option in titleNum == 1
                    } else if (gp.ui.commandNum == 3) {
                        // Back option, go to main title screen
                        gp.ui.titleNum = 0;
                    }
                }
            }
            if (gp.ui.titleNum == 0) {
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    gp.playSE(1);
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                        gp.playSE(1);
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    gp.playSE(1);
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                        gp.playSE(1);
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        gp.playSE(3);
                        gp.gameState = gp.playsState;
                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 1) {
                        gp.playSE(3);
                        gp.ui.titleNum = 1;
                    }
                    if (gp.ui.commandNum == 2) {
                        gp.playSE(3);
                        System.exit(0);
                    }
                }
            }
            if (gp.ui.titleNum == 5){
                if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                    gp.ui.commandNum--;
                    gp.playSE(1);
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 1; // Assuming 4 options in the menu
                        gp.playSE(1);
                    }
                }
                if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                    gp.ui.commandNum++;
                    gp.playSE(1);
                    if (gp.ui.commandNum > 1) {
                        gp.ui.commandNum = 0;
                        gp.playSE(1);
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum = 3;
                }
                if (code == KeyEvent.VK_A || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum = 0;
                }
                if (code == KeyEvent.VK_ENTER) {
                    gp.playSE(3);
                    if (gp.ui.commandNum == 0) {
                        gp.player.playersource=1;
                    } else if (gp.ui.commandNum == 1) {
                        gp.player.playersource=2;
                    }
                    else if (gp.ui.commandNum == 3) {
                        // Back option, go to main title screen
                        gp.ui.titleNum = 1;
                    }
                }

            }

        }
        //DEBUG
        if (code == KeyEvent.VK_T) {
            if (checkdrawtime == false) {
                checkdrawtime = true;

            } else if (checkdrawtime == true) {
                checkdrawtime = false;
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;

        }
    }

}