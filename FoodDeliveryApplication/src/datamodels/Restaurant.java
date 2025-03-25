package datamodels;

import orders.OrderItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Restaurant {
    private final String id;
    private final String name;
    private final String address;
    private final Map<String, MenuItem> menuMap;

    public Restaurant(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.menuMap = new ConcurrentHashMap<>();
    }

    public void addMenuItem(String id, MenuItem item) {
        menuMap.put(id, item);
    }

    public void removeMenuItem(MenuItem item) {
        menuMap.remove(item);
    }

    public String getId() {
        return id;
    }

    public List<MenuItem> getAllMenuItems() {
        return new ArrayList<>(menuMap.values());
    }

    public MenuItem getMenuItem(String id) {
        return menuMap.get(id);
    }

    public boolean isItemAvailable(OrderItem orderItem) {
        String id = orderItem.getMenuItem().getId();
        MenuItem menuItem = menuMap.get(id);
        return menuItem.getQuantity() >= orderItem.getQuantity();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
