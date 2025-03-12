public class NewsSubscriber implements Subscriber{
    public String subscriberName;

    public NewsSubscriber(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    @Override
    public void onReceive(Message message) {
        System.out.println("Received message: " + message.getContent());
    }

    @Override
    public String getSubscriberName() {
        return subscriberName;
    }

}
