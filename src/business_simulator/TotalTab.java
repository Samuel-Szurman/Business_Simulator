package business_simulator;
import javax.swing.*;
import java.awt.*;

public class TotalTab extends JPanel{
    private String title;
    private JPanel infoPanel;
    private JLabel jl1, jl2;
    int totalMoney = 0;
    int totalIncome = 0;

    public TotalTab(String title){
        super();
        this.title = title;
        setLayout(new GridLayout(3, 1));

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        add(infoPanel);

        jl1 = new JLabel("Pieniądze: " + this.totalMoney, SwingConstants.CENTER);
        jl1.setFont(new Font("Verdana", Font.PLAIN, 18));
        infoPanel.add(jl1);

        jl2 = new JLabel("Przychód: " + this.totalIncome, SwingConstants.CENTER);
        jl2.setFont(new Font("Verdana", Font.PLAIN, 18));
        infoPanel.add(jl2);
    }
}
