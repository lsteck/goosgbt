package com.larry.steck.goos.auction;

/*
*
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
* */

import org.junit.*;

import com.larry.steck.goos.auction.fakes.FakeAuctionServer;
import com.larry.steck.goos.auction.drivers.ApplicationRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class AuctionSniperEndToEndTest {
  private final FakeAuctionServer auction = new FakeAuctionServer("item-54321");
  private final ApplicationRunner application = new ApplicationRunner();

  @Test
  public void canary() {
    assertThat(true).isEqualTo(true);
  }

  @Test
  public void sniperJoinsAuctionUntilAuctionCloses() throws Exception {
    auction.startSellingItem();
    application.startBiddingIn(auction);
    auction.hasReceivedJoinRequestFromSniper();
    auction.announceClosed();
    application.showsSniperHasLostAuction();
  }

  @Test
  public void sniperMakesAHigherBidButLooses() throws Exception{
    auction.startSellingItem();

    application.startBiddingIn(auction);
    auction.hasReceivedJoinRequestFromSniper(); // 1

    auction.reportPrice(1000, 98, "other bidder"); // 2
    application.hasShownSniperIsBidding(); //3

    auction.hasReceivedBid(1098, ApplicationRunner.SNIPER_XMPP_ID); // 4

    auction.announceClosed(); // 5
    application.showsSniperHasLostAuction();
    
  }

  @After
  public void stopAuction() {
    auction.stop();
  }

  @After
  public void stopApplication() {
    application.stop();
  }
}
