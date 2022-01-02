package business_simulator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProductUpgrades {
    private final int width = 170;
    private final int height = 350;
    private final int margin = 10;

    private final int imageSize = width - 2*margin;

    private final int posX;
    private final int posY;

    private final int imageX;
    private final int imageY;

    private final BufferedImage logo;
    private final Upgrade[] upgrades;

    public ProductUpgrades(BufferedImage logo, int x, int y, ProductTab productTab, GameWindow gameWindow) throws IOException {
        this.posX = x;
        this.posY = y;
        this.imageX = posX + margin;
        this.imageY = posY + margin;

        int optionX = posX + margin;
        int optionY = posY + margin + imageSize + margin;
        int optionWidth = width - 2*margin;

        this.logo = logo;

        //int lvlHeight = optionHeight + margin;

        upgrades = new Upgrade[3];
        upgrades[0] = new Upgrade1("Ulepsz magazyn", optionX, optionY, optionWidth, productTab, gameWindow);
        upgrades[1] = new Upgrade2("Zmniejsz koszt produkcji", optionX, optionY + 60, optionWidth, productTab, gameWindow);
        upgrades[2] = new Upgrade3("Zwiększ jakość produktu", optionX, optionY + 120, optionWidth, productTab, gameWindow);
    }
    public void draw(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRoundRect(posX, posY, width, height, 10, 10);
        g.drawImage(logo, imageX, imageY, imageSize, imageSize, null);

        for(Upgrade upgrade : upgrades){
            upgrade.draw(g);
        }
    }

    public void clicked(int x, int y){
        for(Upgrade upgrade : upgrades){
            upgrade.clicked(x, y);
        }
    }

    public void restart(){
        for(Upgrade upgrade : upgrades){
            upgrade.restart();
        }
    }
}
