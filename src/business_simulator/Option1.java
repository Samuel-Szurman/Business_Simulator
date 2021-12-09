package business_simulator;

import java.io.IOException;

public class Option1 extends Option{

    Option1(String description, int x, int y, int width, Tab tab, GameWindow gameWindow) throws IOException {
        super(description, x, y, width, tab, gameWindow);
    }

    @Override
    protected void updateGame() {
        this.tab.increaseMaxProduction();
    }
}
