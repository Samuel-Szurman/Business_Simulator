package business_simulator;

import java.awt.*;

/**
 * Klasa abstrakcyjna dla elementów interfejsu, które informują o stanie gry
 */
public abstract class Status {
    /** Referencja do okna gry */
    GameWindow gameWindow;
    /** Współrzędna x całego elementu */
    private final int posX;
    /** Współrzędna y całego elementu */
    private final int posY;
    /** Szerokość całego elementu */
    private final int width;
    /** Wysokość całego elementu */
    private final int height;

    /**
     * Jedyny konstruktor klasy Status
     * @param posX Współrzędna x całego elementu
     * @param posY Współrzędna x całego elementu
     * @param width Szerokość całego elementu
     * @param height Wysokość całego elementu
     * @param gameWindow Referencja do okna gry
     */
    public Status(int posX, int posY, int width, int height, GameWindow gameWindow){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.gameWindow = gameWindow;
    }

    /**
     * Metoda rysuje cały element na ekranie
     * @param g Obiekt klasy Graphics, na którym jest rysowany element
     */
    public void draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(posX, posY, width, height, 10, 10);
        drawDescription(g, posX, posY, width, height);
    }

    /**
     * Metoda wpisuje napis wewnątrz elementu
     * @param g Obiekt klasy Graphics, na którym jest wpisywany napis
     */
    protected abstract void drawDescription(Graphics g, int x, int y, int width, int height);
}
