package business.domain;

import java.util.List;

public class BeverageOrderItem extends OrderItem{
    private BeverageSize size;
    private Product drink;

    private String milkType;

    public BeverageOrderItem(Integer quantity, BeverageSize size, Product drink, String milkType) {
        super(quantity);
        this.size = size;
        this.drink = drink;
        this.milkType = milkType;
    }

    public BeverageSize getSize() {
        return size;
    }

    public void setSize(BeverageSize size) {
        this.size = size;
    }

    public Product getDrink() {
        return drink;
    }

    public void setDrink(Product drink) {
        this.drink = drink;
    }

    public String getMilkType() {
        return milkType;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
    }

    @Override
    public Double computePrice() {
        return  drink.getPrice() * size.factor * super.getQuantity();
    }

    @Override
    public String toString() {
        return String.format("%s\n\tMilk Type: %s\n\tSize: %s\n\tquantity: %d\n\tprice: %.2f",drink.getName(),milkType
                ,size,super.getQuantity(),computePrice());
    }
}
