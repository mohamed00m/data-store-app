/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appmain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

/**
 *
 * @author smart
 */
public class Main {

    private static final int DEFAULT_WIDTH = 1000;
    private static final int DEFAULT_HEIGHT = 400;
    private static SplashScreen splash;

    private static void drawOnSplash(int percent) {
        Rectangle bounds = splash.getBounds();
        Graphics2D g = splash.createGraphics();
        int height = 10;
        int x = 2;
        int y = bounds.height - height - 2;
        int width = bounds.width - 4;
        Color brightPurple = new Color(76, 36, 121);
        g.setColor(brightPurple);
        g.fillRect(x, y, width * percent / 100, height);
        splash.update();
    }

    /**
     * This method draws on the splash screen.
     */
    private static void init1() {
        splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.err.println("Did you specify a splash image with -splash or in the manifest?");
            System.exit(1);
        }

        try {
            for (int i = 0; i <= 100; i++) {
                drawOnSplash(i);
                Thread.sleep(100); // simulate startup work
            }
        } catch (InterruptedException e) {
        }
    }

    /**
     * This method displays a frame with the same image as the splash screen.
     */
    private static void init2() {
        final Image img = new ImageIcon(splash.getImageURL()).getImage();

        final JFrame splashFrame = new JFrame();
        splashFrame.setUndecorated(true);

        final JPanel splashPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(img, 0, 0, null);
            }
        };

        final JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        splashPanel.setLayout(new BorderLayout());
        splashPanel.add(progressBar, BorderLayout.SOUTH);

        splashFrame.add(splashPanel);
        splashFrame.setBounds(splash.getBounds());
        splashFrame.setVisible(true);

        new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    for (int i = 0; i <= 100; i++) {
                        publish(i);
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                for (Integer chunk : chunks) {
                    progressBar.setString("Loading module " + chunk);
                    progressBar.setValue(chunk);
                    splashPanel.repaint(); // because img is loaded asynchronously
                }
            }

            @Override
            public void done() {
                splashFrame.setVisible(false);

                JFrame theMainFrame = new appFrame();
                theMainFrame.setTitle("Client Data");
                theMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                theMainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("database_icon.png")));
                theMainFrame.setVisible(true);
            }
        }.execute();
    }

    public static void main(String[] args) {
        init1();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                init2();
            }
        });
    }
}
