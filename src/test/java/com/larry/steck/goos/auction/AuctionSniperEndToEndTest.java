package com.larry.steck.goos.auction;

/*
*
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
* */

import com.larry.steck.goos.auction.fakes.FakeAuctionServer;
import org.junit.Test;

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

  //@After
  public void stopAuction() {
    auction.stop();
  }

  //@After
  public void stopApplication() {
    auction.stop();
  }
}
