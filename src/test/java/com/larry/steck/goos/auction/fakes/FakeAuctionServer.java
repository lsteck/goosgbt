package com.larry.steck.goos.auction.fakes;

public class FakeAuctionServer {
  private final String itemId;

  public FakeAuctionServer(final String itemId) {
    this.itemId = itemId;
  }

  public String getItemId() {
    return itemId;
  }

  public void startSellingItem() {}
  public void hasReceivedJoinRequestFromSniper() {}
  public void announceClosed() {}
  public void stop() {}
}
