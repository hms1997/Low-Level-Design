import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Topic {
    private String topicName;
    private Set<Subscriber> subscribers;

    public Topic(String topicName) {
        this.topicName = topicName;
        subscribers = new CopyOnWriteArraySet<>();
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Set<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
}
