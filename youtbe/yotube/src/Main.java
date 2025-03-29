import ChannelObserver.VideoObserver;
import channel.Channel;
import subscribe.Subscriber;
import video.Video;

public class Main {
    public static void main(String[] args) {
        Channel channel1 = new Channel();
        channel1.register(new VideoObserver());

        Subscriber subscriber1 = new Subscriber("Tony");
        Subscriber subscriber2 = new Subscriber("Timmy");
        Subscriber subscriber3 = new Subscriber("Tom");


        channel1.subscribe(subscriber1);
        channel1.subscribe(subscriber2);
        channel1.subscribe(subscriber3);

        Video video1 = new Video();
        video1.setTitle("Harry Potter");
        video1.setLength("2hr");
        video1.setDescription("magic type");
        channel1.upload(video1);


        Video video2 = new Video();
        video2.setTitle("Titanic");
        video2.setLength("3hr");
        video2.setDescription("romantic type");
        channel1.upload(video2);


    }
}