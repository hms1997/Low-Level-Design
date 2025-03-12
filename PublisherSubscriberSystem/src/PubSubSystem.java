import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PubSubSystem {
    private static PubSubSystem pubSubSystem;
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private ConcurrentHashMap<String, Topic> topicMap = new ConcurrentHashMap<>();

    private PubSubSystem() {

    }

    public static PubSubSystem getPubSubSystem() {
        if(pubSubSystem == null) {
            synchronized (PubSubSystem.class) {
                if(pubSubSystem == null) {
                    pubSubSystem = new PubSubSystem();
                }
            }
        }
        return pubSubSystem;
    }

    public void publish(String topicName, Message message) {
        Topic topic = topicMap.get(topicName);
        if(topic == null) {
            System.out.println("Topic " + topicName + " is not created yet");
            return;
        }

        Set<Subscriber> subscribers = topic.getSubscribers();
        executorService.submit(() -> {
           for(Subscriber subscriber : subscribers) {
               subscriber.onReceive(message);
           }
        });
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        Topic topic = topicMap.get(topicName);
        if(topic == null) {
            System.out.println("Topic " + topicName + " is not created yet");
            return;
        }

        Set<Subscriber> subscribers = topic.getSubscribers();
        if(subscribers.contains(subscriber)) {
            System.out.println("Subscriber is already added");
            return;
        }
        subscribers.add(subscriber);
        System.out.println("Subscriber " + subscriber.getSubscriberName() + " is subscribed to topic " + topicName +" successfully");
    }

    public void unSubscribe(String topicName, Subscriber subscriber) {
        Topic topic = topicMap.get(topicName);
        if(topic == null) {
            System.out.println("Topic " + topicName + " is not created yet");
            return;
        }

        Set<Subscriber> subscribers = topic.getSubscribers();
        if(!subscribers.contains(subscriber)) {
            System.out.println("Subscriber is not available");
            return;
        }
        subscribers.remove(subscriber);
        System.out.println("Subscriber " + subscriber.getSubscriberName() + " is removed from topic " + topicName +" successfully");
    }

    public void createTopic(String topicName) {
        if(topicMap.containsKey(topicName)) {
            System.out.println("Topic is already available");
            return;
        }
        topicMap.put(topicName, new Topic(topicName));
        System.out.println("Topic is created successfully");
    }

    public void removeTopic(String topicName) {
        if(!topicMap.containsKey(topicName)) {
            System.out.println("Topic is not available");
            return;
        }
        topicMap.remove(topicName);
        System.out.println("Topic is removed successfully");
    }
}
