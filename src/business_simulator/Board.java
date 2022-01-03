package business_simulator;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Plansza, na której rysowane są ulepszenia oraz informacje o stanie gry
 */
public class Board extends JPanel{
    /**  Tablica kontenerów ulepszeń */
    private final UpgradesContainer[] upgradeContainers;
    /** Element pokazujący, ile pieniędzy ma gracz */
    private final MoneyStatus moneyStatus;
    /** Element pokazujący przychód gracza */
    private final IncomeStatus incomeStatus;
    /** Element pokazujący numer gracza */
    private final TurnStatus turnStatus;

    /**
     * Jedyny konstruktor klasy Board
     * @param productTabs Tablica referencji zakładek
     * @param gameWindow Referencja do okna gry
     * @throws IOException Wyjątek wywoływany w przypadku problemu z wczytaniem obrazków z plików
     */
    public Board(ProductTab[] productTabs, GameWindow gameWindow) throws IOException {
        super();
        setSize(new Dimension(100, 300));

        moneyStatus = new MoneyStatus(10, 30, 170, 50, gameWindow);
        incomeStatus = new IncomeStatus(200, 30, 170, 50, gameWindow);
        turnStatus = new TurnStatus(390, 30, 170, 50, gameWindow);

        BufferedImage burger = ImageIO.read(new File("resources/burger.png"));
        BufferedImage frenchFries = ImageIO.read(new File("resources/french_fries.png"));
        BufferedImage hotDog = ImageIO.read(new File("resources/hot_dog.png"));

        upgradeContainers = new UpgradesContainer[3];
        upgradeContainers[0] = new UpgradesContainer(burger, 10, 100, productTabs[0], gameWindow);
        upgradeContainers[1] = new UpgradesContainer(frenchFries,200, 100, productTabs[1], gameWindow);
        upgradeContainers[2] = new UpgradesContainer(hotDog,390, 100, productTabs[2], gameWindow);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x = e.getX();
                int y = e.getY();
                for(UpgradesContainer upgradesContainer : Board.this.upgradeContainers){
                    upgradesContainer.clicked(x, y);
                }
                repaint();
            }
        });
    }

    /**
     * Metoda rysuje cały komponent na ekranie
     * @param g Obiekt klasy Graphics, na którym rysowany komponent
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        moneyStatus.draw(g);
        incomeStatus.draw(g);
        turnStatus.draw(g);
        for(UpgradesContainer upgradesContainer : this.upgradeContainers){
            upgradesContainer.draw(g);
        }
    }

    /**
     * Metoda wywoływana podczas restartu gry
     */
    public void restart(){
        for(UpgradesContainer upgradesContainer : this.upgradeContainers){
            upgradesContainer.restart();
        }
        repaint();
    }
}
