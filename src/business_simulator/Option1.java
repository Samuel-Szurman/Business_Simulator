package business_simulator;

import java.io.IOException;

public class Option1 extends Option{

    Option1(String description, int x, int y, int width, Tab tab) throws IOException {
        super(description, x, y, width, tab);
    }

    @Override
    protected void updateGame() {
        this.tab.increaseMaxProduction();
    }
}
