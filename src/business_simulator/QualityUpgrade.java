package business_simulator;

import java.io.IOException;

/**
 * Inwestycja polegająca na zwiększeniu jakości produktu
 */
public class QualityUpgrade extends Upgrade {
    /**
     * Jedyny konstruktor klasy QualityUpgrade
     * @param description Opis tekstowy inwestycji
     * @param posX Współrzędna x całego elementu
     * @param posY Współrzędna x całego elementu
     * @param width Szerokość całego elementu
     * @param productTab Referencja do obiektu, w którym będą widoczne ulepszenia
     * @param gameWindow Referencja do okna gry
     * @throws IOException Wyjątek wywoływany w przypadku problemu z wczytaniem obrazków z plików
     */
    QualityUpgrade(String description, int posX, int posY, int width, ProductTab productTab, GameWindow gameWindow) throws IOException {
        super(description, posX, posY, width, productTab, gameWindow);
    }

    /**
     * Metoda zwiększa maksymalny popyt na produkt
     */
    @Override
    protected void updateGame() {
        this.productTab.increaseMaxDemand();
    }
}
