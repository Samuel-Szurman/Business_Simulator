package business_simulator;

import java.io.IOException;

/**
 * Inwestycja polegająca na ulepszeniu magazynu
 */
public class MagazineUpgrade extends Upgrade {

    MagazineUpgrade(String description, int x, int y, int width, ProductTab productTab, GameWindow gameWindow) throws IOException {
        super(description, x, y, width, productTab, gameWindow);
    }

    @Override
    protected void updateGame() {
        this.productTab.increaseMaxProduction();
    }
}
