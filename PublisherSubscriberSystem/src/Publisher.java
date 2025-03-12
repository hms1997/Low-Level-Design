public class Publisher {
    private PubSubSystem pubSubSystem;

    public Publisher(PubSubSystem pubSubSystem) {
        this.pubSubSystem = pubSubSystem;
    }

    public void publish(String topicName, Message message) {
        pubSubSystem.publish(topicName, message);
    }
}
