package business_simulator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Option {
    private final int posX;
    private final int posY;
    private final int width;
    private final int height = 50;
    private final int textHeight = 20;

    private final int starsPosX;
    private final int starsPosY;
    private final int starSize = 20;
    private int starsCount = 0;
    private final String description;
    private final BufferedImage emptyStar, filledStar;
    protected Tab tab;
    protected GameWindow gameWindow;

    Option(String description, int x, int y, int width, Tab tab, GameWindow gameWindow) throws IOException {
        this.tab = tab;
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
                if(gameWindow.money >= 500){
                    gameWindow.money -= 500;
                    starsCount++;
                    updateGame();
                }
                else{
                    JOptionPane.showMessageDialog(gameWindow, "Nie stać cię na zakup.");
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
}
