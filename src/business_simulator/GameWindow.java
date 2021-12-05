package business_simulator;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class GameWindow extends JFrame{

    private JPanel eastPanel, northPanel, southPanel;
    private JTabbedPane tabbedPane;
    private TotalTab totalTab;
    private Tab[] tabs;
    private JLabel jl1;
    private JButton jb1, jb2;
    private Board board;

    GameWindow() throws IOException {
        super();
        setTitle("Business Simulator");
        setLayout(new BorderLayout());
        setSize(800, 600);
        //getContentPane().setBackground(Color.BLACK);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        totalTab = new TotalTab("Ogólne");
        tabs = new Tab[3];
        tabs[0] = new Tab("Burger");
        tabs[1] = new Tab("Frytki");
        tabs[2] = new Tab("Hot-Dog");

        eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(1, 1));
        add(eastPanel, BorderLayout.EAST);

        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        add(northPanel, BorderLayout.NORTH);

        //CENTER
        board = new Board(tabs);
        add(board, BorderLayout.CENTER);

        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout());
        add(southPanel, BorderLayout.SOUTH);

        //NORTH
        jl1 = new JLabel("Business Simulator");
        jl1.setFont(new Font("Verdana", Font.PLAIN, 30));
        northPanel.add(jl1);

        //EAST
        tabbedPane = new JTabbedPane();
        eastPanel.add(tabbedPane);

        tabbedPane.add("Ogólne", totalTab);
        for(Tab tab : tabs){
            tabbedPane.add(tab.getTitle(), tab);
        }

        /*tabs = new ArrayList<Tab>();
        tabs.add(new Tab("Biznes 1"));
        tabs.add(new Tab("Biznes 2"));
        for(var tab : tabs){
            tabbedPane.add(tab.getTitle(), tab);
        }*/

        //BufferedImage myPicture = ImageIO.read(new File("C:\\Users\\samur\\OneDrive\\Pulpit\\company.jpg"));
        //JLabel jl2 = new JLabel(new ImageIcon(myPicture));
        //add(jl2, BorderLayout.CENTER);

        //SOUTH
        jb1 = new JButton("Następna tura");
        jb1.addActionListener(e -> {
            for(Tab tab : tabs){
                tab.nextTurn();
            }
        });

        southPanel.add(jb1);
    }
}
