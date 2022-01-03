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
    /** Współrzędna x całego elementu */
    private final int posX;
    /** Współrzędna y całego elementu */
    private final int posY;
    /** Szerokość całego elementu */
    private final int width;
    /** Wysokość całego elementu */
    private final int height = 50;
    /** Współrzędna x ciągu gwiazdek */
    private final int starsPosX;
    /** Współrzędna y ciągu gwiazdek */
    private final int starsPosY;
    /** Wysokość i szerokość pojedynczej gwiazdki */
    private final int starSize = 20;
    /** Liczba wypełnionych gwiazdek*/
    private int starsCount = 0;
    /** Opis ulepszenia */
    private final String description;
    /** Obrazki pustej i wypełnionej gwiazdki */
    private final BufferedImage emptyStar, filledStar;
    /** Referencja do obiektu, w którym będą widoczne ulepszenia */
    protected ProductTab productTab;
    /** Referencja do okna gry */
    protected GameWindow gameWindow;
    /** Koszt inwestycji */
    private int upgradePrice = 100;

    /**
     * Jedyny konstruktor klasy Upgrade
     * @param description Opis tekstowy inwestycji
     * @param posX Współrzędna x całego elementu
     * @param posY Współrzędna x całego elementu
     * @param width Szerokość całego elementu
     * @param productTab Referencja do zakładki danego produktu
     * @param gameWindow Referencja do okna gry
     * @throws IOException Wyjątek wywoływany w przypadku problemu z wczytaniem obrazków z plików
     */
    Upgrade(String description, int posX, int posY, int width, ProductTab productTab, GameWindow gameWindow) throws IOException {
        this.productTab = productTab;
        this.gameWindow = gameWindow;
        this.description = description;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.starsPosX = this.posX + (width - 5*starSize) / 2;
        this.starsPosY = this.posY + 20;

        emptyStar = ImageIO.read(new File("resources/empty_star.png"));
        filledStar = ImageIO.read(new File("resources/filled_star.png"));
    }

    /**
     * Metoda wywoływana przy kliknięciu myszką
     * @param x Współrzędna x myszki
     * @param y Współrzędna y myszki
     */
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

    /**
     * Metoda abstrakcyjna, która wywołuje zmiany spowodowane przez kupione inwestycje
     */
    abstract protected void updateGame();

    /**
     * Metoda rysuje cały element na ekranie
     * @param g Obiekt klasy Graphics, na którym jest rysowany element
     */
    public void draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(posX, posY, width, height, 10, 10);
        drawDescription(g);
        drawStars(g);
    }

    /**
     * Metoda wpisuje opis inwestycji na ekranie
     * @param g Obiekt klasy Graphics, na którym jest wpisywany napis
     */
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

    /**
     * Metoda odpowiada za rysowanie gwiazdek
     * @param g Obiekt klasy Graphics, na którym są rysowane gwiazdki
     */
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

    /**
     * Metoda wywoływana podczas restartu gry
     */
    public void restart(){
        starsCount = 0;
        upgradePrice = 100;
    }
}
