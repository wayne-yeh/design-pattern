package channelObserver;

import channel.Channel;
import subscribe.Subscriber;
import video.Video;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FireBallObserver implements SubscriberObserver {


    @Override
    public void update(Channel channel) {
        List<Subscriber> subscribers = channel.getSubscribers();
        Video latestVideo = channel.getVideoList().getLast();

        Subscriber toRemove = null;
        for (Subscriber subscriber : subscribers) {
            if (subscriber.getName().equals("FireBall") && latestVideo.getLength() <= 60) {
                toRemove = subscriber;
            }
        }

        if (toRemove != null) {
            toRemove.unsubscribe(channel);
        }
    }
}
