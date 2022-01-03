package business_simulator;

import java.io.IOException;

/**
 * Inwestycja polegająca na ulepszeniu magazynu
 */
public class MagazineUpgrade extends Upgrade {

    /**
     * Jedyny konstruktor klasy MagazineUpgrade
     * @param description Opis tekstowy inwestycji
     * @param posX Współrzędna x całego elementu
     * @param posY Współrzędna x całego elementu
     * @param width Szerokość całego elementu
     * @param productTab Referencja do zakładki danego produktu
     * @param gameWindow Referencja do okna gry
     * @throws IOException Wyjątek wywoływany w przypadku problemu z wczytaniem obrazków z plików
     */
    MagazineUpgrade(String description, int posX, int posY, int width, ProductTab productTab, GameWindow gameWindow) throws IOException {
        super(description, posX, posY, width, productTab, gameWindow);
    }

    /**
     * Metoda zwiększa maksymalną produkcję
     */
    @Override
    protected void updateGame() {
        this.productTab.increaseMaxProduction();
    }
}
