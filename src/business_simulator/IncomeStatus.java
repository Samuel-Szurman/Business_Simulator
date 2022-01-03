package business_simulator;

import java.awt.*;

/**
 * Klasa pokazuje dochód gracza z poprzedniej tury
 */
public class IncomeStatus extends Status {
    /**
     * Jedyny konstruktor klasy TurnStatus
     * @param posX Współrzędna x całego elementu
     * @param posY Współrzędna x całego elementu
     * @param width Szerokość całego elementu
     * @param height Wysokość całego elementu
     * @param gameWindow Referencja do okna gry
     */
    public IncomeStatus(int posX, int posY, int width, int height, GameWindow gameWindow){
        super(posX, posY, width, height, gameWindow);
    }

    /**
     * Metoda wpisuje napis wewnątrz elementu
     * @param g Obiekt klasy Graphics, na którym jest wpisywany napis
     */
    @Override
    protected void drawDescription(Graphics g, int x, int y, int width, int height){
        g.setFont(new Font("Verdana", Font.BOLD, 18));
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int desc = fm.getDescent();
        int stringWidth = fm.stringWidth("Przychód: " + gameWindow.totalIncome);
        g.setColor(Color.BLACK);
        g.drawString("Przychód: " + gameWindow.totalIncome, x + (width-stringWidth)/2, y + asc + (height - (asc+desc)) / 2);
    }
}
