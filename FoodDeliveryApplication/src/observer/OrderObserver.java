package observer;

import orders.Order;

public interface OrderObserver {
    void update(Order order);
}
