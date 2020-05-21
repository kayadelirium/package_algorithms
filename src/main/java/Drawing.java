import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import structures.Detail;
import structures.Plate;

class Drawing extends JFrame {

    private static final long serialVersionUID = 1L;

    public ArrayList<Detail> details;
    public int windowWidth;
    public int windowHeight;

    public Drawing(Plate plate){
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
        this.details = plate.getList();
        this.windowWidth = plate.getWidth();
        this.windowHeight = plate.getHeight();


        JLabel image = new JLabel("Empty space is " + plate.emptySpaces() + "%");
        image.setPreferredSize(new Dimension(140,110));
        JScrollPane js = new JScrollPane(image);

        js.setPreferredSize(new Dimension(150,120));

        JOptionPane.showMessageDialog(null, js);
    }

    public void paint(Graphics g) {
        //Graphics gr = g;
        /*JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(Color.red);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();
        frame.setVisible(true);*/
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

        //this.getContentPane().setBackground(Color.white);


    }

}