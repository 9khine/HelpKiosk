package com.prototype.helpkiosk.ui.comp.accordion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelContent extends JPanel { 
    public PanelContent(int id, MouseListener ml) {
        setLayout(new BorderLayout());
        add(new PanelHeader(id, ml), "First");
        add(getContent(id));
    }
 
    private JPanel getContent(int id) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,2,2,2);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = gbc.NORTHWEST;
        panel.add(new JLabel("Panel " + id + " Content"), gbc);
        return panel;
    }
 
    public Dimension getPreferredSize() {
        return new Dimension(300,700);
    }
}