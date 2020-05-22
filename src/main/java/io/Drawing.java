package io;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;
import java.time.format.TextStyle;
import java.util.ArrayList;

import structures.Detail;
import structures.Plate;

public class Drawing extends JFrame {

    private static final long serialVersionUID = 1L;

    public ArrayList<Detail> details;
    public int windowWidth;
    public int windowHeight;

    public Drawing(){
        JLabel image = new JLabel("The plate is not wide enough for the given details set.");
        image.setPreferredSize(new Dimension(300,50));
        JOptionPane.showMessageDialog(null, image,"ERROR",JOptionPane.ERROR_MESSAGE);
    }

    public Drawing(Plate plate){
        super("DETAILS");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.white);
        setSize(1024, 1024);
        setVisible(true);

        this.details = plate.getList();
        this.windowWidth = plate.getWidth();
        this.windowHeight = plate.getHeight();

        JLabel image = new JLabel("Empty space is " + plate.emptySpaces() + "%");
        image.setPreferredSize(new Dimension(100,80));
        JOptionPane.showMessageDialog(null, image, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void paint(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect(0, 0, 2048, 1024);
        g.setColor(Color.black);
        g.drawRect(100, 100, this.windowWidth, this.windowHeight);
        g.setColor(Color.white);
        g.fillRect(100, 101, this.windowWidth, this.windowHeight-1);
        int k = 0;
        for (Detail detail : details) {
            g.setColor(Color.black);
            g.drawRect(detail.getX() + 100, detail.getY() + 100,
                    detail.getWidth(), detail.getHeight());
            g.setColor(new Color(123, k, 211));
            g.fillRect(detail.getX() + 101, detail.getY() + 101,
                    detail.getWidth() - 1, detail.getHeight() - 1);
            g.setColor(Color.black);
            g.drawString(Integer.toString(detail.getId()),
                    detail.getX() + 95 + detail.getWidth() / 2,
                    detail.getY() + 105 + detail.getHeight() / 2);
            k += 10;
            k %= 255;
        }
    }
}