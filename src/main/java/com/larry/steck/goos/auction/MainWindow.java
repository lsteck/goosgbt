package com.larry.steck.goos.auction;

import javax.swing.*;
import java.awt.*;

import static com.larry.steck.goos.auction.Main.MAIN_WINDOW_NAME;

public class MainWindow extends JFrame {
  public MainWindow() throws HeadlessException {
    super("Auction Sniper");
    setName(MAIN_WINDOW_NAME);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}
