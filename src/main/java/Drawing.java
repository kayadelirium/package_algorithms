import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import structures.Detail;

class Drawing extends JFrame {

    private static final long serialVersionUID = 1L;

    ArrayList<Detail> details;

    public Drawing(ArrayList<Detail> details){
        super("DETAILS WITH TINA KANDELAKI");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.white);
        //set
        setSize(1024, 1024);

       setVisible(true);
        /*JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(Color.red);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();
        frame.setVisible(true);*/
        this.details = details;

        JLabel image = new JLabel("Empty space is " + 75);
        image.setPreferredSize(new Dimension(140,110));
        JScrollPane js = new JScrollPane(image);

        js.setPreferredSize(new Dimension(150,120));

        JOptionPane.showMessageDialog(null, js);
    }

    public void paint(Graphics g) {
        /*JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(Color.red);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();
        frame.setVisible(true);*/
        g.setColor(Color.white);
        g.fillRect(0, 0, 2048, 1024);
        int k = 10;
        for (Detail detail : details) {
            g.setColor(Color.black);
            g.drawRect(detail.getX(), detail.getY(),
                    detail.getWidth(), detail.getHeight());
            g.setColor(new Color(123, 211, k));
            g.fillRect(detail.getX() + 1, detail.getY() + 1,
                    detail.getWidth() - 1, detail.getHeight() - 1);
            g.setColor(Color.black);
            g.drawString("BALENCIAGA", detail.getX(), detail.getY() + 100);
            k += 20;
            k %= 255;
        }

        //this.getContentPane().setBackground(Color.white);


    }

}

