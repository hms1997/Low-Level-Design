package services;

import datamodels.MenuItem;
import datamodels.Restaurant;
import orders.OrderItem;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RestaurantManager {
    private static RestaurantManager restaurantManager;
    private Map<String, Restaurant> restaurantMap = new ConcurrentHashMap();
    private RestaurantManager(){}

    public static RestaurantManager getInstance() {
        if(restaurantManager == null) {
            synchronized (RestaurantManager.class) {
                if(restaurantManager == null) {
                    restaurantManager = new RestaurantManager();
                }
            }
        }
        return restaurantManager;
    }

    public void addRestaurant(Restaurant restaurant) {
        String restaurantId = restaurant.getId();
        restaurantMap.put(restaurantId, restaurant);
    }

    public void deleteRestaurant(String restaurantId) {
        if(!restaurantMap.containsKey(restaurantId)) {
            throw new IllegalArgumentException("Invalid Restaurant Id");
        }
        restaurantMap.remove(restaurantId);
        System.out.println("Removed successfully");
    }

    public void addMenuItem(Restaurant restaurant, MenuItem menuItem) {
        String id = menuItem.getId();
        restaurant.addMenuItem(id, menuItem);
    }

    public List<MenuItem> getRestaurantMenu(Restaurant restaurant) {
        if(restaurantMap.containsKey(restaurant.getId())) {
            throw new IllegalArgumentException("Invalid Restaurant");
        }
        return restaurantMap.get(restaurant.getId()).getAllMenuItems();
    }

    public void increaseMenuItemCount(Restaurant restaurant, MenuItem menuItem, int count) {
        MenuItem currentMenuItem = restaurant.getMenuItem(menuItem.getId());
        currentMenuItem.incrementQuantity(count);
    }

    public void decreaseMenuItemCount(Restaurant restaurant, MenuItem menuItem, int count) {
        MenuItem currentMenuItem = restaurant.getMenuItem(menuItem.getId());
        currentMenuItem.decrementQuantity(count);
    }

    public boolean isRestaurantExists(Restaurant restaurant) {
        return restaurantMap.containsKey(restaurant.getId());
    }

    public void placeOrder(Restaurant restaurant, OrderItem orderItem) {
        MenuItem menuItem = orderItem.getMenuItem();
        int quantity = orderItem.getQuantity();
        MenuItem currentMenuItem = restaurant.getMenuItem(menuItem.getId());
        currentMenuItem.decrementQuantity(quantity);
    }
}
