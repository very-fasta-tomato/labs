package ru.ssau.tk.lab2.ui;

import javax.swing.*;

public class FunctionOperationService extends JDialog {
    JPanel panel = new JPanel();

    public FunctionOperationService(JFrame owner){
        super(owner, "Tabulated function operation service", true);
        setSize(900, 480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.setLayout(null);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(780, 410, Index.buttonWidth, Index.buttonHeight);
        exitButton.addActionListener(e -> this.dispose());

        panel.add(exitButton);
        getContentPane().add(panel);
    }

}
