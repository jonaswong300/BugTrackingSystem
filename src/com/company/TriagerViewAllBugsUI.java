package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TriagerViewAllBugsUI implements ActionListener
{

    //triager must be able to check for duplicate bugs/invalid bugs...    
    //let triager view all bugs in a button array similar to the searchui
    //after triager open, let the triager click a button to flag as duplicate, therefore marking it as solved. 
    //in the file, instead of solved, it'll be solved(Duplicate of another bug)

    JFrame viewFrame = new JFrame();
    JPanel viewPanel = new JPanel();

    public TriagerViewAllBugsUI()
    {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
