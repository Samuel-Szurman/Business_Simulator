package business_simulator;

import java.io.IOException;

public class Option2 extends Option{

    Option2(String description, int x, int y, int width, Business business) throws IOException {
        super(description, x, y, width, business);
    }

    @Override
    protected void updateGame() {
        this.business.decreaseProductionPrice();
    }
}
