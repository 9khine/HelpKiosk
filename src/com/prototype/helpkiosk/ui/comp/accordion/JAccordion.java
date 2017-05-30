package com.prototype.helpkiosk.ui.comp.accordion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class JAccordion extends JPanel {
	
	boolean movingComponents = false;
    int visibleIndex = 1; //number of possible visible (expanded) panels
    int numPanel = 2; //number of accordion panes
    int transitionSpeed = 2; 
	
    public JAccordion(int height, int width) {
        setLayout(new GridLayout(1,1,30,30));
        //add(new PanelContent(0, ml), BorderLayout.NORTH); //learn/do
        //add(new PanelContent(1, ml), BorderLayout.SOUTH); //search
        
        // Add children and compute prefSize.
        int childCount = numPanel;
        Dimension d = new Dimension();
        int h = 0;
        for(int j = 0; j < childCount; j++) {
            PanelContent child = new PanelContent(j+1, ml);
            //add(child);
            if (j==0) add(child, BorderLayout.PAGE_START); //learn/do
            if (j==1) add(child, BorderLayout.PAGE_END); //search
            d = child.getPreferredSize();
            child.setBounds(0, h, d.width, d.height);
            if(j < childCount-1)
                h += PanelHeader.HEIGHT;
        }
        h += d.height;
        setPreferredSize(new Dimension(d.width, h));
        // Set z-order for children.
        setZOrder();
    }
    
    private void setZOrder() {
        Component[] c = getComponents();
        for(int j = 0; j < c.length-1; j++) {
            setComponentZOrder(c[j], c.length-1 - j);
        }
    }
    
    private void setChildVisible(int indexToOpen) {
        // If visibleIndex < indexToOpen, components at
        // [visibleIndex+1 down to indexToOpen] move up.
        // If visibleIndex > indexToOpen, components at
        // [indexToOpen+1 up to visibleIndex] move down.
        // Collect indices of components that will move
        // and determine the distance/direction to move.
        int[] indices = new int[0];
        int travelLimit = 0;
        if(visibleIndex < indexToOpen) {
            travelLimit = PanelHeader.HEIGHT - getComponent(visibleIndex).getHeight();
            int n = indexToOpen - visibleIndex;
            indices = new int[n];
            for(int j = visibleIndex, k = 0; j < indexToOpen; j++, k++)
                indices[k] = j + 1;
        } else if(visibleIndex > indexToOpen) {
        	travelLimit = getComponent(visibleIndex).getHeight() - PanelHeader.HEIGHT;
            
            int n = visibleIndex - indexToOpen;
            indices = new int[n];
            for(int j = indexToOpen, k = 0; j < visibleIndex; j++, k++)
                indices[k] = j + 1;
        }
        movePanels(indices, travelLimit);
        visibleIndex = indexToOpen;
    }
 
    private void movePanels(final int[] indices, final int travel) {
        movingComponents = true;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                Component[] c = getComponents();
                int limit = travel > 0 ? travel : 0;
                int count = travel > 0 ? 0 : travel;
                int dy    = travel > 0 ? 1 : -1;
                while(count < limit) {
                    try {
                        Thread.sleep(transitionSpeed);
                    } catch(InterruptedException e) {
                        System.out.println("interrupted");
                        break;
                    }
                    for(int j = 0; j < indices.length; j++) {
                        // The z-order reversed the order returned
                        // by getComponents. Adjust the indices to
                        // get the correct components to relocate.
                        int index = c.length-1 - indices[j];
                        Point p = c[index].getLocation();
                        p.y += dy;
                        c[index].setLocation(p.x, p.y);
                    }
                    repaint();
                    count++;
                }
                movingComponents = false;
            }
        });
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.start();
    }
 
    private MouseListener ml = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            int index = ((PanelHeader)e.getSource()).id-1;
            System.out.println("index: "+index);
            if(!movingComponents)
                setChildVisible(index);
        }
    };
 
    public JPanel getPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        panel.add(this, gbc);
        return panel;
    }
}
 

 
