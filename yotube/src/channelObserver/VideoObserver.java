package channelObserver;

import channel.Channel;
import subscribe.Subscriber;

import java.util.List;

public class VideoObserver implements ChannelObserver{

    @Override
    public void update(Channel channel) {
       List<Subscriber> subscribers = channel.getSubscribers();

       for (Subscriber subscriber: subscribers) {
           subscriber.getNotification(channel.getVideoList().getLast().getTitle());
       }
    }
}
