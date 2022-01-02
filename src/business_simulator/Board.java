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
    private final ProductUpgrades[] productUpgrades;
    private final MoneyStatus moneyStatus;
    private final IncomeStatus incomeStatus;
    private final TurnStatus turnStatus;

    public Board(ProductTab[] productTabs, GameWindow gameWindow) throws IOException {
        super();
        setSize(new Dimension(100, 300));

        moneyStatus = new MoneyStatus(10, 30, 170, 50, gameWindow);
        incomeStatus = new IncomeStatus(200, 30, 170, 50, gameWindow);
        turnStatus = new TurnStatus(390, 30, 170, 50, gameWindow);

        BufferedImage burger = ImageIO.read(new File("resources/burger.png"));
        BufferedImage frenchFries = ImageIO.read(new File("resources/french_fries.png"));
        BufferedImage hotDog = ImageIO.read(new File("resources/hot_dog.png"));

        productUpgrades = new ProductUpgrades[3];
        productUpgrades[0] = new ProductUpgrades(burger, 10, 100, productTabs[0], gameWindow);
        productUpgrades[1] = new ProductUpgrades(frenchFries,200, 100, productTabs[1], gameWindow);
        productUpgrades[2] = new ProductUpgrades(hotDog,390, 100, productTabs[2], gameWindow);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x = e.getX();
                int y = e.getY();
                for(ProductUpgrades productUpgrades : Board.this.productUpgrades){
                    productUpgrades.clicked(x, y);
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
        for(ProductUpgrades productUpgrades : this.productUpgrades){
            productUpgrades.draw(g);
        }
    }

    public void restart(){
        for(ProductUpgrades productUpgrades : this.productUpgrades){
            productUpgrades.restart();
        }
        repaint();
    }
}
