package business_simulator;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Tab extends JPanel{
    private String title;
    private JPanel previousInfoPanel, currentInfoPanel, sliderPanel;
    private JSlider productPriceSlider, productionSlider;

    private JLabel previousTitleLabel;
    private JLabel previousProductPriceLabel;
    private JLabel previousProductionLabel;
    private JLabel previousProductionPriceLabel;
    private JLabel previousIncomeLabel;
    private JLabel previousDemandLabel;

    private JLabel currentTitleLabel;
    private JLabel currentProductPriceLabel;
    private JLabel currentProductionLabel;
    private JLabel currentProductionPriceLabel;

    private JLabel jl1, jl2, jl3, jl4, jl5;
    private JLabel jl6, jl7, jl8;

    private int production = 5;
    private int productPrice = 15;
    private int productionPrice = 10;
    private int revenues = 0;
    private int expenses = 0;
    private int income = 0;
    private int demand;
    private int maxDemand = 30;
    private int demandSlope = 1;

    int maxProduction = 10;
    int maxPrice = 30;

    public Tab(String title){
        super();
        this.title = title;
        setLayout(new GridLayout(3, 1));

        // infoPanel = new JPanel();
        // infoPanel.setLayout(new GridLayout(2, 1));
        // add(infoPanel);

        previousInfoPanel = new JPanel();
        previousInfoPanel.setLayout(new GridLayout(6, 1));
        add(previousInfoPanel);

        currentInfoPanel = new JPanel();
        currentInfoPanel.setLayout(new GridLayout(6, 1));
        add(currentInfoPanel);

        sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayout(2, 1));
        add(sliderPanel);

        previousTitleLabel = new JLabel("POPRZEDNIA TURA", SwingConstants.CENTER);
        previousTitleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        previousInfoPanel.add(previousTitleLabel);

        previousProductPriceLabel = new JLabel("Cena produktu: -", SwingConstants.CENTER);
        previousProductPriceLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        previousInfoPanel.add(previousProductPriceLabel);

        previousProductionLabel = new JLabel("Produkcja: -", SwingConstants.CENTER);
        previousProductionLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        previousInfoPanel.add(previousProductionLabel);

        previousProductionPriceLabel = new JLabel("Koszt produkcji: -", SwingConstants.CENTER);
        previousProductionPriceLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        previousInfoPanel.add(previousProductionPriceLabel);

        previousIncomeLabel = new JLabel("Przychód: -", SwingConstants.CENTER);
        previousIncomeLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        previousInfoPanel.add(previousIncomeLabel);

        previousDemandLabel = new JLabel("Popyt: -", SwingConstants.CENTER);
        previousDemandLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        previousInfoPanel.add(previousDemandLabel);

        currentInfoPanel.add(new JLabel(""));

        currentTitleLabel = new JLabel("OBECNA TURA", SwingConstants.CENTER);
        currentTitleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        currentInfoPanel.add(currentTitleLabel);

        currentProductPriceLabel = new JLabel("Cena produktu: " + productPrice, SwingConstants.CENTER);
        currentProductPriceLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        currentInfoPanel.add(currentProductPriceLabel);

        currentProductionLabel = new JLabel("Produkcja: " + production, SwingConstants.CENTER);
        currentProductionLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        currentInfoPanel.add(currentProductionLabel);

        currentProductionPriceLabel = new JLabel("Koszt produkcji: " + productionPrice, SwingConstants.CENTER);
        currentProductionPriceLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        currentInfoPanel.add(currentProductionPriceLabel);

        currentInfoPanel.add(new JLabel(""));

        createPriceSlider();
        createProductionSlider();
    }

    public void createPriceSlider(){
        productPriceSlider = new JSlider(JSlider.HORIZONTAL, 0, maxPrice, productPrice);
        productPriceSlider.setBorder(BorderFactory.createTitledBorder("Cena produktu [zł]"));
        productPriceSlider.setMajorTickSpacing(5);
        productPriceSlider.setMinorTickSpacing(1);
        productPriceSlider.setPaintTicks(true);
        productPriceSlider.setPaintLabels(true);
        productPriceSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                currentProductPriceLabel.setText("Cena produktu: " + productPriceSlider.getValue());
            }
        });
        sliderPanel.add(productPriceSlider);
    }

    public void createProductionSlider(){
        productionSlider = new JSlider(JSlider.HORIZONTAL, 0, maxProduction, production);
        productionSlider.setBorder(BorderFactory.createTitledBorder("Produkcja [sztuki]"));
        productionSlider.setMajorTickSpacing(5);
        productionSlider.setMinorTickSpacing(1);
        productionSlider.setPaintTicks(true);
        productionSlider.setPaintLabels(true);
        productionSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                currentProductionLabel.setText("Produkcja: " + productionSlider.getValue());
            }
        });
        sliderPanel.add(productionSlider);
    }

    public void nextTurn(){
        productPrice = getProductPrice();
        production = getProduction();
        calculate();
        previousProductPriceLabel.setText("Cena produktu: " + productPrice);
        previousProductionLabel.setText("Produkcja: " + production);
        previousProductionPriceLabel.setText("Koszt produkcji: " + productionPrice);
        previousIncomeLabel.setText("Przychód: " + income);
        previousDemandLabel.setText("Popyt: " + demand);
    }

    public void calculate(){
        demand = maxDemand - demandSlope * productPrice;
        revenues = Math.min(demand, production) * productPrice;
        expenses = production * productionPrice;
        income = revenues - expenses;
    }

    public void increaseMaxProduction(){
        maxProduction += 5;
        productionSlider.setMaximum(maxProduction);
    }

    public int getProduction(){
        return productionSlider.getValue();
    }

    public int getProductPrice(){
        return productPriceSlider.getValue();
    }

    public String getTitle(){
        return title;
    }

    public void decreaseProductionPrice() {
        this.productionPrice -= 1;
        currentProductionPriceLabel.setText("Koszt produkcji: " + productionPrice);
    }

    public void increaseMaxDemand() {
        this.maxDemand += 10;
    }
}
