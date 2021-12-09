package business_simulator;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel{
    private Building[] buildings;
    private MoneyStatus moneyStatus;
    private IncomeStatus incomeStatus;
    private TurnStatus turnStatus;

    public Board(Tab[] tabs, GameWindow gameWindow) throws IOException {
        super();
        setSize(new Dimension(100, 300));
        int width = getWidth();
        int height = getHeight();

        moneyStatus = new MoneyStatus(10, 30, 170, 50, gameWindow);
        incomeStatus = new IncomeStatus(200, 30, 170, 50, gameWindow);
        turnStatus = new TurnStatus(390, 30, 170, 50, gameWindow);

        BufferedImage burger = ImageIO.read(new File("resources/burger.png"));
        BufferedImage frenchFries = ImageIO.read(new File("resources/french_fries.png"));
        BufferedImage hotDog = ImageIO.read(new File("resources/hot_dog.png"));

        buildings = new Building[3];
        buildings[0] = new Building(burger, 10, 100, tabs[0], gameWindow);
        buildings[1] = new Building(frenchFries,200, 100, tabs[1], gameWindow);
        buildings[2] = new Building(hotDog,390, 100, tabs[2], gameWindow);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x = e.getX();
                int y = e.getY();
                for(Building building : buildings){
                    building.clicked(x, y);
                }
                repaint();
            }
        });
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        moneyStatus.draw(g);
        incomeStatus.draw(g);
        turnStatus.draw(g);
        for(Building building : buildings){
            building.draw(g);
        }
    }
}
