package business_simulator;

import java.io.IOException;

/**
 * Inwestycja polegająca na zwiększeniu jakości produktu
 */
public class QualityUpgrade extends Upgrade {

    QualityUpgrade(String description, int x, int y, int width, ProductTab productTab, GameWindow gameWindow) throws IOException {
        super(description, x, y, width, productTab, gameWindow);
    }

    @Override
    protected void updateGame() {
        this.productTab.increaseMaxDemand();
    }
}
