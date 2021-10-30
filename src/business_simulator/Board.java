package business_simulator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel{
    private int width;
    private int height;

    public Board(){
        int width = getWidth();
        int height = getHeight();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x = e.getX();
                int y = e.getY();
                if(x >= 50 && x < 150 && y >= 50 && 150 > y){
                    System.out.println("udało się");
                }
            }
        });
    }
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect(50, 50, 100, 100);
        g.fillRect(200, 50, 100, 100);
        g.fillRect(350, 50, 100, 100);
    }
}
