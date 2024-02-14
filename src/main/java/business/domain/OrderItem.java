package business.domain;

import java.util.Objects;

public abstract class OrderItem {
    private Integer quantity;

    public OrderItem(Integer quantity) {
        this.quantity = quantity;
    }

    public abstract Double computePrice();

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem item)) return false;
        return Objects.equals(quantity, item.quantity);
    }


}
