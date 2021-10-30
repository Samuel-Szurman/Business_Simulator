package business_simulator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameWindow extends JFrame{
    //private GameBoard gameBoard;
    private Business business;

    private JPanel jp1, jp2, jp3, jp4;
    private JTabbedPane tabbedPane;
    private Tab tab1, tab2;
    private ArrayList<Tab> tabs;
    private JLabel jl1;
    private JButton jb1, jb2;
    private Board board;

    GameWindow() throws IOException {
        setTitle("Business Simulator");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //gameBoard = new GameBoard();
        //add(gameBoard);
        business = new Business(15, 15);

        jp1 = new JPanel();
        jp1.setLayout(new GridLayout(1, 1));
        //jp1.setLayout(new FlowLayout());
        add(jp1, BorderLayout.EAST);

        jp2 = new JPanel();
        jp2.setLayout(new FlowLayout());
        add(jp2, BorderLayout.NORTH);

        //CENTER
        board = new Board();
        add(board, BorderLayout.CENTER);

        //jp3 = new JPanel();
        //jp3.setLayout(new GridLayout(1, 1));
        //add(jp3, BorderLayout.CENTER);

        jp4 = new JPanel();
        jp4.setLayout(new FlowLayout());
        add(jp4, BorderLayout.SOUTH);

        //NORTH
        jl1 = new JLabel("Business Simulator");
        jl1.setFont(new Font("Verdana", Font.PLAIN, 30));
        jp2.add(jl1);

        //EAST
        tabbedPane = new JTabbedPane();
        jp1.add(tabbedPane);

        tabs = new ArrayList<Tab>();
        tabs.add(new Tab("Biznes 1"));
        tabs.add(new Tab("Biznes 2"));
        for(var tab : tabs){
            tabbedPane.add(tab.getTitle(), tab);
        }

        //BufferedImage myPicture = ImageIO.read(new File("C:\\Users\\samur\\OneDrive\\Pulpit\\company.jpg"));
        //JLabel jl2 = new JLabel(new ImageIcon(myPicture));
        //add(jl2, BorderLayout.CENTER);

        //SOUTH
        jb1 = new JButton("Następna tura");
        jb1.addActionListener(e -> {
            for(var tab : tabs){
                int price = tab.getProductionPrice();
                int production = tab.getProduction();
                business.setProductPrice(price);
                business.setProduction(production);
                tab.setJl1("Cena: " + price + " ");
                tab.setJl2("Produkcja: " + production + " ");
            }
        });

        jp4.add(jb1);

        //jp1.setBackground(Color.CYAN);

        //add(jp1, BorderLayout.CENTER);
        //add(jp1);

    }
}
