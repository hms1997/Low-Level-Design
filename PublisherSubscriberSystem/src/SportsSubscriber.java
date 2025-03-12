public class SportsSubscriber implements Subscriber{
    public String subscriberName;
    @Override
    public void onReceive(Message message) {
        System.out.println("Received message: " + message.getContent());
    }

    public SportsSubscriber(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    @Override
    public String getSubscriberName() {
        return subscriberName;
    }
}
