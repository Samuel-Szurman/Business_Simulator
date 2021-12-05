package business_simulator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Option {
    private final int topBorder;
    private final int bottomBorder;
    private final int leftBorder;
    private final int rightBorder;

    private final int posX;
    private final int posY;
    private final int width;
    private final int height = 50;

    private final int textWidth;
    private final int textHeight = 20;

    private final int starsX;
    private final int starsY;
    private final int starSize = 20;
    public int starsCount = 0;
    private final String description;
    private final BufferedImage emptyStar, filledStar;
    protected Tab tab;

    Option(String description, int x, int y, int width, Tab tab) throws IOException {
        this.tab = tab;
        this.description = description;
        this.posX = x;
        this.posY = y;
        this.width = width;
        this.textWidth = width;
        this.starsX = this.posX + (width - 5*starSize) / 2;
        this.starsY = this.posY + 20;

        topBorder = this.posY;
        bottomBorder = this.posY + this.height;
        leftBorder = this.posX;
        rightBorder = this.posX + this.width;

        emptyStar = ImageIO.read(new File("resources/empty_star.png"));
        filledStar = ImageIO.read(new File("resources/filled_star.png"));
    }

    public void clicked(int x, int y){
        if(x > leftBorder && x < rightBorder && y > topBorder && y < bottomBorder){
            if(starsCount < 5){
                starsCount++;
                updateGame();
            }
        }
    }

    abstract protected void updateGame();

    public void draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(posX, posY, width, height, 10, 10);
        drawDescription(g, posX, posY, textWidth, textHeight);
        drawStars(g);
    }

    private void drawDescription(Graphics g, int x, int y, int width, int height){
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int desc = fm.getDescent();
        int stringWidth = fm.stringWidth(description);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 10));
        g.drawString(description, x + (width-stringWidth)/2, y + asc + (height - (asc+desc)) / 2);
    }

    private void drawStars(Graphics g){
        for (int i = 0; i < 5; i++) {
            if(i < starsCount){
                g.drawImage(filledStar, starsX+i*starSize, starsY, starSize, starSize, null);
            }
            else{
                g.drawImage(emptyStar, starsX+i*starSize, starsY, starSize, starSize, null);
            }
        }
    }
}
