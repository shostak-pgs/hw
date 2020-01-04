package app.entity;

import java.util.Objects;

/**
 * Class represents a bin to describe an order
 */
public class Order {
    private long id;
    private long userId;
    private double totalPrice;

    public Order(long id, long userId, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                userId == order.userId &&
                Double.compare(order.totalPrice, totalPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, totalPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

