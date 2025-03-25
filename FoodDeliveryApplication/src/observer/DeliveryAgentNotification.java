package observer;

import orders.Order;

public class DeliveryAgentNotification implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("DeliveryAgent notified about order status: " + order.getOrderStatus());
    }
}
