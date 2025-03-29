package channel;

import channelObserver.ChannelObserver;
import subscribe.Subscriber;
import video.Video;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    List<ChannelObserver> channelObserverList = new ArrayList<>();

    List<Subscriber> subscribers = new ArrayList<>();

    List<Video> videoList = new ArrayList<>();

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }
    public void subscribe(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    private void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public void upload(Video video){
        videoList.add(video);
        channelNotify();
    }

    public void register(ChannelObserver channelObserver){
        channelObserverList.add(channelObserver);
    }
    public void unRegister(ChannelObserver channelObserver){
        channelObserverList.remove(channelObserver);
    }

    private void channelNotify() {
        for (ChannelObserver channelObserver : channelObserverList) {
            channelObserver.update(this);
        }
    }

}
