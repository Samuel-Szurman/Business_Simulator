package business_simulator;

import java.io.IOException;

/**
 * W tej klasie działa gra
 */
public class Main{
    /**
     * Metoda, w której działa cały program
     * @param args Argumenty z wiersza poleceń
     * @throws IOException Wyjątek wywoływany w przypadku problemu z wczytaniem obrazków z plików
     */
    public static void main(String[] args) throws IOException {
        new GameWindow();
    }
}
