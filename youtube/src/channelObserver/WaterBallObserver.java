package channelObserver;

import channel.Channel;
import subscribe.Subscriber;
import video.Video;

import java.util.List;

public class WaterBallObserver implements SubscriberObserver {

    @Override
    public void update(Channel channel) {
        List<Subscriber> subscribers = channel.getSubscribers();
        Video latestVideo = channel.getVideoList().getLast();

        for (Subscriber subscriber: subscribers) {
            if (subscriber.getName().equals("WaterBall")) {
                if (latestVideo.getLength() >= 180 ){
                    subscriber.like(latestVideo);
                }
            }
        }
    }
}
