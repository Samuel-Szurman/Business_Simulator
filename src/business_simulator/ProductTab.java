package business_simulator;
import javax.swing.*;
import java.awt.*;

public class ProductTab extends JPanel{
    private final JSlider productPriceSlider, productionSlider;

    private final JLabel previousProductPriceLabel;
    private final JLabel previousProductionLabel;
    private final JLabel previousProductionPriceLabel;
    private final JLabel previousIncomeLabel;
    private final JLabel previousDemandLabel;

    private final JLabel currentProductPriceLabel;
    private final JLabel currentProductionLabel;
    private final JLabel currentProductionPriceLabel;

    private final int defaultProductionPrice;
    private final int defaultMaxDemand;

    private int production = 5;
    private int productPrice = 30;
    private int productionPrice;
    private int revenues = 0;
    private int expenses = 0;
    private int demand = 0;
    private int maxDemand;
    private int maxProduction = 10;

    public int income = 0;

    public ProductTab(int productionPrice, int maxDemand){
        super();
        this.productionPrice = productionPrice;
        this.defaultProductionPrice = productionPrice;
        this.maxDemand = maxDemand;
        this.defaultMaxDemand = maxDemand;
        setLayout(new GridLayout(3, 1));

        JPanel previousInfoPanel = new JPanel();
        previousInfoPanel.setLayout(new GridLayout(6, 1));
        add(previousInfoPanel);

        JPanel currentInfoPanel = new JPanel();
        currentInfoPanel.setLayout(new GridLayout(6, 1));
        add(currentInfoPanel);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayout(2, 1));
        add(sliderPanel);

        JLabel previousTitleLabel = new JLabel("POPRZEDNIA TURA", SwingConstants.CENTER);
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

        JLabel currentTitleLabel = new JLabel("OBECNA TURA", SwingConstants.CENTER);
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

        productPriceSlider = new JSlider(JSlider.HORIZONTAL, 10, 50, productPrice);
        productPriceSlider.setBorder(BorderFactory.createTitledBorder("Cena produktu"));
        productPriceSlider.setMajorTickSpacing(5);
        productPriceSlider.setMinorTickSpacing(1);
        productPriceSlider.setPaintTicks(true);
        productPriceSlider.setPaintLabels(true);
        productPriceSlider.addChangeListener(e -> currentProductPriceLabel.setText("Cena produktu: " + productPriceSlider.getValue()));
        sliderPanel.add(productPriceSlider);

        productionSlider = new JSlider(JSlider.HORIZONTAL, 0, maxProduction, production);
        productionSlider.setBorder(BorderFactory.createTitledBorder("Produkcja"));
        productionSlider.setMajorTickSpacing(5);
        productionSlider.setMinorTickSpacing(1);
        productionSlider.setPaintTicks(true);
        productionSlider.setPaintLabels(true);
        productionSlider.addChangeListener(e -> currentProductionLabel.setText("Produkcja: " + productionSlider.getValue()));
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

    private void calculate(){
        demand = maxDemand - productPrice;
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

    public void decreaseProductionPrice() {
        this.productionPrice -= 1;
        currentProductionPriceLabel.setText("Koszt produkcji: " + productionPrice);
    }

    public void increaseMaxDemand() {
        this.maxDemand += 10;
    }

    public void restart(){
        production = 5;
        productPrice = 30;
        productionPrice = defaultProductionPrice;
        revenues = 0;
        expenses = 0;
        income = 0;
        maxDemand = defaultMaxDemand;
        maxProduction = 10;
        productionSlider.setMaximum(maxProduction);
        productPriceSlider.setValue(productPrice);
        productionSlider.setValue(production);
        previousProductPriceLabel.setText("Cena produktu: -");
        previousProductionLabel.setText("Produkcja: -");
        previousProductionPriceLabel.setText("Koszt produkcji: -");
        previousIncomeLabel.setText("Przychód: -");
        previousDemandLabel.setText("Popyt: -");
        currentProductPriceLabel.setText("Cena produktu: " + productPrice);
        currentProductionLabel.setText("Produkcja: " + production);
        currentProductionPriceLabel.setText("Koszt produkcji: " + productionPrice);
    }
}
