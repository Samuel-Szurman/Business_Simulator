package business_simulator;
import javax.swing.*;
import java.awt.*;

/**
 * Pokazuje informacje o produkcie oraz umożliwia ustalenie ceny i produkcji
 */
public class ProductTab extends JPanel{
    /** Suwak do ustalenia produkcji */
    private final JSlider productPriceSlider;
    /** Suwak do ustalenia ceny produktu */
    private final JSlider productionSlider;
    /** Etykieta informująca o cenie produktu w poprzedniej turze */
    private final JLabel previousProductPriceLabel;
    /** Etykieta informująca o produkcji poprzedniej turze */
    private final JLabel previousProductionLabel;
    /** Etykieta informująca o cenie produkcji pojedynczego produktu w poprzedniej turze */
    private final JLabel previousProductionPriceLabel;
    /** Etykieta informująca o przychodzie w poprzedniej turze */
    private final JLabel previousIncomeLabel;
    /** Etykieta informująca o popycie w poprzedniej turze */
    private final JLabel previousDemandLabel;
    /** Etykieta pokazująca wybraną cenę produktu w obecnej turze */
    private final JLabel currentProductPriceLabel;
    /** Etykieta pokazująca wybraną produkcję w obecnej turze */
    private final JLabel currentProductionLabel;
    /** Etykieta informująca o cenie produkcji pojedynczego produktu w obecnej turze */
    private final JLabel currentProductionPriceLabel;
    /** Początkowy koszt produkcji pojedynczego produktu */
    private final int baseProductionPrice;
    /** Początkowy maksymalny możliwy popyt */
    private final int baseMaxDemand;
    /** Ilość produktów do wyprodukowania */
    private int production = 5;
    /** Cena pojedynczego produktu */
    private int productPrice = 30;
    /** Koszt produkcji pojedynczego produktu */
    private int productionPrice;
    /** Popyt */
    private int demand = 0;
    /** Maksymalny możliwy popyt */
    private int maxDemand;
    /** Maksymalna ilość produktów do wyprodukowania */
    private int maxProduction = 10;
    /** Przychód */
    public int income = 0;

    /**
     * Jedyny konstruktor klasy ProductTab
     * @param productionPrice Koszt produkcji pojedynczego produktu
     * @param maxDemand Maksymalny możliwy popyt na produkt
     */
    public ProductTab(int productionPrice, int maxDemand){
        super();
        this.productionPrice = productionPrice;
        this.baseProductionPrice = productionPrice;
        this.maxDemand = maxDemand;
        this.baseMaxDemand = maxDemand;
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

    /**
     * Metoda wywoływana przy następnej turze
     */
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

    /**
     * Metoda obliczająca przychód
     */
    private void calculate(){
        demand = maxDemand - productPrice;
        int revenues = Math.min(demand, production) * productPrice;
        int expenses = production * productionPrice;
        income = revenues - expenses;
    }

    /**
     * Metoda zwiększa maksymalną produkcję
     */
    public void increaseMaxProduction(){
        maxProduction += 5;
        productionSlider.setMaximum(maxProduction);
    }

    /**
     * Metoda zwraca wybraną przez gracza produkcję z JSlidera
     * @return produkcja
     */
    public int getProduction(){
        return productionSlider.getValue();
    }

    /**
     * Metoda zwraca wybraną przez gracza cenę produktu z JSlidera
     * @return cena produktu
     */
    public int getProductPrice(){
        return productPriceSlider.getValue();
    }

    /**
     * Metoda zmniejsza koszt produkcji
     */
    public void decreaseProductionPrice() {
        this.productionPrice -= 1;
        currentProductionPriceLabel.setText("Koszt produkcji: " + productionPrice);
    }

    /**
     * Metoda zwiększa maksymalny możliwy popyt
     */
    public void increaseMaxDemand() {
        this.maxDemand += 10;
    }

    /**
     * Metoda wywoływana podczas restartu gry
     */
    public void restart(){
        production = 5;
        productPrice = 30;
        productionPrice = baseProductionPrice;
        income = 0;
        maxDemand = baseMaxDemand;
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
