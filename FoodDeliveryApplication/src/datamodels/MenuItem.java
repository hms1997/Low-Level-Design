package datamodels;

import java.util.concurrent.atomic.AtomicInteger;

public class MenuItem {
    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private boolean available;
    private AtomicInteger quantity;

    public MenuItem(String id, String name, String description, double price, AtomicInteger quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = true;
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        int value = quantity.intValue();
        return value;
    }


    public void incrementQuantity(int count) {
        this.quantity.addAndGet(count);
    }

    public void decrementQuantity(int count) {
        this.quantity.addAndGet(-count);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", quantity=" + quantity +
                '}';
    }
}
