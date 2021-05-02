/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appmain;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author smart
 */
public class MyPrintedPanel extends JPanel implements Printable{

    @Override
    public int print(Graphics g, PageFormat pf, int i) throws PrinterException {
        Graphics2D g2 = (Graphics2D)g;
        Component[] comps = this.getComponents();
        for (Component comp : comps) {
            int width = comp.getWidth();
            int height = comp.getHeight();
            int x = comp.getX();
            int y = comp.getY();
            if(comp instanceof JTextField){
                JTextField text_filed = (JTextField)comp;
                String text = text_filed.getText();
                g2.drawString(text, x, y);
            }else if(comp instanceof JLabel){
                JLabel label = (JLabel)comp;
                String lbl_txt = label.getText();
                g2.drawString(lbl_txt, x, y);
            }
            g2.drawRect(x, y, width, height);
        }
        return NO_SUCH_PAGE;
    }
    
}
