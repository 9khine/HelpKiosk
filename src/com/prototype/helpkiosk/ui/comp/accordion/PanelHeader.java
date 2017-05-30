package com.prototype.helpkiosk.ui.comp.accordion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelHeader extends JPanel { // panel title bar
    int id;
    JLabel titleLabel;
    Color c1 = new Color(200,180,180);
    Color c2 = new Color(200,220,220);
    Color fontFg = Color.blue;
    Color rolloverFg = Color.red;
    public final static int HEIGHT = 50; //height of title bar
 
    public PanelHeader(int id, MouseListener ml) {
        this.id = id;
        setLayout(new BorderLayout());
        add(titleLabel = new JLabel("Panel " + id, JLabel.CENTER));
        titleLabel.setForeground(fontFg);
        Dimension d = getPreferredSize();
        System.out.println("PanelHeader -- height: " + d.height + " width: " + d.width);
        d.height = HEIGHT;
        setPreferredSize(d);
        addMouseListener(ml);
        addMouseListener(listener);
    }
 
    protected void paintComponent(Graphics g) {
        int w = getWidth();
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new GradientPaint(w/2, 0, c1, w/2, HEIGHT/2, c2));
        g2.fillRect(0,0,w,HEIGHT);
    }
 
    private MouseListener listener = new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            titleLabel.setForeground(rolloverFg);
        }
 
        public void mouseExited(MouseEvent e) {
            titleLabel.setForeground(fontFg);
        }
    };  
}
