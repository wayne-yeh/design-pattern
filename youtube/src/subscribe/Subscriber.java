package subscribe;

import channel.Channel;
import video.Video;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {



    String name;
    List<Channel> userChannels = new ArrayList<>();

    public Subscriber(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getNotification(String str){
        System.out.println("Subscriber "+ this.name +", New Video is " + str);
    }

    public void like(Video video) {
        video.addLike();
        System.out.println("Subscriber "+ this.name +", Like Video is " + video.getTitle());
    }

    public void unsubscribe(Channel channel) {
        channel.removeSubscriber(this);
        System.out.println("Subscriber " + this.name + " unsubscribed " + channel.getName());
    }

    public void subscribe(Channel channel) {
        userChannels.add(channel);
        channel.addSubscribe(this);
    }
}
