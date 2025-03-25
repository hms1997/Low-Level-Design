package services;

import datamodels.Customer;
import datamodels.DeliveryAgent;
import datamodels.MenuItem;
import datamodels.Restaurant;
import enums.OrderStatus;
import observer.OrderObserver;
import orders.Order;
import orders.OrderItem;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class FoodDeliveryService {
    private static FoodDeliveryService foodDeliveryService;
    private static RestaurantManager restaurantManager = RestaurantManager.getInstance();
    private final Map<String, Customer> customers;
    private final Map<String, Order> orders;
    private final Map<String, DeliveryAgent> deliveryAgents;
    private final List<OrderObserver> observers;
    private final ScheduledExecutorService scheduler;

    private FoodDeliveryService() {
        customers = new ConcurrentHashMap<>();
        orders = new ConcurrentHashMap<>();
        deliveryAgents = new ConcurrentHashMap<>();
        observers = new CopyOnWriteArrayList<>();
    }

    public static FoodDeliveryService getInstance() {
        if(foodDeliveryService == null) {
            synchronized (RestaurantManager.class) {
                if(foodDeliveryService == null) {
                    foodDeliveryService = new FoodDeliveryService();
                }
            }
        }
        return foodDeliveryService;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.update(order);
        }
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public void removeCustomer(Customer customer) {
        if(!customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Invalid Customer");
        }
        customers.remove(customer.getId());
    }

    public void addDeliveryAgent(DeliveryAgent deliveryAgent) {
        deliveryAgents.put(deliveryAgent.getId(), deliveryAgent);
    }

    public void removeDeliveryAgent(DeliveryAgent deliveryAgent) {
        if(!deliveryAgents.containsKey(deliveryAgent.getId())) {
            throw new IllegalArgumentException("Invalid DeliveryAgent");
        }
        deliveryAgents.remove(deliveryAgent.getId());
    }

    public String createOrder(Customer customer, Restaurant restaurant, List<OrderItem> orderItems) {
        if(customers.get(customer.getId()) == null) throw new IllegalArgumentException("Register the customer first");
        if(!restaurantManager.isRestaurantExists(restaurant)) throw new IllegalArgumentException("Register the restaurant first");

        Order order = new Order(UUID.randomUUID().toString(), customer, restaurant);

        //Assign a delivery agent
        DeliveryAgent deliveryAgent = findDeliveryAgent();
        if(deliveryAgent != null) {
            order.assignDeliveryAgent(deliveryAgent);
        } else {
            System.out.println("No delivery Agent is available");
            return null;
        }

        for(OrderItem orderItem : orderItems) {
            synchronized (orderItem) {
                if (restaurant.isItemAvailable(orderItem)) {
                    restaurantManager.placeOrder(restaurant, orderItem);
                    order.addOrderItems(orderItem);
                    updateOrderStatus(order, OrderStatus.CONFIRMED);
                } else {
                    System.out.println("Not enough item available, Item: " + orderItem.getMenuItem().getDescription());
                }
            }

        }

        if(order.getOrderItems().size() > 0) {
            orders.put(order.getOrderId(), order);
            System.out.println("order placed");
            updateOrderStatus(order, OrderStatus.CONFIRMED);
            try {
                System.out.println("Preparing order...");
                Thread.sleep(2000);
                System.out.println("Order prepared");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Non of the item is available");
        }
        updateOrderStatus(order, OrderStatus.DELIVERED);
        return order.getOrderId();
    }

    public void cancelOrder(String orderId) {
        if(orders.containsKey(orderId)) {
            throw new IllegalArgumentException("Invalid order");
        }
        Order order = orders.get(orderId);
        Restaurant restaurant = order.getRestaurant();
        for(OrderItem orderItem : order.getOrderItems()) {
            MenuItem menuItem = orderItem.getMenuItem();
            restaurantManager.increaseMenuItemCount(restaurant, menuItem, menuItem.getQuantity());
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        System.out.println("Order canceled");
    }

    private void updateOrderStatus(Order order, OrderStatus status) {
        order.setOrderStatus(status);
        notifyObservers(order);
    }

    private DeliveryAgent findDeliveryAgent() {
        for(DeliveryAgent deliveryAgent : deliveryAgents.values()) {
            if(deliveryAgent.isAvailable()) {
                deliveryAgent.setAvailable(false);
                return deliveryAgent;
            }
        }
        return null;
    }
}
