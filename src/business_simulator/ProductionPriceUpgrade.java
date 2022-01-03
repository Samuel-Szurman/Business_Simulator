package business_simulator;

import java.io.IOException;

/**
 * Inwestycja polegająca na zmniejszeniu ceny produkcji
 */
public class ProductionPriceUpgrade extends Upgrade {

    /**
     * Jedyny konstruktor klasy ProductionPriceUpgrade
     * @param description Opis tekstowy inwestycji
     * @param posX Współrzędna x całego elementu
     * @param posY Współrzędna x całego elementu
     * @param width Szerokość całego elementu
     * @param productTab Referencja do zakładki danego produktu
     * @param gameWindow Referencja do okna gry
     * @throws IOException Wyjątek wywoływany w przypadku problemu z wczytaniem obrazków z plików
     */
    ProductionPriceUpgrade(String description, int posX, int posY, int width, ProductTab productTab, GameWindow gameWindow) throws IOException {
        super(description, posX, posY, width, productTab, gameWindow);
    }

    /**
     * Metoda zmniejsza koszt produkcji pojedynczego produktu
     */
    @Override
    protected void updateGame() {
        this.productTab.decreaseProductionPrice();
    }
}
