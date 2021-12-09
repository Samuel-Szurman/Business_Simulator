package business_simulator;

import java.awt.*;

public class IncomeStatus {
    GameWindow gameWindow;
    int posX;
    int posY;
    int width;
    int height;

    public IncomeStatus(int posX, int posY, int width, int height, GameWindow gameWindow){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.gameWindow = gameWindow;
    }

    public void draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(posX, posY, width, height, 10, 10);
        drawDescription(g, posX, posY, width, height);
    }

    private void drawDescription(Graphics g, int x, int y, int width, int height){
        g.setFont(new Font("Verdana", Font.BOLD, 18));
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int desc = fm.getDescent();
        int stringWidth = fm.stringWidth("Przychód: " + gameWindow.totalIncome);
        g.setColor(Color.BLACK);
        g.drawString("Przychód: " + gameWindow.totalIncome, x + (width-stringWidth)/2, y + asc + (height - (asc+desc)) / 2);
    }
}
