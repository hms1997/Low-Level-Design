public interface Subscriber {
    public void onReceive(Message message);
    public String getSubscriberName();
}
