package business_simulator;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

/**
 * Okno całej gry
 */
public class GameWindow extends JFrame{
    /** Pieniądze gracza */
    public int money = 1000;
    /** Przychód gracza */
    public int totalIncome = 0;
    /** Numer bieżącej tury */
    public int turn = 1;
    /** Numer ostatniej tury */
    public final int lastTurn = 20;

    /**  Tablica zakładek produktów */
    private final ProductTab[] productTabs;
    /** Plansza, na której rysowane są ulepszenia oraz informacje o stanie gry */
    private final Board board;

    /**
     * Metoda wywoływana podczas restartu gry
     */
    private void restart(){
        money = 1000;
        totalIncome = 0;
        turn = 1;
        board.restart();
        for(ProductTab productTab : productTabs){
            productTab.restart();
        }
    }

    /**
     * Metoda wyświetla nowe okienko na koniec gry
     */
    private void showEndMessage(){
        String[] options = {"Od nowa", "Zakończ grę"};
        int choice = JOptionPane.showOptionDialog(this, "Koniec gry\nTwój wynik: " + money, "Koniec gry",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if(choice == -1 || choice == 1){
            this.dispose();
        }
        else if(choice == 0){
            restart();
        }
    }

    /**
     * Jedyny konstruktor klasy GameWindow
     * @throws IOException Wyjątek wywoływany w przypadku problemu z wczytaniem obrazków z plików
     */
    public GameWindow() throws IOException {
        super();
        setTitle("Business Simulator");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        String[] productNames = {"Burger", "Frytki", "Hot-Dog"};
        
        productTabs = new ProductTab[3];
        productTabs[0] = new ProductTab(20, 40);
        productTabs[1] = new ProductTab(15, 30);
        productTabs[2] = new ProductTab(10, 20);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(1, 1));
        add(eastPanel, BorderLayout.EAST);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        add(northPanel, BorderLayout.NORTH);

        board = new Board(productTabs, this);
        add(board, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout());
        add(southPanel, BorderLayout.SOUTH);

        JLabel titleLabel = new JLabel("Business Simulator");
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        northPanel.add(titleLabel);

        JTabbedPane productsTabbedPane = new JTabbedPane();
        eastPanel.add(productsTabbedPane);
        for (int i = 0; i < 3; i++) {
            productsTabbedPane.add(productNames[i], productTabs[i]);
        }

        JButton nextTurnButton = new JButton("Następna tura");
        nextTurnButton.addActionListener(e -> {
            if(turn == lastTurn){
                showEndMessage();
            }
            else{
                totalIncome = 0;
                for(ProductTab productTab : productTabs){
                    productTab.nextTurn();
                    totalIncome += productTab.income;
                }
                money += totalIncome;
                board.repaint();
                turn++;
            }
        });
        southPanel.add(nextTurnButton);
    }
}
