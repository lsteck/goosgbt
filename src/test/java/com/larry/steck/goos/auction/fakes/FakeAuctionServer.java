package com.larry.steck.goos.auction.fakes;

import org.hamcrest.Matcher;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
//import static org.hamcrest.core.IsEqual.equalTo;

public class FakeAuctionServer {
  public static final String ITEM_ID_AS_LOGIN = "auction-%s";
  public static final String AUCTION_RESOURCE = "Auction";
  public static final String XMPP_HOSTNAME = "localhost";

  private static final String AUCTION_PASSWORD = "auction";

  private final String itemId;
  private final XMPPConnection connection;
  private Chat currentChat;

  private final SingleMessageListener messageListener = new SingleMessageListener();

  public FakeAuctionServer(final String itemId) {
    this.itemId = itemId;
    this.connection = new XMPPConnection(XMPP_HOSTNAME);
  }

  public void startSellingItem() throws XMPPException {
    connection.connect();

    connection.login(
      format(ITEM_ID_AS_LOGIN, itemId),
      AUCTION_PASSWORD,
      AUCTION_RESOURCE
    );

    connection.getChatManager().addChatListener(
      new ChatManagerListener() {
        public void chatCreated(Chat chat, boolean createdLocally) {
          currentChat = chat;
          chat.addMessageListener(messageListener);
        }
      });
  }

  public String getItemId() {
    return itemId;
  }

  public void hasReceivedJoinRequestFromSniper() throws InterruptedException {
    messageListener.receivesAMessage(); //1
  }

  public void announceClosed() throws XMPPException {
    currentChat.sendMessage(new Message()); // 2
  }

  public void stop() {
    connection.disconnect(); // 3
  }

  public void reportPrice(int price, int increment, String bidder) throws XMPPException {
    currentChat.sendMessage(String.format("SQLVersion: 1.1; Event: PRICE; "
                            + "CurrentPrice: %d; Increment: %d; Bidder: %s;",
                            price, increment, bidder));
  }

  public void hasReceivedBid(int bid, String sniperId) throws InterruptedException{
    assertThat(currentChat.getParticipant(), equalTo(sniperId));
    messageListener.receivesAMessage(
        equalTo(
            String.format("SQLVersion: 1.1; Command: BID; Price: %d;", bid)));
  }

  private class SingleMessageListener implements MessageListener {
    private final ArrayBlockingQueue<Message> messages =
      new ArrayBlockingQueue<Message>(1);

    @Override
    public void processMessage(Chat chat, Message message) {
      messages.add(message);
    }

    public void receivesAMessage() throws InterruptedException {
      assertThat(messages.poll(5, SECONDS)).as("Message").isNotNull(); // 4
    }

    public void receivesAMessage(Matcher<? super String> messageMatcher) throws InterruptedException{
      final Message message = messages.poll(5, TimeUnit.SECONDS);
      assertThat("Message", message, is(notNullValue()));
      assertThat(message.getBody(), messageMatcher);

    }
  }
}
