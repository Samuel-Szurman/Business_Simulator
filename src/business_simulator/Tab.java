package business_simulator;
import javax.swing.*;
import java.awt.*;

public class Tab extends JPanel{
    private String title;
    private JPanel infoPanel;
    private JSlider productionPriceSlider, productionSlider;
    private JLabel jl1, jl2;
    int maxProduction = 10;
    int maxPrice = 30;

    public Tab(String title){
        super();
        this.title = title;
        setLayout(new GridLayout(3, 1));

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        add(infoPanel);

        jl1 = new JLabel("[Dane biznesowe]", SwingConstants.CENTER);
        jl1.setFont(new Font("Verdana", Font.PLAIN, 18));
        infoPanel.add(jl1);

        jl2 = new JLabel("[Dane biznesowe]", SwingConstants.CENTER);
        jl2.setFont(new Font("Verdana", Font.PLAIN, 18));
        infoPanel.add(jl2);

        createPriceSlider();
        createProductionSlider();
    }

    public void createPriceSlider(){
        productionPriceSlider = new JSlider(JSlider.HORIZONTAL, 0, maxPrice, 2);
        productionPriceSlider.setBorder(BorderFactory.createTitledBorder("Cena produktu [zł]"));
        productionPriceSlider.setMajorTickSpacing(5);
        productionPriceSlider.setMinorTickSpacing(1);
        productionPriceSlider.setPaintTicks(true);
        productionPriceSlider.setPaintLabels(true);
        add(productionPriceSlider);
    }

    public void createProductionSlider(){
        productionSlider = new JSlider(JSlider.HORIZONTAL, 0, maxProduction, 2);
        productionSlider.setBorder(BorderFactory.createTitledBorder("Produkcja [sztuki]"));
        productionSlider.setMajorTickSpacing(5);
        productionSlider.setMinorTickSpacing(1);
        productionSlider.setPaintTicks(true);
        productionSlider.setPaintLabels(true);
        add(productionSlider);
    }

    public void increaseMaxProduction(){
        this.maxProduction += 5;
        this.productionSlider.setMaximum(this.maxProduction);
    }

    public int getProduction(){
        return productionSlider.getValue();
    }

    public int getProductionPrice(){
        return productionPriceSlider.getValue();
    }

    public void setJl1(String text){
        jl1.setText(text);
    }

    public void setJl2(String text){
        jl2.setText(text);
    }

    public String getTitle(){
        return this.title;
    }
}
