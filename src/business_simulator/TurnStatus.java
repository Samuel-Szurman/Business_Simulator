package business_simulator;

import java.awt.*;

/**
 * Klasa wyświetlająca numer tury na ekranie
 */
public class TurnStatus {
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
     * Jedyny konstruktor klasy TurnStatus
     * @param posX Współrzędna x całego elementu
     * @param posY Współrzędna x całego elementu
     * @param width Szerokość całego elementu
     * @param height Wysokość całego elementu
     * @param gameWindow Referencja do okna gry
     */
    public TurnStatus(int posX, int posY, int width, int height, GameWindow gameWindow){
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
     * Metoda wpisuje opis inwestycji na ekranie
     * @param g Obiekt klasy Graphics, na którym jest wpisywany napis
     */
    private void drawDescription(Graphics g, int x, int y, int width, int height){
        g.setFont(new Font("Verdana", Font.BOLD, 18));
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int desc = fm.getDescent();
        int stringWidth = fm.stringWidth("Tura: " + gameWindow.turn + "/" + gameWindow.lastTurn);
        g.setColor(Color.BLACK);
        g.drawString("Tura: " + gameWindow.turn + "/" + gameWindow.lastTurn, x + (width-stringWidth)/2, y + asc + (height - (asc+desc)) / 2);
    }
}
