package observer;

import orders.Order;

public class CustomerNotification implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("Customer notified about order status: " + order.getOrderStatus());
    }
}
