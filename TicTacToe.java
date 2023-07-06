import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class TicTacToe implements ActionListener {
    public static void main(String[] args) {
        TicTacToe t1=new TicTacToe();
    }

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panle = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] button = new JButton[9];
    boolean player1_turn;
    BufferedImage lisaImg;
    BufferedImage jisooImg;
    ImageIcon lisaIcon;
    ImageIcon jisooIcon;

    TicTacToe() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(Color.PINK);
        textfield.setForeground(Color.BLACK);
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("TIC-TAC-TOE");
        textfield.setOpaque(true);

        title_panle.setLayout(new BorderLayout());
        title_panle.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(Color.PINK);

        for (int i = 0; i < 9; i++) {
            button[i] = new JButton();
            button_panel.add(button[i]);
            button[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            button[i].setFocusable(false);
            button[i].addActionListener(this);
            button[i].setBackground(Color.BLACK);
        }

        title_panle.add(textfield);
        frame.add(title_panle, BorderLayout.NORTH);
        frame.add(button_panel);
        firstTurn();

        try {
            // Load lisa image
            lisaImg = ImageIO
                    .read(getClass().getResourceAsStream("/lisa_bp_png_by_ne1912_by_natashaeuginia1912-db15zsa.png"));
            lisaImg = resizeImage(lisaImg, 250, 250); 
            lisaIcon = new ImageIcon(lisaImg);

            // Load jisoo image
            jisooImg = ImageIO.read(getClass().getResourceAsStream("/R1.png"));
            jisooImg = resizeImage(jisooImg, 250, 250); 
            jisooIcon = new ImageIcon(jisooImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return resizedImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == button[i]) {
                if (player1_turn) {
                    if (button[i].getIcon() == null) {
                        button[i].setIcon(lisaIcon);
                        player1_turn = false;
                        textfield.setText("JISOO's Turn");
                        check();
                    }
                }

                else {
                    if (button[i].getIcon() == null) {
                        button[i].setIcon(jisooIcon);
                        player1_turn = true;
                        textfield.setText("LISA's Turn");
                        check();
                    }

                }
            }
        }
    }

    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("LISA's TURN");
        } else {
            player1_turn = false;
            textfield.setText("JISOO's TURN");
        }

    }

    public void check() {
        // check lisa win
        if ((button[0].getIcon() == lisaIcon) &&
                (button[1].getIcon() == lisaIcon) &&
                (button[2].getIcon() == lisaIcon)) {
            lisaWins(0, 1, 2);
        }
        if ((button[3].getIcon() == lisaIcon) &&
                (button[4].getIcon() == lisaIcon) &&
                (button[5].getIcon() == lisaIcon)) {
            lisaWins(3, 4, 5);
        }
        if ((button[6].getIcon() == lisaIcon) &&
                (button[7].getIcon() == lisaIcon) &&
                (button[8].getIcon() == lisaIcon)) {
            lisaWins(6, 7, 8);
        }
        if ((button[0].getIcon() == lisaIcon) &&
                (button[3].getIcon() == lisaIcon) &&
                (button[6].getIcon() == lisaIcon)) {
            lisaWins(0, 3, 6);
        }
        if ((button[1].getIcon() == lisaIcon) &&
                (button[4].getIcon() == lisaIcon) &&
                (button[7].getIcon() == lisaIcon)) {
            lisaWins(1, 4, 7);
        }
        if ((button[2].getIcon() == lisaIcon) &&
                (button[5].getIcon() == lisaIcon) &&
                (button[8].getIcon() == lisaIcon)) {
            lisaWins(2, 5, 8);
        }
        if ((button[0].getIcon() == lisaIcon) &&
                (button[4].getIcon() == lisaIcon) &&
                (button[8].getIcon() == lisaIcon)) {
            lisaWins(0, 4, 8);
        }
        if ((button[2].getIcon() == lisaIcon) &&
                (button[4].getIcon() == lisaIcon) &&
                (button[6].getIcon() == lisaIcon)) {
            lisaWins(2, 4, 6);
        }

        if ((button[0].getIcon() == jisooIcon) &&
                (button[1].getIcon() == jisooIcon) &&
                (button[2].getIcon() == jisooIcon)) {
            jisooWins(0, 1, 2);
        }
        if ((button[3].getIcon() == jisooIcon) &&
                (button[4].getIcon() == jisooIcon) &&
                (button[5].getIcon() == jisooIcon)) {
            jisooWins(3, 4, 5);
        }
        if ((button[6].getIcon() == jisooIcon) &&
                (button[7].getIcon() == jisooIcon) &&
                (button[8].getIcon() == jisooIcon)) {
            jisooWins(6, 7, 8);
        }
        if ((button[0].getIcon() == jisooIcon) &&
                (button[3].getIcon() == jisooIcon) &&
                (button[6].getIcon() == jisooIcon)) {
            jisooWins(0, 3, 6);
        }
        if ((button[1].getIcon() == jisooIcon) &&
                (button[4].getIcon() == jisooIcon) &&
                (button[7].getIcon() == jisooIcon)) {
            jisooWins(1, 4, 7);
        }
        if ((button[2].getIcon() == jisooIcon) &&
                (button[5].getIcon() == jisooIcon) &&
                (button[8].getIcon() == jisooIcon)) {
            jisooWins(2, 5, 8);
        }
        if ((button[0].getIcon() == jisooIcon) &&
                (button[4].getIcon() == jisooIcon) &&
                (button[8].getIcon() == jisooIcon)) {
            jisooWins(0, 4, 8);
        }
        if ((button[2].getIcon() == jisooIcon) &&
                (button[4].getIcon() == jisooIcon) &&
                (button[6].getIcon() == jisooIcon)) {
            jisooWins(2, 4, 6);
        }

    }

    public void lisaWins(int a, int b, int c) {
        button[a].setBackground(new Color(255, 182, 193));
        button[b].setBackground(new Color(255, 182, 193));
        button[c].setBackground(new Color(255, 182, 193));

        for (int i = 0; i < 9; i++) {
            button[i].setEnabled(false);

        }
        textfield.setText("LISA WIN");
    }

    public void jisooWins(int a, int b, int c) {
        button[a].setBackground(new Color(255, 182, 193));
        button[b].setBackground(new Color(255, 182, 193));
        button[c].setBackground(new Color(255, 182, 193));

        for (int i = 0; i < 9; i++) {
            button[i].setEnabled(false);

        }
        textfield.setText("JISOO WIN");
    }
    

}
