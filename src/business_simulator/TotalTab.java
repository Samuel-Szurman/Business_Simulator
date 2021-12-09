package business_simulator;
import javax.swing.*;
import java.awt.*;

public class TotalTab extends JPanel{
    private String title;
    private JPanel infoPanel;
    private JLabel jl1, jl2;
    GameWindow gameWindow;

    public TotalTab(String title, GameWindow gameWindow){
        super();
        this.title = title;
        this.gameWindow = gameWindow;
        setLayout(new GridLayout(3, 1));

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        add(infoPanel);

        jl1 = new JLabel("Pieniądze: " + gameWindow.money, SwingConstants.CENTER);
        jl1.setFont(new Font("Verdana", Font.PLAIN, 18));
        infoPanel.add(jl1);

        jl2 = new JLabel("Przychód: " + gameWindow.totalIncome, SwingConstants.CENTER);
        jl2.setFont(new Font("Verdana", Font.PLAIN, 18));
        infoPanel.add(jl2);
    }

    public void nextTurn(){
        jl1.setText("Pieniądze: " + gameWindow.money);
        jl2.setText("Przychód: " + gameWindow.totalIncome);
    }
}
