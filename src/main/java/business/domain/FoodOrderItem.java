package business.domain;

import java.util.Objects;

public class FoodOrderItem extends OrderItem{

    private Product food;

    public FoodOrderItem(Integer quantity, Product snack) {
        super(quantity);
        this.food = snack;
    }

    @Override
    public Double computePrice() {
        return super.getQuantity() * food.getPrice();
    }

    public Product getFood() {
        return food;
    }

    public void setFood(Product food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return String.format("%s\n\tquantity: %d\n\tprice: %.2f",food.getName(),super.getQuantity(),computePrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodOrderItem that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(food, that.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food);
    }
}
