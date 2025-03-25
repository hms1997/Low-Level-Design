package orders;

import datamodels.Customer;
import datamodels.DeliveryAgent;
import datamodels.MenuItem;
import datamodels.Restaurant;
import enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private Customer customer;
    private DeliveryAgent agent;
    private Restaurant restaurant;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;

    public Order(String orderId, Customer customer, Restaurant restaurant) {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.orderItems = new ArrayList<>();
        this.orderStatus = OrderStatus.PENDING;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DeliveryAgent getAgent() {
        return agent;
    }

    public void assignDeliveryAgent(DeliveryAgent agent) {
        this.agent = agent;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItems(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
