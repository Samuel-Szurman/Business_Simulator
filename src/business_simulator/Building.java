package business_simulator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Building{
    private boolean isBought = false;

    private final int width = 170;
    private final int height = 350;
    private final int margin = 10;

    private final int imageSize = width - 2*margin;

    private final int optionHeight = 50;
    private final int lvlHeight = optionHeight + margin;
    private final int optionWidth = width - 2*margin;

    private int posX;
    private int posY;

    private int imageX;
    private int imageY;

    private int optionX;
    private int optionY;

    private BufferedImage logo;

    private String[] optionDescriptions = {"Ulepsz magazyn", "Zmniejsz koszt produkcji", "Zwiększ jakość produktu"};
    private Option[] options;

    public Building(BufferedImage logo, int x, int y, Tab tab, GameWindow gameWindow) throws IOException {
        this.posX = x;
        this.posY = y;
        this.imageX = posX + margin;
        this.imageY = posY + margin;

        this.optionX = posX + margin;
        this.optionY = posY + margin + imageSize + margin;

        this.logo = logo;

        options = new Option[3];
        options[0] = new Option1(optionDescriptions[0], optionX, optionY + 0 * lvlHeight, optionWidth, tab, gameWindow);
        options[1] = new Option2(optionDescriptions[1], optionX, optionY + 1 * lvlHeight, optionWidth, tab, gameWindow);
        options[2] = new Option3(optionDescriptions[2], optionX, optionY + 2 * lvlHeight, optionWidth, tab, gameWindow);
    }
    public void draw(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRoundRect(posX, posY, width, height, 10, 10);
        g.drawImage(logo, imageX, imageY, imageSize, imageSize, null);

        for(Option option : options){
            option.draw(g);
        }
    }

    public void clicked(int x, int y){
        for(Option option : options){
            option.clicked(x, y);
        }
    }
}
