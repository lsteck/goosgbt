package com.larry.steck.goos.auction.drivers;

import com.larry.steck.goos.auction.Main;
import com.larry.steck.goos.auction.fakes.FakeAuctionServer;

public class ApplicationRunner {
  public static final String SNIPER_ID = "sniper";
  public static final String SNIPER_PASSWORD = "sniper";
  private AuctionSniperDriver driver;

  public void startBiddingIn(final FakeAuctionServer auction) {
    Thread thread = new Thread("Test Application") {
      @Override public void run() {  // 1
        try {
          Main.main(
            XMPP_HOSTNAME,
            SNIPER_ID,
            SNIPER_PASSWORD,
            auction.getItemId()
          ); // 2
        }
        catch (Exception e) {
          e.printStackTrace(); // 3
        }
      }
    };

    thread.setDaemon(true);
    thread.start();
    driver = new AuctionSniperDriver(1000); // 4
    driver.showsSniperStatus(STATUS_JOINING); // 5
  }

  public void showsSniperHasLostAuction() {
    driver.showsSniperStatus(STATUS_LOST); //6
  }
  public void stop() {
    if (driver != null) {
      driver.dispose(); // 7
    }
  }
}
