package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TriagerGenerateReportUI 
{
    final static int width = 1040, height = 500;

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    Object [] reportsDropdown;
    JComboBox<Object> reportsBox;

    public TriagerGenerateReportUI()
    {
        showInterface();
    }

    public void showInterface()
    {
        frame.setSize(800, 600);
        frame.add(panel);
        panel.setLayout(null);



        frame.setVisible(true);
    }
}
