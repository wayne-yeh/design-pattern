package channel;

import channelObserver.SubscriberObserver;
import subscribe.Subscriber;
import video.Video;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    List<SubscriberObserver> subscriberObserverList = new ArrayList<>();

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
    public void addSubscribe(Subscriber subscriber){
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

    public void register(SubscriberObserver subscriberObserver){
        subscriberObserverList.add(subscriberObserver);
    }
    public void unRegister(SubscriberObserver subscriberObserver){
        subscriberObserverList.remove(subscriberObserver);
    }

    private void channelNotify() {
        for (SubscriberObserver subscriberObserver : subscriberObserverList) {
            subscriberObserver.update(this);
        }
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
}
