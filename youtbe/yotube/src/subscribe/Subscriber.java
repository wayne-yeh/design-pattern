package subscribe;

public class Subscriber {


    String name;

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
}
