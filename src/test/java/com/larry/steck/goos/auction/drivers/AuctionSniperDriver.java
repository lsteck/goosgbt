package com.larry.steck.goos.auction.drivers;

import com.larry.steck.goos.auction.Main;
import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.driver.JFrameDriver;
import com.objogate.wl.swing.driver.JLabelDriver;
import com.objogate.wl.swing.gesture.GesturePerformer;

import static org.hamcrest.core.IsEqual.equalTo;

public class AuctionSniperDriver extends JFrameDriver {
  public AuctionSniperDriver(int timeoutMillis) {
    super(new GesturePerformer(),
      JFrameDriver.topLevelFrame(
        named(Main.MAIN_WINDOW_NAME),
        showingOnScreen()),
      new AWTEventQueueProber(timeoutMillis, 1));
  }

  public void showsSniperStatus(String statusText) {
    new JLabelDriver(this, named(Main.SNIPER_STATUS_NAME))
        .hasText(equalTo(statusText));
  }
}
