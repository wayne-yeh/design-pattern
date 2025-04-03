import channelObserver.FireBallObserver;
import channel.Channel;
import channelObserver.WaterBallObserver;
import subscribe.Subscriber;
import video.Video;

public class Main {
    public static void main(String[] args) {
        Channel channel1 = new Channel();
        channel1.setName("channel1");
        channel1.register(new FireBallObserver());
        channel1.register(new WaterBallObserver());

        Subscriber subscriber1 = new Subscriber("FireBall");
        Subscriber subscriber2 = new Subscriber("WaterBall");
        Subscriber subscriber3 = new Subscriber("Tom");

        subscriber1.subscribe(channel1);
        subscriber2.subscribe(channel1);
        subscriber3.subscribe(channel1);

        Video video1 = new Video();
        video1.setTitle("Harry Potter");
        video1.setLength(500);
        video1.setDescription("magic type");
        channel1.upload(video1);


        Video video2 = new Video();
        video2.setTitle("Titanic");
        video2.setLength(30);
        video2.setDescription("romantic type");
        channel1.upload(video2);


    }
}