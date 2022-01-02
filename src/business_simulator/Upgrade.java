package business_simulator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Klasa abstrakcyjna dla ulepszeń
 */
public abstract class Upgrade {
    private final int posX;
    private final int posY;
    private final int width;
    private final int height = 50;

    private final int starsPosX;
    private final int starsPosY;
    private final int starSize = 20;
    private int starsCount = 0;
    private final String description;
    private final BufferedImage emptyStar, filledStar;
    protected ProductTab productTab;
    protected GameWindow gameWindow;

    private int upgradePrice = 100;

    Upgrade(String description, int x, int y, int width, ProductTab productTab, GameWindow gameWindow) throws IOException {
        this.productTab = productTab;
        this.gameWindow = gameWindow;
        this.description = description;
        this.posX = x;
        this.posY = y;
        this.width = width;
        this.starsPosX = this.posX + (width - 5*starSize) / 2;
        this.starsPosY = this.posY + 20;

        emptyStar = ImageIO.read(new File("resources/empty_star.png"));
        filledStar = ImageIO.read(new File("resources/filled_star.png"));
    }

    public void clicked(int x, int y){
        if(x > posX && x < posX + width && y > posY && y < posY + height){
            if(starsCount < 5){
                String[] options = {"Tak", "Anuluj"};
                int choice = JOptionPane.showOptionDialog(gameWindow, "Czy chcesz kupić ulepszenie za " + upgradePrice + "?", "",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if(choice == 0){
                    if(gameWindow.money >= upgradePrice){
                        gameWindow.money -= upgradePrice;
                        upgradePrice += 100;
                        starsCount++;
                        updateGame();
                    }
                    else{
                        JOptionPane.showMessageDialog(gameWindow, "Nie stać cię na zakup.", "",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }

    abstract protected void updateGame();

    public void draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(posX, posY, width, height, 10, 10);
        drawDescription(g);
        drawStars(g);
    }

    private void drawDescription(Graphics g){
        g.setFont(new Font("Verdana", Font.BOLD, 10));
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int desc = fm.getDescent();
        int stringWidth = fm.stringWidth(description);
        g.setColor(Color.BLACK);
        int textHeight = 20;
        g.drawString(description, posX + (width-stringWidth)/2, posY + asc + (textHeight - (asc+desc)) / 2);
    }

    private void drawStars(Graphics g){
        for (int i = 0; i < 5; i++) {
            if(i < starsCount){
                g.drawImage(filledStar, starsPosX+i*starSize, starsPosY, starSize, starSize, null);
            }
            else{
                g.drawImage(emptyStar, starsPosX+i*starSize, starsPosY, starSize, starSize, null);
            }
        }
    }

    public void restart(){
        starsCount = 0;
    }
}
