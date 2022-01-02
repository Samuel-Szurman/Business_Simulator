package business_simulator;

import java.io.IOException;

public class Upgrade1 extends Upgrade {

    Upgrade1(String description, int x, int y, int width, ProductTab productTab, GameWindow gameWindow) throws IOException {
        super(description, x, y, width, productTab, gameWindow);
    }

    @Override
    protected void updateGame() {
        this.productTab.increaseMaxProduction();
    }
}
