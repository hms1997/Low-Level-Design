package driver;

import datamodels.Customer;
import datamodels.DeliveryAgent;
import datamodels.MenuItem;
import datamodels.Restaurant;
import enums.OrderStatus;
import observer.CustomerNotification;
import observer.DeliveryAgentNotification;
import observer.OrderObserver;
import orders.Order;
import orders.OrderItem;
import services.FoodDeliveryService;
import services.RestaurantManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FoodDeliveryApplicationDriver {
    public static void main(String[] args) {
        FoodDeliveryService foodDeliveryService = FoodDeliveryService.getInstance();
        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        OrderObserver customerObserver = new CustomerNotification();
        OrderObserver userAgentObserver = new DeliveryAgentNotification();
        foodDeliveryService.addObserver(customerObserver);
        foodDeliveryService.addObserver(userAgentObserver);

        // Test 1: Add a customer
        Customer customer1 = new Customer("C1", "Himangshu", "himangshu@example.com", "1234567890");
        foodDeliveryService.addCustomer(customer1);
        System.out.println("Customer added: " + customer1.getName());

        // Test 2: Add restaurant
        Restaurant restaurant = new Restaurant("R1", "Pizza Place", "Bangalore");
        restaurantManager.addRestaurant(restaurant);
        System.out.println("restaurant added: " + restaurant.getName());

        //Add menu item
        MenuItem pizza = new MenuItem("M1", "Pepperoni Pizza", "veg pizza", 300, new AtomicInteger(10));
        restaurantManager.addMenuItem(restaurant, pizza);

        // Test 3: Add a delivery agent
        DeliveryAgent agent1 = new DeliveryAgent("A1", "Agent Smith", "9876543210");
        foodDeliveryService.addDeliveryAgent(agent1);
        System.out.println("Delivery agent added: " + agent1.getName());

        // Test 5: Create an order
        OrderItem orderItem = new OrderItem(pizza, 1);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        String orderId = foodDeliveryService.createOrder(customer1, restaurant, orderItems);
        System.out.println("Order created with ID: " + orderId);

        // Test 6: Cancel an order
        try {
            foodDeliveryService.cancelOrder(orderId);
            System.out.println("Order cancelled: " + orderId);
        } catch (IllegalArgumentException e) {
            System.out.println("Error cancelling order: " + e.getMessage());
        }

        // Test 2: Remove a customer
        foodDeliveryService.removeCustomer(customer1);
        System.out.println("Customer removed: " + customer1.getName());

        // Test 4: Remove a delivery agent
        foodDeliveryService.removeDeliveryAgent(agent1);
        System.out.println("Delivery agent removed: " + agent1.getName());
    }
}

