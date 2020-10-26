package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class userTrackBugUI implements ActionListener {

    final static int width = 1050, height = 600;

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    JTextField trackInput = new JTextField(600);
    String[] terms = {"Bug ID", "Bug Title"};
    final JComboBox<String> options = new JComboBox<String>(terms);
    JButton submit = new JButton("Track");

    public userTrackBugUI(){
        System.out.println("At track bug UI");
        showForm();
    }

    public void showForm(){
        frame.setSize(width, height);
        frame.add(panel);
        panel.setLayout(null);

        JLabel trackLabel = new JLabel("Track Bugs: ");
        trackLabel.setFont(trackLabel.getFont().deriveFont(32.0f));
        trackLabel.setBounds(170,150,400,50);
        panel.add(trackLabel);

        trackInput.setFont(trackInput.getFont().deriveFont(20.0f));
        trackInput.setBounds(170,200,650,50);
        panel.add(trackInput);

        options.setFont(options.getFont().deriveFont(18.0f));
        options.setBounds(170,260,300,50);
        panel.add(options);

        submit.setFont(submit.getFont().deriveFont(18.0f));
        submit.setBounds(170,350,200,50);
        submit.addActionListener(this);
        panel.add(submit);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        userTrackBugController utbc = new userTrackBugController();

        String inputFromUser = trackInput.getText();

        System.out.println("Text: " + inputFromUser);
        if(options.getSelectedItem().equals("Bug Title")){
            utbc.searchByBugTitle(inputFromUser);
        }else if (options.getSelectedItem().equals("Bug ID")){
            utbc.searchByBugId(inputFromUser);
        }

    }

}
