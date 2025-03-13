public class PublisherSubscriberDriver {
    private final static PubSubSystem pubSubSystem = PubSubSystem.getPubSubSystem();
    public static void main(String[] args) {
        Publisher newsPublisher = new Publisher(pubSubSystem);
        Publisher sportsPublisher = new Publisher(pubSubSystem);

        Subscriber newsSubscriber = new NewsSubscriber("News Subscriber");
        Subscriber sportsSubscriber = new SportsSubscriber("Sports Subscriber");

        pubSubSystem.createTopic("news");
        pubSubSystem.createTopic("sports");

        pubSubSystem.subscribe("news", newsSubscriber);
        pubSubSystem.subscribe("sports", sportsSubscriber);

        newsPublisher.publish("news", new Message("Meta is launching a new AI model"));
        sportsPublisher.publish("sports", new Message("India own the Champion Trophy"));
    }
}