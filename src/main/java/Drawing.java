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
        Graphics gr = g;
        /*JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(Color.red);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();
        frame.setVisible(true);*/
        g.setColor(Color.white);
        g.fillRect(0, 0, 2048, 1024);
        int k = 10;
        for (int i = 0; i < details.size(); i++) {
            gr.setColor(Color.black);
            gr.drawRect(this.details.get(i).getX(), this.details.get(i).getY(),
                    this.details.get(i).getWidth(), this.details.get(i).getHeight());
            gr.setColor(new Color(123, 211, k));
            gr.fillRect(this.details.get(i).getX()+1, this.details.get(i).getY()+1,
                    this.details.get(i).getWidth()-1, this.details.get(i).getHeight()-1);
            gr.setColor(Color.black);
            gr.drawString("BALENCIAGA", this.details.get(i).getX(), this.details.get(i).getY()+100);
            k+=20;
        }

        //this.getContentPane().setBackground(Color.white);


    }

}

