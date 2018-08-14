package com.root.bind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
  private JButton submitButton;
  private JPanel panelMain;

  public App() {
    submitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Hello World");
      }
    });
  }

  public static void main(String[] args) {
    JFrame mainFrame = new JFrame("App");
    mainFrame.setContentPane(new App().panelMain);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.pack();
    mainFrame.setVisible(true);
  }

}
