package business_simulator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Przedstawia graficznie produkt oraz pokazuje dostępne inwestycje
 */
public class UpgradesContainer {
    /** Szerokość całego elementu */
    private final int width = 170;
    /** Wysokośc całego elementu */
    private final int height = 350;
    /** Rozmiar marginesu */
    private final int margin = 10;
    /** Rozmiar obrazka (szerokość i wysokość) */
    private final int imageSize = width - 2*margin;
    /** Współrzędna x całego elementu */
    private final int posX;
    /** Współrzędna y całego elementu */
    private final int posY;
    /** Współrzędna x obrazka */
    private final int imageX;
    /** Współrzędna y obrazka */
    private final int imageY;
    /** Obrazek produktu */
    private final BufferedImage logo;
    /** Tablica ulepszeń */
    private final Upgrade[] upgrades;

    /**
     * Jedyny konstruktor klasy UpgradeContainer
     * @param logo Obrazek produktu
     * @param posX Współrzędna x całego elementu
     * @param posY Współrzędna y całego elementu
     * @param productTab Referencja do zakładki danego produktu
     * @param gameWindow Referencja do okna gry
     * @throws IOException Wyjątek wywoływany w przypadku problemu z wczytaniem obrazków z plików
     */
    public UpgradesContainer(BufferedImage logo, int posX, int posY, ProductTab productTab, GameWindow gameWindow) throws IOException {
        this.posX = posX;
        this.posY = posY;
        this.imageX = posX + margin;
        this.imageY = posY + margin;

        int optionX = posX + margin;
        int optionY = posY + margin + imageSize + margin;
        int optionWidth = width - 2*margin;

        this.logo = logo;

        upgrades = new Upgrade[3];
        upgrades[0] = new MagazineUpgrade("Ulepsz magazyn", optionX, optionY, optionWidth, productTab, gameWindow);
        upgrades[1] = new ProductionPriceUpgrade("Zmniejsz koszt produkcji", optionX, optionY + 60, optionWidth, productTab, gameWindow);
        upgrades[2] = new QualityUpgrade("Zwiększ jakość produktu", optionX, optionY + 120, optionWidth, productTab, gameWindow);
    }

    /**
     * Metoda rysuje cały element na ekranie
     * @param g Obiekt klasy Graphics, na którym jest rysowany element
     */
    public void draw(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRoundRect(posX, posY, width, height, 10, 10);
        g.drawImage(logo, imageX, imageY, imageSize, imageSize, null);

        for(Upgrade upgrade : upgrades){
            upgrade.draw(g);
        }
    }

    /**
     * Metoda wywoływana przy kliknięciu myszką
     * @param x Współrzędna x myszki
     * @param y Współrzędna y myszki
     */
    public void clicked(int x, int y){
        for(Upgrade upgrade : upgrades){
            upgrade.clicked(x, y);
        }
    }

    /**
     * Metoda wywoływana podczas restartu gry
     */
    public void restart(){
        for(Upgrade upgrade : upgrades){
            upgrade.restart();
        }
    }
}
